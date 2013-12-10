package com.example.fragment;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragment.BaseListviewFragment.BaseListViewAdapter;
import com.example.slidemenu.R;
import com.example.slidemenu.SettingComponentItem;

@SuppressLint("ValidFragment")
public class SettingListviewFragment extends BaseListviewFragment<SettingComponentItem>
{
	public SettingListviewFragment(ArrayList<SettingComponentItem> arrList, int convertViewLayout, Context context)
	{
		super(arrList, convertViewLayout, context);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
	
	
	@Override
	public void setConvertViewDetail(int position, View convertView)
	{
		TextView settingListText = (TextView) convertView.findViewById(R.id.setting_list_text);
		settingListText.setText(mArrayList.get(position).getText());
	}

	
	@Override
	public void OnItemClickListener(AdapterView<?> AdapterView, View convertView, int position)
	{
		switch (position)
		{
		case 0:
			//Report problem
			getFragmentManager().beginTransaction().replace(R.id.main_container, new ReportProblemFragment()).commit();
			break;
			
		case 1:
			//Blog
			Toast.makeText(getActivity(), "Blog", Toast.LENGTH_LONG).show();
			break;
						
		case 2:
			//Privacy problem
			Toast.makeText(getActivity(), "Privacy problem", Toast.LENGTH_LONG).show();
			break;
			
		case 3:
			//Terms & Service
			Toast.makeText(getActivity(), "Terms & Service", Toast.LENGTH_LONG).show();
			break;
			
		case 4:
			//About
			Toast.makeText(getActivity(), "About", Toast.LENGTH_LONG).show();
			break;
			
		case 5:
			//social connection
			Toast.makeText(getActivity(), "social connection", Toast.LENGTH_LONG).show();
			break;
			
		case 6:
			//Edit profile
			Toast.makeText(getActivity(), "Edit profile", Toast.LENGTH_LONG).show();
			break;
			
		case 7:
			//Log out
			Toast.makeText(getActivity(), "Log out", Toast.LENGTH_LONG).show();
			break;

		default:
			
			break;
		}
		
	}

}
