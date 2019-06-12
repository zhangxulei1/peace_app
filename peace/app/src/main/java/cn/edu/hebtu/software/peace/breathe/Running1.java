package cn.edu.hebtu.software.peace.breathe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.breathe.utils.WaveFunctionView_running2;


public class Running1 extends AppCompatActivity implements View.OnClickListener {
    private Animation fadeInAnimation;
    private TextView tv;
    private static int flag=0;
    WaveFunctionView_running2 waveFunctionView2;
    private Button btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=new Intent(Running1.this,MusicService.class);
        super.onCreate(savedInstanceState);
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_running1);

        initView();
        btn_finish.setOnClickListener(this);

        init();
        startService(intent);
        fadeInAnimation = AnimationUtils.loadAnimation(Running1.this, R.anim.fade_in);

        Intent i=getIntent();
        String t=i.getStringExtra("times");
        Pattern p=Pattern.compile("\\d+");
        Matcher m=p.matcher(t);
        m.find();
        String ti=m.group();
        ti=ti+"000";
        Log.i("ti",ti+"");
        int time=Integer.parseInt(ti);
        time=time*60;
        Log.i("time",time+"");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },time);
    }

    protected void onStop(){
        Intent intent = new Intent(Running1.this,MusicService.class);
        stopService(intent);
        super.onStop();
    }
    private void initView() {
        //结束按钮
        btn_finish=findViewById(R.id.btn_finish);
        waveFunctionView2=findViewById(R.id.BezierView_running2);
        tv=findViewById(R.id.tv);
    }
    public void init() {
        Timer timer=new Timer(true);
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                waveFunctionView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waveFunctionView2.ChangeWaveLevel(0);
                        tv.startAnimation(fadeInAnimation);
                        tv.setText("呼气");
                        tv.startAnimation(fadeInAnimation);

                    }
                }, 0);
            }
        };
        timer.schedule(timerTask,4000,8000);

        Timer timer1=new Timer(true);
        TimerTask timerTask1=new TimerTask() {
            @Override
            public void run() {
                waveFunctionView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waveFunctionView2.ChangeWaveLevel(10);
                        tv.startAnimation(fadeInAnimation);
                        tv.setText("吸气");
                        tv.startAnimation(fadeInAnimation);

                    }
                }, 0);
            }
        };
        timer1.schedule(timerTask1,8000,8000);
        Timer timer3=new Timer(true);
        TimerTask timerTask3=new TimerTask() {
            @Override
            public void run() {
                waveFunctionView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.startAnimation(fadeInAnimation);
                        tv.setText("屏气");
                        tv.startAnimation(fadeInAnimation);

                    }
                }, 0);
            }
        };
        timer3.schedule(timerTask3,2000,8000);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_finish:
                intent = new Intent(Running1.this, HuxiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out, R.anim.fade_in);
                break;
        }
    }
}
