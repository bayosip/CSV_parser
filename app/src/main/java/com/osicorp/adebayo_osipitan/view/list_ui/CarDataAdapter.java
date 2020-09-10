package com.osicorp.adebayo_osipitan.view.list_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.view.FragmentListener;

import java.util.List;
import java.util.zip.Inflater;

public class CarDataAdapter extends RecyclerView.Adapter<CarDataAdapter.DataViewHolder> {

    List<Car_Owners_Data> data;
    FragmentListener listener;
    private DataViewHolder holder;

    public CarDataAdapter(List<Car_Owners_Data> data, FragmentListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(listener.getViewContext());
        View view = inflater.inflate(R.layout.car_data_item, parent, false);
        holder = new DataViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        holder.setCarData(data);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        TextView carModel, carYear, carOwner;
        ImageButton details;
        View view;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            initWidget(itemView);
        }

        private void initWidget(View v){
            carModel = v.findViewById(R.id.textCarName);
            carYear = v.findViewById(R.id.textCarYear);
            carOwner = v.findViewById(R.id.textOwnerName);
            details = v.findViewById(R.id.buttonFullDetails);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showInDetail(getAdapterPosition());
                }
            });
        }

        public void setCarData(List<Car_Owners_Data> data) {
            carModel.setText(data.get(getAdapterPosition()).getCar_model());
            carYear.setText(""+data.get(getAdapterPosition()).getCar_model_year());
            String name = data.get(getAdapterPosition()).getFirst_name()+" "
                    + data.get(getAdapterPosition()).getLast_name();
            carOwner.setText(name);
        }
    }
}
