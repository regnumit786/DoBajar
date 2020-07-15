
package com.dobajar.myapplication.Model.SubCategoriesModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategorieModel {

    @SerializedName("data")
    @Expose
    private List<DatumSub> data = null;

    public List<DatumSub> getData() {
        return data;
    }

    public void setData(List<DatumSub> data) {
        this.data = data;
    }

}
