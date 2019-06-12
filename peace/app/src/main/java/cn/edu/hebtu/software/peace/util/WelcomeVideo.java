package cn.edu.hebtu.software.peace.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.VideoView;

public class WelcomeVideo extends VideoView{
    public WelcomeVideo(Context context) {
        super(context);
    }
    public WelcomeVideo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WelcomeVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {//这里重写onMeasure的方法
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);//得到默认的大小（0，宽度测量规范）
        int height = getDefaultSize(0, heightMeasureSpec);//得到默认的大小（0，高度度测量规范）
        setMeasuredDimension(width, height); //设置测量尺寸,将高和宽放进去
    }
    @Override
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener l) {
        super.setOnPreparedListener(l);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


}
