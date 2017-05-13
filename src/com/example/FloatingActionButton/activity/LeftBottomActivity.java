package com.example.FloatingActionButton.activity;

import android.app.Activity;
import android.os.Bundle;
import com.example.FloatingActionButton.R;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu;

/**
 * 左下角的逻辑
 */
public class LeftBottomActivity extends Activity {

    FloatingActionsMenu right_labels;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_bottom);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        right_labels = (FloatingActionsMenu) findViewById(R.id.right_labels);
    }

    private void initData() {
        FloatingActionButton addedOnce = new FloatingActionButton(this);
        addedOnce.setTitle("Added once");
        right_labels.addButton(addedOnce);

        FloatingActionButton addedTwice = new FloatingActionButton(this);
        addedTwice.setTitle("Added twice");
        right_labels.addButton(addedTwice);
        right_labels.removeButton(addedTwice);
        right_labels.addButton(addedTwice);
    }

    private void initEvent() {

    }
}
