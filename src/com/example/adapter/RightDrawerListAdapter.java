package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.slidemenu.PanelContentsListItem;
import com.example.slidemenu.R;

public class RightDrawerListAdapter extends BaseAdapter
{
	private int INVALID_ID = -1;
	private Context mContext;
	private ArrayList<PanelContentsListItem> mPanelArrayList;
	private HashMap<PanelContentsListItem, Integer> mIdMap = new HashMap<PanelContentsListItem, Integer>();
	private HashMap<PanelContentsListItem, Boolean> mSelectMap = new HashMap<PanelContentsListItem, Boolean>();
	private LayoutInflater inflater;
	private boolean EDIT_MODE = false;

	public RightDrawerListAdapter(Context context, ArrayList<PanelContentsListItem> mPanelArrayList)
	{
		this.mContext = context;
		this.mPanelArrayList = mPanelArrayList;
		setTagID();
		setTagSelect();
	}

	public void setTagSelectToggle(PanelContentsListItem item)
	{
		if (mSelectMap.get(item) == true)
		{
			mSelectMap.put(item, false);

		} else
		{
			mSelectMap.put(item, true);
		}
		notifyDataSetChanged();
	}

	public void setTagID()
	{
		for (int i = 0; i < mPanelArrayList.size(); i++)
		{
			mIdMap.put(mPanelArrayList.get(i), i);
		}
	}
	
	public int getTag(PanelContentsListItem key){
		return mIdMap.get(key);
	}
	
	public void setTagSelect()
	{
		for (int i = 0; i < mPanelArrayList.size(); i++)
		{
			if (mSelectMap.containsKey(mPanelArrayList.get(i)) != true)
			{
				mSelectMap.put(mPanelArrayList.get(i), false);
			}
		}
	}

	public void setEditMode(boolean EDIT_MODE)
	{
		this.EDIT_MODE = EDIT_MODE;
		notifyDataSetChanged();
	}

	@Override
	public int getCount()
	{
		return mPanelArrayList.size();
	}

	@Override
	public Object getItem(int position)
	{
		if (position == mPanelArrayList.size())
		{
			return INVALID_ID;
		}
		return mPanelArrayList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		if (position < 0 || position >= mIdMap.size())
		{
			return INVALID_ID;
		}
		Object item = getItem(position);
		return mIdMap.get(item);
	}

	public boolean getState(int position)
	{
		Object item = getItem(position);
		return mSelectMap.get(item);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parents)
	{

		ImageView panelImage = null;
		PanelContentsListItem PanelContentsObject = (PanelContentsListItem) getItem(position);
		if (convertView == null)
		{
			inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.right_drawer_panel, parents, false);
			panelImage = (ImageView) convertView.findViewById(R.id.panel_image_view);
			panelImage.setImageBitmap(PanelContentsObject.getmPanel());
			return convertView;
		} else
		{
			panelImage = (ImageView) convertView.findViewById(R.id.panel_image_view);
			panelImage.setImageBitmap(PanelContentsObject.getmPanel());

			if (EDIT_MODE == true && position != (getCount() - 1))
			{
				convertView.findViewById(R.id.panel_select_effect).setVisibility(View.VISIBLE);
				ImageButton delButton = (ImageButton) convertView.findViewById(R.id.panel_delete_button);
				delButton.setVisibility(View.VISIBLE);
				delButton.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						mPanelArrayList.remove(position);
						notifyDataSetChanged();
					}
				});
				return convertView;
			} else
			{
				if (getState(position) == true)
				{
					convertView.findViewById(R.id.panel_select_effect).setVisibility(View.VISIBLE);
					convertView.findViewById(R.id.panel_delete_button).setVisibility(View.INVISIBLE);
					return convertView;
				} else
				{
					convertView.findViewById(R.id.panel_select_effect).setVisibility(View.INVISIBLE);
					convertView.findViewById(R.id.panel_delete_button).setVisibility(View.INVISIBLE);
					return convertView;
				}
			}
		}
	}
}
