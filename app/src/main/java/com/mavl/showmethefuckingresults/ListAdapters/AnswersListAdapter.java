package com.mavl.showmethefuckingresults.ListAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mavl.showmethefuckingresults.ObjectExam.Answer;
import com.mavl.showmethefuckingresults.ObjectExam.ExamInfo;
import com.mavl.showmethefuckingresults.ObjectExam.PartB;
import com.mavl.showmethefuckingresults.ObjectResult.Exam;
import com.mavl.showmethefuckingresults.R;

import java.util.List;

/**
 * Created by mavl on 16.06.2017.
 */

public class AnswersListAdapter extends BaseAdapter {

    LayoutInflater lInflater;
    List<Answer> answers;
    ExamInfo examInfo;
    TextView name;
    TextView status;

    public AnswersListAdapter(Context context, List<Answer> answers, ExamInfo examInfo) {
        this.answers = answers;
        this.examInfo = examInfo;
        lInflater = LayoutInflater.from(context);
        //lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = lInflater.inflate(R.layout.subject, null);
        }
        Answer answer = answers.get(position);
        List<PartB> partB = examInfo.getPartB();
        String legalSymbols;
        String maxMark;

        if (position >= partB.size())
            examInfo.getWithCriteria().get(position);


        ((TextView) view.findViewById(R.id.tvAnswer)).setText(answer.getAnswer());
        ((TextView) view.findViewById(R.id.tvMark)).setText(answer.getMark());
        //((TextView) view.findViewById(R.id.tvExamStatus)).setText(answer.getStatus()+"");
        return view;
    }
}
