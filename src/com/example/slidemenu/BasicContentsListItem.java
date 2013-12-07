package com.example.slidemenu;

import android.widget.ImageView;

public class BasicContentsListItem
{
	private int imageResource;
	private String contentText;

	public BasicContentsListItem(int resource, String content_text)
	{
		setImageResource(resource);
		setcontentText(content_text);
	}

	public void setImageResource(int resource)
	{
		this.imageResource = resource;
	}

	public void setcontentText(String content_text)
	{
		this.contentText = content_text;
	}

	public int getImageResource()
	{
		return imageResource;
	}

	public String getContentText()
	{
		return contentText;
	}
}
