package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;


public class DataBaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase dataBase;

    public DataBaseHelper(Context context) {
        super(context, ApiConstance.dataBase, null, 1);
        try {
            dataBase = context.openOrCreateDatabase(ApiConstance.dataBase, Context.MODE_PRIVATE, null);
            createTable();
        }catch (Exception error){
            error.printStackTrace();
        }
    }

    private void createTable(){
        try {
            System.out.println("Create Table");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + ApiConstance.typeTable + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "description TEXT);");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + ApiConstance.materialTable + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "Type_Id INTEGER," + "description TEXT," + " FOREIGN KEY (id) REFERENCES " + ApiConstance.typeTable + " (Type_Id ));");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + ApiConstance.agentTable + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT," + "description TEXT);");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + ApiConstance.invoiceTable + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "agent_id INTEGER ," + "date TEXT," + "isBuy INTEGER," + "description TEXT," + " FOREIGN KEY (id) REFERENCES " + ApiConstance.agentTable + " (agent_id ));");
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS " + ApiConstance.invoiceDetailTable + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "invoice_id INTEGER ," + "material_id INTEGER," + "number INTEGER," + "price REAL," + "total REAL," + " FOREIGN KEY (id) REFERENCES " + ApiConstance.invoiceTable + " (invoice_id )," + " FOREIGN KEY (id) REFERENCES " + ApiConstance.materialTable + " (material_id ));");
        }catch (Exception error) {
            System.out.print(error.getMessage());
        }
    }

    long insert(String tableName, ContentValues contentValues){
        try {
            return dataBase.insert(tableName,null,contentValues);
        }catch (Exception error) {
            System.out.println("Error in insert Methode "+ error.getMessage());
            return 0;
        }
    }

    Cursor select(String query){
        try {
            dataBase = getReadableDatabase();
            return dataBase.rawQuery(query,null);
        }catch (Exception error) {
            System.out.print(error.getMessage());
            return null;
        }
    }

    int delete(String tableName,String whereClause,String[] whereArgs){
        try {
            dataBase = getWritableDatabase();
            return dataBase.delete(tableName,whereClause,whereArgs);
        }catch (Exception error){
            error.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dataBase = db;
        insertAgent();
        insertType();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println(oldVersion);

    }

    private void insertAgent(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("description","customer");
        contentValues.put("type","1");
        contentValues.put("name","ahmad");
        System.out.println(insert(ApiConstance.agentTable,contentValues));
        contentValues = new ContentValues();
        contentValues.put("description","resource");
        contentValues.put("type","0");
        contentValues.put("name","hennas");
        System.out.println(insert(ApiConstance.agentTable,contentValues));
    }
    private void insertType(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("description","Order Sales");
        contentValues.put("name","Sales");
        System.out.println(insert(ApiConstance.typeTable,contentValues));
        contentValues = new ContentValues();
        contentValues.put("description","Order Purchases");
        contentValues.put("name","Purchases");
        System.out.println(insert(ApiConstance.typeTable,contentValues));
    }
}
