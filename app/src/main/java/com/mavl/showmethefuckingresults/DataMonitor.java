package com.mavl.showmethefuckingresults;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DataMonitor extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DataMonitor", "Broadcast received");
        MainActivity mainActivity = ((CheckEgeApp) context.getApplicationContext()).mainActivity;
        String content = intent.getExtras().getString("result-content");
        if ((content!=null)&&(content.equals("ERROR")))
            mainActivity.logout();
        else
            mainActivity.getExamsResult(content);
    }
}
