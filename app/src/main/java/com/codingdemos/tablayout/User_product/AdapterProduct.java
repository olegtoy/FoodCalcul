package com.codingdemos.tablayout.User_product;

/**
 * Created by olegtojgildin on 11.05.2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;

import java.util.ArrayList;

class AdapterProduct extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Product> productList;

    AdapterProduct(Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);
        this.productList = products;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Product product = productList.get(position);
        viewHolder.image_name.setText(product.getName());
        viewHolder.carb.setText(Double.toString(product.getCarbohydrates()));
        viewHolder.fats.setText(Double.toString(product.getFat()));
        viewHolder.prot.setText(Double.toString(product.getProtein()));
        viewHolder.cal.setText(Double.toString(product.getCal()));

        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parent_row = (View) view.getParent();
                ListView lv = (ListView) parent_row.getParent();
                final int position = lv.getPositionForView(parent_row);

                UsersDBhelper usersDBhelper = new UsersDBhelper(getContext());
                SQLiteDatabase db = usersDBhelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put(Product.KEY_name, product.getName());
                values.put(Product.KEY_carbhydrates, product.getCarbohydrates());
                values.put(Product.KEY_fat, product.getFat());
                values.put(Product.KEY_protein, product.getProtein());
                values.put(Product.KEY_Cal, product.getCal());
                double weigth;
                if (!viewHolder.count.getText().toString().equals(""))
                    weigth = Double.parseDouble(viewHolder.count.getText().toString());
                else
                    weigth = 0;
                values.put(Product.KEY_weigth, weigth);
                db.insert(Product.TABLE2, null, values);
                db.close();
            }
        });
        return convertView;
    }

    private String formatValue(int count, String unit) {
        return String.valueOf(count) + " " + unit;
    }

    private class ViewHolder {
        TextView image_name, carb, fats, prot, cal;
        EditText count;
        Button btn;

        ViewHolder(View view) {
            btn = view.findViewById(R.id.AddToList);

            image_name = (TextView) view.findViewById(R.id.image_name);
            carb = (TextView) view.findViewById(R.id.carb);
            fats = (TextView) view.findViewById(R.id.fat);
            prot = (TextView) view.findViewById(R.id.protein);
            cal = (TextView) view.findViewById(R.id.cal);
            count = (EditText) view.findViewById(R.id.weigth);

        }
    }
}