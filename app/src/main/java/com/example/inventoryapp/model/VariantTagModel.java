
package com.example.inventoryapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantTagModel {

    @SerializedName("variant_tags")
    @Expose
    private VariantTags variantTags;

    public VariantTags getVariantTags() {
        return variantTags;
    }

    public void setVariantTags(VariantTags variantTags) {
        this.variantTags = variantTags;
    }

}
