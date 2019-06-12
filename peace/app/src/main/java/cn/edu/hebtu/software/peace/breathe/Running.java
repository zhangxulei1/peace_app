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
import cn.edu.hebtu.software.peace.breathe.utils.WaveFunctionView_running;

public class Running extends AppCompatActivity implements View.OnClickListener {
    private Animation fadeInAnimation;
    private TextView tv;
    WaveFunctionView_running waveFunctionView;
    //结束按钮
    private Button btn_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=new Intent(Running.this,MusicService.class);
        super.onCreate(savedInstanceState);
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView( R.layout.activity_running);

        initView();
        btn_finish.setOnClickListener(this);

        init();

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

        startService(intent);

        fadeInAnimation = AnimationUtils.loadAnimation(Running.this, R.anim.fade_in);

    }
    protected void onStop(){
        Intent intent = new Intent(Running.this,MusicService.class);
        stopService(intent);
        super.onStop();
    }


            public void init() {
                Timer timer=new Timer(true);
                TimerTask timerTask=new TimerTask() {
                    @Override
                    public void run() {
                                waveFunctionView.postDelayed(new Runnable() {
                        @Override
                     public void run() {
                    waveFunctionView.ChangeWaveLevel(0);
                            tv.startAnimation(fadeInAnimation);
                            tv.setText("吸气");
                            tv.startAnimation(fadeInAnimation);
                    }
                }, 0);
                    }
                };
                timer.schedule(timerTask,2500,5000);

        Timer timer1=new Timer(true);
        TimerTask timerTask1=new TimerTask() {
            @Override
            public void run() {
                waveFunctionView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        waveFunctionView.ChangeWaveLevel(10);
                        tv.startAnimation(fadeInAnimation);
                        tv.setText("呼气");
                        tv.startAnimation(fadeInAnimation);


                    }
                }, 0);
            }
        };
        timer1.schedule(timerTask1,5000,5000);
//                for(;cishu>0;cishu--){
//                Handler handler=new Handler();
//                Runnable runnable=new Runnable() {
//                    @Override
//                    public void run() {
//                        waveFunctionView.ChangeWaveLevel(0);
//                    }
//                };
//                handler.postDelayed(runnable,6000);
//
//            Handler handler1=new Handler();
//            Runnable runnable1=new Runnable() {
//                @Override
//                public void run() {
//                    waveFunctionView.ChangeWaveLevel(10);
//                }
//            };
//            handler1.postDelayed(runnable1,12000);
//                }
//            }
//        }).start();
//            waveFunctionView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    waveFunctionView.ChangeWaveLevel(0);
//                    }
//                }, 3000);
////
//            waveFunctionView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    waveFunctionView.ChangeWaveLevel(10);
//                }
//            }, 6000);
//
//        waveFunctionView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                waveFunctionView.ChangeWaveLevel(0);
//            }
//        }, 9000);
//
//
//        waveFunctionView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                waveFunctionView.ChangeWaveLevel(10);
//            }
//        }, 12000);
//        waveFunctionView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                waveFunctionView.ChangeWaveLevel(0);
//            }
//        }, 15000);
//
//
//        waveFunctionView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                waveFunctionView.ChangeWaveLevel(10);
//            }
//        }, 18000);

    }

    private void initView() {
        //结束按钮
        btn_finish=findViewById(R.id.btn_finish);
        waveFunctionView=findViewById(R.id.BezierView_running);
        tv=findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_finish:
                intent=new Intent(Running.this,HuxiActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_out,R.anim.fade_in);
                break;

        }
    }

}
