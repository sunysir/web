package com.suny.common.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.suny.common.Fragment;

import java.util.List;

public abstract class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在界面未初始化前调用初始化窗口
        initWindow();
        if(initArgs(getIntent().getExtras())){
            int layoutId = getContentLayoutId();
            ininView();
            initData();
            setContentView(layoutId);
        }else{
            finish();
        }
    }

    protected abstract int getContentLayoutId();

    protected abstract void initData();

    protected abstract void ininView();

    protected abstract void initWindow();

    protected boolean initArgs(Bundle bundle){
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //得到当前Activity下的所有Fragment
        List<androidx.fragment.app.Fragment> fragments = getSupportFragmentManager().getFragments();
        //判断是否为空
        if(fragments!=null && fragments.size()>0){
            //遍历所有fragment
            for (androidx.fragment.app.Fragment fragment : fragments){
                //判断是否为我们能够处理的fragment
                if (fragment instanceof com.suny.common.Fragment){
                    //判断back键是否有处理，返回true则拦截不往下执行
                    if(((Fragment) fragment).onBackPressed()){
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
