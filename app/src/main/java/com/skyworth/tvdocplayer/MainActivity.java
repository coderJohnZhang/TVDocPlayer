package com.skyworth.tvdocplayer;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import net.pbdavey.awt.Graphics2D;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import and.awt.Dimension;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int FILE_DECODE_FINISH = 100;
    private static final int FILE_SCAN_FINISH = 101;
    private static final int PPT_DECODE_MESSAGE = 102;
    private ViewPager mBannerViewPager;
    private ImageView mLoadingTv;
    private final UIHandler mHandler = new UIHandler(MainActivity.this);
    private PdfRenderer mPdfRenderer = null;
    private ParcelFileDescriptor mParcelFileDescriptor = null;
    private String mountPath = "";
    private final List<File> mAllFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置为无标题(去掉Android自带的标题栏，全屏功能与此无关)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        mBannerViewPager = (ViewPager) findViewById(R.id.banner_vp);
        mLoadingTv = (ImageView) findViewById(R.id.loading_iv);
        mLoadingTv.setVisibility(View.GONE);
        mBannerViewPager.setVisibility(View.GONE);
        Intent intent = getIntent();
        mountPath = intent.getStringExtra("mountPath");
        Log.d(TAG, "onCreate: mountPath = " + mountPath);
        if (mountPath == null || "".equals(mountPath)) {
            List<Map<String, String>> datas = new ArrayList<>();

            try {
                StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);
                Class<?> storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");

                Method getVolumeList = storageManager.getClass().getMethod("getVolumeList");
                Method getPath = storageVolumeClazz.getMethod("getPath");
                Method isRemovable = storageVolumeClazz.getMethod("isRemovable");

                Object result = getVolumeList.invoke(storageManager);

                final int length = Array.getLength(result);

                Method getUserLabel = storageVolumeClazz.getMethod("getUserLabel");

                for (int i = 0; i < length; i++) {

                    Object storageVolumeElement = Array.get(result, i);

                    String userLabel = (String) getUserLabel.invoke(storageVolumeElement);
                    String path = (String) getPath.invoke(storageVolumeElement);
                    boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                    if (removable) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put(userLabel, path);
                        datas.add(map);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onCreate: datas size = " + datas.size());
            if (datas.size() == 0) {
                Toast.makeText(this, R.string.please_insert_usb, Toast.LENGTH_LONG).show();
            } else if (datas.size() == 1) {
                for (String value : datas.get(0).values()) {
                    mountPath = value;
                    break;
                }
                Log.d(TAG, "onCreate: datas.size() == 1 mountPath = " + mountPath);
                requestStoragePermissions();
            } else {
                showUsbSelectList(datas);
            }
        } else {
            if (!mountPath.contains("emulated")) {
                requestStoragePermissions();
            }
        }
    }

    private void showUsbSelectList(List<Map<String, String>> data) {
        Log.d(TAG, "showUsbSelectList: data size = " + data.size());
        String[] usbNames = new String[data.size()];
        String[] usbPaths = new String[data.size()];

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) != null) {

                for (Map.Entry<String, String> entry : data.get(i).entrySet()) {
                    usbNames[i] = entry.getKey();
                    usbPaths[i] = entry.getValue();
                }
            }
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(R.string.select_usb);
        alertBuilder.setSingleChoiceItems(usbNames, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mountPath = usbPaths[i];
                requestStoragePermissions();
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    private void requestStoragePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android 6.0以上
            int readPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            Log.d(TAG, "requestStoragePermissions: readPermission = " + readPermission);
            if (readPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            } else {
                scanFiles();
            }
        } else {
            scanFiles();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scanFiles();

            } else {
                Toast.makeText(this, R.string.please_grant_storage_permission, Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }
        }
    }

    private void scanFiles() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAllFiles.clear();
                File file = new File(mountPath);
                if (!file.canRead()) {
                    mountPath = mountPath.replace("/mnt/usb", "/storage");
                }
                getAllSDCardFiles(mountPath);
                Message msg = mHandler.obtainMessage();
                msg.what = FILE_SCAN_FINISH;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    private void getAllSDCardFiles(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }

        try {
            File file = new File(path);

            if (!file.exists()) {
                return;
            }

            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                return;
            }

            for (File f : files) {
                if (f.isDirectory()) {
                    getAllSDCardFiles(f.getAbsolutePath());
                } else {
                    mAllFiles.add(f);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final List<File> pptList = new ArrayList<>();

    private void showPPTList() {
        pptList.clear();
        for (File file : mAllFiles) {
            String fileName = file.getName();
            Log.d(TAG, "showPPTList: file name: " + file.getName());
            if (fileName.endsWith(".pptx")) {
                pptList.add(file);
            }
        }
        if (pptList.size() > 0) {
            Log.d(TAG, "showPPTList: pptList size = " + pptList.size());
            String[] pptNames = new String[pptList.size()];
            for (int i = 0; i < pptList.size(); i++) {
                pptNames[i] = pptList.get(i).getName();
            }
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle(R.string.please_select_ppt_file);
            alertBuilder.setSingleChoiceItems(pptNames, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    decodePptToImage(pptList.get(i).getAbsolutePath());
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, R.string.no_ppt_files, Toast.LENGTH_LONG).show();
        }
    }

    private final List<Bitmap> pptBitmaps = new ArrayList<>();

    private void decodePptToImage(String absolutePath) {
        pptBitmaps.clear();
        mLoadingTv.setVisibility(View.VISIBLE);
        mBannerViewPager.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    XMLSlideShow ppt = new XMLSlideShow(OPCPackage.open(absolutePath, PackageAccess.READ));
                    Dimension xPgSize = ppt.getPageSize();
                    XSLFSlide[] xSlides = ppt.getSlides();
                    int xSlideCount = xSlides.length;
                    for (int i = 0; i < xSlideCount; i++) {
                        final Bitmap bmp = Bitmap.createBitmap((int) xPgSize.getWidth(),
                                (int) xPgSize.getHeight(), Bitmap.Config.RGB_565);
                        Canvas canvas = new Canvas(bmp);
                        Paint paint = new Paint();
                        paint.setColor(Color.WHITE);
                        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
                        canvas.drawPaint(paint);
                        final Graphics2D graphics2d = new Graphics2D(canvas);

                        final AtomicBoolean isCanceled = new AtomicBoolean(false);
                        try {
                            xSlides[i].draw(graphics2d, isCanceled,
                                    mHandler, i);
                            mHandler.sendMessage(Message.obtain(mHandler, PPT_DECODE_MESSAGE,
                                    i, 0, bmp));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = FILE_DECODE_FINISH;
                    msg.obj = pptBitmaps;
                    mHandler.sendMessage(msg);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private final List<File> pdfList = new ArrayList<>();

    private void showPDFList() {
        pdfList.clear();
        for (File file : mAllFiles) {
            String fileName = file.getName();
            Log.d(TAG, "showPDFList: file name: " + file.getName());
            if (fileName.endsWith(".pdf")) {
                pdfList.add(file);
            }
        }
        if (pdfList.size() > 0) {
            Log.d(TAG, "showPDFList: pdfList size = " + pdfList.size());
            String[] pdfNames = new String[pdfList.size()];
            for (int i = 0; i < pdfList.size(); i++) {
                pdfNames[i] = pdfList.get(i).getName();
            }
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle(R.string.please_select_pdf_file);
            alertBuilder.setSingleChoiceItems(pdfNames, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    decodePdfToImage(pdfList.get(i));
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, R.string.no_pdf_files, Toast.LENGTH_LONG).show();
        }
    }

    private void decodePdfToImage(File file) {
        Log.d(TAG, "decodePdfToImage: file name = " + file.getName());
        mLoadingTv.setVisibility(View.VISIBLE);
        mBannerViewPager.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Bitmap> bitmaps = new ArrayList<>();
                    mParcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
                    mPdfRenderer = new PdfRenderer(mParcelFileDescriptor);
                    Log.d(TAG, "decodePdfToImage: mPdfRenderer size = " + mPdfRenderer.getPageCount());
                    if (mPdfRenderer.getPageCount() > 0) {
                        for (int i = 0; i < mPdfRenderer.getPageCount(); i++) {
                            PdfRenderer.Page page = mPdfRenderer.openPage(i);
                            //Bitmap必须是ARGB，不可以是RGB
                            Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
                            /*
                             * 调用PdfRender.Page的render方法渲染bitmap
                             *
                             * render的参数说明：
                             * destination : 要渲染的bitmap对象
                             * destClip ：传一个矩形过去，矩形的尺寸不能大于bitmap的尺寸，最后渲染的pdf会是rect的大小，可为null
                             * transform : 一个Matrix bitmap根据该Matrix图像进行转换
                             * renderMode ：渲染模式 可选2种 RENDER_MODE_FOR_DISPLAY 和 RENDER_MODE_FOR_PRINT
                             */
                            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                            bitmaps.add(bitmap);
                            page.close();
                        }
                    }
                    Message msg = mHandler.obtainMessage();
                    msg.what = FILE_DECODE_FINISH;
                    msg.obj = bitmaps;
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private final List<View> viewList = new ArrayList<>();

    private void initImageBanner(List<Bitmap> bitmaps) {
        viewList.clear();
        Log.d(TAG, "initImageBanner: bitmaps size = " + bitmaps.size());
        for (int i = 0; i < bitmaps.size(); i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.banner_item, mBannerViewPager, false);
            imageView.setImageBitmap(bitmaps.get(i));
            viewList.add(imageView);
        }
        BannerAdapter bannerAdapter = new BannerAdapter(viewList);
        mBannerViewPager.setAdapter(bannerAdapter);
        mHandler.postDelayed(mAutoSwitchRunnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (mPdfRenderer != null) {
            mPdfRenderer.close();
        }
        if (mParcelFileDescriptor != null) {
            try {
                mParcelFileDescriptor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private final Runnable mAutoSwitchRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = mBannerViewPager.getCurrentItem();
            currentItem = (currentItem + 1) % viewList.size();
            Log.d(TAG, "mAutoSwitchRunnable run: currentItem = " + currentItem);
            mBannerViewPager.setCurrentItem(currentItem);

            mHandler.postDelayed(this, 3000);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            requestStoragePermissions();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class UIHandler extends Handler {
        private final WeakReference<MainActivity> ref;

        UIHandler(MainActivity activity) {
            ref = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = ref.get();
            if (activity == null) {
                return;
            }
            Log.d(TAG, "handleMessage: msg.what = " + msg.what);
            switch (msg.what) {
                case FILE_DECODE_FINISH:
                    mLoadingTv.setVisibility(View.GONE);
                    mBannerViewPager.setVisibility(View.VISIBLE);
                    initImageBanner((List<Bitmap>) msg.obj);
                    break;
                case FILE_SCAN_FINISH:
                    Log.d(TAG, "mAllFiles size = " + mAllFiles.size());
                    final String[] items = {"PPT", "PDF"};
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
                    alertBuilder.setTitle(R.string.select_file_type_play);
                    alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            switch (i) {
                                case 0:
                                    showPPTList();
                                    break;
                                case 1:
                                    showPDFList();
                                    break;
                                default:
                                    break;
                            }
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                    break;
                case PPT_DECODE_MESSAGE:
                    Bitmap bmp = (Bitmap) msg.obj;
                    pptBitmaps.add(bmp);
                    break;
            }
        }
    }
}
