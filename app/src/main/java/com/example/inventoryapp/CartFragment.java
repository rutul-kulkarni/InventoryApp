package com.example.inventoryapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.inventoryapp.model.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CartFragment extends Fragment {
    Context context;
//    ArrayList<Integer> cartImgList;
    List<Variant> cartItemList;
    RecyclerView recyclerView;

    View rootView;

    CartRecyclerViewAdapter adapter;

    public CartFragment(Context context ,List<Variant> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
//        this.cartImgList = cartImgList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        initView();

        return rootView;
    }

    private void initView() {
        recyclerView = rootView.findViewById(R.id.cart_rec_view);

        adapter = new CartRecyclerViewAdapter(context, cartItemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);




    }

    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            cartItemList.remove(viewHolder.getAdapterPosition());
            Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    };

}