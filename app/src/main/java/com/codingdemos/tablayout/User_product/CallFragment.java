package com.codingdemos.tablayout.User_product;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.codingdemos.tablayout.All_product.CustomAdapter;
import com.codingdemos.tablayout.All_product.DBhelper;
import com.codingdemos.tablayout.All_product.ProductRepo;
import com.codingdemos.tablayout.MainActivity;
import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CallFragment extends Fragment {
    Button addtoList;
    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    ProductRepo productRepo;
    private final static String TAG= MainActivity.class.getName().toString();
    Button btn;
    private int selectCategory;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        int categor_position=0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             categor_position = bundle.getInt("pos", 0);
        }
        Log.d("categor2",Integer.toString(categor_position));

        final View v = inflater.inflate(R.layout.fragment_call, container, false);
        productRepo = new ProductRepo(getActivity());
        cursor=productRepo.getStudentList();

        ArrayList<Product> arraylist=new ArrayList<Product>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int Category = cursor.getInt(cursor.getColumnIndex(Product.KEY_category));
            Log.d("categor2333",Integer.toString(Category));

            if (Category == categor_position) {
                String name = cursor.getString(cursor.getColumnIndex(Product.KEY_name));
                Double carb = cursor.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
                Double fat = cursor.getDouble(cursor.getColumnIndex(Product.KEY_fat));
                Double prot = cursor.getDouble(cursor.getColumnIndex(Product.KEY_protein));
                Double cal = cursor.getDouble(cursor.getColumnIndex(Product.KEY_Cal));
                arraylist.add(new Product(name,carb,fat,prot,cal));
            }
            cursor.moveToNext();
        }


        for (int i = 0; i <arraylist.size() ; i++) {

            Log.d("prod",arraylist.get(i).getName());
        }

        AdapterProduct adapterProduct=new AdapterProduct(CallFragment.this.getActivity(),R.layout.item_product,arraylist);
       // customAdapter = new CustomAdapter(CallFragment.this.getActivity(),  cursor, 0,categor_position);
        listView = (ListView) v.findViewById(R.id.listprod);
       // listView.setAdapter(customAdapter);
        listView.setAdapter(adapterProduct);
//        customAdapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls, menu);
        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit ");
                cursor=productRepo.getStudentListByKeyword(s);
                if (cursor==null){
                    Toast.makeText(CallFragment.this.getActivity(),"No records found!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CallFragment.this.getActivity(), cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                }
                customAdapter.swapCursor(cursor);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange ");
                cursor=productRepo.getStudentListByKeyword(s);
                if (cursor!=null){

                    ArrayList<Product> arraylist=new ArrayList<Product>();

                    cursor.moveToFirst();
                    while (!cursor.isAfterLast())
                    {
//                        int Category = cursor.getInt(cursor.getColumnIndex(Product.KEY_category));
                      //  Log.d("categor2333",Integer.toString(Category));

                            String name = cursor.getString(cursor.getColumnIndex(Product.KEY_name));
                            Double carb = cursor.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
                            Double fat = cursor.getDouble(cursor.getColumnIndex(Product.KEY_fat));
                            Double prot = cursor.getDouble(cursor.getColumnIndex(Product.KEY_protein));
                            Double cal = cursor.getDouble(cursor.getColumnIndex(Product.KEY_Cal));
                            arraylist.add(new Product(name,carb,fat,prot,cal));

                        cursor.moveToNext();
                    }
                    AdapterProduct adapterProduct=new AdapterProduct(CallFragment.this.getActivity(),R.layout.item_product,arraylist);
                    listView.setAdapter(adapterProduct);

                }
                return false;
            }

        });

    }



}
