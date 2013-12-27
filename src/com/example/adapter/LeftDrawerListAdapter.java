package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.slidemenu.BasicContentsListItem;
import com.example.slidemenu.BsicContentsInnerListItem;
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
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeftDrawerListAdapter extends BaseExpandableListAdapter
{
	private Context mContext;
	private ArrayList<BasicContentsListItem> mBasicContentsItemList;
	private ArrayList<BsicContentsInnerListItem> mGenreInnerList;
	private HashMap<BasicContentsListItem, Integer> mTagMap = new HashMap<BasicContentsListItem, Integer>();
	private HashMap<BasicContentsListItem, ArrayList<BsicContentsInnerListItem>> mChildMap = new HashMap<BasicContentsListItem, ArrayList<BsicContentsInnerListItem>>();
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
		mBasicContentsItemList = new ArrayList<BasicContentsListItem>();
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_searchbox, null));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon0, "Momo"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon1, "Saved Work"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon2, "Most Popular"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon3, "Most Viewd"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon4, "New"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon5, "Genres"));
		mBasicContentsItemList.add(new BasicContentsListItem(R.drawable.left_slide_icon6, "Settings"));
		
		mGenreInnerList = new ArrayList<BsicContentsInnerListItem>();
		mGenreInnerList.add(new BsicContentsInnerListItem("genre1"));
		mGenreInnerList.add(new BsicContentsInnerListItem("genre2"));
		mGenreInnerList.add(new BsicContentsInnerListItem("genre3"));
		mGenreInnerList.add(new BsicContentsInnerListItem("genre4"));
		mGenreInnerList.add(new BsicContentsInnerListItem("genre5"));
		
		
		for (int i = 0; i < mBasicContentsItemList.size(); i++) {
		    if(mBasicContentsItemList.get(i).getContentText() == "Genres"){
		        mChildMap.put(mBasicContentsItemList.get(i), mGenreInnerList);
		    }else{
		        mChildMap.put(mBasicContentsItemList.get(i), new ArrayList<BsicContentsInnerListItem>());
		    }
		   
        }
	}

	private void setTag(){
		for (int i = 0; i < mBasicContentsItemList.size(); i++)
		{
			mTagMap.put(mBasicContentsItemList.get(i), i);
		}
	}
	
	public int getTag(BasicContentsListItem key){
		return mTagMap.get(key);
	}
	
	
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildMap.get(mBasicContentsItemList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            switch (groupPosition)
            {
            case 6:
                convertView = inflater.inflate(R.layout.left_drawer_inner_genre_list, parent, false);
                ((TextView) convertView.findViewById(R.id.inner_genre_text)).setText(((BsicContentsInnerListItem)getChild(groupPosition, childPosition)).getContentText());
                break;
            }
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildMap.get(mBasicContentsItemList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBasicContentsItemList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return mBasicContentsItemList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            BasicContentsListItem BasicContentsObject = (BasicContentsListItem) getGroup(groupPosition);

            switch (groupPosition)
            {
            case 0:
                convertView = inflater.inflate(R.layout.left_drawer_searchbox, parent, false);
                ImageView searchImage = (ImageView) convertView.findViewById(R.id.search_image);
                searchImage.setImageResource(BasicContentsObject.getImageResource());
                break;

            default:
                convertView = inflater.inflate(R.layout.left_drawer_basic_list, parent, false);
                ImageView iconImage = (ImageView) convertView.findViewById(R.id.basiclist_icon_image);
                TextView text = (TextView) convertView.findViewById(R.id.basiclist_text);
                iconImage.setImageResource(BasicContentsObject.getImageResource());
                text.setText(BasicContentsObject.getContentText());
                if (groupPosition == 1)
                {
                    LinearLayout basiclistLayout = (LinearLayout) convertView.findViewById(R.id.basiclist_layout_inner);
                    basiclistLayout.setBackgroundResource(R.drawable.left_slide_momo_background);
                }
                break;
            }
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
