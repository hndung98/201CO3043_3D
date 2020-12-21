package com.sorimachi.fastfoodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.ModelShop;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelShop> mList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ShopAdapter(Context context, ArrayList<ModelShop> list){
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_shop_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view, mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelShop item_shop = mList.get(position);
        ImageView image = holder.item_image;
        TextView name,address;

        name = holder.item_name;
        address = holder.item_address;

        name.setText(item_shop.getName());
        address.setText(item_shop.getAddress());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.capsule)
                .error(R.drawable.capsule)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext)
                .load(item_shop.getImage())
                .apply(options)
                .into(image);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_image;
        TextView item_name, item_address;
        public ViewHolder(View view, OnItemClickListener listener) {
            super(view);

            item_image = view.findViewById(R.id.item_image);
            item_name = view.findViewById(R.id.item_name);
            item_address = view.findViewById(R.id.item_address);

            view.setOnClickListener(new View.OnClickListener() {
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
