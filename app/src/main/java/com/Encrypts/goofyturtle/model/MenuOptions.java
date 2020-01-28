package com.Encrypts.goofyturtle.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuOptions {
    @SerializedName("CatagoryName")
    private String catagoryName;
    @SerializedName("SiteUrl")

    private String siteURL;

    @SerializedName("child")
    private List<Child> child;


    public String getCatagoryName() { return catagoryName; }
    public void setCatagoryName(String value) { this.catagoryName = value; }

    public String getSiteURL() { return siteURL; }
    public void setSiteURL(String value) { this.siteURL = value; }

    public List<Child> getChild() { return child; }
    public void setChild(List<Child> value) { this.child = value; }
}


