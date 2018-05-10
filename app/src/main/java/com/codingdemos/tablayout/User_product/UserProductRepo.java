package com.codingdemos.tablayout.User_product;

/**
 * Created by olegtojgildin on 07.05.2018.
 */

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
        // db.delete(String tableName, String whereClause, String[] whereArgs);
        // If whereClause is null, it will delete all rows.
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
                Product.KEY_count +
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

    public  void addProduct(Product product) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Product.KEY_name, product.getName());
        values.put(Product.KEY_carbhydrates, product.getCarbohydrates());
        values.put(Product.KEY_fat, product.getFat());
        values.put(Product.KEY_protein, product.getProtein());
        values.put(Product.KEY_Cal, product.getCal());
        values.put(Product.KEY_count, product.getCount());
        db.insert(Product.TABLE2, null, values);
        db.close();
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
                Product.KEY_count+
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
