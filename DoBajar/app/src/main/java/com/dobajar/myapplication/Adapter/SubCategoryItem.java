package com.dobajar.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.Model.SubCategory.DatumSubCategory;
import com.dobajar.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryItem extends RecyclerView.Adapter<SubCategoryItem.subCategoryViewHolder> {

    private Context context;
    private List<DatumSubCategory> datumSubCategoryList;

    public SubCategoryItem(Context context, List<DatumSubCategory> datumSubCategoryList) {
        this.context = context;
        this.datumSubCategoryList = datumSubCategoryList;
    }

    @NonNull
    @Override
    public subCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.subcategoryitem, parent, false);
        return new subCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subCategoryViewHolder holder, int position) {
        holder.itemTitle.setText(datumSubCategoryList.get(position).getTitle());
        holder.itemDescription.setText(datumSubCategoryList.get(position).getDescription());
        holder.itemPrize.setText(datumSubCategoryList.get(position).getPrice());
        holder.itemStock.setText(datumSubCategoryList.get(position).getStock());

        String url= datumSubCategoryList.get(position).getImage();
        Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return datumSubCategoryList.size();
    }

    public static class subCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitle, itemPrize, itemStock, itemDescription;
        ImageView itemImage;

        public subCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle= itemView.findViewById(R.id.sub_categories_item_title);
            itemPrize= itemView.findViewById(R.id.sub_categories_item_prize);
            itemStock= itemView.findViewById(R.id.sub_categories_item_stock);
            itemDescription= itemView.findViewById(R.id.sub_categories_item_description);
            itemImage= itemView.findViewById(R.id.sub_categories_item_image);

        }
    }
}
