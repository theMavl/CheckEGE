package com.mavl.showmethefuckingresults;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mavl.showmethefuckingresults.ListAdapters.SubjectsListAdapter;
import com.mavl.showmethefuckingresults.ObjectExam.DetailsActivity;
import com.mavl.showmethefuckingresults.ObjectExam.ExamInDetails;
import com.mavl.showmethefuckingresults.ObjectResult.Res;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public final static int GET_PARTICIPANT_ID = 1;
    public final static String SAVED_DATA = "checkegedata";
    public final static String SAVED_RESULT = "egeresult";
    public final static String EXAM_URL = "http://check.ege.edu.ru/api/exam/";
    public final static int RESULT_OK = 1;
    public final static int RESULT_NEOK = -1;

    TextView tvParticipant;
    Button btSettings;
    String participant;
    ListView lvSubjects;
    TextView tvLastUpdate;
    Res result;
    SharedPreferences sp;
    SharedPreferences.Editor e;
    DataMonitor dataMonitor;
    IntentFilter intentFilter;
    SubjectsListAdapter adapter;
    boolean firstRender = true;
    boolean loggedIn = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Ваши результаты ЕГЭ");

        CheckEgeApp myApplication = (CheckEgeApp) this.getApplicationContext();
        myApplication.mainActivity = this;

        dataMonitor = new DataMonitor();
        intentFilter = new IntentFilter("com.mavl.showmethefuckingresults.NEW_DATA");

        lvSubjects = (ListView)findViewById(R.id.subjects);
        btSettings = (Button)findViewById(R.id.btSettings);
        tvLastUpdate = (TextView)findViewById(R.id.tvLastUpdate);
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            }
        });

        lvSubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("examID", result.getResult().getExams().get(i).getExamId());
                intent.putExtra("subject", result.getResult().getExams().get(i).getSubject());
                intent.putExtra("date", result.getResult().getExams().get(i).getExamDate());
                intent.putExtra("oralDate", result.getResult().getExams().get(i).getOralExamDate());
                intent.putExtra("Participant", participant);
                //startActivity(intent);
            }
        });

        sp = getSharedPreferences(SAVED_DATA, Context.MODE_PRIVATE);

        boolean hasVisited = sp.getBoolean("hasVisited", false);
        boolean hasLoggedIn = sp.getBoolean("hasLoggedIn", false);

        if (!hasVisited) {
            SharedPreferences dsp = PreferenceManager.getDefaultSharedPreferences(this);
            e = dsp.edit();
            e.putString("sync_frequency", "180");
            e.apply();
        }

        if ((!hasVisited) || (!hasLoggedIn)) {
            showLoginActivity();
            e = sp.edit();
            e.putBoolean("hasVisited", true);
            e.apply();
        }
        else {
            loggedIn = true;
            participant = sp.getString("Participant", null);
            getExamsResult(null);
            stopService(new Intent(this, CheckEge.class));
            startService(new Intent(this, CheckEge.class));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(dataMonitor, intentFilter);
        getExamsResult(null);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(dataMonitor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sync:
                Intent intent = new Intent(this, CheckEge.class);
                stopService(intent);
                startService(intent);
                break;
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), NewSettingsActivity.class));
                break;
            case R.id.action_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void logout() {
        e = sp.edit();
        e.putBoolean("hasLoggedIn", false);
        e.putString("Participant", "");
        e.apply();
        stopService(new Intent(this, CheckEge.class));
        CookieManager cookieManager=CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
        deleteFile(SAVED_RESULT);
        showLoginActivity();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("RESULT", "Code "+resultCode);
        if (resultCode == RESULT_NEOK)
            finish();
        if (data == null) {return;}
        //TODO: Request and result codes!
        participant = data.getStringExtra("participantID");
        e.putBoolean("hasLoggedIn", true);
        e.putString("Participant", participant);
        e.apply();
        if (!loggedIn) {
            stopService(new Intent(this, CheckEge.class));
            startService(new Intent(this, CheckEge.class));
            loggedIn = true;
        }
        //tvParticipant.setText(participant);
        //getExamsResult();
    }

    public void oldGetExamsResult() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, EXAM_URL, null, new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsResponse) {
                        Log.d("onresponse","done");
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        result = gson.fromJson(jsResponse.toString(), Res.class);
                        useReceivedData();
                    }
                }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage()+"", Toast.LENGTH_LONG).show();
                        Log.e("Error", error.getMessage());
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

    public void getExamsResult(String examContent) {

        if (examContent == null) {
            examContent = readSavedResult();
            if (examContent == null)
            {
                //Toast.makeText(this, "Ошибка чтения результатов", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else
            Log.d("GetExamResult", "From intent");

        if (examContent.equals("SYNCED")) {
            refreshLastUpdateBar();
            return;
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        result = gson.fromJson(examContent, Res.class);
        Log.d("GetExamResult", "Refresh bar");
        refreshLastUpdateBar();
        useReceivedData();
    }

    void useReceivedData() {
        Log.d("UseR", "Working");
        adapter = new SubjectsListAdapter(this, result.getResult().getExams());
        lvSubjects.setAdapter(adapter);
    }

    private class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {

        }
    }

    public String readSavedResult() {
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
            return null;
        } catch (IOException e) {
            Log.e("read result", "Can not read file: " + e.toString());
            return null;
        }
        return ret;
    }

    void refreshLastUpdateBar() {
        if (!loggedIn) {
            tvLastUpdate.setVisibility(View.GONE);
            return;
        }
        else
            tvLastUpdate.setVisibility(View.VISIBLE);
        Long msTime = sp.getLong("LastUpdate", -1);
        SharedPreferences defaultSp = PreferenceManager.getDefaultSharedPreferences(this);
        int freq = Integer.parseInt(defaultSp.getString("sync_frequency", "-1"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(msTime);
        tvLastUpdate.setText("Последнее обновление: "+formatter.format(calendar.getTime()));
        if (freq == -1)
            tvLastUpdate.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        else {
            Log.d("FFF", calendar.getTimeInMillis()+" "+msTime+" "+freq);
            if ((Calendar.getInstance().getTimeInMillis() - msTime) > freq*60000)
                tvLastUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            else
                tvLastUpdate.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }
    }

    void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, GET_PARTICIPANT_ID);
    }
}
