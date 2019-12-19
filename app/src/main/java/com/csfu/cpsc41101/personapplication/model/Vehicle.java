package com.csfu.cpsc41101.personapplication.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Vehicle extends PersistentObject {
    protected String mVIN;
    protected String mMake;
    protected String mModel;
    protected int mSsn;

    public Vehicle(String VIN, String make, String model, int ssn) {
        mVIN = VIN;
        mMake = make;
        mModel = model;
        mSsn = ssn;
    }

    public Vehicle() {}

    public String getVIN() {
        return mVIN;
    }

    public void setVIN(String VIN) {
        mVIN = VIN;
    }

    public String getMake() {
        return mMake;
    }

    public void setMake(String make) {
        mMake = make;
    }

    public String getModel() {
        return mModel;
    }

    public void setModel(String model) {
        mModel = model;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("Make", mMake);
        vals.put("Model", mModel);
        vals.put("VIN", mVIN);
        vals.put("Owner", mSsn);
        db.insert("Person", null, vals);
    }
    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Vehicle (Make Text, Model Text, Vin Text, Owner INTEGER)");
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mMake = c.getString(c.getColumnIndex("Make"));
        mModel = c.getString(c.getColumnIndex("Model"));
        mVIN = c.getString(c.getColumnIndex("Vin"));
        mSsn = c.getInt(c.getColumnIndex("Owner"));
    }
}

