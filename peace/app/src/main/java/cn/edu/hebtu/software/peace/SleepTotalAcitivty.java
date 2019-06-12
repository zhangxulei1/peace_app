package cn.edu.hebtu.software.peace;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.hebtu.software.peace.focusTotal.TestActivity;
import cn.edu.hebtu.software.peace.focusTotal.WakeUpActivity;
import cn.edu.hebtu.software.peace.fragments.AlarmFragment;
import cn.edu.hebtu.software.peace.fragments.RespiteFragment;

public class SleepTotalAcitivty extends AppCompatActivity implements AlarmFragment.CallBackValue,RespiteFragment.RespiteCallBackValue{


    private List<String> respiteList;
    private List<String> alarmList;
    private TextView[] textViews;
    private ViewPager viewPager;
    private Fragment[] fragments;
    private Button buttonSave;
    private String alarmHourValue, alarmMintueValue;
    private String resultDataMusicName;
    private String englishMusic;
    private String changeCount;
    private String changeWord;
    private String wakeupWord;
    private String wakeupSendWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sleep_total_layout );
        buttonSave = findViewById( R.id.save_all_time );
        changeWord="无任务";
        wakeupWord="关闭";
        wakeupSendWord="guanbi";
        englishMusic="chenshi";

        init();
        viewPager.setAdapter( new MyAdapter( getSupportFragmentManager() ) );
        getVisibleFragment();
        viewPager.setCurrentItem( 0 );

        buttonSave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPager.getCurrentItem();
                Intent intent=new Intent();
                intent.putExtra( "englishMusic",englishMusic );
                Log.e( "sleepTotalActivity名字", englishMusic+"名字");
                if (current == 0) {
//                    String alarmHour = alarmList.get(0);
//                    String alarmMintue = alarmList.get(1);
//                    String alarmTime = alarmHour+":"+alarmMintue;
                    intent.putExtra( "awake", (Serializable) alarmList);
                    intent.putExtra( "wakeupWord",wakeupSendWord );
                    Log.e( "sleeptotal中的send",wakeupSendWord+"值" );
                    if(changeWord.equals( "摇一摇" )) {
                        intent.putExtra("changeCount", changeCount );
                    }else{
                        intent.putExtra("changeCount", "notask"  );
                    }
                    Log.e( "sletatol摇一摇",changeCount+"值" );
//                    intent.setClass( SleepTotalAcitivty.this, SleepActivity.class );
//                    startActivity( intent );
                    setResult(2, intent);
                }
                else if(current==1){
//                    String  respiteMinute = respiteList.get(0);
                    intent.putExtra( "awake", (Serializable) respiteList);
//                    intent.setClass( SleepTotalAcitivty.this, SleepActivity.class );
//                    startActivity( intent );
                    setResult(3, intent);
                }
                finish();
            }
        } );
    }

    public void init() {
        viewPager = findViewById( R.id.view_pager );
        LinearLayout linearLayout = findViewById( R.id.dot_layout );
        int count = linearLayout.getChildCount();
        textViews = new TextView[count];
        fragments = new Fragment[count];
        fragments[0] = new AlarmFragment();
        fragments[1] = new RespiteFragment();

        viewPager.setOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedImage( position );
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );

        for (int i = 0; i < count; i++) {
            textViews[i] = (TextView) linearLayout.getChildAt( i );
            textViews[i].setEnabled( true );
            textViews[i].setTag( i );
            textViews[i].setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item = (int) v.getTag();
                    viewPager.setCurrentItem( item );
                    selectedImage( item );
                }
            } );
        }
    }

    private void selectedImage(int item) {
        //这一步从使能到不使能是状态发生了变化，只要状态发生了变化，那么就会简介调用dot里面的select
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setEnabled( true );
        }
        textViews[item].setEnabled( false );
    }




    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super( fm );
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = SleepTotalAcitivty.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) return fragment;
            Log.e( "fragment", String.valueOf( fragment ) );
        }
        return null;
    }


    @Override
    public void SendMessageValue(List<String> data) {
        alarmList = new ArrayList<>( 2 );
        alarmList = data;
    }

    @Override
    public void SendRespiteValue(List<String> data) {
        respiteList = new ArrayList<>( 1 );
        respiteList = data;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView firstCardText;
        firstCardText = findViewById(R.id.first_card_alarm);



        if (requestCode == 12 && resultCode == 24) {
            resultDataMusicName= data.getStringExtra("yinyue");
            firstCardText.setText(resultDataMusicName);
            Log.e("收到没啦", resultDataMusicName);
        } else {
            firstCardText.setText("晨诗");
        }
        getMusic();

        if (requestCode == 13 && resultCode == 25){
            //起床挑战
            final TextView twoCardText;
            twoCardText = findViewById( R.id.two_card_wakeup );
            wakeupWord = data.getStringExtra( "scene" );
            twoCardText.setText( wakeupWord );
            Log.e( "toatl里面",wakeupWord );
        }
        getwakeupSend();

        if (requestCode == 14 && resultCode == 26){
            //起床挑战
            final TextView threeCardText;
            threeCardText = findViewById( R.id.tv_three_card_text );

            changeWord = data.getStringExtra( "changeWord" );
            changeCount = data.getStringExtra( "changeCount" );
            threeCardText.setText( changeWord );

        }
        if (requestCode == 15 && resultCode == 27){
            //起床挑战
            final TextView fourCardText;
            fourCardText = findViewById( R.id.four_card_jiuqitixing );
            String jiuqin = "关闭";
            jiuqin = data.getStringExtra( "jiuqin" );
            fourCardText.setText( jiuqin );

        }


    }


    //    写一个公共方法，让AlarmActivity中获取到当前的值；
    public  void getMusic() {
        List<Map<Object, String>> list = new ArrayList<>();
        Map<Object, String> map0 = new HashMap<>();
        map0.put( "晨诗", "chenshi" );
        Map<Object, String> map1 = new HashMap<>();
        map1.put( "星光", "xingguang" );
        Map<Object, String> map2 = new HashMap<>();
        map2.put( "晓音", "xiaoyin" );
        Map<Object, String> map3 = new HashMap<>();
        map3.put( "浮光", "chenshi" );
        Map<Object, String> map4 = new HashMap<>();
        map4.put( "宿雨", "suyu" );
        Map<Object, String> map5 = new HashMap<>();
        map5.put( "秘境", "mijing" );
        Map<Object, String> map6 = new HashMap<>();
        map6.put( "荷风", "chenshi" );
        Map<Object, String> map7 = new HashMap<>();
        map7.put( "落霞", "chenshi" );
        Map<Object, String> map8 = new HashMap<>();
        map8.put( "初晴", "chenshi" );
        Map<Object, String> map9 = new HashMap<>();
        map9.put( "疏影", "chenshi" );
        list.add( 0, map0 );
        list.add( 1, map1 );
        list.add( 2, map2 );
        list.add( 3, map3 );
        list.add( 4, map4 );
        list.add( 5, map5 );
        list.add( 6, map6 );
        list.add( 7, map7 );
        list.add( 8, map8 );
        list.add( 9, map9 );

        for (int i = 0; i <= 9; i++) {
            Map<Object, String> map = list.get( i );
            if (map.get( resultDataMusicName ) != null) {
                englishMusic = map.get( resultDataMusicName );
                break;
            }
            else{
                englishMusic="chenshi";
            }
        }

    }

    //    写一个公共方法，让Qinghuanxing轻唤醒中获取到当前的值；
    public  void getwakeupSend() {
        List<Map<Object, String>> list = new ArrayList<>();
        Map<Object, String> map0 = new HashMap<>();
        map0.put( "关闭", "guanbi" );
        Map<Object, String> map1 = new HashMap<>();
        map1.put( "默认场景", "a" );
        Map<Object, String> map2 = new HashMap<>();
        map2.put( "北极的极光", "b" );
        Map<Object, String> map3 = new HashMap<>();
        map3.put( "海边的曼彻斯特", "c" );
        Map<Object, String> map4 = new HashMap<>();
        map4.put( "普罗旺斯的夏天", "d" );
        Map<Object, String> map5 = new HashMap<>();
        map5.put( "托斯卡纳艳阳下", "e" );
        Map<Object, String> map6 = new HashMap<>();
        map6.put( "东京物语", "f" );

        list.add( 0, map0 );
        list.add( 1, map1 );
        list.add( 2, map2 );
        list.add( 3, map3 );
        list.add( 4, map4 );
        list.add( 5, map5 );
        list.add( 6, map6 );
        for (int i = 0; i <= 6; i++) {
            Map<Object, String> map = list.get( i );
            if (map.get( wakeupWord ) != null) {
                wakeupSendWord = map.get(wakeupWord );
                break;
            }
            else{
                wakeupSendWord="guanbi";
            }
        }

    }


}


