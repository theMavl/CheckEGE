package com.mavl.showmethefuckingresults;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mavl.showmethefuckingresults.ObjectResult.Exam;
import com.mavl.showmethefuckingresults.ObjectResult.Res;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.mavl.showmethefuckingresults.MainActivity.SAVED_DATA;

public class CheckEge extends Service {
    public static final int DEFAULT_DELAY = 3600;
    String URL;
    SharedPreferences sp;
    SharedPreferences defaultSp;
    SharedPreferences.Editor e;
    String participant;
    Thread thread;
    boolean killThread = false;
    Res newRes;
    Res oldRes;
    int delay;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        defaultSp = PreferenceManager.getDefaultSharedPreferences(this);
        sp = getSharedPreferences(SAVED_DATA, Context.MODE_PRIVATE);
        URL = getResources().getString(R.string.url_exams);
        participant = sp.getString("Participant", null);
        requestData(0);
        mainLoop();
        return START_STICKY;
    }

    void mainLoop() {
        getDelay();
        if (delay == -1)
            return;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(delay * 1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    if (killThread)
                        break;
                    requestData(1);
                }
            }
        });
        thread.start();
    }

    void getDelay() {
        delay = Integer.parseInt(defaultSp.getString("sync_frequency", "-2"));
        if ((delay == -2)|| (delay < 10)){
            e = defaultSp.edit();
            e.putInt("sync_frequency", DEFAULT_DELAY);
            delay = DEFAULT_DELAY;
            e.apply();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    void noInternetGap() {
        while (!isOnline()) {
            Log.d("No internet gap","Still no internet");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    void requestData(int mode) {
        if (!isOnline()) {
            if (mode == 0)
                return;
            else
                noInternetGap();
        }
        final Gson gson = (new GsonBuilder()).create();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsResponse) {
                        Log.d("onresponse","done");
                        String response = jsResponse.toString();
                        //String response = "{\"Info\":{\"HotlinePhone\":\"8(347)216-43-18\",\"Info\":\"\"},\"Result\":{\"Exams\":[{\"ExamId\":178,\"OralExamId\":null,\"ExamDate\":\"2016-12-07\",\"OralExamDate\":null,\"Subject\":\"Сочинение\",\"OralSubject\":null,\"TestMark\":1,\"Mark5\":5,\"MinMark\":0,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":true,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":19,\"OralExamId\":null,\"ExamDate\":\"2017-05-29\",\"OralExamDate\":null,\"Subject\":\"Информатика и ИКТ\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":5,\"MinMark\":40,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":196,\"OralExamId\":null,\"ExamDate\":\"2017-05-31\",\"OralExamDate\":null,\"Subject\":\"Математика базовая\",\"OralSubject\":null,\"TestMark\":5,\"Mark5\":5,\"MinMark\":3,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":true,\"IsForeignLanguage\":false},{\"ExamId\":24,\"OralExamId\":null,\"ExamDate\":\"2017-06-02\",\"OralExamDate\":null,\"Subject\":\"Математика профильная\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":5,\"MinMark\":27,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":32,\"OralExamId\":null,\"ExamDate\":\"2017-06-07\",\"OralExamDate\":null,\"Subject\":\"Физика\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":0,\"MinMark\":36,\"Status\":0,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":false,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":21,\"OralExamId\":null,\"ExamDate\":\"2017-06-09\",\"OralExamDate\":null,\"Subject\":\"Русский язык\",\"OralSubject\":null,\"TestMark\":0,\"Mark5\":0,\"MinMark\":24,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":true,\"HasResult\":false,\"HasOralResult\":false,\"IsHiddenForRegion\":true,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false}]}}";
                        newRes = gson.fromJson(response, Res.class);
                        Calendar c = Calendar.getInstance();
                        e = sp.edit();
                        e.putLong("LastUpdate", c.getTimeInMillis());
                        e.apply();
                        if (compareData()) {
                            saveResult(response);
                            createBroadcast(response);
                        }else
                            createBroadcast("SYNCED");
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 401) {
                            createBroadcast("ERROR");
                            stopSelf();
                        }
                    }})
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Cookie", "Participant="+participant);
                return params;
            }};
        queue.add(jsObjRequest);
    }

    void createBroadcast(String content) {
        Intent intent=new Intent("com.mavl.showmethefuckingresults.NEW_DATA");
        intent.putExtra("result-content", content);
        Log.d("CheckEGE", "Broadcast sent");
        sendBroadcast(intent);
    }

    void saveResult(String result) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(MainActivity.SAVED_RESULT, Context.MODE_PRIVATE));
            outputStreamWriter.write(result);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    boolean compareData() {
        boolean needToSaveAfterwards = false;
        String ret = "";
        try {
            InputStream inputStream = openFileInput(MainActivity.SAVED_RESULT);
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("read result", "File not found: " + e.toString());
            return true;
        } catch (IOException e) {
            Log.e("read result", "Can not read file: " + e.toString());
            return true;
        }
        Gson gson = (new GsonBuilder()).create();
        oldRes = gson.fromJson(ret, Res.class);
        Exam newExam;
        Exam oldExam;
        try {
            for (int i = 0; i < oldRes.getResult().getExams().size(); i++) {
                oldExam = oldRes.getResult().getExams().get(i);
                newExam = newRes.getResult().getExams().get(i);
                if ((oldExam.getTestMark() == 0) && (newExam.getTestMark() > 0)) {
                    showNotification(1, oldExam.getExamId(), oldExam.getSubject());
                    needToSaveAfterwards = true;
                } else if (oldExam.getTestMark() != newExam.getTestMark()) {
                    showNotification(2, oldExam.getExamId(), oldExam.getSubject());
                    needToSaveAfterwards = true;
                } else if (((oldExam.getStatus() != newExam.getStatus())) || (oldExam.isIsHidden() != newExam.isIsHidden()) || (oldExam.isIsHiddenForRegion() != newExam.isIsHiddenForRegion())) {
                    showNotification(0, oldExam.getExamId(), oldExam.getSubject());
                    needToSaveAfterwards = true;
                }
            }
        } catch (Exception e) {
            return true;
        }
        return needToSaveAfterwards;
    }

    void showNotification(int type, int examID, String subject) {
        // 0 - Изменен статус
        // 1 - Появился результат
        // 2 - Результат изменен
        String statusMessage = "";
        boolean tmpbool;
        tmpbool = defaultSp.getBoolean("notifications_new_message", true);
        if (!tmpbool)
            return;

        switch (type) {
            case 0:
                statusMessage = "изменен статус";
                tmpbool = defaultSp.getBoolean("notifications_new_status", true);
                if (!tmpbool)
                    return;
                break;
            case 1:
                tmpbool = defaultSp.getBoolean("notifications_new_result", true);
                if (!tmpbool)
                    return;
                statusMessage = "есть результат";
                break;
            case 2:
                tmpbool = defaultSp.getBoolean("notifications_new_result", true);
                if (!tmpbool)
                    return;
                statusMessage = "результат изменен";
                break;
        }
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ege_statusbar)
                        .setContentTitle("ЕГЭ - "+statusMessage)
                        .setContentText(subject);
        //mBuilder.setVibrate(new long[] { 500, 10, 500, 10});
        //mBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        //Intent resultIntent = new Intent(this, DetailsActivity.class);
        Intent resultIntent = new Intent();
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        resultIntent.putExtra("examID", examID);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification note = mBuilder.build();
        tmpbool = defaultSp.getBoolean("notifications_vibrate", true);
        if (tmpbool)
            note.defaults |= Notification.DEFAULT_VIBRATE;
        //note.defaults |= 1;//Notification.DEFAULT_SOUND;
        mBuilder.setSound(Uri.parse(defaultSp.getString("notifications_ringtone", "")));
        mBuilder.setAutoCancel(true);
        mNotificationManager.notify(examID, note);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        killThread = true;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}