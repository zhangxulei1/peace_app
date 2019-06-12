package cn.edu.hebtu.software.peace.focusTotal;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.R;


public class TestActivity extends AppCompatActivity implements SensorEventListener {
    //定义sensor管理器
    private SensorManager mSensorManager;
    //震动
    private Vibrator vibrator;
    private TextView text;
    int  num=0;
    private ImageView shakePhone;
    private TextView shakeTimes;
    private Button exit;
    private Animator animator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        //获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //震动
        vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        text = findViewById( R.id.text);
        shakePhone = findViewById(R.id.shakePhone);
        shakeTimes = findViewById(R.id.shakeTimes);
        exit = findViewById(R.id.exit);
        Intent intent = getIntent();
        shakeTimes.setText(intent.getStringExtra("times"));
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                text.setText("摇一摇");
                num=0;
            }
        });


        //1.加载动画
        animator = AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.shakeanimator);
        //2.关联动画和目标对象
        animator.setTarget(shakePhone);
        animator.start(); //开始播放



    }
    @Override
    protected void onResume(){
        super.onResume();

        //加速度传感器
        mSensorManager.registerListener((SensorEventListener) this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        //values[0]:X轴，values[1]：Y轴，values[2]：Z轴
        float[] values = event.values;

        if(sensorType == Sensor.TYPE_ACCELEROMETER){

            /*因为一般正常情况下，任意轴数值最大就在9.8~10之间，只有在你突然摇动手机
            的时候，瞬时加速度才会突然增大或减少。
            所以，经过实际测试，只需监听任一轴的加速度大于14的时候，改变你需要的设置就OK了*/

            if((Math.abs(values[0])>20||Math.abs(values[1])>20||Math.abs(values[2])>20)){
                num++;
                //exit.setVisibility(View.GONE);
                //摇动手机后，设置button上显示的字为空
                //text.setText("摇了"+num+"下");
                //当摇的次数等于规定次数时，动画停止，显示出退出按钮，结束跳出
                if(num == Integer.parseInt(shakeTimes.getText().toString())){
                    exit.setVisibility(View.VISIBLE);
                    onStop();
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("test","进入方法");
//                            Intent intent = new Intent().setClass(TestActivity.this,WakeUpActivity.class);
//                            startActivity(intent);
                            finish();
                        }
                    });
                    animator.end();

                }
                //摇动手机后，再伴随震动提示~~
                vibrator.vibrate(500);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
