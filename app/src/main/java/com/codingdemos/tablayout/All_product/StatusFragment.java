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


/**
 * A simple {@link Fragment} subclass.
 */
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
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_call, container, false);
        ViewGroup footer = (ViewGroup)inflater.inflate(R.layout.status_footer,listView,false);
        userProductRepo = new UserProductRepo(getActivity());
        cursor = userProductRepo.getStudentList();
        userAdapter = new UserAdapter(StatusFragment.this.getActivity(), cursor, 0);
        cursorall=userProductRepo.getStudentList();

        double carb=0;
        double fat=0;
        double protein=0;
        double kal=0;

        if(cursorall!=null){
        for (int i = 0; i <cursorall.getCount() ; i++) {
            carb += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
            fat += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_fat));
            protein += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_protein));
//            kal+=cursorall.getDouble(cursor.getColumnIndex(Product.KEY_Cal));
            cursorall.moveToNext();
        }
        }


        final TextView carb_textView=(TextView)footer.findViewById(R.id.carb);
        final TextView fat_textView=(TextView)footer.findViewById(R.id.fat);
        final TextView protein_textView=(TextView)footer.findViewById(R.id.protein);
      //  TextView kal_textView=(TextView)footer.findViewById(R.id.carb);


        carb_textView.setText(Double.toString(carb));
        fat_textView.setText(Double.toString(fat));
        protein_textView.setText(Double.toString(protein));
        //kal_textView.setText(Double.toString(carb));

        listView = (ListView) v.findViewById(R.id.lstStudent);
        listView.setAdapter(userAdapter);

        Button remove_list=footer.findViewById(R.id.remove);
        remove_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(null);
                userProductRepo.removeAll();
                carb_textView.setText("0");
                fat_textView.setText("0");
                protein_textView.setText("0");

            }

        });
        Button calculate=footer.findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.setAdapter(null);
                userProductRepo = new UserProductRepo(getActivity());
                cursor = userProductRepo.getStudentList();
                userAdapter = new UserAdapter(StatusFragment.this.getActivity(), cursor, 0);
                listView.setAdapter(userAdapter);
                cursorall=userProductRepo.getStudentList();

                double carb=0;
                double fat=0;
                double protein=0;
                double kal=0;

                if(cursorall!=null){
                    for (int i = 0; i <cursorall.getCount() ; i++) {
                        carb += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_carbhydrates));
                        fat += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_fat));
                        protein += cursorall.getDouble(cursor.getColumnIndex(Product.KEY_protein));
//            kal+=cursorall.getDouble(cursor.getColumnIndex(Product.KEY_Cal));
                        cursorall.moveToNext();
                    }
                }

                carb_textView.setText(Double.toString(carb));
                fat_textView.setText(Double.toString(fat));
                protein_textView.setText(Double.toString(protein));
                //kal_textView.setText(Double.toString(carb));

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
        if (item.getItemId() == R.id.action_status) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }
/*

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls, menu);
        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }

        });
*/

    }

