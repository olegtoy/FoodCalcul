package com.codingdemos.tablayout.User_product;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codingdemos.tablayout.All_product.ProductRepo;
import com.codingdemos.tablayout.Product;
import com.codingdemos.tablayout.R;

import java.util.ArrayList;

public class ChatFragment extends ListFragment {

    ProductRepo productRepo;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        final View v = inflater.inflate(R.layout.fragment_chat, container, false);
        productRepo = new ProductRepo(getActivity());
        listView = (ListView) v.findViewById(R.id.listprod2);

        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ChatFragment.CustomAdapter2 customAdapter = new ChatFragment.CustomAdapter2();
        setListAdapter(customAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        setListAdapter(null);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CallFragment glfragment = new CallFragment();
        Bundle args = new Bundle();
        args.putInt("pos", position);
        glfragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragmentchat, glfragment);
        fragmentTransaction.commit();
    }
    Cursor cursor;
    @Override
    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chats, menu);
        SearchManager manager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView search = (SearchView) menu.findItem(R.id.search_chat).getActionView();
        search.setSearchableInfo(manager.getSearchableInfo(getActivity().getComponentName()));
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                cursor = productRepo.getStudentListByKeyword(s);
                if (cursor == null) {
                    Toast.makeText(ChatFragment.this.getActivity(), "No records found!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChatFragment.this.getActivity(), cursor.getCount() + " records found!", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                setListAdapter(null);
                MenuItem item = menu.findItem(R.id.search_chat);
                item.setVisible(false);
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                CallFragment glfragment = new CallFragment();
                Bundle args = new Bundle();
                args.putInt("pos", 111);
                glfragment.setArguments(args);

                fragmentTransaction.replace(R.id.fragmentchat, glfragment);
                fragmentTransaction.commit();
                return false;
            }

        });

    }

    class CustomAdapter2 extends BaseAdapter {
        int[] images = {R.drawable.aaa, R.drawable.bbb, R.drawable.gran, R.drawable.ddd, R.drawable.bread,
                R.drawable.vegetables, R.drawable.nuts, R.drawable.fish, R.drawable.fruts, R.drawable.drink};

        String[] names = getResources().getStringArray(R.array.names);

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.category_item, null);
            ImageView mImageView = (ImageView) view1.findViewById(R.id.imageView);
            TextView mTextView = (TextView) view1.findViewById(R.id.textView);

            mImageView.setImageResource(images[i]);
            mTextView.setText(names[i]);


            return view1;
        }
    }
}