package com.codingdemos.tablayout.User_product;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.codingdemos.tablayout.All_product.CustomAdapter;
import com.codingdemos.tablayout.All_product.DBhelper;
import com.codingdemos.tablayout.All_product.ProductRepo;
import com.codingdemos.tablayout.MainActivity;
import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;

public class CallFragment extends Fragment {
    Button addtoList;
    private CustomAdapter customAdapter;
    ListView listView;
    Cursor cursor;
    ProductRepo productRepo;
    private final static String TAG= MainActivity.class.getName().toString();
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        final View v = inflater.inflate(R.layout.fragment_call, container, false);
        productRepo = new ProductRepo(getActivity());
        cursor=productRepo.getStudentList();
        customAdapter = new CustomAdapter(CallFragment.this.getActivity(),  cursor, 0);
        listView = (ListView) v.findViewById(R.id.lstStudent);
        listView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        productRepo = new ProductRepo(getActivity());
        cursor=productRepo.getStudentList();
        customAdapter = new CustomAdapter(CallFragment.this.getActivity(),  cursor, 0);
        listView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
        cursor=productRepo.getStudentList();
        listView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();

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
                    customAdapter.swapCursor(cursor);
                }
                return false;
            }

        });

    }




}
