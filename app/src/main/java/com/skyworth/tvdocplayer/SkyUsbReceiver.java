package com.skyworth.tvdocplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class SkyUsbReceiver extends BroadcastReceiver {

    private static final String TAG = SkyUsbReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action == " + action);
        if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
            String path = "";
            if (null != intent.getData()) {
                path = intent.getData().getPath();
            }
            Log.d(TAG, "path == " + path);
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("mountPath", path);
            context.startActivity(intent1);

        } else if (action.equals(Intent.ACTION_MEDIA_UNMOUNTED)) {
            Toast.makeText(context, R.string.usb_remove, Toast.LENGTH_SHORT).show();
        }
    }
}
