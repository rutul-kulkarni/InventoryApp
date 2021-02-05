package com.example.inventoryapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inventoryapp.model.Variant;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    Context context;
//    ArrayList<Integer> cartImgList;
    List<Variant> cartItemList;

    LayoutInflater inflater;

    public CartRecyclerViewAdapter(Context context, List<Variant> cartItemList) {
        this.context = context;
//        this.cartImgList = cartImgList;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       inflater = LayoutInflater.from(parent.getContext());
       View view = inflater.inflate(R.layout.cart_rec_view_layout , parent , false);
       ViewHolder viewHolder = new ViewHolder(view);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String variant_name = cartItemList.get(position).getVariantName();
        String variant_price = cartItemList.get(position).getVariantPrice();
//        int img_src = cartImgList.get(position);
        String imgSrc = cartItemList.get(position).getVariantImgSrc();
        String countStr = cartItemList.get(position).getVariantCount();
        List<String> tags = cartItemList.get(position).getSelectedTag();
        String tagsStr = "";
        if(tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                tagsStr = tagsStr + " " + tags.get(i);
            }
        }
        int count = Integer.parseInt(countStr);
        if(count>1)
            holder.badge.setText(String.valueOf(count));
        if(count==1)
            holder.badge.setVisibility(View.GONE);

        holder.variantTagTxt.setText(tagsStr);
        holder.cartItemPriceTxt.setText(variant_price);
        holder.cartItemNameTxt.setText(variant_name);
//        holder.cartItemImg.setImageResource(img_src);
        Glide.with(context)
                .load(imgSrc)
                .into(holder.cartItemImg);

        holder.deleteFromCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFromCart(holder,position);
            }
        });



    }

    void deleteFromCart(ViewHolder holder,int position)
    {
//        cartImgList.remove(position);
//        cartImgList.remove(position);
        Variant variant = cartItemList.get(position);
        int count = Integer.parseInt(variant.getVariantCount());
        if(count>1)
        {
            count--;
            variant.setVariantCount(String.valueOf(count));
            if(count==1)
            {
                holder.badge.setVisibility(View.GONE);
            }
            Toast.makeText(context, "one item removed from cart", Toast.LENGTH_SHORT).show();
        }
        else
        {
            cartItemList.remove(position);
            Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();

        Log.d("cart_list", "deleteFromCart: "+cartItemList);
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cartItemImg;
        TextView cartItemNameTxt;
        TextView cartItemPriceTxt;
        TextView badge;
        ImageButton deleteFromCartBtn;
        TextView variantTagTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deleteFromCartBtn = itemView.findViewById(R.id.delete_from_cart_btn);
            cartItemImg = itemView.findViewById(R.id.cart_img_view);
            cartItemNameTxt = itemView.findViewById(R.id.cart_item_name_txt);
            cartItemPriceTxt = itemView.findViewById(R.id.cart_item_price_txt);
            badge = itemView.findViewById(R.id.notification_badge);
            variantTagTxt = itemView.findViewById(R.id.variant_tag_txt);
        }
    }
}
