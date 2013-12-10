package com.example.fragment;

import java.util.regex.Matcher;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.slidemenu.R;

@SuppressLint({ "ValidFragment", "SetJavaScriptEnabled" })
public class WebViewFragment extends Fragment
{
	private String mUrl;
	private WebView mWebView;
	
	public WebViewFragment(String url){
		this.mUrl = url;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.web_view_fragment, container, false);
		mWebView = (WebView) fragmentView.findViewById(R.id.web_view);
		WebSettings webSetting = mWebView.getSettings();
		webSetting.setSavePassword(false);
		webSetting.setSaveFormData(false);
		webSetting.setJavaScriptEnabled(true);
		webSetting.setSupportZoom(false);
		
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return false;
			}
		});
		mWebView.loadUrl(mUrl);
		return fragmentView;
	}
}
