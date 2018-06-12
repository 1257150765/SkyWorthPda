package ruiduoyi.com.skyworthpda.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by Chen on 2018/6/12.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText implements View.OnClickListener {
    private PopupWindow popupWindow;
    private TextView textView;

    public MyEditText(Context context) {
        this(context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        requestFocus();
        if (popupWindow == null) {
            popupWindow = new PopupWindow(getContext());
            textView = new TextView(getContext());
            popupWindow.setContentView(textView);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置PopupWindow是否能响应外部点击事件
            popupWindow.setOutsideTouchable(true);
            //设置PopupWindow是否能响应点击事件
            popupWindow.setTouchable(true);
        }
        textView.setText(getText().toString());
        popupWindow.showAsDropDown(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
