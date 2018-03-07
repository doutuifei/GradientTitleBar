package com.muzi.gradienttitlebar;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;

/**
 * Created by muzi on 2017/7/20.
 * 727784430@qq.com
 */

public class TypeFaceTask extends AsyncTask<Context, Integer, Typeface> {

    private Context context;
    private onComplete complete;

    public TypeFaceTask setComplete(onComplete complete) {
        this.complete = complete;
        return this;
    }

    public TypeFaceTask(Context context) {
        this.context = context;
    }

    @Override
    protected Typeface doInBackground(Context... params) {
        return Typeface.createFromAsset(context.getAssets(), "iconfont.ttf");
    }

    @Override
    protected void onPostExecute(Typeface typeface) {
        super.onPostExecute(typeface);
        GlobalData.typeface = typeface;
        if (complete != null) {
            complete.onComplete();
        }
    }

    public interface onComplete {
        void onComplete();
    }
}
