package com.codingdemos.tablayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.codingdemos.tablayout.User_product.CallFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calls, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.action_call) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }*/
        return true;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ChatFragment.CustomAdapter2 customAdapter= new ChatFragment.CustomAdapter2();
        setListAdapter(customAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //`setListAdapter(null);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        // getActivity().getFragmentManager().beginTransaction().replace(R.id.newrec, new CallFragment()).addToBackStack(null);
        // Add the fragment to the 'fragment_container' FrameLayout
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
    class CustomAdapter2 extends BaseAdapter {
        int[] images={R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,
                R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,
                R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,
                R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,
                R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,
                R.drawable.fruct,R.drawable.fruct,R.drawable.fruct,R.drawable.fruct};

        String[] names=getResources().getStringArray(R.array.names);
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
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View view1=getLayoutInflater().inflate(R.layout.category_item,null);
            ImageView mImageView=(ImageView)view1.findViewById(R.id.imageView);
            TextView mTextView=(TextView)view1.findViewById(R.id.textView);

            mImageView.setImageResource(images[i]);
            mTextView.setText("FHF");


            return view1;
        }
    }
}