package com.codingdemos.tablayout.All_product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.User_product.UsersDBhelper;

import java.io.IOException;

/**
 * Created by olegtojgildin on 06.05.2018.
 */

public class ProductRepo {
    private DBhelper dbHelper;
private  UsersDBhelper dbuser;
    public ProductRepo(Context context) {
        dbHelper = new DBhelper(context);
         dbuser=new UsersDBhelper(context);
        try {
            dbHelper.updateDataBase();
        }
        catch (IOException ex){

        }
    }

    public Cursor getStudentList() {
        try {
            dbHelper.updateDataBase();
        }
        catch (IOException ex){

        }
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Product.KEY_ROWID + "," +
                Product.KEY_category+ "," +
                Product.KEY_name + "," +
                Product.KEY_carbhydrates + "," +
                Product.KEY_fat + "," +
                Product.KEY_protein + "," +
                Product.KEY_Cal+


                " FROM " + Product.TABLE;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    public void addProduct(Product product) {
        Log.d("...", "ad3333ппппппппппdd = ");

        SQLiteDatabase dbuserSQ = dbuser.getReadableDatabase();
        ContentValues values = new ContentValues();
        Log.d("...", "add = " + product.getName());
        values.put(Product.KEY_name, product.getName());
        values.put(Product.KEY_carbhydrates, product.getCarbohydrates());
        values.put(Product.KEY_fat, product.getFat());
        values.put(Product.KEY_protein, product.getProtein());
        values.put(Product.KEY_Cal, product.getCal());
        values.put(Product.KEY_weigth, product.getWeigth());
        dbuserSQ.insert(Product.TABLE2, null, values);
        dbuserSQ.close();
    }
    public Cursor  getStudentListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Product.KEY_ROWID + "," +
                Product.KEY_name + "," +
                Product.KEY_carbhydrates + "," +
                Product.KEY_fat + "," +
                Product.KEY_protein +","+
                Product.KEY_Cal +
                " FROM " + Product.TABLE+
                " WHERE " +  Product.KEY_name + "  LIKE  '%" +search + "%' "
                ;


        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }



}
