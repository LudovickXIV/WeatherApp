package com.test.weatherapp.adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.weatherapp.MyPlace;
import com.test.weatherapp.R;
import com.test.weatherapp.dialogs.PlacesListDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyPlaceAdapter extends RecyclerView.Adapter<MyPlaceAdapter.PlaceViewHolder> {


    private List<MyPlace> places;
    private PlacesListDialog.OnAdapterChanged listener;

    public void setPlaces(List<MyPlace> places) {
        this.places = places;

    }

    public MyPlaceAdapter(List<MyPlace> places){
        this.places = places;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.my_places_item, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int pos) {
        holder.country.setText(places.get(pos).getCountry());
        holder.city.setText(places.get(pos).getCity());
        holder.id = places.get(pos).getId();
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public void setListener(PlacesListDialog.OnAdapterChanged listener){
        this.listener = listener;
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        protected String id;
        @BindView(R.id.country_item)
        TextView country;
        @BindView(R.id.city_item)
        TextView city;
        @BindView(R.id.select_item)
        Button select;
        @BindView(R.id.delete_item)
        Button delete;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelect(places.get(getAdapterPosition()));
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();

                    RealmResults<MyPlace> delItem = realm.where(MyPlace.class).equalTo("id", id).findAll();
                    delItem.deleteFirstFromRealm();
                    notifyItemRemoved(getAdapterPosition());
                    realm.commitTransaction();
                    if (realm.isEmpty()) {
                        listener.onEmpty();
                    }
                }
            });
        }
    }
}
