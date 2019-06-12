package cn.edu.hebtu.software.peace.focusTotal;

//未登录账号的页面

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.R;


public class Unlogin extends Activity {
    private TextView btn_setting;
    private TextView btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlogin);

        ActionBar actionBar = getActionBar();
//        //google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
//        //为了让下面的背景色一致，还需要添加一行代码：
        actionBar.setCustomView( R.layout.actionbar_title_welcome_unlogin);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

//        返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        btn_setting=findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Unlogin.this,SettingActivity.class);
                startActivity(i);
            }
        });
        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Unlogin.this,Register.class);
                startActivity(i);
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
}
