package cn.edu.hebtu.software.peace.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.edu.hebtu.software.peace.R;

public class TasksCompletedView extends View { // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    private Paint nRingPaint;
    // 画字体的画笔
    private Paint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    private int nRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress;
    Bitmap bitmap = null;
    int imgWidth = 0;
    int ingHight = 0;

    public TasksCompletedView(Context context, AttributeSet attrs) {
    super(context, attrs);
    // 获取自定义的属性
    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.close);
    imgWidth = bitmap.getWidth();
    ingHight = bitmap.getHeight();
    initAttrs(context, attrs);
    initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
    TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TasksCompletedView, 0, 0);
     mRadius = imgWidth / 2;
     mStrokeWidth = typeArray.getDimension(R.styleable.TasksCompletedView_strokeWidth, 10);
     mCircleColor = 0xffffffff;
     mRingColor = typeArray.getColor(R.styleable.TasksCompletedView_ringColor, 0x00000000);
     nRingColor = 0x008080;
     mRingRadius = mRadius + mStrokeWidth / 2;
     }

      private void initVariable() {
      mCirclePaint = new Paint();
      mCirclePaint.setAntiAlias(true);
      mCirclePaint.setColor(mCircleColor);
      mCirclePaint.setStyle(Paint.Style.STROKE);
      mCirclePaint.setStrokeWidth(5);

      mRingPaint = new Paint();
      mRingPaint.setAntiAlias(true);
      mRingPaint.setColor(mRingColor);
      mRingPaint.setStyle(Paint.Style.STROKE);
      mRingPaint.setStrokeWidth(mStrokeWidth);

      nRingPaint = new Paint();
      nRingPaint.setAntiAlias(true);
      nRingPaint.setColor(mRingColor);
      nRingPaint.setStyle(Paint.Style.STROKE);
      nRingPaint.setStrokeWidth(mStrokeWidth);

      mTextPaint = new Paint();
      mTextPaint.setAntiAlias(true);
//      mTextPaint.setStyle(Paint.Style.FILL);
      mTextPaint.setARGB(0, 0, 0, 0);
      mTextPaint.setTextSize(mRadius / 2);

      Paint.FontMetrics fm = mTextPaint.getFontMetrics();
      mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

      }

       @Override
       protected void onDraw(Canvas canvas) {

       mXCenter = getWidth() / 2;
       mYCenter = getHeight() / 2;

        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);

        if (mProgress > 0) {
       RectF oval = new RectF();
       oval.left = (mXCenter - mRingRadius);
       oval.top = (mYCenter - mRingRadius);
       oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
       oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
       canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false,mRingPaint); //
       canvas.drawBitmap(bitmap, mXCenter - imgWidth / 2, mYCenter- ingHight / 2, mTextPaint);

     } else {
     RectF oval = new RectF();
     oval.left = (mXCenter - mRingRadius);
     oval.top = (mYCenter - mRingRadius);
     oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
     oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
     canvas.drawArc(oval, 0, 0, false, nRingPaint); //
     canvas.drawBitmap(bitmap, mXCenter - imgWidth / 2, mYCenter - ingHight / 2, mTextPaint);
     }
     }

     public void setProgress(int progress) {
     mProgress = progress;
     // invalidate();
     postInvalidate();
     }
}
