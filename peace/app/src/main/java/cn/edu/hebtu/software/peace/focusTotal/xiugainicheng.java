package cn.edu.hebtu.software.peace.focusTotal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.hebtu.software.peace.R;


public class xiugainicheng extends Activity {
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiugainicheng);

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView( R.layout.actionbar_title_xiugainicheng);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        Intent i=getIntent();
        editText=findViewById(R.id.nicheng);
        String nicheng=i.getStringExtra("nicheng");
        if(!">".equals(nicheng)){
        editText.setText(nicheng);
        }
        button=findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                String nicheng=i.getStringExtra("nicheng");
                String result=editText.getText().toString();

                if (result!=null){
                i.putExtra("result",result);}
                else{
                    i.putExtra("result",nicheng);
                }
                setResult(7,i);
                finish();
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
