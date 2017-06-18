package com.mavl.showmethefuckingresults.ListAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mavl.showmethefuckingresults.ObjectResult.Exam;
import com.mavl.showmethefuckingresults.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mavl on 15.06.2017.
 */

public class SubjectsListAdapter extends BaseAdapter {
    LayoutInflater lInflater;
    List<Exam> exams;
    TextView name;
    TextView status;
    Context context;

    public SubjectsListAdapter(Context context, List<Exam> exams) {
        this.exams = exams;
        this.context = context;
        lInflater = LayoutInflater.from(context);
        //lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return exams.size();
    }

    @Override
    public Object getItem(int position) {
        return exams.get(position);
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
        Exam exam = exams.get(position);
        ((TextView) view.findViewById(R.id.tvSubjectName)).setText(exam.getSubject());
        ((TextView) view.findViewById(R.id.tvScore)).setText(exam.getStringScore());
        ((TextView) view.findViewById(R.id.tvExamStatus)).setText(exam.getStringStatus());
        if (exam.getTestMark() >= exam.getMinMark())
            ((TextView) view.findViewById(R.id.tvScore)).setTextColor(context.getResources().getColor(R.color.goodResult));
        else if (exam.isHasResult())
            ((TextView) view.findViewById(R.id.tvScore)).setTextColor(context.getResources().getColor(R.color.badResult));

        return view;
    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}