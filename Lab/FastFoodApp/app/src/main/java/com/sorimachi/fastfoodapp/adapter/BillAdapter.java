package com.sorimachi.fastfoodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sorimachi.fastfoodapp.R;
import com.sorimachi.fastfoodapp.data.model.Bill;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Bill> mList;
    private BillAdapter.OnItemClickListener mListener;

    public BillAdapter(Context context, ArrayList<Bill> list){
        mContext = context;
        mList = list;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(BillAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.rv_bill_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        TextView txtTime, txtName, txtNumber;
        Button btnDetails;

        txtTime = holder.txtTime;
        txtName = holder.txtName;
        txtNumber = holder.txtNumber;
        btnDetails = holder.btnDetails;

        txtName.setText("SĐT: " + mList.get(position).getCustomerCode());
        txtTime.setText("Giờ: " + toTime(mList.get(position).getHms()));
        txtNumber.setText("Số món: " + mList.get(position).getOrdersList().size());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTime, txtNumber, txtName;
        Button btnDetails;

        public ViewHolder(@NonNull View itemView, BillAdapter.OnItemClickListener listener) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txt_time);
            txtName = itemView.findViewById(R.id.txt_name);
            txtNumber = itemView.findViewById(R.id.txt_number);
            btnDetails = itemView.findViewById(R.id.btn_details);

            btnDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public String toTime(int number){
        String hms = String.format("%06d", number);
        String hh,mm,ss;

        hh = hms.substring(0,2);
        mm = hms.substring(2,4);
        ss = hms.substring(4);

        hms = hh + ":" + mm + ":" + ss;

        return hms;
    }

}
