package com.sorimachi.fastfoodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.ModelFood;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelFood> mList;
    private OnItemClickListener mListener;
    private String buttonText;

    public FoodAdapter(Context context, ArrayList<ModelFood> list, String buttonText){
        mContext = context;
        mList = list;
        this.buttonText = buttonText;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(FoodAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_food_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view, mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelFood item_food = mList.get(position);
        ImageView image = holder.item_image;
        TextView name, place, price;
        Button btn_add;

        name = holder.item_name;
        place = holder.item_status;
        price = holder.item_price;
        btn_add = holder.item_btn_add;

        name.setText(item_food.getName());
        place.setText(item_food.getStatus());
        price.setText(item_food.getPrice());
        btn_add.setText(buttonText);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.capsule)
                .error(R.drawable.capsule)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext)
                .load(item_food.getImage())
                .apply(options)
                .into(image);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image;
        TextView item_name, item_status, item_price;
        Button item_btn_add;
        public ViewHolder (View view, FoodAdapter.OnItemClickListener listener){
            super(view);

            item_image = view.findViewById(R.id.item_image);
            item_name = view.findViewById(R.id.item_name);
            item_status = view.findViewById(R.id.item_status);
            item_price = view.findViewById(R.id.item_price);
            item_btn_add = view.findViewById(R.id.item_btn_add_to_card);

            item_btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
