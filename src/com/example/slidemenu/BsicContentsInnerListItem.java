package com.example.slidemenu;

public class BsicContentsInnerListItem
{
    private String contentText;

    public BsicContentsInnerListItem(String content_text)
    {
        setcontentText(content_text);
    }

    public void setcontentText(String content_text)
    {
        this.contentText = content_text;
    }

    public String getContentText()
    {
        return contentText;
    }
}

