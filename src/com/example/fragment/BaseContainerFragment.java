package com.example.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.customview.CustomHorizonScrollViewPicker;
import com.example.slidemenu.R;

public class BaseContainerFragment extends Fragment {
    private View fragmentView;
    private LinearLayout horizontalPickerContainer;
    private CustomHorizonScrollViewPicker mHorizontalScrollViewPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.base_container_frgment, container, false);
        initTop();
        initArrangeButton();
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

    FrameLayout.OnClickListener viewOnclickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            mHorizontalScrollViewPicker.scrollTo(90 * (position) * 4, 0);
            for (int i = 0; i < horizontalPickerContainer.getChildCount(); i++) {
                if(view == (FrameLayout)horizontalPickerContainer.getChildAt(i)){
                    ((FrameLayout)horizontalPickerContainer.getChildAt(i)).getChildAt(0).setVisibility(View.VISIBLE);
                }else{
                    ((FrameLayout)horizontalPickerContainer.getChildAt(i)).getChildAt(0).setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    private void initHomefeedListView() {
        // TODO
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
