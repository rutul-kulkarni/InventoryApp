package com.example.inventoryapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.inventoryapp.model.Brand;
import com.example.inventoryapp.model.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BrandVariantFragment extends Fragment {

//    ArrayList<Integer> cartImgList;
    List<Variant> cartItemList;

    String brandName;

    List<Variant> variantList;
    ArrayList<Integer> imgList;

    Context context;


    RecyclerView recyclerView;

    View rootView;

    public BrandVariantFragment(List<Variant> variantList , Context context , List<Variant> cartItemList)
    {
        this.variantList = variantList;
        this.context = context;
//        this.cartImgList = cartImgList;
        this.cartItemList = cartItemList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_brand_variant, container, false);
        init();
        return rootView;

    }
    void init()
    {
        imgList = new ArrayList<>();

        VariantRcyclerViewAapter aapter = new VariantRcyclerViewAapter(context , variantList , brandName ,cartItemList);
        recyclerView = rootView.findViewById(R.id.variant_rec_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context , LinearLayoutManager.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(aapter);

    }


}