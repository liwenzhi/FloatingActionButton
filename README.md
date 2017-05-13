#Android自定义的FloatingActionButton效果

###现在Android的ActionBar功能已经很少人app使用了。但是有些页面还是需要这种按钮的操作（比如执行一些数据刷新或页面跳转的功能），就有了现在的悬浮效果的按钮，并且这个悬浮按钮可以设置在页面的任意位置！其实就是一个ImageButton，经过各种封装和处理后得到一个比较好看的效果。

现在谷歌官方的Design包，已经右有FloatingActionButton这个类了，但是要求版本较高，所有现在很多编译器没法使用，我的也是，这里使用的是自定义的类。具体代码可以看源码，只有几个类，但是要添加一些资源文件。很多具体的属性可以自己修改。

###效果（上下左右四种效果）：
![1](http://i.imgur.com/IOXrTnC.gif)
#使用：
#说明：
##（一）包含的自定义类
###1.FloatingActionButton悬浮按钮的类 
是一个继承了IamgeButton的类，可以在布局中设置悬浮按钮的图片、文字、附带的文字等等数据

###2.FloatingActionsMenu悬浮菜单，里面可以放多个悬浮按钮
是一个继承了ViewGroup的容器类，里面可以放置多个悬浮按钮，可以指定按钮的方向，默认已经设置好了点击一次显示里面的悬浮按钮，第二次隐藏。
悬浮菜单默认已经有一个悬浮按钮，即使里面不添加悬浮，按钮也是会默认显示一个。

###3.继承了悬浮按钮的类，就加了一个mPlusColor属性，用来设置悬浮按钮的颜色，是用来扩展的吧？

##（二）布局文件，及属性的详解
使用的时候包名记得更改
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:fab="http://schemas.android.com/apk/res-auto"  //命名空间的进入
                android:background="@color/background"
                android:layout_width="match_parent"
                android:layout_height="match_parent">
   <!--一组悬浮按钮，里面可以有多个悬浮按钮-->
    <com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu //悬浮菜单
            android:id="@+id/multiple_actions_down"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_alignParentEnd="true"
            android:layout_alignParentTop="true"
            fab:fab_addButtonColorNormal="@color/white"		//菜单按钮正常的背景颜色	
            fab:fab_addButtonColorPressed="@color/white_pressed"//菜单按钮按下的背景颜色
            fab:fab_addButtonSize="mini"			//菜单按钮的大小，normal表示56dp，mini表示40dp
            fab:fab_addButtonPlusIconColor="@color/half_black"	//图像的颜色，区分背景，图像旁边空白的地方就是背景	
            fab:fab_expandDirection="down"//显示悬浮按钮的方向，up表示向上（默认），down表示向下，left表示向左，right表示向右
            fab:fab_labelStyle="@style/menu_labels_style"//设置标签文本的风格，默认是灰色背景，白色字体
            android:layout_marginTop="16dp"		//android的属性就不解释了！！
            android:layout_marginRight="16dp"
            android:layout_marginEnd="16dp">

        <com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton//悬浮按钮
                android:id="@+id/action_enable"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"			
                fab:fab_colorNormal="@color/white"		//正常的颜色
                fab:fab_title="Set bottom menu enabled/disabled"	//标签文本显示的内容
                fab:fab_icon="@drawable/icon_add"		//显示的图标
                fab:fab_colorPressed="@color/white_pressed"	//悬浮按钮按下时显示的颜色
		/>
 
        <com.example.FloatingActionButton.floatingactionbutton.AddFloatingActionButton  //另一种悬浮按钮
                android:id="@+id/semi_transparent"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_plusIconColor="@color/red"		//图像的颜色	
                fab:fab_colorNormal="@color/blue_transparent"		//悬浮按钮正常的颜色
                fab:fab_colorPressed="@color/blue_transparent_pressed"	//悬浮按钮按下的颜色
                android:layout_centerHorizontal="true"
                android:layout_marginBottom="16dp"/>

    </com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu>


</RelativeLayout>

```
##（三）java代码调用
其实就是设置ImageButton的事件，单击事件就是onClick
###这里展示下，右上角部分的逻辑代码：
```
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

```
###它的布局文件：
```
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:fab="http://schemas.android.com/apk/res-auto"
                android:background="@color/background"
                android:layout_width="match_parent"
                android:layout_height="match_parent">


    <!--右上角那组按钮-->
    <com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu
            android:id="@+id/multiple_actions_down"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_alignParentEnd="true"
            android:layout_alignParentTop="true"
            fab:fab_addButtonColorNormal="@color/red"
            fab:fab_addButtonColorPressed="@color/red_pressed"
            fab:fab_addButtonSize="mini"
            fab:fab_addButtonPlusIconColor="@color/menu_plus"
            fab:fab_expandDirection="down"
            fab:fab_labelStyle="@style/labels_style_orange"
            android:layout_marginTop="16dp"
            android:layout_marginRight="16dp"
            android:layout_marginEnd="16dp">
        <!--没有任何操作的按钮，右边那组最下面那个-->
        <com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/white"
                fab:fab_colorPressed="@color/red_pressed"
                fab:fab_size="mini"/>
        <!--右上角第三个（倒数第二个），单击可消失的！-->
        <com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton
                android:id="@+id/button_remove"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/white"
                fab:fab_colorPressed="@color/white_pressed"
                fab:fab_icon="@drawable/ic_fab_star"
                fab:fab_title="Click to remove"/>
        <!--一开始就消失的按钮-->
        <com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton
                android:id="@+id/button_gone"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/white"
                fab:fab_icon="@drawable/icon_delete"
                fab:fab_colorPressed="@color/white_pressed"/>
        <!--右上角第二个-->
        <com.example.FloatingActionButton.floatingactionbutton.FloatingActionButton
                android:id="@+id/action_enable"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                fab:fab_colorNormal="@color/white"
                fab:fab_title="Set bottom menu enabled/disabled"
                fab:fab_icon="@drawable/icon_add"
                fab:fab_colorPressed="@color/white_pressed"/>

    </com.example.FloatingActionButton.floatingactionbutton.FloatingActionsMenu>


</RelativeLayout>

```


###上下左右四种效果，分开展示：

![2](http://i.imgur.com/zY7gln0.gif)

后期，我对右上角部分页面资源文件进行了一点修改，效果：

![3](http://i.imgur.com/BtD0WrO.png)

你也可以根据自己的需求，重新写一个style设置样式

像下面图片的样式，还是不错的，写起来也不难，效果：

![4](http://i.imgur.com/UtfnaLD.png)


#共勉：


##我们要学会狠忍静（很冷静）

###狠：男人对自己狠一点才能有作为。

###忍：适当忍住一下欲望，你才能得到更多。

###静：静下心来才能好好想事情和做事情。

![5](http://i.imgur.com/y71nGfq.jpg)
