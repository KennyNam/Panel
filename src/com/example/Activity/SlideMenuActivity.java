package com.example.Activity;

import java.util.ArrayList;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.example.adapter.LeftDrawerListAdapter;
import com.example.adapter.RightDrawerListAdapter;
import com.example.customview.CustomHorizonScrollViewPicker;
import com.example.customview.DynamicListView;
import com.example.fragment.BaseContainerFragment;
import com.example.fragment.SettingListviewFragment;
import com.example.slidemenu.BasicContentsListItem;
import com.example.slidemenu.PanelContentsListItem;
import com.example.slidemenu.R;
import com.example.slidemenu.SettingComponentItem;

import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class SlideMenuActivity extends Activity
{
	private DrawerLayout mDrawerLayout;
	private ExpandableListView mLeftDrawerList;
	private DynamicListView mRightDrawerList;
	private FrameLayout mContentFrame;
	private int mWindowWidth;
	private int mLeftDrawerListWidth;
	private int mRightDrawerListWidth;
	private float fromXDelta = 0;
	private FrameLayout.LayoutParams layoutParams;
	private static int SEARCH_REQUEST_CODE = 0;
	private ArrayList<PanelContentsListItem> mPanelArrayList;
	private PanelContentsListItem presentSelectedItemPosition = null;
	private RightDrawerListAdapter mRightDrawerAdapter;
	private LeftDrawerListAdapter mLeftDrawerListAdapter;
	private ImageButton mPanelEditDoneButton;
	private boolean PANEL_EDIT_STATU = false;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_menu_activity);
		mContentFrame = (FrameLayout) findViewById(R.id.content_frame);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mPanelEditDoneButton = (ImageButton) findViewById(R.id.panel_edit_done_btn);
		mPanelEditDoneButton.setOnClickListener(editButtonClickListener);

		// check Display (It just check Display size), It's will removed
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		Log.e("displaywidth", String.valueOf(width));
		Log.e("displayheight", String.valueOf(height));

		// check left drawer Image's width to define LeftDrawer's width
		// I need "R.drawable.left_slide_momo_background" because it have same
		// width with whole LeftDrawer
		setDrawerWidth();
		mDrawerLayout.setScrimColor(color.transparent);
		mLeftDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);

		mRightDrawerList = (DynamicListView) findViewById(R.id.panel_list_view);
		mRightDrawerList.getLayoutParams().width = mLeftDrawerListWidth;
		SetRightDrawerPanelList();
		mRightDrawerAdapter = new RightDrawerListAdapter(this, mPanelArrayList);
		mRightDrawerList.setAdapter(mRightDrawerAdapter);
		mRightDrawerList.setPanelList(mPanelArrayList);
		mRightDrawerList.setOnItemClickListener(ListViewOnClickListener);

		mLeftDrawerListAdapter = new LeftDrawerListAdapter(this);
		mLeftDrawerList.setAdapter(mLeftDrawerListAdapter);
		mLeftDrawerList.getLayoutParams().width = mLeftDrawerListWidth;
		
		mLeftDrawerList.setOnGroupClickListener(GroupClickListener);
		mLeftDrawerList.setOnChildClickListener(ChildClickListener);
		
		mDrawerLayout.setDrawerListener(mDrawerListener);

		getActionBar().hide();
		BaseContainerFragment baseContaiverFagment = new BaseContainerFragment();
		baseContaiverFagment.setArguments(getIntent().getExtras());
		getFragmentManager().beginTransaction().add(R.id.content_frame, baseContaiverFagment).commit();
	}

    private AdapterView.OnItemClickListener ListViewOnClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> AdapterView, View convertView, int position, long arg3) {
            PanelContentsListItem PanelContentsObject = (PanelContentsListItem) mRightDrawerAdapter.getItem(position);
            if (position == AdapterView.getCount() - 1) {
                mRightDrawerList.addPanel();
            } else {
                if (presentSelectedItemPosition != null) {
                    mRightDrawerAdapter.setTagSelectToggle(presentSelectedItemPosition);
                }
                mRightDrawerAdapter.setTagSelectToggle(PanelContentsObject);
                presentSelectedItemPosition = PanelContentsObject;
            }

        }
    };
    
    
    //LeftDrawer inner Genre click listener
    ExpandableListView.OnChildClickListener ChildClickListener = new OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            return false;
        }
    }; 
    
    ExpandableListView.OnGroupClickListener GroupClickListener = new OnGroupClickListener() {

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            int tag = mLeftDrawerListAdapter.getTag((BasicContentsListItem) mLeftDrawerListAdapter.getGroup(groupPosition));
            switch (tag) {
            case 0:
                confirmDrawerState(GravityCompat.START);
                Intent intentToSearchActivity = new Intent(SlideMenuActivity.this, SearchActivity.class);
                startActivityForResult(intentToSearchActivity, SEARCH_REQUEST_CODE);
                break;

            case 7:
                confirmDrawerState(GravityCompat.START);
                ArrayList<SettingComponentItem> arrList = new ArrayList<SettingComponentItem>();
                String[] settingComponenttext = new String[] { "Report problem", "Blog", "Privacy problem",
                        "Terms & Service", "About", "Social connection", "Edit profile", "Log out" };
                for (int i = 0; i < settingComponenttext.length; i++) {
                    arrList.add(new SettingComponentItem(settingComponenttext[i]));
                }
                SettingListviewFragment settingListviewFragment = new SettingListviewFragment(arrList,
                        R.layout.setting_listview_component, SlideMenuActivity.this);
                getFragmentManager().beginTransaction().add(R.id.main_container, settingListviewFragment).commit();
                break;

            default:
                break;
            }

            return false;
        }
    };
    
	private void SetRightDrawerPanelList()
	{
		Bitmap testImage0 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon0);
		Bitmap testImage1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon1);
		Bitmap testImage2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon2);
		Bitmap testImage3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon3);
		Bitmap testImage4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon4);
		Bitmap testImage5 = BitmapFactory.decodeResource(this.getResources(), R.drawable.left_slide_icon5);
		Bitmap addImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.right_slide_add_image);
		mPanelArrayList = new ArrayList<PanelContentsListItem>();
		mPanelArrayList.add(new PanelContentsListItem(testImage0));
		mPanelArrayList.add(new PanelContentsListItem(testImage1));
		mPanelArrayList.add(new PanelContentsListItem(testImage2));
		mPanelArrayList.add(new PanelContentsListItem(testImage3));
		mPanelArrayList.add(new PanelContentsListItem(testImage4));
		mPanelArrayList.add(new PanelContentsListItem(testImage5));
		mPanelArrayList.add(new PanelContentsListItem(testImage0));
		mPanelArrayList.add(new PanelContentsListItem(addImage));
	}

	View.OnClickListener editButtonClickListener = new OnClickListener()
	{

		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.panel_edit_done_btn:
				if (PANEL_EDIT_STATU == false)
				{
					mRightDrawerAdapter.setEditMode(true);
					mPanelEditDoneButton.setImageResource(R.drawable.right_side_done_btn);
					PANEL_EDIT_STATU = true;
				} else
				{
					mRightDrawerAdapter.setEditMode(false);
					mPanelEditDoneButton.setImageResource(R.drawable.right_side_edit_btn);
					PANEL_EDIT_STATU = false;
				}
				break;

			default:
				break;
			}
		}
	};

	DrawerLayout.DrawerListener mDrawerListener = new DrawerListener()
	{

		@Override
		public void onDrawerStateChanged(int arg0)
		{

		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset)
		{
			layoutParams = (FrameLayout.LayoutParams) mContentFrame.getLayoutParams();

			switch (drawerView.getId())
			{
			case R.id.left_drawer:
				animationControler(slideOffset * mLeftDrawerListWidth);
				break;

			case R.id.right_drawer:
				animationControler(-(slideOffset * mRightDrawerListWidth));
				break;

			default:
				break;
			}
		}

		@Override
		public void onDrawerOpened(View arg0)
		{

		}

		@Override
		public void onDrawerClosed(View arg0)
		{

		}
	};

	/**
	 * To close Drawer correctly, Confirm resent state before operate
	 * 
	 * @param gravity
	 */
	private void confirmDrawerState(int gravity)
	{
		int reverseGravity = -1;
		if (gravity == GravityCompat.START)
		{
			reverseGravity = GravityCompat.END;
		} else
		{
			reverseGravity = GravityCompat.START;
		}

		if (mDrawerLayout.isDrawerOpen(gravity))
		{
			mDrawerLayout.closeDrawer(gravity);
		} else if (mDrawerLayout.isDrawerOpen(gravity) == false)
		{
			if (mDrawerLayout.isDrawerOpen(reverseGravity) == true)
			{
				mDrawerLayout.closeDrawer(reverseGravity);
			}
			mDrawerLayout.openDrawer(gravity);
		}
	}

	/**
	 * Control Drawer Animation
	 * 
	 * @param toXDelta
	 */
	private void animationControler(float toXDelta)
	{
		float gapBetweenToFrom = fromXDelta - toXDelta;
		Animation animationTranslateOption;

		if (gapBetweenToFrom > 0)
		{
			gapBetweenToFrom = -(gapBetweenToFrom);
		}

		animationTranslateOption = new TranslateAnimation(fromXDelta, toXDelta + gapBetweenToFrom, 0, 0);
		fromXDelta = toXDelta;
		animationTranslateOption.setFillAfter(true);
		mContentFrame.startAnimation(animationTranslateOption);
		animationTranslateOption = new TranslateAnimation(fromXDelta, toXDelta, 0, 0);
		animationTranslateOption.setFillAfter(true);
		mContentFrame.startAnimation(animationTranslateOption);
	}

	public void setDrawerWidth()
	{
		this.mRightDrawerListWidth = dpToPx(160);
		this.mLeftDrawerListWidth = dpToPx(180);
	}

	public int dpToPx(int dp)
	{
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
		int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK)
		{
			if (requestCode == SEARCH_REQUEST_CODE)
			{
				// doing something after SearchActivity finish
			}
		}
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);
	    CustomHorizonScrollViewPicker testSize = (CustomHorizonScrollViewPicker) findViewById(R.id.horizontal_scroll_view_picker);
	    testSize.setScrollX(testSize.getWidth()/2);
	}

}
