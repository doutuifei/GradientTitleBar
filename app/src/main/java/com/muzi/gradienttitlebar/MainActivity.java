package com.muzi.gradienttitlebar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.sofia.Sofia;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.txTitle)
    TextView txTitle;
    @BindView(R.id.btnLeftIcon)
    IconFont btnLeftIcon;
    @BindView(R.id.btnLeft)
    CircleRelativeLayout btnLeft;
    @BindView(R.id.btnRightIcon)
    IconFont btnRightIcon;
    @BindView(R.id.btnRight)
    CircleRelativeLayout btnRight;
    @BindView(R.id.topView)
    RelativeLayout topView;

    private int scrollHeight;
    private int scrollAlpha = 0;
    private float scrollScale = 0;
    private boolean isCommitColor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onStatusBar();
        scrollHeight = banner.getLayoutParams().height;
        initScrollView();
    }

    /**
     * 沉浸式状态栏
     */
    private void onStatusBar() {
        Sofia.with(this)
                .statusBarDarkFont()
                .invasionStatusBar()
                .fitsSystemWindowView(topView)
                .navigationBarBackground(Color.BLACK);
    }

    private void initScrollView() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= scrollHeight) {
                    //开启滑动过快
                    isCommitColor = false;

                    scrollScale = (float) scrollY / scrollHeight;
                    scrollAlpha = (int) (255 * scrollScale);

                    //文字由透明变为黑色
                    txTitle.setTextColor(Color.argb(scrollAlpha, 0, 0, 0));

                    //背景由透明变为白色
                    topView.setBackgroundColor(Color.argb(scrollAlpha, 255, 255, 255));

                    //按钮背景由黑变白
                    btnLeft.setColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));
                    btnRight.setColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));

                    //按钮文字由白变黑
                    scrollAlpha = 255 - scrollAlpha;
                    btnRightIcon.setTextColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));
                    btnLeftIcon.setTextColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));
                } else {
                    if (!isCommitColor) {
                        //防止滑动过快bug
                        isCommitColor = true;

                        //文字由透明变为黑色
                        txTitle.setTextColor(Color.BLACK);
                        //背景由透明变为白色
                        topView.setBackgroundColor(Color.WHITE);

                        //按钮背景由黑变白
                        btnLeft.setColor(Color.WHITE);
                        btnRight.setColor(Color.WHITE);

                        //按钮文字由白变黑
                        btnRightIcon.setTextColor(Color.BLACK);
                        btnLeftIcon.setTextColor(Color.BLACK);
                    }
                }
            }
        });
    }

    /**
     * 非纯黑色的情况下
     * 下面的黑色为"#0e0e0e" -14,14,14
     */
    private void initScrollViewNotBlack() {
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= scrollHeight) {
                    //开启滑动过快
                    isCommitColor = false;

                    scrollScale = (float) scrollY / scrollHeight;
                    scrollAlpha = (int) (255 * scrollScale);
                    //文字由透明变为黑色
                    txTitle.setTextColor(Color.argb(scrollAlpha, 14, 14, 14));
                    //背景由透明变为白色
                    topView.setBackgroundColor(Color.argb(scrollAlpha, 255, 255, 255));

                    //按钮背景由黑变白
                    btnLeft.setColor(Color.argb(206, scrollAlpha, scrollAlpha, scrollAlpha));
                    btnRight.setColor(Color.argb(206, scrollAlpha, scrollAlpha, scrollAlpha));
                    //按钮文字由白变黑
                    scrollAlpha = 255 - scrollAlpha;
                    if (scrollAlpha <= 14) {
                        scrollAlpha = 14;
                    }
                    btnRightIcon.setTextColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));
                    btnLeftIcon.setTextColor(Color.argb(255, scrollAlpha, scrollAlpha, scrollAlpha));
                } else {
                    if (!isCommitColor) {
                        //防止滑动过快bug
                        isCommitColor = true;

                        //文字由透明变为黑色
                        txTitle.setTextColor(Color.argb(255, 14, 14, 14));
                        //背景由透明变为白色
                        topView.setBackgroundColor(Color.argb(255, 255, 255, 255));

                        //按钮背景由黑变白
                        btnLeft.setColor(Color.WHITE);
                        btnRight.setColor(Color.WHITE);

                        //按钮文字由白变黑
                        btnRightIcon.setTextColor(Color.argb(255, 14, 14, 14));
                        btnLeftIcon.setTextColor(Color.argb(255, 14, 14, 14));
                    }
                }
            }
        });
    }

}
