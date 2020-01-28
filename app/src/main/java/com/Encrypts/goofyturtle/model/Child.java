package com.Encrypts.goofyturtle.model;


import com.google.gson.annotations.SerializedName;

public class Child {
    private String categoryName;
    private String siteURL;

    @SerializedName("categoryName")
    public String getCategoryName() { return categoryName; }
    @SerializedName("categoryName")
    public void setCategoryName(String value) { this.categoryName = value; }

    @SerializedName("siteUrl")
    public String getSiteURL() { return siteURL; }
    @SerializedName("siteUrl")
    public void setSiteURL(String value) { this.siteURL = value; }
}