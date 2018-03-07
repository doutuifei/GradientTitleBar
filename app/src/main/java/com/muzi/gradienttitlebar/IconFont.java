package com.muzi.gradienttitlebar;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by muzi on 2018/1/24.
 * 727784430@qq.com
 */

public class IconFont extends android.support.v7.widget.AppCompatTextView {

    private TypeFaceTask typeFaceTask;

    public IconFont(Context context) {
        this(context, null);
    }

    public IconFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        if (GlobalData.typeface == null) {
            typeFaceTask = new TypeFaceTask(context.getApplicationContext());
            typeFaceTask.execute();
            return;
        }

        try {
            setTypeface(GlobalData.typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
