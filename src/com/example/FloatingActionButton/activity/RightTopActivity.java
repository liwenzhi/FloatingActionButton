package com.example.FloatingActionButton.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.FloatingActionButton.R;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton;
import com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu;

/**
 * 右上角的逻辑
 */
public class RightTopActivity extends Activity {

    FloatingActionsMenu multiple_actions_down;
    FloatingActionButton removeAction;
    FloatingActionButton button_gone;
    FloatingActionButton action_enable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_top);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        multiple_actions_down = (FloatingActionsMenu) findViewById(R.id.multiple_actions_down);
        removeAction = (FloatingActionButton) findViewById(R.id.button_remove);
        button_gone = (FloatingActionButton) findViewById(R.id.button_gone);
        action_enable = (FloatingActionButton) findViewById(R.id.action_enable);
    }

    private void initData() {
        button_gone.setVisibility(View.GONE);

    }

    private void initEvent() {
        //监听点击事件
        removeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除某个对象，可以是自己
                multiple_actions_down.removeButton(removeAction);
                button_gone.setVisibility(View.VISIBLE);//刚开始隐藏的显示出来
            }
        });

        action_enable.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(RightTopActivity.this, "右边第二个按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
