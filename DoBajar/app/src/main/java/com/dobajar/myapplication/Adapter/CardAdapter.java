package com.dobajar.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dobajar.myapplication.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    //private List<CardItem> cardItemList;

    private Context context;
    private List<Integer> cardImage1;
    private List<String> cardQuantity;
    private List<String> cardPrize1;
    private List<String> cardProductName;
    private OnItemClickListener mListener;
    private int count=0;
    private int countDown=0;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    public CardAdapter(Context context, List<Integer> cardImage1, List<String> cardQuantity, List<String> cardPrize1, List<String> cardProductName) {
        this.context = context;
        this.cardImage1 = cardImage1;
        this.cardQuantity = cardQuantity;
        this.cardPrize1 = cardPrize1;
        this.cardProductName = cardProductName;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.card_item_smpl, null);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder cardViewHolder, final int i) {
        cardViewHolder.productsName.setText(cardProductName.get(i));
        cardViewHolder.productsPrize.setText(cardPrize1.get(i));
        cardViewHolder.productQuantity.setText(cardQuantity.get(i));
        cardViewHolder.imageView.setImageResource(cardImage1.get(i));

        cardViewHolder.cartQuantityIncrement.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                count++;
                String te= cardQuantity.get(i);
                int tt= Integer.parseInt(te)+count;
                if (count>0) {
                    cardViewHolder.productQuantity.setText(String.valueOf(tt));
                }
            }
        });

        cardViewHolder.cartQuantityDecrement.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                count--;
                String te= cardQuantity.get(i);
                int tt= Integer.parseInt(te)+count;
                if (count>0) {
                    cardViewHolder.productQuantity.setText(String.valueOf(tt));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cardQuantity.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        View mView;
        ImageView imageView;
        TextView productsName, productsPrize, productQuantity;
        ImageButton cartQuantityIncrement, cartQuantityDecrement;

        @SuppressLint("WrongViewCast")
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            mView= itemView;
            imageView=  itemView.findViewById(R.id.card_product_image);
            productsName= itemView.findViewById(R.id.card_product_name);
            productsPrize= itemView.findViewById(R.id.card_product_prize);
            productQuantity= itemView.findViewById(R.id.card_product_quantity);
            cartQuantityIncrement= itemView.findViewById(R.id.card_product_increment);
            cartQuantityDecrement= itemView.findViewById(R.id.card_product_decrement);

            //fab= itemView.findViewById(R.id.fab_card_product_remove);

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
