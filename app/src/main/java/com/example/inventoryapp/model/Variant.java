
package com.example.inventoryapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variant {

    List<String> selectedTag;

    public List<String> getSelectedTag() {
        return selectedTag;
    }

    public void setSelectedTag(List<String> selectedTag) {
        this.selectedTag = selectedTag;
    }

    @SerializedName("variant_id")
    @Expose
    private String variantId;
    @SerializedName("variant_count")
    @Expose
    private String variantCount;
    @SerializedName("variant_name")
    @Expose
    private String variantName;
    @SerializedName("variant_price")
    @Expose
    private String variantPrice;
    @SerializedName("variant_img_src")
    @Expose
    private String variantImgSrc;
    @SerializedName("variant_tag")
    @Expose
    private List<String> variantTag = null;

    public String getVariantId() {
        return variantId;
    }

    public void setVariantId(String variantId) {
        this.variantId = variantId;
    }

    public String getVariantCount() {
        return variantCount;
    }

    public void setVariantCount(String variantCount) {
        this.variantCount = variantCount;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(String variantPrice) {
        this.variantPrice = variantPrice;
    }

    public String getVariantImgSrc() {
        return variantImgSrc;
    }

    public void setVariantImgSrc(String variantImgSrc) {
        this.variantImgSrc = variantImgSrc;
    }

    public List<String> getVariantTag() {
        return variantTag;
    }

    public void setVariantTag(List<String> variantTag) {
        this.variantTag = variantTag;
    }

}
