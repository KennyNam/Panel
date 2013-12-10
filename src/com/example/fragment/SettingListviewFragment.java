package com.example.fragment;

import java.util.ArrayList;

import com.example.slidemenu.R;
import com.example.slidemenu.SettingComponentItem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class SettingListviewFragment extends BaseListviewFragment<SettingComponentItem>
{
	public SettingListviewFragment(ArrayList<SettingComponentItem> arrList, int convertViewLayout, Context context)
	{
		super(arrList, convertViewLayout, context);
		mBaseListview.setOnItemClickListener(listViewOnItemClickListener);
	}
	
	AdapterView.OnItemClickListener listViewOnItemClickListener = new OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
		{
			
		}
	};
	@Override
	public void setConvertViewDetail(int position, View convertView)
	{
		TextView settingListText = (TextView) convertView.findViewById(R.id.setting_list_text);
		settingListText.setText(mArrayList.get(position).getText());
	}
}
