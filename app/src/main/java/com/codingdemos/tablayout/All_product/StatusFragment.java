package com.codingdemos.tablayout.All_product;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingdemos.tablayout.MainActivity;
import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;
import com.codingdemos.tablayout.User_product.CallFragment;
import com.codingdemos.tablayout.User_product.UserAdapter;
import com.codingdemos.tablayout.User_product.UserProductRepo;
public class StatusFragment extends Fragment {
    private UserAdapter userAdapter;
    ListView listView;
    Cursor cursor;
    Cursor cursorall;
    UserProductRepo userProductRepo;
    private final static String TAG = MainActivity.class.getName().toString();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_call, container, false);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.status_footer, listView, false);
        userProductRepo = new UserProductRepo(getActivity());
        cursor = userProductRepo.getStudentList();
        userAdapter = new UserAdapter(StatusFragment.this.getActivity(), cursor, 0);
        cursorall = userProductRepo.getStudentList();

        double carb = 0;
        double fat = 0;
        double protein = 0;
        double kal = 0;
        double weigth = 0;

        if (cursorall != null) {
            for (int i = 0; i < cursorall.getCount(); i++) {
                carb += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
                fat += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_fat));
                protein += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_protein));
                kal += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_Cal));
                weigth += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_weigth));
                cursorall.moveToNext();
            }
        }


        final TextView carb_textView = (TextView) footer.findViewById(R.id.carb);
        final TextView fat_textView = (TextView) footer.findViewById(R.id.fat);
        final TextView protein_textView = (TextView) footer.findViewById(R.id.protein);
        final TextView kal_textView = (TextView) footer.findViewById(R.id.cal);
        final TextView weigth_textView = (TextView) footer.findViewById(R.id.weigthall);

        carb_textView.setText(Double.toString(carb));
        fat_textView.setText(Double.toString(fat));
        protein_textView.setText(Double.toString(protein));
        kal_textView.setText(Double.toString(kal));

        listView = (ListView) v.findViewById(R.id.listprod);
        listView.setAdapter(userAdapter);

        Button remove_list = footer.findViewById(R.id.remove);
        remove_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(null);
                userProductRepo.removeAll();
                carb_textView.setText("0.0");
                fat_textView.setText("0.0");
                protein_textView.setText("0.0");
                kal_textView.setText("0.0");
                weigth_textView.setText("0.0");


            }

        });
        Button calculate = footer.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(null);
                userProductRepo = new UserProductRepo(getActivity());
                cursor = userProductRepo.getStudentList();
                userAdapter = new UserAdapter(StatusFragment.this.getActivity(), cursor, 0);
                listView.setAdapter(userAdapter);
                cursorall = userProductRepo.getStudentList();

                double carb = 0;
                double fat = 0;
                double protein = 0;
                double kal = 0;
                double weigth = 0;
                if (cursorall != null) {
                    for (int i = 0; i < cursorall.getCount(); i++) {

                        double weigth_temp=cursorall.getDouble(cursor.getColumnIndex(Product.KEY_weigth));
                        Log.d("weig",Double.toString(weigth_temp));
                        weigth += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_weigth));
                        carb += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates))/100*weigth_temp;
                        fat += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_fat))/100*weigth_temp;
                        protein += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_protein))/100*weigth_temp;
                        kal += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_Cal))/100*weigth_temp;

                        cursorall.moveToNext();
                    }
                }

                carb_textView.setText(String.format("%.1f",carb));
                fat_textView.setText(String.format("%.1f",fat));
                protein_textView.setText(String.format("%.1f",protein));
                kal_textView.setText(String.format("%.1f",kal));
                weigth_textView.setText(String.format("%.1f",weigth));
            }

        });
        listView.addHeaderView(footer);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_status, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

}

