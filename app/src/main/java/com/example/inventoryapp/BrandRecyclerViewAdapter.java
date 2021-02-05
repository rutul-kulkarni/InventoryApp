package com.example.inventoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.model.Brand;
import com.example.inventoryapp.model.Variant;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrandRecyclerViewAdapter extends RecyclerView.Adapter<BrandRecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Brand> brandsList;
    List<Variant> variantList;
    ArrayList<Integer> brandImgList;

//    ArrayList<Integer> cartImgList;
    List<Variant> cartItemList;

    LayoutInflater inflater;

    public BrandRecyclerViewAdapter(Context context, List<Brand> brandsList, ArrayList<Integer> brandImgList,List<Variant> cartItemList) {
        this.context = context;
        this.brandsList = brandsList;
        this.brandImgList = brandImgList;
//        this.cartImgList = cartImgList;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public BrandRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.brand_rec_view_layout , null , false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BrandRecyclerViewAdapter.ViewHolder holder, int position) {
        int brandImg = brandImgList.get(position);
        String brandName = brandsList.get(position).getBrandName();
        holder.imageView.setImageResource(brandImg);
        holder.textView.setText(brandName);
        //adding recycler view to brand name and showing on fragment

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variantList = brandsList.get(position).getVariants();
                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.placeholder1 , new BrandVariantFragment(variantList , context ,cartItemList));
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return brandsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view =itemView;
            imageView = itemView.findViewById(R.id.brand_img_view);
            textView = itemView.findViewById(R.id.brand_name_txt);

        }
    }
}
