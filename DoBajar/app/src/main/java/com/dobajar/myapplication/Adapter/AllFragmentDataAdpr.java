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

import java.util.List;

public class AllFragmentDataAdpr extends RecyclerView.Adapter<AllFragmentDataAdpr.DataViewHolder> {
    private Context context;
    private List<Integer> image1;
    private List<String> prize1;
    private List<String> name;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public AllFragmentDataAdpr(Context context, List<Integer> image1, List<String> name) {
        this.context = context;
        this.image1 = image1;
        this.name = name;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_data_sample, null);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllFragmentDataAdpr.DataViewHolder dataViewHolder, int i) {
        //dataViewHolder.productPrize.setText(prize1.get(i));
        dataViewHolder.name.setText(name.get(i));
        dataViewHolder.productImage.setImageResource(image1.get(i));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView productImage;
        TextView productPrize, name;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mView= itemView;
            productImage= itemView.findViewById(R.id.product_image);
            name= itemView.findViewById(R.id.product_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener!= null){
                        int position= getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
