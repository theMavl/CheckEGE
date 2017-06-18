package com.mavl.showmethefuckingresults;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

import static com.mavl.showmethefuckingresults.MainActivity.SAVED_DATA;

public class SettingsActivity extends AppCompatActivity {
    Button btApply;
    RadioGroup rg;
    RadioButton[] btns;
    int delay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btApply = (Button)findViewById(R.id.btApply);
        rg = (RadioGroup)findViewById(R.id.srg);
        btns = new RadioButton[rg.getChildCount()];

        ((Button)findViewById(R.id.debugreset)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = "{\"Info\":{\"HotlinePhone\":\"8(347)216-43-18\",\"Info\":\"\"},\"Result\":{\"Exams\":[{\"ExamId\":178,\"OralExamId\":null,\"ExamDate\":\"2016-12-07\",\"OralExamDate\":null,\"Subject\":\"Сочинение\",\"OralSubject\":null,\"TestMark\":1,\"Mark5\":5,\"MinMark\":0,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":true,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":19,\"OralExamId\":null,\"ExamDate\":\"2017-05-29\",\"OralExamDate\":null,\"Subject\":\"Информатика и ИКТ\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":5,\"MinMark\":40,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":196,\"OralExamId\":null,\"ExamDate\":\"2017-05-31\",\"OralExamDate\":null,\"Subject\":\"Математика базовая\",\"OralSubject\":null,\"TestMark\":5,\"Mark5\":5,\"MinMark\":3,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":true,\"IsForeignLanguage\":false},{\"ExamId\":24,\"OralExamId\":null,\"ExamDate\":\"2017-06-02\",\"OralExamDate\":null,\"Subject\":\"Математика профильная\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":5,\"MinMark\":27,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":32,\"OralExamId\":null,\"ExamDate\":\"2017-06-07\",\"OralExamDate\":null,\"Subject\":\"Физика\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":0,\"MinMark\":36,\"Status\":0,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false},{\"ExamId\":21,\"OralExamId\":null,\"ExamDate\":\"2017-06-09\",\"OralExamDate\":null,\"Subject\":\"Русский язык\",\"OralSubject\":null,\"TestMark\":100,\"Mark5\":0,\"MinMark\":24,\"Status\":6,\"OralStatus\":null,\"HasAppeal\":false,\"IsHidden\":false,\"HasResult\":true,\"HasOralResult\":false,\"IsHiddenForRegion\":false,\"AppealStatus\":null,\"IsComposition\":false,\"IsBasicMath\":false,\"IsForeignLanguage\":false}]}}";
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(MainActivity.SAVED_RESULT, Context.MODE_PRIVATE));
                    outputStreamWriter.write(txt);
                    outputStreamWriter.close();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
                SharedPreferences.Editor e = getSharedPreferences(SAVED_DATA, Context.MODE_PRIVATE).edit();
                e.putString("Participant", "kek");
                e.apply();
            }
        });

        for (int i = 0; i < btns.length; i++)
            btns[i] = (RadioButton) rg.getChildAt(i);

        final SharedPreferences sp = getSharedPreferences(SAVED_DATA, Context.MODE_PRIVATE);
        delay = sp.getInt("refresh-delay", 0);

        switch (delay) {
            case -1:
                btns[0].setChecked(true);
                break;
            case 5000:
                btns[1].setChecked(true);
                break;
            case 10000:
                btns[2].setChecked(true);
        }

        btApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c = rg.getCheckedRadioButtonId();
                SharedPreferences.Editor e = sp.edit();
                Log.d("RB", c+"");
                switch (c) {
                    case R.id.s_0:
                        Toast.makeText(getApplicationContext(), "Служба остановлена", Toast.LENGTH_SHORT).show();
                        e.putInt("refresh-delay", -1);
                        //CheckEge.setDelay(-1);
                        break;
                    case R.id.s_1:
                        Toast.makeText(getApplicationContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show();
                        e.putInt("refresh-delay", 5000);
                        //CheckEge.setDelay(5000);
                        break;
                    case R.id.s_2:
                        Toast.makeText(getApplicationContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show();
                        e.putInt("refresh-delay", 10000);
                        //CheckEge.setDelay(10000);
                }
                if (delay < 5000) {
                    Toast.makeText(getApplicationContext(), "Служба запущена", Toast.LENGTH_SHORT).show();
                    startService(new Intent(getApplicationContext(), CheckEge.class));
                }
                e.apply();
                finish();
            }
        });
    }
}
