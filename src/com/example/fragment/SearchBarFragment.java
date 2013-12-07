package com.example.fragment;

import android.app.Fragment;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.slidemenu.R;

public class SearchBarFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.search_bar_fragment, container, false);
		ImageButton btnBack = (ImageButton) fragmentView.findViewById(R.id.back_button_search);

		// When Back Button (in top menu)is clicked, back to SlideMenuActivity
		btnBack.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view)
			{
				Intent intent = getActivity().getIntent();
				getActivity().setResult(getActivity().RESULT_OK, intent);
				getActivity().finish();
			}
		});
		return fragmentView;
	}
}
