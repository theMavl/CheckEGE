package com.mavl.showmethefuckingresults.ObjectExam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mavl.showmethefuckingresults.MainActivity;
import com.mavl.showmethefuckingresults.ObjectResult.Res;
import com.mavl.showmethefuckingresults.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    Intent intent;
    int examID;
    String subjectName;
    String examDate;
    ExamInDetails examInDetails;
    String participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intent = getIntent();
        examID = intent.getIntExtra("examID", -1);
        subjectName = intent.getStringExtra("subject");
        examDate = intent.getStringExtra("date");
        participant = intent.getStringExtra("Participant");
    }

    void getExamDetails() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, MainActivity.EXAM_URL+examID, null, new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsResponse) {
                        Log.d("onresponse","done");
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        examInDetails = gson.fromJson(jsResponse.toString(), ExamInDetails.class);
                        //setAdapter();
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
}
