package com.example.Activity;

import com.example.fragment.CategoryButtonFragment;
import com.example.fragment.SearchBarFragment;
import com.example.fragment.UserSearchListFragment;
import com.example.slidemenu.R;

import android.app.Activity;
import android.os.Bundle;

public class SearchActivity extends	Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		getActionBar().hide();
		
		SearchBarFragment searchBarFragment = new SearchBarFragment();
		getFragmentManager().beginTransaction().add(R.id.search_bar_container, searchBarFragment).commit();
		
		CategoryButtonFragment categoryButtonFragment = new CategoryButtonFragment();
		getFragmentManager().beginTransaction().add(R.id.category_button_container, categoryButtonFragment).commit();
		
		UserSearchListFragment userSearchListFragment = new UserSearchListFragment();
		getFragmentManager().beginTransaction().add(R.id.Search_List_container, userSearchListFragment).commit();
	}
}
