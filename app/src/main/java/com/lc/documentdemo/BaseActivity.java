package com.lc.documentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Author: lc
 * Email: rekirt@163.com
 * Date: 5/25/16
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntentValue();
        if(getContentViewResId()!=0)setContentView(getContentViewResId());
        else setContentView(getContentView());
        initView();
        setListener();
        initData();
    }

    protected void initData() {
    }

    protected void setListener() {
    }

    protected void initView() {
    }

    protected View getContentView() {
        return null;
    }

    protected int getContentViewResId() {
        return 0;
    }

    protected void initIntentValue() {
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.inject(this);
    }

    public void openActivity(Class clz){
        Intent intent = new Intent(this,clz);
        startActivity(intent);
    }
}
