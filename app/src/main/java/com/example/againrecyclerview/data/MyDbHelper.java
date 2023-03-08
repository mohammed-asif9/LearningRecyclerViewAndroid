package com.example.againrecyclerview.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MyDbHelper extends SQLiteOpenHelper {

    Context context;

    public MyDbHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create = "CREATE TABLE "+Constants.TABLE_NAME +"("+
                Constants.COLUMN_ID+" INTEGER PRIMARY KEY ," +
                Constants.COLUMN_NAME +" TEXT ,"+
                Constants.COLUMN_PRIORITY + " TEXT)";

        sqLiteDatabase.execSQL(create);
        Log.d("dbAsif","table is created");
        Toast.makeText(context, "db created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_NAME,task.getmTaskName());
        contentValues.put(Constants.COLUMN_PRIORITY,task.getmTaskPriority());

        db.insert(Constants.TABLE_NAME,null,contentValues);
        db.close();

        Log.d("dbAsif","A new task  is added in the table");

    }

    public List<List<String>> fetchAllRows(){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+Constants.TABLE_NAME;

        Cursor cursor=db.rawQuery(query,null);
        Log.d("dbAsif","Fetch All Rows");


        List<List<String>> rows = new ArrayList<>();
        while(cursor.moveToNext()){
            Log.d("dbAsif","Fetching rows");
            List<String> row = new ArrayList<>();
            int name=cursor.getColumnIndex(Constants.COLUMN_NAME);
            int pri=cursor.getColumnIndex(Constants.COLUMN_PRIORITY);
            row.add(cursor.getString(name));
            row.add(cursor.getString(pri));
            rows.add(row);
            Log.d("dbAsif",cursor.getInt(0)+" "+cursor.getString(name)+" "+cursor.getString(pri));
        }
        cursor.close();
        return rows;
    }

    public int updateTask(Task task){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_NAME,task.getmTaskName());
        contentValues.put(Constants.COLUMN_PRIORITY,task.getmTaskPriority());

        return db.update(Constants.TABLE_NAME,contentValues,Constants.COLUMN_ID+"=?"
                ,new String[]{String.valueOf(task.getmId())});

    }

    public int deleteTask(){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Constants.TABLE_NAME,Constants.COLUMN_ID+"=?",
                new String[]{"6"});


    }

}
