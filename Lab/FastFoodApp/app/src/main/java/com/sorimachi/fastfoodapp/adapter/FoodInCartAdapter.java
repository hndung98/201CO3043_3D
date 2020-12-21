package com.sorimachi.fastfoodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.ModelFoodInCart;

import java.util.ArrayList;

public class FoodInCartAdapter extends RecyclerView.Adapter<FoodInCartAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelFoodInCart> mList;
    private boolean allowEditAmount;
    private OnMinusItemClickListener minusListener;
    private OnPlusItemClickListener plusListener;

    public FoodInCartAdapter(Context context, ArrayList<ModelFoodInCart> list, boolean allowEditAmount){
        mContext = context;
        mList = list;
        this.allowEditAmount = allowEditAmount;
    }

    public interface OnMinusItemClickListener {
        void onItemClick(int position);
    }

    public interface OnPlusItemClickListener{
        void onItemClick(int position);
    }

    public void setOnMinusItemClickListener(OnMinusItemClickListener listener) {
        minusListener = listener;
    }

    public void setOnPlusItemClickListener(OnPlusItemClickListener listener){
        plusListener =  listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_food_in_cart_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, minusListener, plusListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name = holder.item_name;
        TextView price = holder.item_price;
        TextView amount = holder.item_amount;
        ImageButton btn_minus = holder.item_minus;
        ImageButton btn_plus = holder.item_plus;

        int intAmount = mList.get(position).getAmount();
        int intPrice = Integer.parseInt(mList.get(position).getPrice()) * intAmount;

        name.setText(mList.get(position).getName());
        price.setText(intPrice+"");
        amount.setText(intAmount+"");
        if(!allowEditAmount){
            btn_minus.setVisibility(View.INVISIBLE);
            btn_plus.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_name, item_price, item_amount;
        ImageButton item_minus, item_plus;

        public ViewHolder(@NonNull View itemView, OnMinusItemClickListener minusListener, OnPlusItemClickListener plusListener) {
            super(itemView);

            item_name = itemView.findViewById(R.id.item_name);
            item_price = itemView.findViewById(R.id.item_price);
            item_amount = itemView.findViewById(R.id.item_amount);
            item_minus = itemView.findViewById(R.id.btn_minus);
            item_plus = itemView.findViewById(R.id.btn_plus);

            item_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (minusListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            minusListener.onItemClick(position);
                            int amount = mList.get(position).getAmount();
                            int price = Integer.parseInt(mList.get(position).getPrice()) * amount;
                            item_amount.setText(amount + "");
                            item_price.setText(price + "");
                        }
                    }
                }
            });

            item_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (plusListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            plusListener.onItemClick(position);
                            int amount = mList.get(position).getAmount();
                            int price = Integer.parseInt(mList.get(position).getPrice()) * amount;
                            item_amount.setText(amount + "");
                            item_price.setText(price + "");
                        }
                    }
                }
            });
        }
    }

}
