package cn.edu.hebtu.software.peace.focusTotal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.R;


public class about extends Activity {
    private TextView gongneng_introduce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView( R.layout.actionbar_title_about);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        gongneng_introduce=findViewById(R.id.gongneng_introduce);
        gongneng_introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(about.this,WelcomeActivity.class);
                startActivity(i);
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(about.this,SettingActivity.class);
                startActivity(intent);
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
