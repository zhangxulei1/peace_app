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

import cn.edu.hebtu.software.peace.IndexActivity;
import cn.edu.hebtu.software.peace.R;

public class SettingActivity extends Activity {

    private ListView lv;
    private List<String> list;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_setting);

        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        list.add("个人资料");
        list.add("账号与安全");
        list.add("");
        list.add("常见问题");
        list.add("关于");
        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);
        tiaozhuan();

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(SettingActivity.this, IndexActivity.class);
                startActivity(intent);
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=LayoutInflater.from(SettingActivity.this);
            View view=inflater.inflate(R.layout.my_adapter_item,null);
            TextView textView=view.findViewById(R.id.myadater_tv);
            textView.setText(list.get(position));
            return view;
        }
    }
    private void tiaozhuan() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i;
                switch (position) {
                    case 0:
                        i = new Intent(SettingActivity.this, gerenziliao.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(SettingActivity.this, safe.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(SettingActivity.this, problem.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(SettingActivity.this, about.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }


}
