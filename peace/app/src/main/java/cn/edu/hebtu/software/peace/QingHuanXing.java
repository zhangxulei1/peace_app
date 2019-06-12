package cn.edu.hebtu.software.peace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.edu.hebtu.software.peace.entity.MessageEvent;

public class QingHuanXing extends Activity implements View.OnClickListener {
    private Boolean flag=true;
    private TextView time;
    private ImageView back;
    private ImageView start_light;
    private LinearLayout awakescene;
    private LinearLayout awaketime;
    private TextView scene_title;
    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_awakening);

        s="关闭";
        initView();


        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra( "scene",s );
                    setResult( 25 ,intent);
                    finish();
            }
        } );

        start_light.setOnClickListener(this);
        awakescene.setOnClickListener(this);
        awaketime.setOnClickListener(this);

    }

    private void initView() {
        back=findViewById(R.id.back);
        start_light=findViewById(R.id.start_light);
        awakescene=findViewById(R.id.awakescene);
        awaketime=findViewById(R.id.awaketime);
        time=findViewById(R.id.time);
        scene_title=findViewById(R.id.scene_title);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
//            case R.id.back:
//                //
//                Intent intent1 = new Intent();
//                if (s!=null){
//                    intent1.putExtra( "scene",s );
//                    setResult( 25 ,intent1);}
//                    finish();
//                break;
            case R.id.start_light:
                if (flag==true) {
                    flag=false;
                    start_light.setImageResource(R.drawable.circle_selected);
                    awakescene.setVisibility(View.VISIBLE);
                    awaketime.setVisibility(View.VISIBLE);
                    s="默认场景";
                }else {
                    flag=true;
                    start_light.setImageResource(R.drawable.circle_select);
                    awaketime.setVisibility(View.GONE);
                    awakescene.setVisibility(View.GONE);
                }
                break;
            case R.id.awakescene:
                if(!EventBus.getDefault().isRegistered(QingHuanXing.this)) {
                    EventBus.getDefault().register(QingHuanXing.this);

                    Intent intent = new Intent(getApplicationContext(), WakeSpotActivity.class);
                    startActivity(intent);

                }
                //换场景
                break;
            case R.id.awaketime:
                i=new Intent(QingHuanXing.this,WakeTimeActivity.class);
                String t=time.getText().toString();
                i.putExtra("t",t);
                startActivityForResult(i,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==2){
            String ti= String.valueOf(data.getIntExtra("time",0));
            time.setText(ti+" 分钟");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onClickMsg(MessageEvent messageEvent){
        scene_title.setText(messageEvent.getMsg());
        s= (String) scene_title.getText();
    }
}
