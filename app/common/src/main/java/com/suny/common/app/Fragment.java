package com.suny.common;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public abstract class Fragment extends androidx.fragment.app.Fragment {
    protected View mRoot;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            this.mRoot = root;
        }else {
            if(mRoot.getParent() != null){
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //当View创建完成后初始化数据
        initData();
    }

    /**
     * 得到当前界面的资源文件Id
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     * @param view 根布局文件View
     */
    protected abstract void initWidget(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化参数
     * @param bundle Activity传进来的参数
     */
    protected abstract void initArgs(Bundle bundle);

    /**
     * 返回按键时触发调用
     * @return false 默认返回false，表示不处理走原生流程，返回true，表示自己处理Activity不是finish
     */
    public boolean onBackPressed(){
        return false;
    }
}
