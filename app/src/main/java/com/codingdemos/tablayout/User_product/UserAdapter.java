package com.codingdemos.tablayout.User_product;

/**
 * Created by olegtojgildin on 07.05.2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;

public class UserAdapter extends CursorAdapter {
    TextView image_name, carb,fats,prot,count;

    private LayoutInflater mInflater;

    public UserAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view    =    mInflater.inflate(R.layout.item_product_users, parent, false);
        ViewHolder holder  =   new ViewHolder();
        holder.image_name    =   (TextView)  view.findViewById(R.id.image_name);
        holder.carb    =   (TextView)  view.findViewById(R.id.carb);
        holder.fats    =   (TextView)  view.findViewById(R.id.fat);
        holder.prot    =   (TextView)  view.findViewById(R.id.protein);
        holder.cal   =   (TextView)  view.findViewById(R.id.cal);
        holder.weigth=(TextView)  view.findViewById(R.id.weigth);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/
        double weigth_product=cursor.getDouble(cursor.getColumnIndex(Product.KEY_weigth));
        double carbhydrates=weigth_product*cursor.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates))/100;
        double fat=weigth_product*cursor.getDouble(cursor.getColumnIndex(Product.KEY_fat))/100;
        double protein=weigth_product*cursor.getDouble(cursor.getColumnIndex(Product.KEY_protein))/100;
        double cal=weigth_product*cursor.getDouble(cursor.getColumnIndex(Product.KEY_Cal))/100;


        ViewHolder holder  =   (ViewHolder)    view.getTag();
        holder.image_name.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_name)));
       /* holder.carb.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_carbhydrates)));
        holder.fats.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_fat)));
        holder.prot.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_protein)));
        holder.cal.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_Cal)));*/
        holder.carb.setText(Double.toString(carbhydrates));
        holder.fats.setText(Double.toString(fat));
        holder.prot.setText(Double.toString(protein));
        holder.cal.setText(Double.toString(cal));

        holder.weigth.setText(Double.toString(weigth_product));
       /* Log.d("INDEx",Integer.toString(cursor.getColumnIndex(Product.KEY_name)));
        Log.d("INDEx",Integer.toString(cursor.getColumnIndex(Product.KEY_carbhydrates)));
        Log.d("INDEx",Integer.toString(cursor.getColumnIndex(Product.KEY_fat)));
        Log.d("INDEx",Integer.toString(cursor.getColumnIndex(Product.KEY_protein)));
        Log.d("INDEx",Integer.toString(cursor.getColumnIndex("fat")));
  //      holder.count.setText(cursor.getString(cursor.getColumnIndex(Product.KEY_count)));*/

    }

    static class ViewHolder {
        TextView image_name, carb,fats,prot,weigth,cal;

    }
}