package cn.edu.hebtu.software.peace.focusTotal;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mob.MobSDK;

import java.util.HashMap;

import cn.edu.hebtu.software.peace.R;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class safe extends Activity {
    private TextView shouji;
    private Button button;
    private TextView weixin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_safe);
        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title_safe);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        MobSDK.init(this);

        button=findViewById(R.id.exit_zhanghao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(safe.this)
                        .setTitle("退出账号")
                        .setMessage("是否退出账号")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //退出账号
                                Intent i=new Intent(safe.this,Unlogin.class);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        }).create();
                alertDialog.show();

            }
        });
        shouji=findViewById(R.id.shouji);
        shouji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode(safe.this);
            }
        });

        weixin=findViewById(R.id.weixin);
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Intent intent=getIntent();
        String phone=intent.getStringExtra("phone");
        if (phone!=null){
        shouji.setText(phone);}
    }





    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作
                    shouji.setText(phone);
                } else{
                    // TODO 处理错误的结果
                }
            }
        });
        page.show(context);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(safe.this,SettingActivity.class);
                startActivity(intent);
                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
