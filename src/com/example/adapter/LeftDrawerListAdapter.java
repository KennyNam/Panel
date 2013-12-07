package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.slidemenu.BasicContentsListItem;
import com.example.slidemenu.PanelContentsListItem;
import com.example.slidemenu.R;
import com.example.slidemenu.R.drawable;
import com.example.slidemenu.R.id;
import com.example.slidemenu.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeftDrawerListAdapter extends BaseAdapter
{
	private Context mContext;
	private ArrayList<BasicContentsListItem> BasicContentsItemList;
	private HashMap<BasicContentsListItem, Integer> mTagMap = new HashMap<BasicContentsListItem, Integer>();
	private LayoutInflater inflater;

	public LeftDrawerListAdapter(Context context)
	{
		mContext = context;
		initContents();
		setTag();
		inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private void initContents()
	{
		BasicContentsItemList = new ArrayList<BasicContentsListItem>();
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_searchbox, null));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon0, "Momo"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon1, "Saved Work"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon2, "Most Popular"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon3, "Most Viewd"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon4, "New"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon5, "Genres"));
		BasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon6, "Settings"));
	}

	private void setTag(){
		for (int i = 0; i < BasicContentsItemList.size(); i++)
		{
			mTagMap.put(BasicContentsItemList.get(i), i);
		}
	}
	
	public int getTag(BasicContentsListItem key){
		return mTagMap.get(key);
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return BasicContentsItemList.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return BasicContentsItemList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub

		convertView = null;
		if (convertView == null)
		{
			BasicContentsListItem BasicContentsObject = (BasicContentsListItem) getItem(position);

			switch (position)
			{
			case 0:
				convertView = inflater.inflate(R.layout.left_drawer_searchbox, parent, false);
				ImageView searchImage = (ImageView) convertView.findViewById(R.id.search_image);
				searchImage.setImageResource(BasicContentsObject.getImageResource());
				convertView.setTag(BasicContentsObject);
				break;

			default:
				convertView = inflater.inflate(R.layout.left_drawer_basic_list, parent, false);
				ImageView iconImage = (ImageView) convertView.findViewById(R.id.basiclist_icon_image);
				TextView text = (TextView) convertView.findViewById(R.id.basiclist_text);
				iconImage.setImageResource(BasicContentsObject.getImageResource());
				text.setText(BasicContentsObject.getContentText());
				if (position == 1)
				{
					LinearLayout basiclistLayout = (LinearLayout) convertView.findViewById(R.id.basiclist_layout_inner);
					basiclistLayout.setBackgroundResource(R.drawable.left_slide_momo_background);
				}
				convertView.setTag(BasicContentsObject);
				break;
			}
		}
		return convertView;
	}
}
