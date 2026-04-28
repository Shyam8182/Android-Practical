package com.example.database;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Modify extends Activity {
    Database db;
    Spinner sp1;
    EditText edtname,edtcity,edtcpi;
    Button btnmodify,btndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);

        sp1=(Spinner)findViewById(R.id.sp1);

        btnmodify=(Button)findViewById(R.id.btnupdate);
        btndelete=(Button)findViewById(R.id.btndelete);

        edtname=(EditText)findViewById(R.id.edname);
        edtcity=(EditText)findViewById(R.id.edcity);
        edtcpi=(EditText)findViewById(R.id.edcpi);


        LoadSpinner();

        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                // TODO Auto-generated method stub
                String selected=parent.getItemAtPosition(pos).toString();
                Toast.makeText(Modify.this, "Selected"+selected, Toast.LENGTH_LONG).show();

                getDetail(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        btnmodify.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                UpdateStudent();

            }
        });

        btndelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DeleteStudent();
                LoadSpinner();
            }
        });

    }

    public void LoadSpinner()
    {
        List<String> labels=new ArrayList<String>();
        db=new Database(Modify.this);
        db.open();

        Cursor c=db.getRoll();

        if(c.moveToFirst())
        {
            do
            {
                labels.add(c.getString(0));//first column of database
                Log.d("Roll=",c.getString(0));
            }
            while(c.moveToNext());

            if(c!=null && !c.isClosed())
            {
                c.close();
            }

        }

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp1.setAdapter(dataAdapter);

        c.close();
        db.close();

    }

    public void getDetail(String rroll)
    {
        db=new Database(Modify.this);
        db.open();

        Cursor c=db.getStudentDetail(rroll);

        if(c.moveToFirst())
        {
            do
            {
                //labels.add(c.getString(0));//first column of database
                Log.d("Name=",c.getString(0));
                Log.d("City=",c.getString(1));
                Log.d("Cpi=",c.getString(2));
                edtname.setText(c.getString(0));
                edtcity.setText(c.getString(1));
                edtcpi.setText(c.getString(2));


            }
            while(c.moveToNext());

            if(c!=null && !c.isClosed())
            {
                c.close();
            }

        }



        c.close();
        db.close();
    }

    public void UpdateStudent()
    {
        String srollno=sp1.getSelectedItem().toString();
        String sname=edtname.getText().toString();
        String scity=edtcity.getText().toString();
        String scpi=edtcpi.getText().toString();

        db=new Database(Modify.this);
        db.open();
        db.UpdateStudentDetail(srollno, sname, scity, scpi);
        db.close();
        Toast.makeText(Modify.this,"Updated",Toast.LENGTH_LONG).show();

    }

    public void DeleteStudent()
    {
        String srollno=sp1.getSelectedItem().toString();
        db=new Database(Modify.this);
        db.open();
        db.DeleteStudentDetail(srollno);
        db.close();
        Toast.makeText(Modify.this, "Deleted", Toast.LENGTH_LONG).show();
    }

}
