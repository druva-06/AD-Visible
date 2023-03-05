package com.example.advisible.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advisible.R;
import com.example.advisible.domain.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    List<Address> mAddressList;
    private RadioButton mSelectedRadioButton;

    public AddressAdapter(Context applicationContext, List<Address> mAddressList) {
        this.context = applicationContext;
        this.mAddressList = mAddressList;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_address_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {
        holder.mAddress.setText(mAddressList.get(position).getAddress());

        holder.mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Address address:mAddressList){
                    address.setSelected(false);
                }
                mAddressList.get(holder.getAdapterPosition()).setSelected(true);
                if(mSelectedRadioButton!=null){
                    mSelectedRadioButton.setChecked(false);
                }
                mSelectedRadioButton = (RadioButton) v;
                mSelectedRadioButton.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mAddress;
        private RadioButton mRadio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAddress = itemView.findViewById(R.id.address_add);
            mRadio = itemView.findViewById(R.id.select_address);
        }
    }
}
