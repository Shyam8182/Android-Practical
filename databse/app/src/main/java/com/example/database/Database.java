package com.example.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class Database {
    public static final String DATABASE_NAME="studentdb";
    public static final String TABLE_NAME="stud";
    public static final int DATABASE_VERSION=1;

    public static final String ROLLNO="rollno";
    public static final String NAME="name";
    public static final String CITY="city";
    public static final String CPI="cpi";

    public static final String TABLE_CREATE="create table stud(rollno INTEGER, name Text, city Text, cpi Flot);";

    Context context=null;
    SQLiteDatabase db;
    databasehelper dbhelper;

    public Database(Context ctx) {
        // TODO Auto-generated constructor stub

        context =ctx;
        dbhelper=new databasehelper(ctx);
    }
    class databasehelper extends SQLiteOpenHelper
    {

        public databasehelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub

            db.execSQL(TABLE_CREATE);
            Log.d("Table is created", "Table is created");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS stud");
            onCreate(db);
        }

    }

    public Database open() throws SQLException
    {
        db=dbhelper.getWritableDatabase();
        return this;

    }
    public void close()
    {
        dbhelper.close();
    }

    public long insertdata(String sroll,String sname,String scity,String scpi)
    {
        Log.d("roll=", sroll);
        Log.d("Name=",sname);
        Log.d("City=",scity);
        Log.d("Cpi=",scpi);

        ContentValues intialvalues=new ContentValues();
        intialvalues.put(ROLLNO, sroll);
        intialvalues.put(NAME, sname);
        intialvalues.put(CITY, scity);
        intialvalues.put(CPI, scpi);
        Log.d("Inserted", "Successfully");
        return db.insert(TABLE_NAME, null, intialvalues);


    }

    public Cursor getRoll()
    {
        String query="select rollno from "+TABLE_NAME;
        Cursor cur=db.rawQuery(query,null);
        return cur;
    }

    public Cursor getStudentDetail(String rroll)
    {
        String query="select name, city, cpi from "+TABLE_NAME+" where rollno='"+rroll+"'";
        Log.d("Query=",query);
        Cursor cur=db.rawQuery(query,null);
        return cur;
    }
    public void UpdateStudentDetail(String srollno, String sname, String scity, String scpi)
    {
        ContentValues values=new ContentValues();//temporary data holder
        values.put(ROLLNO, srollno);
        values.put(NAME, sname);
        values.put(CITY, scity);
        values.put(CPI, scpi);

        db.update(TABLE_NAME, values, ROLLNO+" = ?",new String[]{srollno});

    }

    public void DeleteStudentDetail(String srollno) {


        db.delete(TABLE_NAME, ROLLNO + " = ?", new String[]{srollno});
    }
}
