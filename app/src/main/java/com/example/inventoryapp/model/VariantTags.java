
package com.example.inventoryapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantTags {

    @SerializedName("size")
    @Expose
    private List<String> size = null;
    @SerializedName("color")
    @Expose
    private List<String> color = null;
    @SerializedName("ram")
    @Expose
    private List<String> ram = null;

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getRam() {
        return ram;
    }

    public void setRam(List<String> ram) {
        this.ram = ram;
    }

}
