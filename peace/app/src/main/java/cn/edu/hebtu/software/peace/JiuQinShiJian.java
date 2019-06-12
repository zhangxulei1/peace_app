package cn.edu.hebtu.software.peace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class JiuQinShiJian extends Activity implements View.OnClickListener {
    private String[] string = {"一", "二", "三", "四", "五", "六", "日"};
    private List<Boolean> flag = new ArrayList<>();
    private TextView workday;
    private String s = "";
    private Boolean flag1 = true;
    private Boolean flag2 = true;
    private Boolean flag3 = true;
    private Boolean flag4 = true;
    private Boolean flag5 = true;
    private Boolean flag6 = false;
    private Boolean flag7 = false;
    private String minutes;
    private String seconds;
    private LinearLayout repeat;
    private Button btn_close;
    private ImageView btn_exit;
    private CalendarView_Jiuqintixing cv1;
    private CalendarView_Jiuqintixing cv2;
    private String jiuqin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_jiu_qin_shi_jian );
        jiuqin = "关闭";

        initView();
        initCv1();
        initCv2();

        repeat.setOnClickListener( this );
        btn_close.setOnClickListener( this );
        btn_exit.setOnClickListener( this );
    }

    private void initArray() {
        flag.add( flag1 );
        flag.add( flag2 );
        flag.add( flag3 );
        flag.add( flag4 );
        flag.add( flag5 );
        flag.add( flag6 );
        flag.add( flag7 );
    }


    private void initCv2() {
        final List<String> secods = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                secods.add( "0" + i );
            } else {
                secods.add( i + "" );
            }
        }
        cv2.setData( secods );
        cv2.setOnSelectListener( new CalendarView_Jiuqintixing.onSelectListener() {
            @Override
            public void onSelect(String text) {
                seconds = text;
            }
        } );
    }

    private void initCv1() {
        final List<String> mins = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            if (i < 10) {
                mins.add( "0" + i );
            } else {
                mins.add( i + "" );
            }
        }
        cv1.setData( mins );
        cv1.setOnSelectListener( new CalendarView_Jiuqintixing.onSelectListener() {
            @Override
            public void onSelect(String text) {
                minutes = text;
            }
        } );
    }

    private void initView() {
        cv1 = findViewById( R.id.cv1 );
        cv2 = findViewById( R.id.cv2 );
        btn_close = findViewById( R.id.btn_close );
        repeat = findViewById( R.id.repeat );
        workday = findViewById( R.id.workday );
        btn_exit = findViewById( R.id.top_left );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left:
                Intent intent = new Intent();
                intent.putExtra( "jiuqin", jiuqin );
                Log.e( "jiuqin提醒", jiuqin );
                setResult( 27, intent );
                finish();
                break;
            //关闭提醒
            case R.id.btn_close:
                showPopview1();
                break;
            //重复天数
            case R.id.repeat:
                showPopview2();
                break;
            //保存无
        }
    }


    private void showPopview2() {
        jiuqin = "开启";
        if (flag != null) {
            s = "";
            flag.clear();
        }
        View parent = ((ViewGroup) this.findViewById( android.R.id.content )).getChildAt( 0 );
        View popView = View.inflate( this, R.layout.popup2, null );

        final Button btn_queding = popView.findViewById( R.id.queding1 );
        Button btn_cancel = popView.findViewById( R.id.cancel1 );

        final Button zhouyi = popView.findViewById( R.id.zhouyi );
        final Button zhouer = popView.findViewById( R.id.zhouer );
        final Button zhousan = popView.findViewById( R.id.zhousan );
        final Button zhousi = popView.findViewById( R.id.zhousi );
        final Button zhouwu = popView.findViewById( R.id.zhouwu );
        final Button zhouliu = popView.findViewById( R.id.zhouliu );
        final Button zhouri = popView.findViewById( R.id.zhouri );

        if (flag1 == false) {
            zhouyi.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag1 == true) {
            zhouyi.setBackgroundResource( R.drawable.btn_press );
        }
        if (flag2 == false) {
            zhouer.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag2 == true) {
            zhouer.setBackgroundResource( R.drawable.btn_press );
        }

        if (flag3 == false) {
            zhousan.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag3 == true) {
            zhousan.setBackgroundResource( R.drawable.btn_press );
        }
        if (flag4 == false) {
            zhousi.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag4 == true) {
            zhousi.setBackgroundResource( R.drawable.btn_press );
        }
        if (flag5 == false) {
            zhouwu.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag5 == true) {
            zhouwu.setBackgroundResource( R.drawable.btn_press );
        }
        if (flag6 == false) {
            zhouliu.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag6 == true) {
            zhouliu.setBackgroundResource( R.drawable.btn_press );
        }
        if (flag7 == false) {
            zhouri.setBackgroundResource( R.drawable.btn_normal );
        } else if (flag7 == true) {
            zhouri.setBackgroundResource( R.drawable.btn_press );
        }
        zhouyi.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag1 == false) {
                    zhouyi.setBackgroundResource( R.drawable.btn_press );
                    flag1 = true;
                } else if (flag1 == true) {
                    zhouyi.setBackgroundResource( R.drawable.btn_normal );
                    flag1 = false;
                }
            }
        } );

        zhouer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == false) {
                    zhouer.setBackgroundResource( R.drawable.btn_press );
                    flag2 = true;
                } else if (flag2 == true) {
                    zhouer.setBackgroundResource( R.drawable.btn_normal );
                    flag2 = false;
                }
            }
        } );
        zhousan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag3 == false) {
                    zhousan.setBackgroundResource( R.drawable.btn_press );
                    flag3 = true;
                } else if (flag3 == true) {
                    zhousan.setBackgroundResource( R.drawable.btn_normal );
                    flag3 = false;
                }
            }
        } );
        zhousi.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag4 == false) {
                    zhousi.setBackgroundResource( R.drawable.btn_press );
                    flag4 = true;
                } else if (flag4 == true) {
                    zhousi.setBackgroundResource( R.drawable.btn_normal );
                    flag4 = false;
                }
            }
        } );
        zhouwu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag5 == false) {
                    zhouwu.setBackgroundResource( R.drawable.btn_press );
                    flag5 = true;
                } else if (flag5 == true) {
                    zhouwu.setBackgroundResource( R.drawable.btn_normal );
                    flag5 = false;
                }
            }
        } );
        zhouliu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag6 == false) {
                    zhouliu.setBackgroundResource( R.drawable.btn_press );
                    flag6 = true;
                } else if (flag6 == true) {
                    zhouliu.setBackgroundResource( R.drawable.btn_normal );
                    flag6 = false;
                }
            }
        } );
        zhouri.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag7 == false) {
                    zhouri.setBackgroundResource( R.drawable.btn_press );
                    flag7 = true;
                } else if (flag7 == true) {
                    zhouri.setBackgroundResource( R.drawable.btn_normal );
                    flag7 = false;
                }
            }
        } );

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow2 = new PopupWindow( popView, width, height );
        popWindow2.setAnimationStyle( R.style.popwin_anim_style );
        popWindow2.setFocusable( true );
        popWindow2.setOutsideTouchable( true );// 设置允许在外点击消失

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.queding1:
                        initArray();
                        Log.i( "array", flag.toString() );
                        for (int i = 0; i < flag.size(); i++) {
                            if (flag.get( i ) == true) {
                                s += string[i];
                            }
                        }
//                        Log.i("s",s);
                        if ((flag1 == true) && (flag2 == true) && (flag3 == true) && (flag4 == true) && (flag5 == true) && (flag6 == false) && (flag7 == false)) {
                            workday.setText( "工作日 >" );
                        } else {
                            workday.setText( s );
                        }
                        break;

                    case R.id.cancel1:

                        break;
                }
                popWindow2.dismiss();
            }
        };

        btn_cancel.setOnClickListener( listener );
        btn_queding.setOnClickListener( listener );
        //给星期设监听器

        ColorDrawable dw = new ColorDrawable( 0x30000000 );
        popWindow2.setBackgroundDrawable( dw );
        popWindow2.showAtLocation( parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0 );
        popWindow2.update();
    }

    private void showPopview1() {
        View parent = ((ViewGroup) this.findViewById( android.R.id.content )).getChildAt( 0 );
        View popView = View.inflate( this, R.layout.popup1, null );

        Button btn_queding = popView.findViewById( R.id.queding );
        Button btn_cancel = popView.findViewById( R.id.cancel );

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow1 = new PopupWindow( popView, width, height );
        popWindow1.setAnimationStyle( R.style.popwin_anim_style );

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.queding:
                        break;
                    case R.id.cancel:
                        break;
                }
                popWindow1.dismiss();
            }
        };
        btn_cancel.setOnClickListener( listener );
        btn_queding.setOnClickListener( listener );

        popWindow1.setFocusable( true );//这里必须设置为true才能点击区域外或者消失

        popWindow1.setTouchable( true );//这个控制PopupWindow内部控件的点击事件

        popWindow1.setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );

        popWindow1.setOutsideTouchable( true );
        popWindow1.showAtLocation( parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0 );
        popWindow1.update();
    }
}
