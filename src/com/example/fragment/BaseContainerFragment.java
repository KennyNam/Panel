package com.example.fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.customview.CustomHorizonScrollViewPicker;
import com.example.slidemenu.R;

public class BaseContainerFragment extends Fragment
{
    private ArrayList<String> arrangeByList;
    private View fragmentView;
    private LinearLayout horizontalPickerContainer;
    private CustomHorizonScrollViewPicker mHorizontalScrollViewPicker;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		fragmentView = inflater.inflate(R.layout.base_container_frgment, container, false);
		initTop();
		initArrangeButton();
		return fragmentView;
	}

	private void initTop()
	{
		TopMenuBarFragment topMenuBar = new TopMenuBarFragment();
		getFragmentManager().beginTransaction().add(R.id.top_container, topMenuBar).commit();
	}

	private void initArrangeButton(){
	    mHorizontalScrollViewPicker = (CustomHorizonScrollViewPicker) fragmentView.findViewById(R.id.horizontal_scroll_view_picker);
	    horizontalPickerContainer = (LinearLayout) mHorizontalScrollViewPicker.getChildAt(0);
	    
	    
	    arrangeByList = new ArrayList<String>();
	    arrangeByList.add("Popular");
	    arrangeByList.add("Most View");
	    arrangeByList.add("New");
	    
	    
	    for (int i = 0; i < arrangeByList.size(); i++) {
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(360, LayoutParams.MATCH_PARENT);
	        TextView textView = new TextView(getActivity());
            textView.setText(arrangeByList.get(i));
            textView.setTag(i);
            textView.setOnClickListener(textViewOnclickListener);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            
            if(i == 0){
                params.leftMargin = 180;
            }else if(i == arrangeByList.size()-1){
                params.rightMargin = 180;
            }
            textView.setLayoutParams(params);
            horizontalPickerContainer.addView(textView);
        }
	    horizontalPickerContainer.getWidth();
	}
	
	TextView.OnClickListener textViewOnclickListener = new OnClickListener() {
        
        @Override
        public void onClick(View view) {
            int position = (Integer)view.getTag();
            mHorizontalScrollViewPicker.scrollTo(90 * (position) * 4 , 0);
        }
    };
	
	private void initHomefeedListView(){
	    //TODO
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
        
	}
}
