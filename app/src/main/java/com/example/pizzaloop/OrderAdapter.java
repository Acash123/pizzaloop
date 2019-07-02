package com.example.pizzaloop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.myViewHolder>{
    private Context mContext;
    private ArrayList<Order> mData;
    public static String OrderSTATUS;
    public static String orderId;
    public static String userId;
    public static String pizzaName;
    public static int qty;
    public static Double totalPrice;
    public static String paymentMethod;
    public static String phoneNumber ;
    public static String Address;
    public static String OrderStatus;
    public static String pizzaid;

    //New Code
    private OnItemClickListener mListner;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setonItemClickListner(OnItemClickListener listener){
        mListner = listener;
    }
    //

    public OrderAdapter(Context context, ArrayList<Order> data){
        mContext=context;
        mData=data;
    }


    @Override
    public OrderAdapter.myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup , false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrderAdapter.myViewHolder myViewHolder, int position) {
        Order currentitem = mData.get(position);




       // Picasso.get().load(BackgroundUrl).fit().centerCrop().into(myViewHolder.Background);
//
        String orderId=currentitem.getOrderId();
        String userId=currentitem.getUserId();
        String pizzaName=currentitem.getPizzaName();
        String pizzaID=currentitem.getPizzaId();
        int qty=currentitem.getQty();
        Double totalPrice=currentitem.getTotalPrice();
        String paymentMethod=currentitem.getPaymentMethod();
        String phoneNumber=currentitem.getPhoneNumber();
        String Address=currentitem.getAddress();
        String OrderStatus=currentitem.getOrderStatus();
        myViewHolder.pname.setText(pizzaName);
        myViewHolder.Price.setText("RS."+ totalPrice);
        /////////////////////////////////////////////////
        OrderSTATUS=OrderStatus;
       this.userId=userId;
        this.pizzaName=pizzaName;
       this.qty=qty;
         this.totalPrice=totalPrice;
       this.paymentMethod=paymentMethod;
        this.Address=Address;
     this.OrderStatus=OrderStatus;
         this.orderId=orderId;
         this.phoneNumber=phoneNumber;
         this.pizzaid=pizzaID;
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
          //  Background = itemView.findViewById(R.id.card_background);
            Price = itemView.findViewById(R.id.pizza_price_cart);
            pname = itemView.findViewById(R.id.pizza_name_cart);
            Background = itemView.findViewById(R.id.cart_card_background);

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
    public static String ORDER_STATUS(){
        return OrderStatus;
    }
    public static String PIZZA_NAME(){
        return pizzaName;
    }
    public static String USER_ID(){
        return userId;
    }
    public static int QTY(){
        return qty;
    }
    public static Double TOTAL_PRICE(){
        return totalPrice;
    }
    public static String PIZZAID(){
        return pizzaid;
    }
    public static String PHONENUMBER(){
        return phoneNumber;
    }
    public static String ADDRESS(){
        return Address;
    }
    public static String PAYMENTMETHOD(){return paymentMethod;}
    public static String ORDER(){return orderId;}



    }










