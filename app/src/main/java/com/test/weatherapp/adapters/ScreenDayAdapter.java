package com.test.weatherapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.weatherapp.R;
import com.test.weatherapp.pojo.Period;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenDayAdapter extends RecyclerView.Adapter<ScreenDayAdapter.DayWeatherViewHolder> {

    private List<Period> weathers;

    public ScreenDayAdapter(List<Period> weathers) {
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public DayWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.day_item, parent, false);
        return new DayWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayWeatherViewHolder holder, int pos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(weathers.get(pos).getTimestamp() * 1000L);

        holder.time.setText(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY) + ":00"));
        holder.temp.setText(String.valueOf(weathers.get(pos).getAvgTempC() + "°"));
        setIconWeather(holder, pos);
    }

    /**
     * метод для установки изображения для каждой погоды
     * @param holder
     * @param pos
     */
    private void setIconWeather(DayWeatherViewHolder holder, int pos) {
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

    public class DayWeatherViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.dayItemTime)
        TextView time;
        @BindView(R.id.dayItemWeather)
        ImageView image;
        @BindView(R.id.dayItemListTemp)
        TextView temp;
        public DayWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
