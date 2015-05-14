package com.example.kazimad.test_onix.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Kazimad on 07.05.2015.
 */
public class Item implements Parcelable {

    @SerializedName("_id")
    private int id;
    private String link;
    private String text;


    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.text);
        parcel.writeString(this.link);

    }

    public Item() {
    }

    private Item(Parcel in) {
        this.id = in.readInt();
        this.text = in.readString();
        this.link = in.readString();

    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
