package com.example.slidemenu;

public class SettingComponentItem
{
	private String mText;

	public SettingComponentItem(String text){
		this.mText = text;
	};
	
	public void setText(String text)
	{
		this.mText = text;
	}
	
	public String getText()
	{
		return mText;
	}
	
}
