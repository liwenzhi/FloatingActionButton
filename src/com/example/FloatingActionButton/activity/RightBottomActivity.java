package com.example.FloatingActionButton.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.FloatingActionButton.R;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu;

/**
 * 右下角的逻辑
 */
public class RightBottomActivity extends Activity {

    FloatingActionsMenu multiple_actions;
    FloatingActionButton action_a;
    FloatingActionButton action_b;
    FloatingActionButton actionC;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_bottom);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        multiple_actions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        action_a = (FloatingActionButton) findViewById(R.id.action_a);
        action_b = (FloatingActionButton) findViewById(R.id.action_b);
        actionC = new FloatingActionButton(getBaseContext());


    }

    private void initData() {
        actionC.setTitle("Hide/Show Action above");
        multiple_actions.addButton(actionC);
    }

    private void initEvent() {
        //监听点击事件
        action_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_a.setTitle("Action A clicked");
            }
        });

        action_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(RightBottomActivity.this, "右边底下第二个按钮", Toast.LENGTH_SHORT).show();
            }
        });

        actionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action_a.setVisibility(action_a.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
            }
        });
    }
}
