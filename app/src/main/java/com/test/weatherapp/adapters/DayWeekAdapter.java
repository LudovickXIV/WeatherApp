package com.test.weatherapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.weatherapp.R;
import com.test.weatherapp.WeekListFragment;
import com.test.weatherapp.pojo.Period;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayWeekAdapter extends RecyclerView.Adapter<DayWeekAdapter.WeekWeatherViewHolder>{

    private List<Period> weathers;
    private Context context;
    int selected_position = 0;
    private WeekListFragment.OnDayChanged listener;


    public DayWeekAdapter(Context context, List<Period> weathers){
        this.context = context;
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public WeekWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.week_item, parent, false);
        return new WeekWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekWeatherViewHolder holder, int pos) {
        holder.itemView.setBackgroundColor(selected_position == pos ? context.getResources().getColor(R.color.weekWeatherSelectedColor) : Color.TRANSPARENT);
        holder.temp.setTextColor(selected_position == pos ? context.getResources().getColor(R.color.dayBackgroundColor) : context.getResources().getColor(R.color.weekWeatherTextColor));
        holder.day.setTextColor(selected_position == pos ? context.getResources().getColor(R.color.dayBackgroundColor) : context.getResources().getColor(R.color.weekWeatherTextColor));
        holder.image.setColorFilter(selected_position == pos ? context.getResources().getColor(R.color.dayBackgroundColor) : context.getResources().getColor(R.color.weekWeatherTextColor));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(weathers.get(pos).getTimestamp() * 1000L);
        String[] arr_day = context.getResources().getStringArray(R.array.day_of_week);

        String day = arr_day[calendar.get(Calendar.DAY_OF_WEEK)];
        String minMax = weathers.get(pos).getMinTempC() + "°" + "/" +
                weathers.get(pos).getMaxTempC() + "°";
        holder.day.setText(day);
        holder.temp.setText(minMax);

        setIconWeather(holder, pos);
    }

    /**
     * метод для установки изображения для каждой погоды
     * @param holder
     * @param pos
     */
    private void setIconWeather(WeekWeatherViewHolder holder, int pos) {
        if (weathers.get(pos).getCloudsCoded().contains("CL") ||
                weathers.get(pos).getCloudsCoded().contains("FW")) {
            if (weathers.get(pos).isDay()) {
                holder.image.setImageResource(R.drawable.ic_white_day_bright);
            } else {
                holder.image.setImageResource(R.drawable.ic_white_night_bright);
            }
        } else {
            if (weathers.get(pos).isDay()) {
                holder.image.setImageResource(R.drawable.ic_white_day_cloudy);
            } else {
                holder.image.setImageResource(R.drawable.ic_white_night_cloudy);
            }

            try {
                if (weathers.get(pos).getWeatherCoded() != null) {
                    if (weathers.get(pos).getWeatherCoded().get(0).getWx().contains("T")) {
                        if (weathers.get(pos).isDay()) {
                            holder.image.setImageResource(R.drawable.ic_white_day_thunder);
                        } else {
                            holder.image.setImageResource(R.drawable.ic_white_night_thunder);
                        }
                    } else if (weathers.get(pos).getWeatherCoded().get(0).getWx().contains("R") ||
                            weathers.get(pos).getWeatherCoded().get(0).getWx().contains("S")) {
                        if (weathers.get(pos).isDay()) {
                            holder.image.setImageResource(R.drawable.ic_white_day_rain);
                        } else {
                            holder.image.setImageResource(R.drawable.ic_white_night_rain);
                        }
                    } else if (weathers.get(pos).getWeatherCoded().get(0).getWx().contains("RS") ||
                            weathers.get(pos).getWeatherCoded().get(0).getWx().contains("SI")) {
                        if (weathers.get(pos).isDay()) {
                            holder.image.setImageResource(R.drawable.ic_white_day_rain);
                        } else {
                            holder.image.setImageResource(R.drawable.ic_white_night_rain);
                        }
                    } else if (weathers.get(pos).getWeatherCoded().get(0).getWx().contains("RW") ||
                            weathers.get(pos).getWeatherCoded().get(0).getWx().contains("SW")) {
                        if (weathers.get(pos).isDay()) {
                            holder.image.setImageResource(R.drawable.ic_white_day_shower);
                        } else {
                            holder.image.setImageResource(R.drawable.ic_white_night_shower);
                        }
                    } else {
                        if (weathers.get(pos).isDay()) {
                            holder.image.setImageResource(R.drawable.ic_white_day_cloudy);
                        } else {
                            holder.image.setImageResource(R.drawable.ic_white_night_cloudy);
                        }
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                if (weathers.get(pos).isDay()) {
                    holder.image.setImageResource(R.drawable.ic_white_day_cloudy);
                } else {
                    holder.image.setImageResource(R.drawable.ic_white_night_cloudy);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public void setListener(WeekListFragment.OnDayChanged listener){
        this.listener = listener;
    }

    public class WeekWeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.weekWeatherDay)
        TextView day;
        @BindView(R.id.weekWeatherIc)
        ImageView image;
        @BindView(R.id.weekWeatherTemp)
        TextView temp;

        public WeekWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ViewGroup.LayoutParams params = itemView.getLayoutParams();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            listener.onSelect(weathers.get(getAdapterPosition()));
        }
    }

}
