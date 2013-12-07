package com.example.slidemenu;

import android.graphics.Bitmap;

public class PanelContentsListItem
{
	private Bitmap mPanel;
	
	public PanelContentsListItem(Bitmap mPanel)
	{
		this.mPanel = mPanel;
	}
	
	public void setmPanel(Bitmap mPanel)
	{
		this.mPanel = mPanel;
	}
	
	public Bitmap getmPanel()
	{
		return mPanel;
	}
}
