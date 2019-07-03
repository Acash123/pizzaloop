package com.example.pizzaloop.Order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pizzaloop.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>{
    private Context mContext;
    private ArrayList<item_details> mData;
    //New Code
    private OnItemClickListener mListner;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setonItemClickListner(OnItemClickListener listener){
        mListner = listener;
    }
    //

    public Adapter(Context context, ArrayList<item_details> data){
        mContext=context;
        mData=data;
    }


    @Override
    public Adapter.myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_item, viewGroup , false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter.myViewHolder myViewHolder, int position) {
        item_details currentitem = mData.get(position);


            String BackgroundUrl = currentitem.getBackgroundURL();
            String name = currentitem.getName();
            double price = currentitem.price;
            String details = currentitem.getDetails();


                myViewHolder.pname.setText(name);
                myViewHolder.Price.setText("RS." + price);
                Picasso.get().load(BackgroundUrl).fit().centerCrop().into(myViewHolder.Background);
            
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        public ImageView Background;
        public TextView Price;
        public TextView pname;
        public myViewHolder(View itemView) {
            super(itemView);
            Background = itemView.findViewById(R.id.card_background);
            Price = itemView.findViewById(R.id.pizza_price);
            pname = itemView.findViewById(R.id.pizza_name);

            //New Code

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onItemClick(position);
                        }
                    }
                }
            });



            //
        }
    }


}





