package edu.uta.leanmed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.uta.leanmed.activities.R;
import edu.uta.leanmed.pojo.Inventory;

/**
 * Created by Vaibhav's Console on 4/21/2019.
 */

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder>{
    private Context mContext;
    private List<Inventory> inventoryList;
    OnItemClickListener mItemClickListener;
    public MedicineAdapter(Context mContext, List<Inventory> inventoryList){
        this.mContext=mContext;
        this.inventoryList=inventoryList;
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView medicineName,medInput,dosage,inventory,expiry,zone;
        private LinearLayout medicine;
        public ViewHolder(View itemView) {
            super(itemView);
            medicine=itemView.findViewById(R.id.medicine);
            medicineName=itemView.findViewById(R.id.medicine_name);
            medInput=itemView.findViewById(R.id.med_input);
            dosage=itemView.findViewById(R.id.invetory);
            expiry=itemView.findViewById(R.id.expiry);
            zone=itemView.findViewById(R.id.availableAt);
            inventory=itemView.findViewById(R.id.invetory);
            medicine.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView,getPosition());
            }
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final Inventory inventory= inventoryList.get(position);
        holder.medicineName.setText(inventory.getMedicine().getTradeName()+" ("+inventory.getMedicine().getGenName()+")");
        holder.medInput.setText(mContext.getString(R.string.med_input)+inventory.getMedicine().getMedicineType());
        holder.dosage.setText(mContext.getString(R.string.dosage)+inventory.getMedicine().getDosage());
        holder.expiry.setText(mContext.getString(R.string.expiry)+inventory.getExpiryDate());
        holder.inventory.setText(mContext.getString(R.string.inventory)+inventory.getUnits());
        holder.zone.setText(mContext.getString(R.string.available_at)+inventory.getZone().getZoneId());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }
}
