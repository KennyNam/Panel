package com.example.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.slidemenu.R;

@SuppressLint("NewApi")
public class TopMenuBarFragment extends Fragment
{
	private ImageButton mLeftDrawerButton;
	private ImageButton mRightDrawerButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.top_menu_bar_fragment, container, false);
		mLeftDrawerButton = (ImageButton) fragmentView.findViewById(R.id.left_drawer_btn);
		mRightDrawerButton = (ImageButton) fragmentView.findViewById(R.id.right_drawer_btn);
		mLeftDrawerButton.setOnClickListener(DrawerButtonListener);
		mRightDrawerButton.setOnClickListener(DrawerButtonListener);

		return fragmentView;
	}

	// Open & Close SlideMenu Listener
	Button.OnClickListener DrawerButtonListener = new Button.OnClickListener()
	{
		@SuppressLint("NewApi")
		@Override
		public void onClick(View v)
		{
			DrawerLayout mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
			LinearLayout mContentFrame = (LinearLayout) getActivity().findViewById(R.id.Base_container_inner);

			switch (v.getId())
			{
			case R.id.left_drawer_btn:
				if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
				{
					mDrawerLayout.closeDrawer(GravityCompat.START);
				} else if (mDrawerLayout.isDrawerOpen(GravityCompat.START) == false)
				{
					if (mDrawerLayout.isDrawerOpen(GravityCompat.END) == true)
					{
						mDrawerLayout.closeDrawer(GravityCompat.END);
					}
					mDrawerLayout.openDrawer(GravityCompat.START);

				}
				break;

			case R.id.right_drawer_btn:
				mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.END);
				if (mDrawerLayout.isDrawerOpen(GravityCompat.END))
				{
					mDrawerLayout.closeDrawer(GravityCompat.END);
				} else if (mDrawerLayout.isDrawerOpen(GravityCompat.END) == false)
				{
					if (mDrawerLayout.isDrawerOpen(GravityCompat.START) == true)
					{
						mDrawerLayout.closeDrawer(GravityCompat.START);
					}
					mDrawerLayout.openDrawer(GravityCompat.END);

				}
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().hide();
	}
}
