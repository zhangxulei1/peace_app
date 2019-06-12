package cn.edu.hebtu.software.peace.focusTotal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.IndexActivity;
import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.util.MusicService1;
import cn.edu.hebtu.software.peace.util.MusicService2;
import cn.edu.hebtu.software.peace.util.MusicService3;
import cn.edu.hebtu.software.peace.util.MusicService4;
import cn.edu.hebtu.software.peace.util.SelectMinute_PickView;
import cn.edu.hebtu.software.peace.util.TipHelper;
import cn.edu.hebtu.software.peace.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;


public class NoticeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager pager;
    private ViewPager pager2;
    //声明ViewPager的适配器
    private PagerAdapter mAdpater;
    //用于装载四个Tab的List
    private List<View> mTabs = new ArrayList<View>();
    private List<View> mTabs2 = new ArrayList<View>();
    private ImageView item1;
    private ImageView item2;
    private ImageView item3;
    private ImageView item4;
    //页面滑动时，上方中间图形变换
    private LinearLayout topcenter;
    private LinearLayout topcenterpoint;
    private ImageView topcenterimg;
    //下方按钮
    private LinearLayout noticestart;
    private Button noticepause;
    private LinearLayout noticemiddle;
    private Button noticegoon;
    private Button noticestop;
    //左上方叉号退出
    private ImageView topleft;
    //右上方太阳，点击静音，变成月亮
    private ImageView topright = null;
    private boolean flag = false;
    private int[] image = {R.drawable.sound,//太阳
            R.drawable.silent//月亮
    };


    //中间圆圈变换
    private RelativeLayout before;
    private RelativeLayout later;
    //圆圈内文字，时间齿轮
    private RelativeLayout middleText;
    private RelativeLayout selectTime;
    private TextView minutes;
    private String[] minStr = {"30"};
    private TextView second;
    private SelectMinute_PickView minute_pv;
    private TextView minutes2;
    private TextView second2;
    //背景图片颜色
    private ImageView backColor1;
    private ImageView backColor2;
    private ImageView backColor3;
    private ImageView backColor4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_main);

        //上方中间圆点
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item1.setImageResource(R.drawable.welcome_circle_later);
        //初始化控件
        initViews();
        //初始化事件
        initEventsbefore();
        initEventslater();
        //默认选中第一个Tab
        pager.setCurrentItem(0);
        pager2.setCurrentItem(0);
        //初始化数据
        initDatebefore();
        initDatelater();
        //绑定监听器
        noticestart.setOnClickListener(this);
        noticepause.setOnClickListener(this);
        noticegoon.setOnClickListener(this);
        noticestop.setOnClickListener(this);
        topleft.setOnClickListener(this);
        topright.setOnClickListener(this);
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
        minute_pv.setOnSelectListener(new SelectMinute_PickView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                minStr[0] = text;
            }
        });
        middleText.setOnClickListener(this);
        selectTime.setOnClickListener(this);



    }


    //初始化事件一
    private void initEventsbefore() {
        //添加ViewPager的切换Tab的监听事件
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = pager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        item1.setImageResource(R.drawable.welcome_circle_later);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle);
                        backColor1.setVisibility(View.VISIBLE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.GONE);
                        break;
                    case 1:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle_later);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle);
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.VISIBLE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.GONE);
                        break;
                    case 2:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle_later);
                        item4.setImageResource(R.drawable.welcome_circle);
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.VISIBLE);
                        backColor4.setVisibility(View.GONE);
                        break;
                    case 3:
                        item1.setImageResource(R.drawable.welcome_circle);
                        item2.setImageResource(R.drawable.welcome_circle);
                        item3.setImageResource(R.drawable.welcome_circle);
                        item4.setImageResource(R.drawable.welcome_circle_later);
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.VISIBLE);
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

    //初始化事件二
    private void initEventslater() {
        //添加ViewPager的切换Tab的监听事件
        pager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = pager2.getCurrentItem();
                final Intent[] i = new Intent[4];
                i[0] = new Intent(NoticeActivity.this, MusicService1.class);
                i[1] = new Intent(NoticeActivity.this, MusicService2.class);
                i[2] = new Intent(NoticeActivity.this, MusicService3.class);
                i[3] = new Intent(NoticeActivity.this, MusicService4.class);
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
                        backColor1.setVisibility(View.VISIBLE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.GONE);
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
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.VISIBLE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.GONE);
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
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.VISIBLE);
                        backColor4.setVisibility(View.GONE);
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
                        backColor1.setVisibility(View.GONE);
                        backColor2.setVisibility(View.GONE);
                        backColor3.setVisibility(View.GONE);
                        backColor4.setVisibility(View.VISIBLE);
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


    //进行延时操作
    class MyAsyncTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
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

    //初始化数据一
    private void initDatebefore() {
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

    }

    //初始化数据二,
    private void initDatelater() {
        //初始化ViewPager的适配器
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs2.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs2.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs2.get(position));
            }
        };
        //设置ViewPager的适配器
        pager2.setAdapter(mAdpater);
    }


    //初始化控件
    private void initViews() {
        pager = findViewById(R.id.viewPager);

        pager2 = findViewById(R.id.viewPager2);
        //获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View test1 = inflater.inflate(R.layout.notice_item1, null);
        View test2 = inflater.inflate(R.layout.notice_item2, null);
        View test3 = inflater.inflate(R.layout.notice_item3, null);
        View test4 = inflater.inflate(R.layout.notice_item4, null);
        View test5 = inflater.inflate(R.layout.notice_later_item1, null);
        View test6 = inflater.inflate(R.layout.notice_later_item2, null);
        View test7 = inflater.inflate(R.layout.notice_later_item3, null);
        View test8 = inflater.inflate(R.layout.notice_later_item4, null);
        //将四个Tab添加到集合中
        mTabs.add(test1);
        mTabs.add(test2);
        mTabs.add(test3);
        mTabs.add(test4);
        mTabs2.add(test5);
        mTabs2.add(test6);
        mTabs2.add(test7);
        mTabs2.add(test8);

        topcenter = findViewById(R.id.top_center);
        topcenterpoint = findViewById(R.id.top_center_point);
        topcenterimg = findViewById(R.id.top_center_img);

        noticestart = findViewById(R.id.notice_start);
        noticepause = findViewById(R.id.notice_pause);
        noticemiddle = findViewById(R.id.notice_middle);
        noticegoon = findViewById(R.id.notice_goon);
        noticestop = findViewById(R.id.notice_stop);

        topleft = findViewById(R.id.top_left);
        topright = findViewById(R.id.top_right);
        topright.setImageResource(image[0]);


        minute_pv = findViewById(R.id.minute_pv);
        middleText = findViewById(R.id.middle_text);
        selectTime = findViewById(R.id.select_minute);
        minutes = findViewById(R.id.minutes);
        second = findViewById(R.id.second);

        minutes2 = findViewById(R.id.minutes2);
        second2 = findViewById(R.id.second2);


        before = findViewById(R.id.before);
        later = findViewById(R.id.later);

        backColor1 = findViewById(R.id.backColor1);
        backColor2 = findViewById(R.id.backColor2);
        backColor3 = findViewById(R.id.backColor3);
        backColor4 = findViewById(R.id.backColor4);

    }


    //点击事件
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        final Intent[] i = new Intent[4];
        Handler handler = new Handler();
        minutes2.setText(minutes.getText());
        second2.setText(second.getText());
        String m = (String) minutes2.getText();
        final Long[] time = {Long.parseLong(m) * 60};
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                time[0]--;
                String formatLongToTimeStr = formatLongToTimeStr(time[0]);
                String[] split = formatLongToTimeStr.split("：");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        minutes2.setText(split[1]);
                        int m = Integer.parseInt((minutes2.getText()).toString());
                        if (m < 10) {
                            minutes2.setText("0" + split[1]);
                        }
                    }
                    if (i == 1) {
                        second2.setText(split[2]);
                        int s = Integer.parseInt((second2.getText()).toString());
                        if (s < 10) {
                            second2.setText("0" + split[2]);
                        }
                    }
                }
                if (time[0] > 0) {
                    handler.postDelayed(this, 1000);
                }
                if (Integer.parseInt(split[2]) == 0 && Integer.parseInt(split[1]) == 0) {
                    int s = pager2.getCurrentItem();
                    before.setVisibility(View.VISIBLE);
                    later.setVisibility(View.GONE);
                    noticestart.setVisibility(View.VISIBLE);
                    noticepause.setVisibility(View.GONE);
                    noticemiddle.setVisibility(View.GONE);
                    TipHelper.Vibrate(NoticeActivity.this, 1000);
                    stopService(i[s]);
                }
            }
        };
        switch (v.getId()) {
            case R.id.notice_start:
                //点击开始按钮，开始倒计时，播放音乐
                noticestart.setVisibility(View.GONE);
                noticepause.setVisibility(View.VISIBLE);
                setWave();
                before.setVisibility(View.GONE);
                later.setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 1000);
                pager2.setCurrentItem(pager.getCurrentItem());
                if (pager2.getCurrentItem() == 0) {
                    i[0] = new Intent(NoticeActivity.this, MusicService1.class);
                    startService(i[0]);
                } else if (pager2.getCurrentItem() == 1) {
                    i[1] = new Intent(NoticeActivity.this, MusicService2.class);
                    startService(i[1]);
                } else if (pager2.getCurrentItem() == 2) {
                    i[2] = new Intent(NoticeActivity.this, MusicService3.class);
                    startService(i[2]);
                } else if (pager2.getCurrentItem() == 3) {
                    i[3] = new Intent(NoticeActivity.this, MusicService4.class);
                    startService(i[3]);
                }
                noticepause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.removeCallbacks(runnable);
                        noticepause.setVisibility(View.GONE);
                        noticemiddle.setVisibility(View.VISIBLE);
                        stopWave();
                        if (pager2.getCurrentItem() == 0) {
                            i[0] = new Intent(NoticeActivity.this, MusicService1.class);
                            stopService(i[0]);
                        } else if (pager2.getCurrentItem() == 1) {
                            i[1] = new Intent(NoticeActivity.this, MusicService2.class);
                            stopService(i[1]);
                        } else if (pager2.getCurrentItem() == 2) {
                            i[2] = new Intent(NoticeActivity.this, MusicService3.class);
                            stopService(i[2]);
                        } else if (pager2.getCurrentItem() == 3) {
                            i[3] = new Intent(NoticeActivity.this, MusicService4.class);
                            stopService(i[3]);
                        }
                    }
                });
                noticegoon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.postDelayed(runnable, 1000);
                        noticemiddle.setVisibility(View.GONE);
                        noticepause.setVisibility(View.VISIBLE);
                        setWave();
                        if (pager2.getCurrentItem() == 0) {
                            i[0] = new Intent(NoticeActivity.this, MusicService1.class);
                            startService(i[0]);
                        } else if (pager2.getCurrentItem() == 1) {
                            i[1] = new Intent(NoticeActivity.this, MusicService2.class);
                            startService(i[1]);
                        } else if (pager2.getCurrentItem() == 2) {
                            i[2] = new Intent(NoticeActivity.this, MusicService3.class);
                            startService(i[2]);
                        } else if (pager2.getCurrentItem() == 3) {
                            i[3] = new Intent(NoticeActivity.this, MusicService4.class);
                            startService(i[3]);
                        }
                    }
                });
                break;
            case R.id.notice_stop:
                noticemiddle.setVisibility(View.GONE);
                noticestart.setVisibility(View.VISIBLE);
                before.setVisibility(View.VISIBLE);
                later.setVisibility(View.GONE);
                if (pager2.getCurrentItem() == 0) {
                    i[0] = new Intent(NoticeActivity.this, MusicService1.class);
                    stopService(i[0]);
                } else if (pager2.getCurrentItem() == 1) {
                    i[1] = new Intent(NoticeActivity.this, MusicService2.class);
                    stopService(i[1]);
                } else if (pager2.getCurrentItem() == 2) {
                    i[2] = new Intent(NoticeActivity.this, MusicService3.class);
                    stopService(i[2]);
                } else if (pager2.getCurrentItem() == 3) {
                    i[3] = new Intent(NoticeActivity.this, MusicService4.class);
                    stopService(i[3]);
                }
                pager.setCurrentItem(pager2.getCurrentItem());
                break;
            case R.id.top_left:
                Intent i1=new Intent( NoticeActivity.this,MusicService1.class );
                stopService( i1 );
                Intent i2=new Intent( NoticeActivity.this,MusicService2.class );
                stopService( i2);
                Intent i3=new Intent( NoticeActivity.this,MusicService3.class );
                stopService( i3 );
                Intent i4=new Intent( NoticeActivity.this,MusicService4.class );
                stopService( i4 );

                intent.setClass(NoticeActivity.this, IndexActivity.class);
                startActivity(intent);
                break;
            case R.id.top_right:
                if (flag) {
                    topright.setImageResource(image[0]);
                    flag = false;
                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,false);
                } else {
                    topright.setImageResource(image[1]);
                    flag = true;
                    AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC,true); //静音模式
                }



                break;

            case R.id.middle_text:
                middleText.setVisibility(View.GONE);
                selectTime.setVisibility(View.VISIBLE);
                Handler handler3 = new Handler();
                handler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        middleText.setVisibility(View.VISIBLE);
                        selectTime.setVisibility(View.GONE);
                        minutes.setText(minStr[0]);
                        second.setText("00");
                    }
                }, 3000);
                break;
            case R.id.select_minute:
                middleText.setVisibility(View.VISIBLE);
                selectTime.setVisibility(View.GONE);
                minutes.setText(minStr[0]);
                second.setText("00");
                break;
        }
    }

    //开始水波纹
    private void setWave() {
        final ViewUtil viewUtil;
        viewUtil = findViewById(R.id.wave);
        viewUtil.setInitialRadius(200);
        viewUtil.setDuration(10000);
        viewUtil.setStyle(Paint.Style.STROKE);
        viewUtil.setColor(Color.parseColor("#ffffff"));
        viewUtil.setInterpolator(new LinearOutSlowInInterpolator());
        viewUtil.start();
    }

    //停止水波纹
    private void stopWave() {
        final ViewUtil viewUtil;
        viewUtil = findViewById(R.id.wave);
        viewUtil.setInitialRadius(200);
        viewUtil.setDuration(10000);
        viewUtil.setStyle(Paint.Style.STROKE);
        viewUtil.setColor(Color.parseColor("#ffffff"));
        viewUtil.setInterpolator(new LinearOutSlowInInterpolator());
        viewUtil.stop();
    }

    //倒计时
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue();
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;
    }

}
