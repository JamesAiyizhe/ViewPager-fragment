package com.baway.fragmentviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment1 extends Fragment {
	private Activity mContext;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		//在Fragment中通过getActivity（）可以得到宿主Activity的实例
		//Activity实例最好保存到全局，避免调用getActivity时Fragment与Activity已经解绑，就会出现空指针
		mContext = getActivity();
		//注意此处inflater方法传参只允许下面两种方式  ××××××××××××××××××××
		View view = inflater.inflate(R.layout.fragment1_layout, null);
//		View view = inflater.inflate(R.layout.fragment1_layout, container, false);
		Button btn = (Button)view.findViewById(R.id.btn);
		//通过getArguments()获取传过来的数值
		Bundle data = getArguments();
		String position = data.getString("position");
		btn.setText("当前是Fragment" + position);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "第一个Fragment", Toast.LENGTH_LONG).show();
			}
		});
		return view;
	}
	//实例化Fragment时将值传进来（同一个容器中Fragment之间的传值可以通过setArguments（Bundle）传值
	public static Fragment1 newInstance(String position){
		Fragment1 fragment = new Fragment1();
		Bundle data  = new Bundle();
		data.putString("position", position);
		fragment.setArguments(data);
		return fragment;
	}
}
