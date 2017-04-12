package com.a16mb.damrod.quizwars;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Damrod on 12.04.2017.
 */

public class QuestionAdapter extends ArrayAdapter<QuestionModel> {


    public QuestionAdapter(Context context, List<QuestionModel> questionList) {
        super(context,R.layout.question_layout, questionList);
    }


    static class ViewHolder {
        TextView questionTextView;
        RadioButton radioButton01, radioButton02, radioButton03, radioButton04;
        TextView numberTextView;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final QuestionModel question = getItem(position);
        ViewHolder viewHolder = null;

        if (convertView == null)
        {

            // If there's no view to re-use, inflate a brand new view for row
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View customView = inflater.inflate(R.layout.question_layout, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.questionTextView = (TextView) customView.findViewById(R.id.question_text_view);
            viewHolder.numberTextView = (TextView) customView.findViewById(R.id.number_text_view);

            viewHolder.radioButton01 = (RadioButton) customView.findViewById(R.id.answer01_radiobutton);
            viewHolder.radioButton02 = (RadioButton) customView.findViewById(R.id.answer02_radiobutton);
            viewHolder.radioButton03 = (RadioButton) customView.findViewById(R.id.answer03_radiobutton);
            viewHolder.radioButton04 = (RadioButton) customView.findViewById(R.id.answer04_radiobutton);

            customView.setTag(viewHolder);
            //viewHolder = (ViewHolder) customView.getTag();
            convertView = customView;

        }
        else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.questionTextView.setText(question.getQuestion());
        viewHolder.numberTextView.setText(Integer.toString(position+1) +".");

        viewHolder.radioButton01.setText(question.getAll_answers().get(0));
        viewHolder.radioButton02.setText(question.getAll_answers().get(1));
        viewHolder.radioButton03.setText(question.getAll_answers().get(2));
        viewHolder.radioButton04.setText(question.getAll_answers().get(3));

        viewHolder.radioButton01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    question.setSelected_answer(buttonView.getText().toString());
                }
            }
        });

        viewHolder.radioButton02.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    question.setSelected_answer(buttonView.getText().toString());
                }
            }
        });

        viewHolder.radioButton03.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    question.setSelected_answer(buttonView.getText().toString());
                }
            }
        });

        viewHolder.radioButton04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    question.setSelected_answer(buttonView.getText().toString());
                }
            }
        });

        return convertView;
    }

    @Override

    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
