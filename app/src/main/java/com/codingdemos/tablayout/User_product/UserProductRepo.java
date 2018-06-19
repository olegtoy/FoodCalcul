package com.codingdemos.tablayout.User_product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codingdemos.tablayout.All_product.CustomAdapter;
import com.codingdemos.tablayout.Product;

/**
 * Created by olegtojgildin on 06.05.2018.
 */

public class UserProductRepo {
    private UsersDBhelper dbHelper;

    public UserProductRepo(Context context) {
        dbHelper = new UsersDBhelper(context);
    }

    public void removeAll()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
        db.delete(Product.TABLE2, null, null);
    }
    public Cursor getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Product.KEY_ROWID + "," +
                Product.KEY_name + "," +
                Product.KEY_carbhydrates + "," +
                Product.KEY_fat + "," +
                Product.KEY_protein +"," +
                Product.KEY_Cal +
                ","+ Product.KEY_weigth+
                " FROM " + Product.TABLE2;


        Cursor cursor= db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }

    public Cursor  getStudentListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  rowid as " +
                Product.KEY_ROWID + "," +
                Product.KEY_name + "," +
                Product.KEY_carbhydrates + "," +
                Product.KEY_fat + "," +
                Product.KEY_protein + "," +
                Product.KEY_Cal+","+
                Product.KEY_weigth+
                " FROM " + Product.TABLE2+
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
