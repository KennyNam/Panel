package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.slidemenu.R;

public class BaseContainerFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.base_container_frgment, container, false);
		initTop();
		return fragmentView;
	}

	private void initTop()
	{
		TopMenuBarFragment topMenuBar = new TopMenuBarFragment();
		getFragmentManager().beginTransaction().add(R.id.top_container, topMenuBar).commit();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		getActivity().getActionBar().hide();
	}
}
