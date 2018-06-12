package ruiduoyi.com.skyworthpda.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by Chen on 2018/6/11.
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = ev.getX();
                endY = ev.getY();
                float distantX = Math.abs(endX  - startX);
                float distantY = Math.abs(endY  - startY);
                if (distantX > distantY){
                    return true;
                }else {
                    return false;
                }

        }
        return super.onInterceptTouchEvent(ev);
    }
}
