package com.wfw.para.weframework.a.activity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/13.
 */

public class EditTextActivity extends Activity {
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.multiAutoCompleteTextView)
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    @BindView(R.id.errorEdit)
    TextInputLayout errorEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_edittext);
        ButterKnife.bind(this);

        autoComplete();
        errorEdit.setErrorEnabled(true);
        errorEdit.setError("出现错误");
    }

    void autoComplete() {
        String[] arr = {"aa", "aab", "aac"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        autoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}
