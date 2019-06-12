package cn.edu.hebtu.software.peace.focusTotal;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.edu.hebtu.software.peace.util.CalendarView;
import cn.edu.hebtu.software.peace.util.CircleImageView;
import cn.edu.hebtu.software.peace.util.RoundTools;
import cn.edu.hebtu.software.peace.util.WheelView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cn.edu.hebtu.software.peace.R;

public class gerenziliao extends Activity {
    private String name;
    private TextView date;
    private String selectText="";
    private TextView gender;
    private ArrayList<String> genderList=new ArrayList<>();
    private CircleImageView iv_head;
    private RoundTools roundTools;
    private Intent intent;
    private int SELECT_PICTURE = 0x00;
    private int SELECT_CAMER = 0x01;
    private Bitmap bitmap;
    private Button btn_choose;
    private TextView nicheng;
    private CalendarView calendarView1,calendarView2,calendarView3;
    private String years="2019",months="5",days="11";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenziliao);

        btn_choose = findViewById( R.id.btn_choose);
        nicheng = findViewById(R.id.text_gerenziliao_nicheng);
        iv_head=findViewById(R.id.iv_head);
        date=findViewById(R.id.text_gerenziliao_date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCalendar();
            }
        });

        ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.actionbar_title_gerenziliao);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //返回键的设置 退出程序
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupwindow();
            }
        });

        nicheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(gerenziliao.this, xiugainicheng.class);
                i.putExtra("nicheng", nicheng.getText().toString());
                startActivityForResult(i, 6);
            }
        });
        initView();

        initData();

        initListeners();
    }

    private void myCalendar() {
        //初始化对话框             R.style.CalendarDialog 是自定义的弹框主题，在styles设置
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //初始化自定义布局参数
        LayoutInflater layoutInflater = getLayoutInflater();
        //绑定布局
        View customLayout = layoutInflater.inflate(R.layout.view_slide_calendar, (ViewGroup) findViewById(R.id.customDialog));
        //为对话框设置视图
        builder.setView(customLayout);

        //加载年月日的三个 CalendarView 的 id
        calendarView1 = (CalendarView) customLayout.findViewById(R.id.year);
        calendarView2 = (CalendarView) customLayout.findViewById(R.id.month);
        calendarView3 = (CalendarView) customLayout.findViewById(R.id.day);

        //定义滚动选择器的数据项（年月日的）
        ArrayList<String> gradeYear = new ArrayList<>();
        ArrayList<String> gradeMonth = new ArrayList<>();
        ArrayList<String> gradeDay = new ArrayList<>();

        //为数据项赋值
        int thisYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new java.util.Date()));
        for(int i=1980;i<=thisYear;i++) //从1980到今年
            gradeYear.add(i + "");
        for(int i=1;i<=12;i++)            // 1月到12月
            gradeMonth.add(i + "");
        for(int i=1;i<=31;i++)           // 1日到31日
            gradeDay.add(i + "");

        //为滚动选择器设置数据
        calendarView1.setData(gradeYear);
        calendarView2.setData(gradeMonth);
        calendarView3.setData(gradeDay);

        //滚动选择事件
        calendarView1.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                years = data;
            }
        });
        calendarView2.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                months = data;
            }
        });
        calendarView3.setOnSelectListener(new CalendarView.onSelectListener() {
            @Override
            public void onSelect(String data) {
                days = data;
            }
        });

        //对话框的确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                date.setText(years + " 年 "+ months + " 月 " + days + " 日 ");

            }
        });
        //对话框的取消按钮
        builder.setNegativeButton("取消", null);
        //显示对话框
        builder.show();


    }

    private void initListeners() {
        findViewById(R.id.text_gerenziliao_gender).setOnClickListener(View ->showDialog(gender,genderList,2));
    }

    private void initData() {
        genderList.add("男");
        genderList.add("女");
        genderList.add("保密");
    }

    private void initView() {
        gender=findViewById(R.id.text_gerenziliao_gender);
    }

    private void showDialog(TextView textView, ArrayList<String> list, int selected){
        showChoiceDialog(list, textView, selected,
                new WheelView.OnWheelViewListener() {
                    @Override
                    public void onSelected(int selectedIndex, String item) {
                        selectText = item;
                    }
                });
    }

    private void showChoiceDialog(ArrayList<String> dataList,final TextView textView,int selected,
                                  WheelView.OnWheelViewListener listener){
        selectText = "";
        View outerView = LayoutInflater.from(this).inflate(R.layout.dialog_wheelview,null);
        final WheelView wheelView = outerView.findViewById(R.id.wheel_view);
        wheelView.setOffset(2);// 对话框中当前项上面和下面的项数
        wheelView.setItems(dataList);// 设置数据源
        wheelView.setSeletion(selected);// 默认选中第三项
        wheelView.setOnWheelViewListener(listener);

        // 显示对话框，点击确认后将所选项的值显示到Button上
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(outerView)
                .setPositiveButton("确认",
                        (dialogInterface, i) -> {
                            textView.setText(selectText);
                            textView.setTextColor(this.getResources().getColor(R.color.color_333333));
                        })
                .setNegativeButton("取消",null).create();
        alertDialog.show();
        int green = this.getResources().getColor(R.color.green);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(green);
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(green);
    }


    private void showPopupwindow() {
        View parent = ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(this, R.layout.popwindow_avater, null);

        Button btnCamera = (Button) popView.findViewById(R.id.btn_camera_pop_camera);
        Button btnAlbum = (Button) popView.findViewById(R.id.btn_camera_pop_album);
        Button btnCancel = (Button) popView.findViewById(R.id.btn_camera_pop_cancel);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setAnimationStyle(R.style.popwin_anim_style);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);// 设置允许在外点击消失

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_camera_pop_camera:
                        //通过相机拍摄照片
                        Intent intentCamer = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intentCamer, SELECT_CAMER);

                        break;
                    case R.id.btn_camera_pop_album:
                        //通过相册选择图片
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                        break;
                    case R.id.btn_camera_pop_cancel:

                        break;
                }
                popWindow.dismiss();
            }
        };

        btnCamera.setOnClickListener(listener);
        btnAlbum.setOnClickListener(listener);
        btnCancel.setOnClickListener(listener);

        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==7){
            String result=data.getStringExtra("result");
            nicheng.setText(result);
        }
        if (requestCode == SELECT_PICTURE) {
            Log.i("TAG", "相册");
            handle(resultCode, data);
            roundTools = new RoundTools();
            Bitmap roundAlbum = roundTools.toRoundBitmap(bitmap);
            iv_head.setImageBitmap(roundAlbum);
        } else if (requestCode == SELECT_CAMER) {
            Log.i("TAG", "相机");
            if (data.getData() == null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                Log.i("TAG", "BitData    " + bitmap);
                roundTools = new RoundTools();
                Bitmap roundCamer = roundTools.toRoundBitmap(bitmap);
                iv_head.setImageBitmap(roundCamer);
            } else try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                if (bitmap != bitmap) {//主要是防止handle处理出错，就会将先前获取相册的照片show出来
                    iv_head.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 数据处理 共同点提取
     *
     * @param resultCode
     * @param data
     */
    private void handle(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {//结果代码是Ok的
            Uri uri = data.getData();
            if (uri != null && data.getData() != null) {
                Log.i("TAG", "uri 和 data.getData()不为空");
                ContentResolver contentResolver = this.getContentResolver();
                if (bitmap != null) {
                    bitmap.recycle();
                }
                try {
                    bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));//出错
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Log.i("TAG", "uri为空或者data为空   " + "数据：" + data.getData() + "  uri: " + uri);
            }
        }
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
