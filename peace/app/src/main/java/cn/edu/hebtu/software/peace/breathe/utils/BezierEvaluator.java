package cn.edu.hebtu.software.peace.breathe.utils;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class BezierEvaluator implements TypeEvaluator<PointF> {

    private PointF mcontrolp1;
    private PointF mcontrolp2;
    public BezierEvaluator(PointF controlp1, PointF controlp2) {
        this.mcontrolp1 = controlp1;
        this.mcontrolp2 = controlp2;
    }

    @Override
    public PointF evaluate(float time, PointF start, PointF end) {

        float timeleft = 1.0f - time;
        PointF point = new PointF();

        point.x = timeleft * timeleft * timeleft * (start.x) + 3 * timeleft * timeleft * time *
                (mcontrolp1.x) + 3 * timeleft * time *
                time * (mcontrolp2.x) + time * time * time * (end.x);

        point.y = timeleft * timeleft * timeleft * (start.y) + 3 * timeleft * timeleft * time *
                (mcontrolp1.y) + 3 * timeleft * time *
                time * (mcontrolp2.y) + time * time * time * (end.y);
        return point;
    }
}
