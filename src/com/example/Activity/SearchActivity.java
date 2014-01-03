package com.example.Activity;

import com.example.fragment.CategoryButtonFragment;
import com.example.fragment.SearchBarFragment;
import com.example.fragment.UserSearchListFragment;
import com.example.slidemenu.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class SearchActivity extends	FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		getActionBar().hide();
		getSupportFragmentManager().beginTransaction().add(R.id.search_bar_container, new SearchBarFragment()).commit();
		getSupportFragmentManager().beginTransaction().add(R.id.category_button_container, new CategoryButtonFragment()).commit();
		getSupportFragmentManager().beginTransaction().add(R.id.Search_List_container, new UserSearchListFragment(null, 0, this)).commit();
	}
}
