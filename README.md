# 渐变标题栏

## 效果展示
![img](https://github.com/mzyq/GradientTitleBar/blob/master/img/1.gif)

## 思路
先看结构图,根据滑动距离计算alpha
![img](https://github.com/mzyq/GradientTitleBar/blob/master/img/3.png)

1. 最外层是一个 ```NestedScrollView```，监听Y轴滑动距离。
2. 长的是一个titleBar，背景为透明色,滑动过程中由透明色变为白色
3. 两边的是功能按钮，由一个白色的图标和一个黑色半透明的圆形背景组成：图标由白色变为黑色，背景由黑色半透明变为白色透明
4. 中间的标题文字由透明色变为黑色

## 代码
```
  scrollHeight = banner.getLayoutParams().height;
  scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
             @Override
             public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                 if (scrollY <= scrollHeight) {
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
                 }
             }
         });
```

然后运行，看效果，缓慢滑动没有问题，快速滑动，功能按钮的背景没有消失~~~<br/>
![img](https://github.com/mzyq/GradientTitleBar/blob/master/img/2.gif)

## 修正代码
```
scrollHeight = banner.getLayoutParams().height;
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
```
当滑动距离超过参照物的高度```scrollHeight```就强制设置为想要的效果 <br/>
加上一个```boolean isCommitColor```为了节约内存

# Library
* [沉浸式状态栏：Sofia](https://github.com/yanzhenjie/Sofia)

# 注意
* ```theme```要设置成全屏
```
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="windowNoTitle">true</item>
        <item name="windowActionBar">false</item>
    </style>
```

* 使用```Sofia```设置沉浸式效果
```
 Sofia.with(this)
                .statusBarDarkFont()
                .invasionStatusBar()
                .fitsSystemWindowView(topView)
                .navigationBarBackground(Color.BLACK);
```

* 什么是IconFont？<br/>
  阿里的字体图标，可以动态改变颜色，具体用法自行百度

* 华为的虚拟按键可能有问题，```Sofia```还没有兼容好

# License
```
Copyright 2017 Mu Zi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```