package cn.edu.hebtu.software.peace.breathe;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.edu.hebtu.software.peace.IndexActivity;
import cn.edu.hebtu.software.peace.R;
import cn.edu.hebtu.software.peace.breathe.utils.SelectMinute_PickView;

public class HuxiActivity extends Activity implements View.OnClickListener{
//    private TextView t1;
//    private TextView t2;
    private TextView tvTime;
    private TextView tvTime1;
    private ViewPager pager;
    //声明ViewPager的适配器
    private PagerAdapter mAdpater;
    //用于装载四个Tab的List
    private List<View> mTabs = new ArrayList<View>();

    private ImageView item1;
    private ImageView item2;

    private LinearLayout topcenter;
    private LinearLayout topcenterpoint;
    private ImageView topcenterimg;

    private Button noticestart;
    private Button noticestop;


    private ImageView topleft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.activity_huxi);
        Toast.makeText(HuxiActivity.this, "适用于平复情绪、缓解压力", Toast.LENGTH_SHORT).show();

        item1 = findViewById( R.id.item1);
        item2 = findViewById(R.id.item2);
        item1.setImageResource(R.mipmap.welcome_circle_later);


        //初始化控件
        initViews();
        //初始化事件
        initEvents();

        //默认选中第一个Tab
        pager.setCurrentItem(0);
        //初始化数据
        initDate();

        //绑定监听器
        noticestart.setOnClickListener(this);
        noticestop.setOnClickListener(this);
        topleft.setOnClickListener(this);


    }


    //初始化事件
    private void initEvents() {
        //添加ViewPager的切换Tab的监听事件
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (pager.getCurrentItem()==0){

                    noticestart.setVisibility(View.VISIBLE);


                }else if (pager.getCurrentItem()==1){


                    noticestart.setVisibility(View.VISIBLE);

                }

            }
            @Override
            public void onPageSelected(int position) {
                //获取ViewPager的当前Tab
                int currentItem = pager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        Toast.makeText(HuxiActivity.this, "适用于平复情绪、缓解压力", Toast.LENGTH_SHORT).show();
                        item1.setImageResource(R.mipmap.welcome_circle_later);
                        item2.setImageResource(R.mipmap.welcome_circle);
                        break;
                    case 1:
                        Toast.makeText(HuxiActivity.this, "适用于放松身心、快速入眠", Toast.LENGTH_SHORT).show();
                        item1.setImageResource(R.mipmap.welcome_circle);
                        item2.setImageResource(R.mipmap.welcome_circle_later);
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
                Thread.sleep(2000); //睡
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

    //初始化数据
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
    }


    //初始化控件
    private void initViews() {
        pager = findViewById(R.id.viewPager);
        //获取到四个Tab
        LayoutInflater inflater = LayoutInflater.from(this);
        View test1 = inflater.inflate(R.layout.breathe_notice_item1, null);
        initTest1(test1);


        View test2 = inflater.inflate(R.layout.breathe_notice_item2, null);
        initTest2(test2);
        //将四个Tab添加到集合中
        mTabs.add(test1);
        mTabs.add(test2);


        topcenter = findViewById(R.id.top_center);
        topcenterpoint = findViewById(R.id.top_center_point);
        topcenterimg = findViewById(R.id.top_center_img);

        noticestart = findViewById(R.id.notice_start);
//        noticepause = findViewById(R.id.notice_pause);
//        noticemiddle = findViewById(R.id.notice_middle);
//        noticegoon = findViewById(R.id.notice_goon);
        noticestop = findViewById(R.id.notice_stop);

        topleft = findViewById(R.id.top_left);

//        t1=findViewById( R.id.t1 );
//        t2=findViewById( R.id.t2 );
    }

    private void initTest2(final View test2) {
        final RelativeLayout middleText;
        final RelativeLayout selectTime;
        final String[] strSelect = {"1"};


        SelectMinute_PickView minute_pv;
        minute_pv = test2.findViewById(R.id.minute_pv1);
        middleText = test2.findViewById(R.id.middle_text1);
        selectTime = test2.findViewById(R.id.select_minute1);
        tvTime1 = test2.findViewById(R.id.tv_time1);

        middleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.GONE);
                selectTime.setVisibility(View.VISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        middleText.setVisibility(View.VISIBLE);
                        selectTime.setVisibility(View.GONE);
                        tvTime1.setText(strSelect[0]+" 分钟");
                    }
                },2000);
            }
        });
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.VISIBLE);
                selectTime.setVisibility(View.GONE);
                tvTime1.setText(strSelect[0]+" 分钟");
            }
        });


        final List<String> seconds = new ArrayList<String>();
        for (int i = 1; i <=15; i++) {
            seconds.add(i+"");
        }
        minute_pv.setData(seconds);
        minute_pv.setOnSelectListener(new SelectMinute_PickView.onSelectListener() {
            @Override
            public void onSelect(String text) {
//                Toast.makeText(HuxiActivity.this, "选择了 " + text + " 分", Toast.LENGTH_LONG).show();
                strSelect[0] = text;
            }
        });
    }

    private void initTest1(final View test1) {
        final RelativeLayout middleText;
        final RelativeLayout selectTime;
        final String[] strSelect = {"1"};

        SelectMinute_PickView minute_pv;
        minute_pv = test1.findViewById(R.id.minute_pv);
        middleText = test1.findViewById(R.id.middle_text);
        selectTime = test1.findViewById(R.id.select_minute);
        tvTime = test1.findViewById(R.id.tv_time);

        middleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.GONE);
                selectTime.setVisibility(View.VISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        middleText.setVisibility(View.VISIBLE);
                        selectTime.setVisibility(View.GONE);
                        tvTime.setText(strSelect[0]+" 分钟");
                    }
                },2000);
            }
        });
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                middleText.setVisibility(View.VISIBLE);
                selectTime.setVisibility(View.GONE);
                tvTime.setText(strSelect[0]+" 分钟");
            }
        });


        final List<String> seconds = new ArrayList<String>();
        for (int i = 1; i <=15; i++) {
            seconds.add(i+"");
        }
        minute_pv.setData(seconds);
        minute_pv.setOnSelectListener(new SelectMinute_PickView.onSelectListener() {
            @Override
            public void onSelect(String text) {
//                Toast.makeText(HuxiActivity.this, "选择了 " + text + " 分", Toast.LENGTH_LONG).show();
                strSelect[0] = text;
            }
        });
    }

    //点击事件
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.notice_start:
                    String time=tvTime.getText().toString();
                    Pattern p=Pattern.compile("\\d+");
                    Matcher m=p.matcher(time);
                    m.find();
                    String t=m.group();

                String time1=tvTime1.getText().toString();
                Pattern p1=Pattern.compile("\\d+");
                Matcher m1=p1.matcher(time1);
                m1.find();
                String t1=m1.group();
                    if(pager.getCurrentItem()==0){
                    i=new Intent(HuxiActivity.this,SpActivity.class);
                    i.putExtra("time",t);
                    startActivity(i);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }else if(pager.getCurrentItem()==1){
                        i=new Intent(HuxiActivity.this,SpActivity1.class);
                        i.putExtra("time1",t1);
                        startActivity(i);
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }
//                noticestart.setVisibility(View.GONE);
//                noticestop.setVisibility(View.VISIBLE);
                break;
//            case R.id.notice_pause:
//                noticepause.setVisibility(View.GONE);
//                noticemiddle.setVisibility(View.VISIBLE);
//                break;
//            case R.id.notice_goon:
//                noticemiddle.setVisibility(View.GONE);
//                noticepause.setVisibility(View.VISIBLE);
//                break;
            case R.id.notice_stop:
                noticestop.setVisibility(View.GONE);
                noticestart.setVisibility(View.VISIBLE);
                break;
            case R.id.top_left:
                Intent intent = new Intent().setClass(HuxiActivity.this, IndexActivity.class);
                startActivity(intent);

                break;

        }
    }





}
