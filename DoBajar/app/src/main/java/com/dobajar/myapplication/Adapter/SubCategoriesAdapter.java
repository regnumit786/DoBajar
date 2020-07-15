package com.dobajar.myapplication.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.Model.Products.AllProducts;
import com.dobajar.myapplication.Model.Retrofit.ApiClint;
import com.dobajar.myapplication.Model.Retrofit.RetrofitClint;
import com.dobajar.myapplication.R;
import com.dobajar.myapplication.activity.AllCategories;
import com.dobajar.myapplication.activity.Babarege;
import com.dobajar.myapplication.activity.Bakery;
import com.dobajar.myapplication.activity.Dairy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubViewHolder> {
    private Context context;
    private List<String> name;
    private List<String> image;

    public SubCategoriesAdapter(Context context, List<String> name, List<String> image) {
        this.context = context;
        this.name = name;
        this.image = image;
    }

    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.subcategoriesample, parent, false);
        return new SubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubViewHolder holder, int position) {
        holder.nameText.setText(name.get(position));
        String imgURL= image.get(position);
        Picasso.get()
                .load(imgURL)
                .error(R.mipmap.ic_launcher)
                .into(holder.imageButton);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, holder.nameText.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(context, Babarege.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class SubViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        ImageButton imageButton;
        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton= itemView.findViewById(R.id.imgbtn_sub_categories_sample);
            nameText= itemView.findViewById(R.id.txt_sub_categories_sample);
        }
    }
}
