package com.csfu.cpsc41101.personapplication.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Person extends PersistentObject {
    protected String mFirstName;
    protected String mLastName;
    protected int mSsn;
    protected ArrayList<Vehicle> mVehicles;

    public Person(String fName, String lName, int ssn) {
        mFirstName = fName;
        mLastName = lName;
        mSsn = ssn;
    }

    public Person() {}

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public ArrayList<Vehicle> getVehicles() {
        return mVehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        mVehicles = vehicles;
    }

    @Override
    public void insert(SQLiteDatabase db) {
        ContentValues vals = new ContentValues();
        vals.put("FirstName", mFirstName);
        vals.put("LastName", mLastName);
        vals.put("SSN", mSsn);
        db.insert("Person", null, vals);
        for (int i=0; i<mVehicles.size(); i++) {
            mVehicles.get(i).insert(db);
        }
    }

    @Override
    public void initFrom(SQLiteDatabase db, Cursor c) {
        mFirstName = c.getString(c.getColumnIndex("FirstName"));
        mLastName = c.getString(c.getColumnIndex("LastName"));
        mSsn = c.getInt(c.getColumnIndex("SSN"));

        // construct the vehicle objects owned by the person
        mVehicles = new ArrayList<Vehicle>();
        Cursor cursor = db.query("Vehicle", null, "Owner=?", new String[]{new Integer(mSsn).toString()}, null, null, null);
        if (cursor.getCount() >0) {
            while (cursor.moveToNext()) {
                Vehicle vObj = new Vehicle();
                vObj.initFrom(db, cursor);
            }
        }
    }

    @Override
    public void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Person (FirstName Text, LastName Text, SSN INTEGER)");
    }
}
