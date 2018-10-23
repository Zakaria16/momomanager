package com.mazitekgh.mtnmomo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * MtnMomo
 * Created by Zakaria on 03-Sep-18 at 7:02 PM.
 */
public class Momo implements Parcelable {

    public static final Creator<Momo> CREATOR = new Creator<Momo>() {
        @Override
        public Momo createFromParcel(Parcel in) {
            return new Momo(in);
        }

        @Override
        public Momo[] newArray(int size) {
            return new Momo[size];
        }
    };
    private String dateStr;
    private String contentStr;
    private String sender;
    private String txID;
    private String currentBalance;
    private String amount;
    private int type;
    private String reference;


    public Momo() {
    }


    public Momo(int type, String dateStr, String contentStr, String senderReceiver, String txID, String currentBalance, String amount, String reference) {
        this.type = type;
        this.dateStr = dateStr;
        this.contentStr = contentStr;
        this.sender = senderReceiver;
        this.txID = txID;
        this.currentBalance = currentBalance;
        this.amount = amount;
        this.reference = reference;

    }

    private Momo(Parcel in) {
        dateStr = in.readString();
        contentStr = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dateStr);
        dest.writeString(contentStr);
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTxID() {
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }


}