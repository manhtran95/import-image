package com.mycompany.importimage;


public class ImageItem {
    private String imageDecodableString;
    private String title;

    public ImageItem(String imageDecodableString, String title) {
        super();
        this.imageDecodableString = imageDecodableString;
        this.title = title;
    }

    public String getImageDecodableString() {
        return imageDecodableString;
    }

    public void setImageDecodableString(String imageDecodableString) {
        this.imageDecodableString = imageDecodableString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
