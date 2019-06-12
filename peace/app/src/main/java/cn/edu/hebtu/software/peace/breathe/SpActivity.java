package cn.edu.hebtu.software.peace.breathe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.hebtu.software.peace.R;

public class SpActivity extends AppCompatActivity {

    private int recLen = 4;//跳过倒计时提示3秒
    private TextView tv;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private String time;
    private Animation fadeInAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_sp);

        Intent i=getIntent();
        final String time=i.getStringExtra("time");
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(SpActivity.this, Running.class);
                intent.putExtra("times",time);
                startActivity(intent);
                overridePendingTransition( R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        }, 4000);//延迟4S后发送handler信息
        fadeInAnimation = AnimationUtils.loadAnimation(SpActivity.this, R.anim.fade_in);
    }
    private void initView() {
        tv = findViewById(R.id.tv);//跳过
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText("" + recLen);
                    tv.startAnimation(fadeInAnimation);
                    if (recLen ==1) {
                        timer.cancel();
//                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

}
