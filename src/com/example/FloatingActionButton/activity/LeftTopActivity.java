package com.example.FloatingActionButton.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.FloatingActionButton.R;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu;

/**
 * 右上角座左边的逻辑
 */
public class LeftTopActivity extends Activity {

    FloatingActionsMenu multiple_actions_left;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_top);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        multiple_actions_left = (FloatingActionsMenu) findViewById(R.id.multiple_actions_left);

    }

    private void initData() {


    }

    private void initEvent() {

    }
}
