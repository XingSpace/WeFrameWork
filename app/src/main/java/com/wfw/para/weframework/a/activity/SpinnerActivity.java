package com.wfw.para.weframework.a.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/12.
 */

public class SpinnerActivity extends Activity {

    @BindView(R.id.spinner)
    Spinner spinner;
    String[] strs;
    LayoutInflater layoutInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_spinner);
        ButterKnife.bind(this);

        strs = getResources().getStringArray(R.array.languages);
        layoutInflater = LayoutInflater.from(this);

        setSpinner();
    }

    private void setSpinner() {
        spinner.setAdapter(new SpinnerAdapter());
    }

    class SpinnerAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return strs.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = layoutInflater.inflate(R.layout.a_spinner_item, null);
            ((TextView) view.findViewById(R.id.textView)).setText(strs[i]);
            return view;
        }
    }
}
