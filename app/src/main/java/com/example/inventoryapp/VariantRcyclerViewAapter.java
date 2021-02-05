package com.example.inventoryapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inventoryapp.model.Variant;
import com.example.inventoryapp.model.VariantTagModel;
import com.example.inventoryapp.model.VariantTags;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class VariantRcyclerViewAapter extends RecyclerView.Adapter<VariantRcyclerViewAapter.ViewHolder> {

    Context context;

    List<Variant> variantsList;

    List<Variant> cartItemList;


    LayoutInflater inflater;

    public VariantRcyclerViewAapter(Context context, List<Variant> variantsList ,String brandName ,List<Variant> cartItemList) {
        this.context = context;
//        this.variantImgList = variantImgList;
        this.variantsList = variantsList;
//        this.cartImgList = cartImgList;
        this.cartItemList = cartItemList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater =  LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.variant_rec_view_layout , null , false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String variant_name = variantsList.get(position).getVariantName();
        String variant_price = variantsList.get(position).getVariantPrice();
        String imgSrc = variantsList.get(position).getVariantImgSrc();

        holder.variantPriceTxt.setText(variant_price);
        holder.variantNameTxt.setText(variant_name);
        //holder.variantImg.setImageResource(img_src);
        Glide.with(context)
                .load(imgSrc)
                .into(holder.variantImg);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(variantsList.get(position));


            }
        });
    }

    @Override
    public int getItemCount() {
        return variantsList.size();
    }

    void addToCart(Variant variant)
    {
//        cartImgList.add(img_src);
        if(variant.getVariantTag() ==null)
        {
            checkInCartAndAdd(variant);
        }
        else
        {
            String variantTagJson = Utils.getJsonFromAssets(context ,"variant_tag.json");
            Gson gson = new Gson();
            VariantTagModel variantTagModel = gson.fromJson(variantTagJson,VariantTagModel.class);
            VariantTags variantTags = variantTagModel.getVariantTags();
//            Toast.makeText(context, "Select variant tag", Toast.LENGTH_SHORT).show();
            List<String> tagList = new ArrayList<>();
            List<String> selectedVariants = new ArrayList<>();
            if(variantTags.getColor() != null)
            {
               tagList.clear();
               tagList.addAll(variantTags.getColor());
               String[] arr = tagList.toArray(new String[0]);
               AlertDialog.Builder builder = new AlertDialog.Builder(context);
               builder.setTitle("Select Color")
                       .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                               if(b) {
                                   selectedVariants.add(arr[i]);
                               }
                               else
                                   selectedVariants.remove(arr[i]);
                           }
                       })
                       .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                                if(variantTags.getSize() != null)
                                {
                                    tagList.clear();
                                    tagList.addAll(variantTags.getSize());
                                    String[] arr = tagList.toArray(new String[0]);
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    builder1.setTitle("Choose Size")
                                            .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                    if(b)
                                                        selectedVariants.add(arr[i]);
                                                    else
                                                        selectedVariants.remove(arr[i]);
                                                }
                                            })
                                            .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    if(variantTags.getRam()!=null)
                                                    {
                                                        tagList.clear();
                                                        tagList.addAll(variantTags.getRam());
                                                        String[] arr = tagList.toArray(new String[0]);
                                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                                        builder2.setTitle("Choose Ram")
                                                                .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                                        if(b)
                                                                            selectedVariants.add(arr[i]);
                                                                        else
                                                                            selectedVariants.remove(arr[i]);
                                                                    }
                                                                })
                                                                .setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        checkInCartAndAdd(variant);


                                                                    }
                                                                })
                                                                .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        checkInCartAndAdd(variant);

                                                                    }
                                                                })
                                                                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        dialogInterface.cancel();

                                                                    }
                                                                })
                                                                .create()
                                                                .show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            })
                                            .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    if(variantTags.getRam()!=null)
                                                    {
                                                        tagList.clear();
                                                        tagList.addAll(variantTags.getRam());
                                                        String[] arr = tagList.toArray(new String[0]);
                                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                                        builder2.setTitle("Choose Ram")
                                                                .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                                        if(b)
                                                                            selectedVariants.add(arr[i]);
                                                                        else
                                                                            selectedVariants.remove(arr[i]);
                                                                    }
                                                                })
                                                                .setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        checkInCartAndAdd(variant);

                                                                    }
                                                                })
                                                                .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        checkInCartAndAdd(variant);

                                                                    }
                                                                })
                                                                .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                                    }
                                                                })
                                                                .create()
                                                                .show();
                                                    }

                                                }
                                            })
                                            .create()
                                            .show();
                                }
                           }
                       })
                       .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               if(variantTags.getSize() != null)
                               {
                                   tagList.clear();
                                   tagList.addAll(variantTags.getSize());
                                   String[] arr = tagList.toArray(new String[0]);
                                   AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                   builder1.setTitle("Choose Size")
                                           .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                   if(b)
                                                       selectedVariants.add(arr[i]);
                                                   else
                                                       selectedVariants.remove(arr[i]);
                                               }
                                           })
                                           .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i) {
                                                   if(variantTags.getRam()!=null)
                                                   {
                                                       tagList.clear();
                                                       tagList.addAll(variantTags.getRam());
                                                       String[] arr = tagList.toArray(new String[0]);
                                                       AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                                       builder2.setTitle("Choose Ram")
                                                               .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                                       if(b)
                                                                           selectedVariants.add(arr[i]);
                                                                       else
                                                                           selectedVariants.remove(arr[i]);
                                                                   }
                                                               })
                                                               .setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                        checkInCartAndAdd(variant);
                                                                   }
                                                               })
                                                               .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                       checkInCartAndAdd(variant);

                                                                   }
                                                               })
                                                               .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                       dialogInterface.cancel();

                                                                   }
                                                               })
                                                               .create()
                                                               .show();
                                                   }
                                               }
                                           })
                                           .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i) {
                                                   dialogInterface.cancel();
                                               }
                                           })
                                           .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialogInterface, int i) {
                                                   if(variantTags.getRam()!=null)
                                                   {
                                                       tagList.clear();
                                                       tagList.addAll(variantTags.getRam());
                                                       String[] arr = tagList.toArray(new String[0]);
                                                       AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                                       builder2.setTitle("Choose Ram")
                                                               .setMultiChoiceItems(arr, null, new DialogInterface.OnMultiChoiceClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                                                       if(b) {
                                                                           selectedVariants.add(arr[i]);
                                                                       }
                                                                       else {
                                                                           selectedVariants.remove(arr[i]);
                                                                       }
                                                                   }
                                                               })
                                                               .setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                       checkInCartAndAdd(variant);

                                                                   }
                                                               })
                                                               .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                       checkInCartAndAdd(variant);

                                                                   }
                                                               })
                                                               .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialogInterface, int i) {
                                                                       dialogInterface.cancel();

                                                                   }
                                                               })
                                                               .create()
                                                               .show();
                                                   }

                                               }
                                           })
                                           .create()
                                           .show();
                               }
                           }
                       })
                       .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.cancel();
                           }
                       })
                       .create()
                       .show();
                variant.setSelectedTag(selectedVariants);
                Log.i("selected variants", "addToCart: "+selectedVariants);

            }



        }

    }

    void checkInCartAndAdd(Variant variant)
    {
        boolean isAvailable = false;
        for(int i=0;i<cartItemList.size();i++)
        {
            Variant variantInCart = cartItemList.get(i);
            if(variant.getVariantId() == variantInCart.getVariantId())
            {
                isAvailable = true;
            }
        }
        if(isAvailable)
        {
            int count = Integer.parseInt(variant.getVariantCount());
            count++;
            variant.setVariantCount(String.valueOf(count));
            Toast.makeText(context, "alredy in cart", Toast.LENGTH_SHORT).show();
        }
        else {
            variant.setVariantCount("1");
            cartItemList.add(variant);
            Toast.makeText(context, "Added to the cart", Toast.LENGTH_SHORT).show();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView variantImg;
        TextView variantNameTxt;
        TextView variantPriceTxt;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            variantImg = itemView.findViewById(R.id.variant_img_view);
            variantNameTxt = itemView.findViewById(R.id.variant_name_txt);
            variantPriceTxt = itemView.findViewById(R.id.variant_price_txt);

        }
    }

    public interface onMultiChoiceListener{
        void onPositiveButtonClicked(String[] list , ArrayList<String> selectedItemList);
        void onNegativeButtonClicked();
    }

    onMultiChoiceListener multiChoiceListener;


}
