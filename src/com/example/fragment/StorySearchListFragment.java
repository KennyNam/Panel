package com.example.fragment;

import com.example.slidemenu.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StorySearchListFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.story_search_list_fragment, container, false);
		return fragmentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}
}
