package cn.edu.hebtu.software.peace.focusTotal;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.util.SelectTimes;

public class WakeUpActivity extends AppCompatActivity {
    private ImageView back;
    private LinearLayout task;
    private LinearLayout shake;
    private ImageView taskheart;
    private ImageView shakeheart;
    private TextView times;
    private TextView setting;
    private String[] minStr = {"30"};
    private SelectTimes minute_pv;
    private String changeWord;
    private String changeCount;

    private TextView start;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView( R.layout.sleep_wakeup);
        back = findViewById( R.id.back);
        task = findViewById(R.id.task);
        shake = findViewById(R.id.shake);
        taskheart = findViewById(R.id.taskheart);
        shakeheart = findViewById(R.id.shakeheart);
        times = findViewById(R.id.times);
        setting = findViewById(R.id.setting);
        start = findViewById(R.id.start);
        task.getBackground().setAlpha(100);
        shake.getBackground().setAlpha(100);

        task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
        task.getBackground().setAlpha(130);
        taskheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
        shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
        shake.getBackground().setAlpha(100);
        shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
        setting.setVisibility(View.GONE);
        changeWord = "无任务";

        changeCount="30";
        changeCount = (String) times.getText();



        //箭头监听器，点击返回
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(  );
                intent.putExtra( "changeWord",changeWord );
                intent.putExtra( "changeCount",changeCount );
                Log.e( "WakeUpActivity页面中",changeWord+"值" );
                Log.e( "WakeUpActivity页Count：",changeCount+"值" );
                setResult( 26 ,intent);
                finish();
            }
        } );


        //task监听器，点击改变样式，并返回“无任务”
        //后加

        task.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                task.getBackground().setAlpha(130);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                shake.getBackground().setAlpha(100);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.INVISIBLE);
                changeWord = "无任务";

            }
        });
        //shake监听器，点击改变样式，并返回“摇一摇”
        //后加
        shake.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                shake.setBackground(getResources().getDrawable(R.drawable.wakeup_shape_selected));
                shake.getBackground().setAlpha(130);
                shakeheart.setImageDrawable(getResources().getDrawable(R.drawable.selected));
                task.setBackground(getResources().getDrawable(R.drawable.wakeup_shape));
                task.getBackground().setAlpha(100);
                taskheart.setImageDrawable(getResources().getDrawable(R.drawable.select));
                setting.setVisibility(View.VISIBLE);
                changeWord="摇一摇";
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
        //点击开始按钮，跳到摇一摇界面
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(WakeUpActivity.this, TestActivity.class);
                intent.putExtra("times",times.getText());
                startActivity(intent);
            }
        });
    }
    private void showBottomDialog(){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this,R.style.DialogTheme);
        //2、设置布局
        //View view = View.inflate(getActivity(),R.layout.dialog_layout,null);
        View view = LayoutInflater.from(this).inflate(
                R.layout.dialog_layout, null);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        minute_pv = dialog.findViewById(R.id.message);
        //时间齿轮
        List<String> data = new ArrayList<String>();
        final List<String> seconds = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            data.add("0" + i);
        }
        for (int i = 0; i < 60; i++) {
            seconds.add(i < 10 ? "0" + i : "" + i);
        }
        minute_pv.setData(seconds);
        minute_pv.setOnSelectListener(new SelectTimes.onSelectListener(){
            @Override
            public void onSelect(String text) {
                minStr[0] = text;
            }
        });
        dialog.show();
        //确定按钮
        dialog.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                times.setText(minStr[0]);
                changeCount= (String) times.getText();
                Log.e( "changeCount_wakeupAc值",changeCount+"zhi" );
            }
        });

        //取消按钮
        dialog.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });



    }


}
