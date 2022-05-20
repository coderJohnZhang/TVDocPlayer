package and.awt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageIO {

    public static BufferedImage read(InputStream byteArrayInputStream) {
        Bitmap bm = BitmapFactory.decodeStream(byteArrayInputStream);
        return bm == null ? null : new BufferedImage(bm);
    }

    public static boolean write(Bitmap bitmap, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(bitmap2Bytes(bitmap));
            out.flush();
            out.close();
            Log.e("write", "write: " + file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void write(byte[] bytes, int pos) {
        try {
            FileOutputStream out = new FileOutputStream(new File("/sdcard/" + pos + ".png"));
            out.write(bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bitmap转byte数组
    private static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);//png类型
        return baos.toByteArray();
    }
}