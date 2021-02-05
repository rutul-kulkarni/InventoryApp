package com.example.inventoryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.inventoryapp.model.Brand;
import com.example.inventoryapp.model.BrandModel;
import com.example.inventoryapp.model.Variant;
import com.google.gson.Gson;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class    MainActivity extends AppCompatActivity {

    List<Brand> brandsList;
    ArrayList<Integer> brandImgList;
    RecyclerView recyclerView;

    static List<Variant> cartItemList;

    Button cartBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        brandImgList = new ArrayList<>();
        brandsList = new ArrayList<>();

        cartItemList= new ArrayList<>();
//        cartImgList = new ArrayList<>();

//        brandsList.add("Gionee");
//        brandsList.add("HTC");
//        brandsList.add("Lenovo");
//        brandsList.add("Mi");
//        brandsList.add("Samsung");
//        brandsList.add("Vivo");
        //brandsList.add("Cart");

        brandImgList.add(R.drawable.gionee);
        brandImgList.add(R.drawable.htc);
        brandImgList.add(R.drawable.lenovo);
        brandImgList.add(R.drawable.mi);
        brandImgList.add(R.drawable.samsung);
        brandImgList.add(R.drawable.vivo);
        //brandImgList.add(R.drawable.ic_baseline_add_shopping_cart_24);

        //json file
        String brandJsonString = Utils.getJsonFromAssets(getApplicationContext() , "brands.json");
        Log.i("json", "onCreate: "+brandJsonString);
        Gson gson = new Gson();

        BrandModel brandModel = gson.fromJson(brandJsonString,BrandModel.class);
        Log.i("brands list", "onCreate: "+brandModel.getBrands());
        brandsList = brandModel.getBrands();

        BrandRecyclerViewAdapter viewAdapter =new BrandRecyclerViewAdapter(this,brandsList,brandImgList ,cartItemList);
        recyclerView = findViewById(R.id.brand_rec_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL ,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(viewAdapter);

        cartBtn =findViewById(R.id.cart_btn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.placeholder1 ,new CartFragment(getBaseContext(),cartItemList));
                transaction.commit();
            }
        });



    }
}