package cn.edu.hebtu.software.peace.focusTotal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.peace.R;

public class problem extends Activity {
    private ListView lv_problem;
    private List<String> problemlist;
    private MyAdapter1 myAdapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_problem);

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title_problem);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        lv_problem=findViewById(R.id.lv_problem);
        problemlist=new ArrayList<>();
        problemlist.add("1.平靜在我的设备上无法正常运行");
        problemlist.add("2.无法正常收到专注结束的通知");
        problemlist.add("3.专注的计时器有异常");
        problemlist.add("4.声音播放的问题");
        problemlist.add("5.为什么已经入'放松'声音就自动播放？");
        problemlist.add("6.为什么语言设置为日语，冥想页面显示为英语？");
        problemlist.add("7.如何获取日帖的壁纸原图？");
        problemlist.add("8.平靜的电池使用情况如何？");
        problemlist.add("9.睡眠质量是如何计算的？");
        problemlist.add("10.注册平靜账号对我有什么帮助？");
        problemlist.add("11.同一账号在不同设备上登录平靜时如何显示？");
        problemlist.add("12.加入会员计划可以享受什么?");
        myAdapter1=new MyAdapter1();
        lv_problem.setAdapter(myAdapter1);

        lv_problem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position){
                    case 0:
                        i=new Intent(problem.this,problem_yunxing.class);
                        startActivity(i);
                        break;
                    case 1:
                        i=new Intent(problem.this,problem_two.class);
                        startActivity(i);
                        break;
                    case 2:
                        i=new Intent(problem.this,problem_three.class);
                        startActivity(i);
                        break;
                    case 3:
                        i=new Intent(problem.this,problem_four.class);
                        startActivity(i);
                        break;
                        default:
                            i=new Intent(problem.this,problem_yunxing.class);
                            startActivity(i);
                            break;
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    class MyAdapter1 extends BaseAdapter {
        @Override
        public int getCount() {
            return problemlist.size();
        }

        @Override
        public Object getItem(int position) {
            return problemlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView ==null){
                convertView=LayoutInflater.from(problem.this).inflate(R.layout.my_adapter_item_problem,null);
                holder=new Holder();
                holder.tv=convertView.findViewById(R.id.tv_problem);
                convertView.setTag(holder);

            }else{
                holder=(Holder) convertView.getTag();
            }
            holder.tv.setText(problemlist.get(position));
            return convertView;
        }
        class Holder{
            TextView tv;
        }
    }
}
