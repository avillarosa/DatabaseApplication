package com.csfu.cpsc41101.personapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csfu.cpsc41101.personapplication.model.Person;
import com.csfu.cpsc41101.personapplication.model.PersonDB;
import com.csfu.cpsc41101.personapplication.model.Vehicle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPersonObjects();
        setContentView(R.layout.person_summary);

        root = findViewById(R.id.person_summary);

        ArrayList<Person> personList = new PersonDB(this).getPeople();
        for (int i=0; i<personList.size(); i++) {
            Person p = personList.get(i);
            LayoutInflater inflater = LayoutInflater.from(this);
            View row_view = inflater.inflate(R.layout.person_row, root, false);
            TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
            firstNameView.setText(p.getFirstName());
            TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
            lastNameView.setText(p.getLastName());
            //
            root.addView(row_view);
        }
        //
        // findViewById(R.id.first_name);
    }

    protected void createPersonObjects() {
        // Create James Person object
 /*       Person p = new Person("James", "Shen");
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("J899999999", "Honda", "Accord"));
        p.setVehicles(vehicles);
        ArrayList<Person> personList = new ArrayList<Person>();
        personList.add(p);
        // Create Another Person
        p = new Person("John", "Chang");
        vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("J47474747", "Toyota", "Camry"));
        p.setVehicles(vehicles);
        personList.add(p);
        //
        PersonDB.getInstance().setPeople(personList); */
    }

}
