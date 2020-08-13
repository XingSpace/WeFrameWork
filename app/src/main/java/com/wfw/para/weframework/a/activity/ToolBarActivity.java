package com.wfw.para.weframework.a.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/12.
 */

public class ToolBarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_1)
    Toolbar toolbar1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_toolbar);
        ButterKnife.bind(this);

        setToolBar1();
    }

    private void setToolBar1() {
        toolbar1.setLogo(R.mipmap.ic_launcher);
        toolbar1.setTitle("My Title");
        toolbar1.setSubtitle("Sub title");

        setSupportActionBar(toolbar1);
        toolbar1.setNavigationIcon(R.mipmap.ic_launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.a_menu_toolbar, menu);//加载menu文件到布局
        return true;
    }


}
