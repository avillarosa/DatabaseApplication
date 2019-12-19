package com.csfu.cpsc41101.personapplication.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

public class PersonDB {
    //private static final PersonDB ourInstance = new PersonDB();

    protected ArrayList<Person> mPeople;

    //static public PersonDB getInstance() {
    //    return ourInstance;
    //}

    SQLiteDatabase mSQLiteDatabase;

    public PersonDB(Context context) {

        File dbFile = context.getDatabasePath("person.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

        // Create the Person and Vehicle tables if necessary
        new Person().createTable(mSQLiteDatabase);
        new Vehicle().createTable(mSQLiteDatabase);

        // createPersonObjects();
    }

    public ArrayList<Person> getPeople() {
        return mPeople;
    }

    public void setPeople(ArrayList<Person> people) {
        mPeople = people;
    }

    public ArrayList<Person> retrievePersonObjects() {
        mPeople = new ArrayList<Person>();
        Cursor cursor = mSQLiteDatabase.query("Person", null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Person pObj = new Person();
                pObj.initFrom(mSQLiteDatabase, cursor);
                mPeople.add(pObj);
            }
        }
        return mPeople;
    }

    protected void createPersonObjects() {
        // Create James Person object
        Person p = new Person("James", "Shen", 74848888);
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("J899999999", "Honda", "Accord",74848888));
        p.setVehicles(vehicles);
        p.insert(mSQLiteDatabase);

        mPeople = new ArrayList<Person>();
        mPeople.add(p);

        // Create Another Person
        p = new Person("John", "Chang", 774888899);
        vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("J47474747", "Toyota", "Camry", 774888899));
        p.setVehicles(vehicles);
        p.insert(mSQLiteDatabase);

        mPeople.add(p);
        //
        //PersonDB.getInstance().setPeople(personList);
    }

}
