package com.example.fragment;

import java.util.ArrayList;

import com.example.slidemenu.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public abstract class BaseListviewFragment<T> extends Fragment {
	protected ArrayList<T> mArrayList;
	private int mConvertViewLayout;
	private LayoutInflater inflater;	
	private Context mContext;
	protected ListView mBaseListview;
	private BaseListViewAdapter baseAdapter;
	
	public BaseListviewFragment(ArrayList<T> arrList, int convertViewLayout, Context context)
	{
		this.mArrayList = arrList;
		this.mConvertViewLayout = convertViewLayout;
		this.mContext = context;
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.base_listview, container, false);
		mBaseListview = (ListView) fragmentView.findViewById(R.id.baseListview);
		baseAdapter = new BaseListViewAdapter();
		mBaseListview.setAdapter(baseAdapter);
		return fragmentView;
	}
	
	
	class BaseListViewAdapter extends BaseAdapter{
		
		public BaseListViewAdapter(){
			super();
		}
		
		@Override
		public int getCount()
		{
			return mArrayList.size();
		}

		@Override
		public Object getItem(int position)
		{
			return mArrayList.get(position);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if(convertView == null) {
				convertView = inflater.inflate(mConvertViewLayout, parent, false);
			}
			setConvertViewDetail(position, convertView);
			return convertView;
			
		}

		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}
		
	}
	public abstract void setConvertViewDetail(int position, View convertView);
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
}
