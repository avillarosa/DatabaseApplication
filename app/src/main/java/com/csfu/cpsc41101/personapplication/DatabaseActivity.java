package com.csfu.cpsc41101.personapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

public class DatabaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        File dbFile = this.getDatabasePath("test02.db");
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
        // Create Person table
        db.execSQL("CREATE TABLE IF NOT EXISTS Person (FirstName Text, LastName Text, SSN INTEGER)");
        //
        db.execSQL("DELETE FROM Person WHERE SSN=?", new String[]{"667448555"});
        db.execSQL("INSERT INTO Person VALUES ('James', 'Shen', 667448555)");
        //
        db.execSQL("DELETE FROM Person WHERE SSN=?", new String[]{"353666474"});
        db.execSQL("INSERT INTO Person VALUES (?,?,?)", new String[]{"Mary", "Hsu", "353666474"});
        //
        db.delete("Person", "SSN=?", new String[]{"757484499"});
        ContentValues vals = new ContentValues();
        vals.put("FirstName", "Jerry");
        vals.put("LastName", "Ramos");
        vals.put("SSN", 757484499);
        db.insert("Person", null, vals);
        //
        Cursor cursor = db.query("Person",null, null, null, null,null,null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                String fName = cursor.getString(cursor.getColumnIndex("FirstName"));
                int ssn = cursor.getInt(cursor.getColumnIndex("SSN"));
                //
                Log.d("Database Log", fName + " : " + ssn);
            }
        }
        //
        db.execSQL("UPDATE Person SET FirstName =? WHERE SSN=?", new String[]{"John", "667448555"});
        //
        vals = new ContentValues();
        vals.put("FirstName", "Rudy");
        db.update("Person", vals, "SSN=?", new String[]{"757484499"});

        db.close();
    }
}
