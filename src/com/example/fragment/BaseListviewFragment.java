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
public abstract class BaseListviewFragment extends Fragment {
	private ArrayList<?> mArrayList;
	private int mConvertViewLayout;
	private LayoutInflater inflater;
	private Context mContext;
	private View mConvertView;
	private int mPosition;
	private ListView mBaseListview;
	private BaseListViewAdapter baseAdapter;
	
	public BaseListviewFragment(ArrayList<?> arrList, int convertViewLayout, Context context)
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
		public long getItemId(int position)
		{
			return -1;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			mPosition = position;
			if(convertView == null) {
				convertView = inflater.inflate(mConvertViewLayout, parent, false);
				mConvertView = convertView;
				return setConvertViewDetail();
			}else{
				return setConvertViewDetail();
			}
			
		}
		
	}

	public View getConvertView(){
		return mConvertView;
	}
	
	public int getPosition(){
		return mPosition;
	}
	
	public abstract View setConvertViewDetail();
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
}
