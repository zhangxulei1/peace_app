package cn.edu.hebtu.software.peace.focusTotal;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import cn.edu.hebtu.software.peace.IndexActivity;
import cn.edu.hebtu.software.peace.util.RenSheng;
import cn.edu.hebtu.software.peace.util.haiyang;
import cn.edu.hebtu.software.peace.util.leiyu;
import cn.edu.hebtu.software.peace.util.mingxiang;
import cn.edu.hebtu.software.peace.util.yutian;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.R;

public class RelaxActivity extends Activity implements View.OnClickListener {
    private ViewPager pager;
    //声明ViewPager的适配器
    private PagerAdapter mAdpater;
    //用于装载四个Tab的List
    private List<View> mTabs = new ArrayList<View>();

    private ImageView item1;
    private ImageView item2;
    private ImageView item3;
    private ImageView item4;

    private LinearLayout topcenter;
    private LinearLayout topcenterpoint;
    private ImageView topcenterimg;

    /*private LinearLayout noticestart;
    private Button noticepause;
    private LinearLayout noticemiddle;
    private Button noticegoon;
    private Button noticestop;*/
    private LinearLayout cancelsilent;

    private ImageView topright;
    private boolean flag = false;
    private int[] image = {R.drawable.sound,
            R.drawable.silent
};
    private ImageView topleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.relax_main);

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item1.setImageResource(R.drawable.welcome_circle_later);

        //初始化控件
        initViews();
        //初始化事件
        initEvents();
        //默认选中第一个Tab
        pager.setCurrentItem(0);
        //初始化数据
        initDate();



        //绑定监听器
        cancelsilent.setOnClickListener(this);
        cancelsilent=findViewById(R.id.cancel_silent);
        cancelsilent.getBackground().setAlpha(150);
        topleft.setOnClickListener(this);
        topright.setOnClickListener(this);



        panduan();

        dimBackground(1.0f,0.7f);


    }
    private void dimBackground(final float from, final float to) {
        final Window window = getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.alpha = (Float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.start();
    }


    private void panduan() {
    }

    private void initDate() {
        //初始化ViewPager的适配器
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs.get(position));
            }
        };
        //设置ViewPager的适配器
        pager.setAdapter(mAdpater);
        pager.setPageTransformer(true, new ScalePageTransformer());

    }

    public class ScalePageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE=0.75f;

        @Override
        public void transformPage(View page, float position) {
            //Log.d("TAG", "<"+page.hashCode()+", "+position+">");
            // out of left screen
            if(position<-1.0f) {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
            // slide left
            else if(position<=0.0f) {
                page.setAlpha(1.0f);
                page.setTranslationX(0.0f);
                page.setScaleX(1.0f);
                page.setScaleY(1.0f);
            }
            // slide right
            else if(position<=1.0f) {
                page.setAlpha(1.0f-position);
                page.setTranslationX(-page.getWidth()*position);
                float scale=MIN_SCALE+(1.0f-MIN_SCALE)*(1.0f-position);
                page.setScaleX(scale);
                page.setScaleY(scale);
            }
            // out of right screen
            else {
                page.setScaleX(MIN_SCALE);
                page.setScaleY(MIN_SCALE);
            }
        }
    }

    private void initEvents() {
        //添加ViewPager的切换Tab的监听事件
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /*if((cancelsilent.getVisibility()==View.GONE)){
                    Intent i;
                    i=new Intent(RelaxActivity.this,yutian.class);
                    stopService(i);
                    i=new Intent(RelaxActivity.this,haiyang.class);
                    stopService(i);
                    i=new Intent(RelaxActivity.this,mingxiang.class);
                    stopService(i);
                    i=new Intent(RelaxActivity.this,leiyu.class);
                    stopService(i);
                    if (pager.getCurrentItem()==0){
                        i=new Intent(RelaxActivity.this,yutian.class);
                        startService(i);
                    }else if(pager.getCurrentItem()==1){
                        i=new Intent(RelaxActivity.this,haiyang.class);
                        startService(i);
                    }else if(pager.getCurrentItem()==2){
                        i=new Intent(RelaxActivity.this,mingxiang.class);
                        startService(i);
                    }else if(pager.getCurrentItem()==3){
                        i=new Intent(RelaxActivity.this,leiyu.class);
                        startService(i);
                    }
                }*/
            }

            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = pager.getCurrentItem();
                final Intent[] i = new Intent[4];
                i[0] = new Intent(RelaxActivity.this, yutian.class);
                i[1] = new Intent(RelaxActivity.this, mingxiang.class);
                i[2] = new Intent(RelaxActivity.this, leiyu.class);
                i[3] = new Intent(RelaxActivity.this, haiyang.class);
                switch (currentItem) {
                    case 0:
                        item1.setImageResource(R.drawable.welcome_circle_later);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle);
                        startService(i[0]);
                        stopService(i[1]);
                        stopService(i[2]);
                        stopService(i[3]);
                        break;
                    case 1:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle_later);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle);
                        stopService(i[0]);
                        startService(i[1]);
                        stopService(i[2]);
                        stopService(i[3]);
                        break;
                    case 2:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle_later);
                        item4.setImageResource(R.drawable.welcome_circle);
                        stopService(i[0]);
                        stopService(i[1]);
                        startService(i[2]);
                        stopService(i[3]);
                        break;
                    case 3:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle_later);
                        stopService(i[0]);
                        stopService(i[1]);
                        stopService(i[2]);
                        startService(i[3]);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 0:   //什么都没做，显示图片
                        topcenterimg.setVisibility(View.VISIBLE);
                        topcenterpoint.setVisibility(View.GONE);
                        break;
                    case 1:   //正在滑动，显示点
                        topcenterpoint.setVisibility(View.VISIBLE);
                        topcenterimg.setVisibility(View.GONE);
                        break;
                    case 2:   //滑动完毕，后显示图片
                        new MyAsyncTask().execute();
                        break;
                }
            }
        });
    }
    class MyAsyncTask extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            try {
                Thread.sleep(20000); //睡
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            topcenterimg.setVisibility(View.VISIBLE);
            topcenterpoint.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        pager = findViewById(R.id.viewPager);
        //获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View test1 = inflater.inflate(R.layout.relax_item1, null);

        View test2 = inflater.inflate(R.layout.relax_item2, null);
        View test3 = inflater.inflate(R.layout.relax_item3, null);
        View test4 = inflater.inflate(R.layout.relax_item4, null);
        //将四个Tab添加到集合中
        mTabs.add(test1);
        mTabs.add(test2);
        mTabs.add(test3);
        mTabs.add(test4);

        topcenter = findViewById(R.id.top_center);
        topcenterpoint = findViewById(R.id.top_center_point);
        topcenterimg = findViewById(R.id.top_center_img);

        cancelsilent = findViewById(R.id.cancel_silent);
        topleft = findViewById(R.id.top_left);
        topright=findViewById( R.id.top_right );

    }


    protected void onStop(){
        Intent intent = new Intent(RelaxActivity.this,yutian.class);
        stopService(intent);
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        Intent i;
        Intent intentrensheng;
        switch (v.getId()) {
            case R.id.cancel_silent:
                cancelsilent.setVisibility(View.GONE);
                intentrensheng=new Intent(RelaxActivity.this,RenSheng.class);
                startService(intentrensheng);
                if (pager.getCurrentItem()==0){
                    i=new Intent(RelaxActivity.this,yutian.class);
                    startService(i);
                }else if(pager.getCurrentItem()==1){
                    i=new Intent(RelaxActivity.this,mingxiang.class);
                    startService(i);
                }else if(pager.getCurrentItem()==2){
                    i=new Intent(RelaxActivity.this,leiyu.class);
                    startService(i);
                }else if(pager.getCurrentItem()==3){
                    i=new Intent(RelaxActivity.this,haiyang.class);
                    startService(i);
                }


                break;

            case R.id.top_left:

                Intent i1=new Intent( RelaxActivity.this,yutian.class );
                stopService( i1 );
                Intent i2=new Intent( RelaxActivity.this,mingxiang.class );
                stopService( i2);
                Intent i3=new Intent( RelaxActivity.this,leiyu.class );
                stopService( i3 );
                Intent i4=new Intent( RelaxActivity.this,haiyang.class );
                stopService( i4 );

                Intent intent = new Intent().setClass(RelaxActivity.this, IndexActivity.class);
                startActivity(intent);

                break;
            case R.id.top_right:
                if (flag) {
                    topright.setImageResource(image[0]);
                    flag = false;
                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService( Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,false);
                } else {
                    topright.setImageResource(image[1]);
                    flag = true;
                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,true); //静音模式
                }
                break;


        }

    }

}
