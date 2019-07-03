package com.example.pizzaloop.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pizzaloop.Login.Contact;

public class DatabaseHelper extends SQLiteOpenHelper {


    private  static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";

    private static final String COLUMN_PNO="phoneno";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_PASSWORD="pass";
   SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts (id integer primary key not null  , "+
                                "phoneno text not null , uname text not null , pass text not null );";

  public DatabaseHelper(Context context){
      super(context, DATABASE_NAME , null ,DATABASE_VERSION);
  }


    @Override
    public void onCreate(SQLiteDatabase db) {
  db.execSQL(TABLE_CREATE);
  this.db=db;
    }
public void insertContact(Contact c){

db = this.getWritableDatabase();
    ContentValues values = new ContentValues();

    String query = "select * from contacts ";
    Cursor cursor = db.rawQuery(query,null);
    int count = cursor.getCount();

    values.put(COLUMN_ID , count);
    values.put(COLUMN_PNO,c.getPhoneNum());
    values.put(COLUMN_UNAME,c.getUname());
    values.put(COLUMN_PASSWORD,c.getPass());

    db.insert(TABLE_NAME,null, values);
    db.close();
}

public String searchPass(String phoneno){
      db = this.getReadableDatabase();
      String query = "select phoneno, pass from "+TABLE_NAME;
    Cursor cursor = db.rawQuery(query , null);
String a ,b ;
b= "not found";
    if(cursor.moveToFirst())
    {
        do{
            a = cursor.getString(0);
               if(a.equals(phoneno)){
                   b= cursor.getString(1);

                   break;
               }

        }while (cursor.moveToNext());
    }
    return b;
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public Cursor getAllData(){
        db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
