package com.dobajar.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SubCategoryItem extends RecyclerView.Adapter<SubCategoryItem.subCategoryViewHolder> {

    private Context context;
    private List<String> title;
    private List<String> image;
    private List<String> description;
    private List<String> prize;
    private List<String> stock;

    public SubCategoryItem(Context context, List<String> title, List<String> image, List<String> description, List<String> prize, List<String> stock) {
        this.context = context;
        this.title = title;
        this.image = image;
        this.description = description;
        this.prize = prize;
        this.stock = stock;
    }

    @NonNull
    @Override
    public subCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.subcategoryitem, parent, false);
        return new subCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subCategoryViewHolder holder, int position) {
        holder.itemTitle.setText(title.get(position));
        holder.itemDescription.setText(description.get(position));
        holder.itemPrize.setText(prize.get(position));
        holder.itemStock.setText(stock.get(position));

        String url= image.get(position);
        Picasso.get()
                .load(url)
                .error(R.mipmap.ic_launcher)
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return title.size();
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
