package com.example.fragment;

import com.example.slidemenu.R;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class CategoryButtonFragment extends Fragment
{
	private LinearLayout userCategoryButton;
	private LinearLayout storyCategoryButton;
	private EditText editTextSearch;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View fragmentView = inflater.inflate(R.layout.category_button_fragment, container, false);
		userCategoryButton = (LinearLayout) fragmentView.findViewById(R.id.user_category_button_search);
		storyCategoryButton = (LinearLayout) fragmentView.findViewById(R.id.story_category_button_search);
		userCategoryButton.setOnClickListener(SearchCategoryclickListener);
		storyCategoryButton.setOnClickListener(SearchCategoryclickListener);
		
		return fragmentView;
	}

	View.OnClickListener SearchCategoryclickListener = new OnClickListener()
	{

		@Override
		public void onClick(View view)
		{
			if (view instanceof LinearLayout)
			{
				switch (view.getId())
				{
				case R.id.user_category_button_search:
					storyCategoryButton.setBackgroundResource(R.drawable.search_tab_button_01);
					userCategoryButton.setBackgroundResource(R.drawable.search_tab_button_02);
					getFragmentManager().beginTransaction().replace(R.id.Search_List_container, new UserSearchListFragment(null, 0, null)).commit();
					break;

				case R.id.story_category_button_search:
					storyCategoryButton.setBackgroundResource(R.drawable.search_tab_button_02);
					userCategoryButton.setBackgroundResource(R.drawable.search_tab_button_01);
					getFragmentManager().beginTransaction().replace(R.id.Search_List_container, new StorySearchListFragment(null, 0, null)).commit();
					break;

				default:
					break;
				}
			}
		}
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		editTextSearch = (EditText) getActivity().findViewById(R.id.edit_text_search);
		// When user input some text
		editTextSearch.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				Log.e("onTextChanged", s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				Log.e("beforeTextChanged", s.toString());
				
			}

			@Override
			public void afterTextChanged(Editable s)
			{
				Log.e("afterTextChanged", s.toString());

			}
		});

		// after input some text and press enter key
		editTextSearch.setOnEditorActionListener(new OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				Log.e("onEditorAction", v.getText().toString());
				return false;
			}
		});
	}
}
