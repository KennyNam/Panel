package com.example.fragment;

import java.util.ArrayList;

import com.example.slidemenu.R;
import com.example.slidemenu.SettingComponentItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class SettingListviewFragment extends BaseListviewFragment
{
	private ArrayList<?> mArrList;
	private View mConvertView;
	private int mPosition;
	public SettingListviewFragment(ArrayList<?> arrList, int convertViewLayout, Context context)
	{
		super(arrList, convertViewLayout, context);
		this.mArrList = arrList;
	}

	@Override
	public View setConvertViewDetail()
	{
		mConvertView = getConvertView();
		mPosition = getPosition();
		TextView settingListText = (TextView) mConvertView.findViewById(R.id.setting_list_text);
		if(mArrList.get(0) instanceof SettingComponentItem) {
			settingListText.setText(((SettingComponentItem) mArrList.get(mPosition)).getText());
		} else {
			//Error
			settingListText.setText("null");
		}
		return mConvertView;
	}
}
