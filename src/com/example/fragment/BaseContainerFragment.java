package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.customview.CustomHorizonScrollViewPicker;
import com.example.slidemenu.R;

public class BaseContainerFragment extends Fragment {
    private View fragmentView;
    private LinearLayout horizontalPickerContainer;
    private CustomHorizonScrollViewPicker mHorizontalScrollViewPicker;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.base_container_frgment, container, false);
        initTop();
        initArrangeButton();
        initHomefeedListView();
        return fragmentView;
    }

    private void initTop() {
        TopMenuBarFragment topMenuBar = new TopMenuBarFragment();
        getFragmentManager().beginTransaction().add(R.id.top_container, topMenuBar).commit();
    }

    private void initArrangeButton() {
        mHorizontalScrollViewPicker = (CustomHorizonScrollViewPicker) fragmentView
                .findViewById(R.id.horizontal_scroll_view_picker);
        horizontalPickerContainer = (LinearLayout) mHorizontalScrollViewPicker.getChildAt(0);
        for (int i = 0; i < horizontalPickerContainer.getChildCount(); i++) {
            FrameLayout selectFrame = (FrameLayout) horizontalPickerContainer.getChildAt(i);
            selectFrame.setTag(i);
            selectFrame.setOnClickListener(viewOnclickListener);
        }
    }
    
    private void initHomefeedListView() {
        mPager = (ViewPager) fragmentView.findViewById(R.id.pager);
        
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    FrameLayout.OnClickListener viewOnclickListener = new OnClickListener() {
        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            mHorizontalScrollViewPicker.smoothScrollTo(90 * (position) * 4, 0);

            for (int i = 0; i < horizontalPickerContainer.getChildCount(); i++) {
                ImageView recentImage = (ImageView) ((FrameLayout) horizontalPickerContainer.getChildAt(i))
                        .getChildAt(0);

                if (view == (FrameLayout) horizontalPickerContainer.getChildAt(i)) {
                    recentImage.setVisibility(View.VISIBLE);
                    AlphaAnimation imageColorAnimation = new AlphaAnimation(0, 1);
                    imageColorAnimation.setDuration(350);
                    recentImage.startAnimation(imageColorAnimation);
                } else {
                    recentImage.setVisibility(View.INVISIBLE);
                    recentImage.setAlpha(0);
                }

            }
        }
    };
    
    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	Log.e("test", "fsdfdsf");
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
