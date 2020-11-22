package com.example.business;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ECardStackAdapter extends RecyclerView.Adapter<ECardStackAdapter.EViewHolder> {

    private List<EItemModel> items;

    public ECardStackAdapter(List<EItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public EViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.eitem_card, parent, false);
        return new EViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EViewHolder holder, int position) {
        holder.setData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class EViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, businessName, location, businessType, businessStage, investment, investor, goals;
        EViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.eitem_name);
            businessName = itemView.findViewById(R.id.eitem_bussinessName);
            location = itemView.findViewById(R.id.eitem_location);
            businessType = itemView.findViewById(R.id.eitem_bussinessType);
            businessStage = itemView.findViewById(R.id.eitem_businessStage);
            investment = itemView.findViewById(R.id.eitem_investment);
            investor = itemView.findViewById(R.id.eitem_investor);
            goals = itemView.findViewById(R.id.eitem_goals);

        }

        void setData(EItemModel data) {
            Picasso.get()
                    .load(data.getImage())
                    .fit()
                    .centerCrop()
                    .into(image);
            name.setText(data.getName());
            businessName.setText(data.getBusinessName());
            location.setText(data.getLocation());
            businessType.setText(data.getBusinessType());
            businessStage.setText(data.getBusinessStage());
            investment.setText(data.getInvestment());
            investor.setText(data.getInvestor());
            goals.setText(data.getGoals());

        }
    }

    public List<EItemModel> getItems() {
        return items;
    }

    public void setItems(List<EItemModel> items) {
        this.items = items;
    }
}
