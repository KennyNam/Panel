package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class CustomHorizonScrollViewPicker extends HorizontalScrollView {

    public CustomHorizonScrollViewPicker(Context context) {
        super(context);
    }
    
    public CustomHorizonScrollViewPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
    
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.e("onScrollChanged", "" + this.getScrollX());
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
