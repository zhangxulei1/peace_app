package cn.edu.hebtu.software.peace.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WelcomeViewPagerAdapter extends PagerAdapter {
    private List<View> views;
    public WelcomeViewPagerAdapter(List<View> mViewList) {
        this.views = mViewList;
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化

        ViewGroup parent = (ViewGroup) views.get(position).getParent();
        if (parent != null)
            parent.removeAllViews();

        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView(views.get(position));
    }
}
