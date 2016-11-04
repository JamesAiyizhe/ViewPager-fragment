package com.baway.fragmentviewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * ViewPager嵌套Fragment使用
 * @author lujunfang
 * @date 2016-9-8
 */
public class MainActivity extends FragmentActivity{
	private ViewPager mViewPager;
	private TextView mTv1, mTv2, mTv3;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        initTv();
        //准备数据源
        mFragmentList.add(Fragment1.newInstance("1"));
        mFragmentList.add(Fragment1.newInstance("2"));
        mFragmentList.add(Fragment1.newInstance("3"));
        //构建FragmentPagerAdapter
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			//当页卡切换时改变对应位置的TextView的颜色
			@Override
			public void onPageSelected(int arg0) {
				resetTvColor();
				switch(arg0){
					case 0:
						mTv1.setTextColor(getResources().getColor(android.R.color.holo_red_light));
						break;
					case 1:
						mTv2.setTextColor(getResources().getColor(android.R.color.holo_red_light));
						break;
					case 2:
						mTv3.setTextColor(getResources().getColor(android.R.color.holo_red_light));
						break;
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    private void resetTvColor(){
    	mTv1.setTextColor(getResources().getColor(android.R.color.black));
    	mTv2.setTextColor(getResources().getColor(android.R.color.black));
    	mTv3.setTextColor(getResources().getColor(android.R.color.black));
    }
    private void initTv(){
    	mTv1 = (TextView)findViewById(R.id.tv1);
    	mTv2 = (TextView)findViewById(R.id.tv2);
    	mTv3 = (TextView)findViewById(R.id.tv3);
    	mTv1.setOnClickListener(new TvOnClickListener(0));
    	mTv2.setOnClickListener(new TvOnClickListener(1));
    	mTv3.setOnClickListener(new TvOnClickListener(2));
    }
    //TextView点击事件
    class TvOnClickListener implements OnClickListener{
    	private int mIndex;
    	public TvOnClickListener(int index){
    		mIndex = index;
    	}
    	//点击TextView时ViewPager跳转到相应的索引页
		@Override
		public void onClick(View v) {
			mViewPager.setCurrentItem(mIndex);		
		}
    	
    }
    /**
     * ViewPager嵌套Fragment专用适配器：FragmentPagerAdapter
     * 1. 必须实现构造方法，并传入FragmentManager的实例
     * 		1).如果继承自Activity，通过getFragmentManager()获取
     * 		2）.如果继承自FragmentActivity，通过getSupportFragmentManager()获取
     * 2. 最起码实现其中的两个方法：getCount(), getItem()
     * @author lujunfang
     * @date 2016-9-8
     */
    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		//返回Fragment个数
    	@Override
    	public int getCount() {
    		// TODO Auto-generated method stub
    		return mFragmentList.size();
    	}
    	//返回当前索引页的Fragment
    	@Override
    	public Fragment getItem(int position) {
    		// TODO Auto-generated method stub
    		return mFragmentList.get(position);
    	}
    }
}
