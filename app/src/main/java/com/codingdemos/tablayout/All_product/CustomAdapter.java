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
    public ArrayList<Product> productArrayList = new ArrayList<Product>();
    private LayoutInflater mInflater;
    int category_position;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomAdapter(Context context, Cursor c, int flags, int category) {
        super(context, c, flags);
        category_position = category;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_product, parent, false);
        final ViewHolder holder = new ViewHolder();
        holder.image_name = (TextView) view.findViewById(R.id.image_name);
        holder.carb = (TextView) view.findViewById(R.id.carb);
        holder.fats = (TextView) view.findViewById(R.id.fat);
        holder.prot = (TextView) view.findViewById(R.id.protein);
        holder.cal =  (TextView) view.findViewById(R.id.cal);
        holder.count = (EditText) view.findViewById(R.id.weigth);
        view.setTag(holder);
        return view;
    }
    Button btn;
    @Override
    public void bindView(View view2, final Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view2.getTag();

        int Category = cursor.getInt(cursor.getColumnIndex(Product.KEY_category));
        if (Category == category_position) {
            String name = cursor.getString(cursor.getColumnIndex(Product.KEY_name));
            Double carb = cursor.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
            Double fat = cursor.getDouble(cursor.getColumnIndex(Product.KEY_fat));
            Double prot = cursor.getDouble(cursor.getColumnIndex(Product.KEY_protein));
            Double cal = cursor.getDouble(cursor.getColumnIndex(Product.KEY_Cal));

            holder.image_name.setText(name);
            holder.carb.setText(carb.toString());
            holder.fats.setText(fat.toString());
            holder.prot.setText(prot.toString());
            holder.cal.setText(cal.toString());

            boolean flag = false;
            Product temp = new Product(name, carb, fat, prot, cal);
            for (int i = 0; i < productArrayList.size(); i++) {
                if (productArrayList.get(i).getName().equals(temp.getName())) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                productArrayList.add(temp);
            }

            final EditText editText = (EditText) view2.findViewById(R.id.weigth);

            btn = view2.findViewById(R.id.AddToList);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View parent_row = (View) view.getParent();
                    ListView lv = (ListView) parent_row.getParent();
                    final int position = lv.getPositionForView(parent_row);

                    Product product = productArrayList.get(position);
                    UsersDBhelper usersDBhelper = new UsersDBhelper(context);
                    SQLiteDatabase db = usersDBhelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(Product.KEY_name, product.getName());
                    values.put(Product.KEY_carbhydrates, product.getCarbohydrates());
                    values.put(Product.KEY_fat, product.getFat());
                    values.put(Product.KEY_protein, product.getProtein());
                    values.put(Product.KEY_Cal, product.getCal());

                    double weigth;
                    if (!editText.getText().toString().equals(null))
                        weigth = Double.parseDouble(editText.getText().toString());
                    else weigth = 0.0;
                    values.put(Product.KEY_weigth, weigth);
                    db.insert(Product.TABLE2, null, values);
                    db.close();
                }
            });

        }
    }
    static class ViewHolder {
        TextView image_name, carb, fats, prot, cal;
        EditText count;
    }
}