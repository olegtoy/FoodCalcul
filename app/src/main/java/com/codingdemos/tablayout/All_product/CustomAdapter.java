package com.codingdemos.tablayout.All_product;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;
import com.codingdemos.tablayout.User_product.CallFragment;
import com.codingdemos.tablayout.User_product.UserProductRepo;
import com.codingdemos.tablayout.User_product.UsersDBhelper;

import java.util.ArrayList;

import javax.xml.transform.dom.DOMLocator;

public class CustomAdapter extends CursorAdapter {
    TextView image_name, carb, fats, prot, cal;
    public ArrayList<Product> productArrayList = new ArrayList<Product>();
    public Product productselect;
    private LayoutInflater mInflater;
    UserProductRepo userProductRepo;
    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.image_name = (TextView) view.findViewById(R.id.image_name);
        holder.carb = (TextView) view.findViewById(R.id.carb);
        holder.fats = (TextView) view.findViewById(R.id.fat);
        holder.prot = (TextView) view.findViewById(R.id.protein);
        holder.cal = (TextView) view.findViewById(R.id.cal);
        holder.count = (EditText) view.findViewById(R.id.weigth);
        view.setTag(holder);
        return view;
    }

    Button btn;

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        String name = cursor.getString(cursor.getColumnIndex(Product.KEY_name));
        Double carb = cursor.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
        Double fat = cursor.getDouble(cursor.getColumnIndex(Product.KEY_fat));
        Double prot = cursor.getDouble(cursor.getColumnIndex(Product.KEY_protein));
        String cal=cursor.getString(cursor.getColumnIndex(Product.KEY_Cal));
        Double count = 0.0;
        Log.d("...", "id = " + name);
        Log.d("...", "id = " + carb);
        Log.d("...", "id = " + fat);
        Log.d("...", "id = " + prot);
        Log.d("...", "id = " + cal);
        holder.image_name.setText(name);
        holder.carb.setText(carb.toString());
        holder.fats.setText(fat.toString());
        holder.prot.setText(prot.toString());
        holder.cal.setText(cal.toString());

        Product temp = new Product(name, carb, fat, prot,count);
        productArrayList.add(temp);

        btn=view.findViewById(R.id.AddToList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent_row = (View) view.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);
                Product product=productArrayList.get(position);

                UsersDBhelper usersDBhelper=new UsersDBhelper(context);
                SQLiteDatabase db = usersDBhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Product.KEY_name, product.getName());
                values.put(Product.KEY_carbhydrates, product.getCarbohydrates());
                values.put(Product.KEY_fat, product.getFat());
                values.put(Product.KEY_protein, product.getProtein());
                values.put(Product.KEY_Cal, product.getCal());
                values.put(Product.KEY_count, product.getCount());
                db.insert(Product.TABLE2, null, values);
                db.close();
                notifyDataSetChanged();
            }
        });
/*
        btn = view.findViewById(R.id.AddToList);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("...", "ad3333dd = ");

                View parent_row = (View) v.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);
               *//* Log.d("...", "position = " + position);
                Log.d("...", "id = " + getItemId(position));
                Product pr = productArrayList.get(position);
                productselect = pr;*//*
       *//*       CallFragment cl=new CallFragment();
               Log.d("444", productArrayList.get(position).getName());
               cl.ADDDDD(productArrayList.get(position));*//*
            }
        });*/
    }
     static class ViewHolder {
        TextView image_name, carb, fats, prot, cal;
        EditText count;

    }
}