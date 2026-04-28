package com.example.database;
import com.example.database.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class Second extends Activity {
    Button btn1;
    TextView tvroll,tvname,tvcity,tvcpi;
    EditText edtroll,edtname,edtcity,edtcpi;
    String s1,s2,s3,s4;
    public Second() {
        // TODO Auto-generated constructor stub


    }
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);
        btn1=(Button)findViewById(R.id.button1);

        edtroll=(EditText)findViewById(R.id.edtroll);
        edtname=(EditText)findViewById(R.id.edtname);
        edtcity=(EditText)findViewById(R.id.edtcity);
        edtcpi=(EditText)findViewById(R.id.edtcpi);

        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                s1=edtroll.getText().toString().trim();
                s2=edtname.getText().toString().trim();
                s3=edtcity.getText().toString().trim();
                s4=edtcpi.getText().toString().trim();

                Database db=new Database(Second.this);
                db.open();
                db.insertdata(s1, s2, s3, s4);
                db.close();
                Toast.makeText(Second.this,"\n roll"+s1+"\n name"+s2+"\n city"+s3+"\n cpi"+s4 , Toast.LENGTH_LONG).show();

                edtroll.setText("");
                edtname.setText("");
                edtcity.setText("");
                edtcpi.setText("");
            }
        });
    }
}
