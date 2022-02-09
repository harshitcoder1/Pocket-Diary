package com.example.harshit.projectdemo;

import android.content.Context;;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyAdapter{

    MyHelper helper;
    Context context;
    static final String ID="_id";
    static final String DATE="date";
    static final String AMOUNT="amount";
    static final String DESCRIPTION="description";
    MyAdapter(Context context)
    {
        this.context=context;
        helper=new MyHelper(context);
    }
    void  insertData1(String date,int amount,String description){
        SQLiteDatabase db=helper.getWritableDatabase();
       try {
           db.execSQL("insert into expenses(date,amount,description) values('"+date+"',"+amount+",'"+description+"');");
           Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show();
       }catch (Exception e)
       {
           Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();
       }
    }
    void insertData2(String date,int amount,String description){
        SQLiteDatabase db=helper.getWritableDatabase();
        try {
            db.execSQL("insert into income(date,amount,description) values('"+date+"',"+amount+",'"+description+"');");
            Toast.makeText(context,"saved",Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
           Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();
        }
    }
    Cursor getData1()
    {
        String startdate=MainActivity.getInstance().getStartDate(MainActivity.getContext());
        String enddate=MainActivity.getInstance().getEndDate(MainActivity.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from expenses where date<='"+enddate+"' and date>='"+startdate+"' order by date desc,amount;", null);
        return cursor;
    }
    Cursor getData2()
    {
        String startdate=MainActivity.getInstance().getStartDate(MainActivity.getContext());
        String enddate=MainActivity.getInstance().getEndDate(MainActivity.getContext());
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from income where date>='"+startdate+"' and date<='"+enddate+"' order by date desc,amount;", null);
        return cursor;
    }
    void updateData1(int id,String date,int amount,String desc)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            db.execSQL("update expenses set date = '"+date+"', amount = "+amount+", description = '"+desc+"' where _id = "+id+"");
            Toast.makeText(context,"transaction updated",Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();}
    }

    void updateData2(int id,String date,int amount,String desc)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            db.execSQL("update income set date = '"+date+"', amount = "+amount+", description = '"+desc+"' where _id = "+id+"");
            Toast.makeText(context,"transaction updated",Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();}
    }

    void deleteData1(int id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            db.execSQL("delete from expenses where _id = "+id+"");
            Toast.makeText(context,"transaction deleted",Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();}
    }

    void deleteData2(int id)
    {
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            db.execSQL("delete from income where _id = "+id+"");
            Toast.makeText(context,"transaction deleted",Toast.LENGTH_SHORT).show();
        }catch (Exception e){Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();}
    }

    public class MyHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "project_database";
        private static final int DATABASE_Version = 1;
        private Context context;
        public MyHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL("create table expenses(_id integer primary key  autoincrement,date date,amount integer,description varchar(255));");
                db.execSQL("create table income(_id integer primary key  autoincrement,date date,amount integer,description varchar(255));");
                Toast.makeText(context,"table is created",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(context," "+e,Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}
    }
}
