package com.example.mysqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText genderText, nameEditText, ageEditText, idEditText;
    private Button addButton, displayAllDataButton,updateButton, deleteButton;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase =  myDatabaseHelper.getWritableDatabase();

        nameEditText = findViewById(R.id.nameEditTextId);
        genderText = findViewById(R.id.genderEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        idEditText = findViewById(R.id.idEditTextId);

        addButton = findViewById(R.id.addButtonId);
        displayAllDataButton = findViewById(R.id.displayAllDataButtonId);
        updateButton = findViewById(R.id.updateDataButtonId);
        deleteButton = findViewById(R.id.deleteDataButtonId);


        addButton.setOnClickListener(this);
        displayAllDataButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String name = nameEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String gender = genderText.getText().toString();
        String id = idEditText.getText().toString();

        if(v.getId()==R.id.addButtonId) {

            long rowId = myDatabaseHelper.insertData(name,age,gender);
            if(rowId ==-1) {
                Toast.makeText(getApplicationContext(), " unsuccessful", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Row " + rowId + " is successfully inserted", Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId()==R.id.displayAllDataButtonId) {
            Cursor cursor = myDatabaseHelper.displayAllData();

            if( cursor.getCount() == 0 ) {
                // There is no data so we  will display messages
                showData("Error "," No Data Found\n");
                return ;
            }

            StringBuffer stringBuffer = new StringBuffer();
            while(cursor.moveToNext())
            {
                stringBuffer.append("ID" + cursor.getString(0) + "\n");
                stringBuffer.append(" Name" + cursor.getString(1)+ "\n");
                stringBuffer.append(" Age" + cursor.getString(2)+ "\n");
                stringBuffer.append(" Gender" + cursor.getString(3)+ "\n\n\n");
            }

            showData("ResultSet",stringBuffer.toString());
        }

        if( v.getId() == R.id.updateDataButtonId ) {
            Boolean isUpdated =  myDatabaseHelper.updateData(id,name,age,gender);

            if(isUpdated) {
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Data is not updated!", Toast.LENGTH_LONG).show();
            }
        }

        if(v.getId()== R.id.deleteDataButtonId) {

            int value  = myDatabaseHelper.deleteData(id);

            if(value > 0) {
                Toast.makeText(getApplicationContext(), "Data is Deleted", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Data is not Deleted", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }
}