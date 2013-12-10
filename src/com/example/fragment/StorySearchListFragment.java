package com.example.fragment;

import java.util.ArrayList;

import com.example.slidemenu.R;
import com.example.slidemenu.SettingComponentItem;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

@SuppressLint("ValidFragment")
public class StorySearchListFragment extends BaseListviewFragment<SettingComponentItem>
{

	public StorySearchListFragment(ArrayList<SettingComponentItem> arrList, int convertViewLayout, Context context)
	{ 
		super(arrList, convertViewLayout, context);
	}

	@Override
	public void setConvertViewDetail(int position, View convertView)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnItemClickListener(AdapterView<?> AdapterView, View convertView, int position)
	{
		// TODO Auto-generated method stub
		
	}
}
