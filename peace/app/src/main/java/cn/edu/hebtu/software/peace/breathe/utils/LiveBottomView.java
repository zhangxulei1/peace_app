package cn.edu.hebtu.software.peace.breathe.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.hebtu.software.peace.R;

public class LiveBottomView extends RelativeLayout {
    private List<Integer> mList;
    private List<LayoutParams> lp;
    private Context mContext;
    private Random random = new Random();

    public LiveBottomView(Context context) {
        this(context,null);
    }

    public LiveBottomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LiveBottomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        mList = new ArrayList<>();
        lp = new ArrayList<>();
        mList.add( R.mipmap.xingxing1);
        mList.add(R.mipmap.xingxing2);
        mList.add(R.mipmap.xingxing3);
        mList.add(R.mipmap.xingxing4);
        mList.add(R.mipmap.xingxing5);
        mList.add(R.mipmap.xingxing6);
        mList.add(R.mipmap.xingxing1);
        mList.add(R.mipmap.xingxing2);
        mList.add(R.mipmap.xingxing3);
        mList.add(R.mipmap.xingxing4);
        mList.add(R.mipmap.xingxing5);
        mList.add(R.mipmap.xingxing6);
        mList.add(R.mipmap.xingxing1);
        mList.add(R.mipmap.xingxing2);
        mList.add(R.mipmap.xingxing3);
        mList.add(R.mipmap.xingxing4);
        mList.add(R.mipmap.xingxing5);
        mList.add(R.mipmap.xingxing6);
        mList.add(R.mipmap.xingxing1);
        mList.add(R.mipmap.xingxing2);
        mList.add(R.mipmap.xingxing3);
        mList.add(R.mipmap.xingxing4);
        mList.add(R.mipmap.xingxing5);
        mList.add(R.mipmap.xingxing6);
//        mList.add(R.mipmap.xiaoqiu_00000);
//        mList.add(R.mipmap.xiaoqiu_00001);
//        mList.add(R.mipmap.xiaoqiu_00002);
//        mList.add(R.mipmap.xiaoqiu_00000);
//        mList.add(R.mipmap.xiaoqiu_00001);
//        mList.add(R.mipmap.xiaoqiu_00002);
//        mList.add(R.mipmap.xiaoqiu_00000);
//        mList.add(R.mipmap.xiaoqiu_00001);
//        mList.add(R.mipmap.xiaoqiu_00002);


        lp.add(new LayoutParams(30,30));
        lp.add(new LayoutParams(60,60));
        lp.add(new LayoutParams(100,100));

        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 2; i ++) {
                                startBallAnim();
                            }
                        }
                    });

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private ValueAnimator getBezierValueAnimator(final View target) {
        // 初始化贝塞尔估值器
        //随机产生两个点，以确定一条3阶贝塞尔曲线
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(), getPointF());
        // 起点在右下角位置，终点在左上角位置
        ValueAnimator animator= ValueAnimator.ofObject(evaluator, new PointF(random.nextInt(1000),  1000), new PointF(random.nextInt(500)+300, 0));

        animator.setTarget(target);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // 这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                target.setX(pointF.x);
                target.setY(pointF.y);

            }
        });

        animator.setDuration(6000);
        return animator;
    }

    private PointF getPointF() {
        PointF pointF = new PointF();

        pointF.x = random.nextInt(ScreenUtils.getScreenWidth(mContext));
        pointF.y = random.nextInt(300)+100;

        return pointF;
    }

    public void startBallAnim() {
        final ImageView imageView = new ImageView(mContext);

        imageView.setBackgroundResource(mList.get(random.nextInt(mList.size())));
        imageView.setLayoutParams(lp.get(random.nextInt(lp.size())));
        addView(imageView);

        AnimatorSet finalSet = new AnimatorSet();

        ValueAnimator bezierValueAnimator = getBezierValueAnimator(imageView);//贝塞尔曲线路径动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,"alpha",random.nextFloat(),0f);
        objectAnimator.setDuration(6000);

        finalSet.playSequentially(bezierValueAnimator);
        finalSet.setInterpolator(new LinearInterpolator());
        finalSet.setTarget(imageView);

        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                removeView((imageView));//删除爱心

            }
        });
        finalSet.start();
        objectAnimator.start();
    }
}
