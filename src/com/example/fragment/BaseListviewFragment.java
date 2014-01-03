package com.example.fragment;

import java.util.ArrayList;

import com.example.slidemenu.R;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ValidFragment")
public abstract class BaseListviewFragment<T> extends Fragment{
	protected ArrayList<T> mArrayList;
	private int mConvertViewLayout;
	private LayoutInflater inflater;	
	private Context mContext;
	protected ListView mBaseListview;
	protected BaseListViewAdapter baseAdapter;
	
	public BaseListviewFragment(ArrayList<T> arrList, int convertViewLayout, Context context)
	{
		if(arrList == null){
			mArrayList = new ArrayList<T>();
		}else{
			this.mArrayList = arrList;
		}
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
		mBaseListview.setOnItemClickListener(listViewOnItemClickListener);
		return fragmentView;
	}
	
	
	
	class BaseListViewAdapter extends BaseAdapter {
		
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
	
	
	private AdapterView.OnItemClickListener listViewOnItemClickListener = new AdapterView.OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> AdapterView, View convertView, int position, long arg3){
			OnItemClickListener(AdapterView, convertView, position);
		}
	};
	
	public abstract void setConvertViewDetail(int position, View convertView);
	
	public abstract void OnItemClickListener(AdapterView<?> AdapterView, View convertView, int position);
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
}
