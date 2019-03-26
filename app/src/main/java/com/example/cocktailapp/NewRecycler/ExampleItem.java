package com.example.cocktailapp.NewRecycler;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleItem implements Parcelable {

    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mDescricao;
    private String mReceita;
    private String mGarnish;


    public ExampleItem(int imageResource, String text1, String text2, String descricao, String receita ){

        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mDescricao = descricao;
        mReceita = receita;

    }

    protected ExampleItem(Parcel in) {
        mImageResource = in.readInt();
        mText1 = in.readString();
        mText2 = in.readString();
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };

    public int getmImageResource(){
        return mImageResource;
    }

    public String getText1(){
        return mText1;
    }

    public String getText2(){
        return mText2;
    }

    public String getmDescricao(){
        return mDescricao;
    }

    public String getmReceita(){
        return mReceita;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mImageResource);
        parcel.writeString(mText1);
        parcel.writeString(mText2);
    }
}