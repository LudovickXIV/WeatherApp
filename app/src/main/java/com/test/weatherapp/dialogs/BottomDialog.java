package com.test.weatherapp.dialogs;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.test.weatherapp.MyPlace;
import com.test.weatherapp.R;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

import static com.test.weatherapp.MapsActivity.KEY_BOTTOM_SHEET_DIALOG;
import static com.test.weatherapp.MapsActivity.KEY_IS_SAVED;

public class BottomDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private List<Address> data;
    private boolean isSelected;

    @BindView(R.id.country)
    TextView countryTv;
    @BindView(R.id.city)
    TextView cityTv;
    @BindView(R.id.state)
    TextView stateTv;
    @BindView(R.id.lat)
    TextView lat;
    @BindView(R.id.lon)
    TextView lon;
    @BindView(R.id.button_sheet_add)
    CheckBox places;
    @BindView(R.id.button_sheet_continue)
    Button dismissBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = (List<Address>) getArguments().getSerializable(KEY_BOTTOM_SHEET_DIALOG);
        isSelected = getArguments().getBoolean(KEY_IS_SAVED, false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = getLayoutInflater().inflate(R.layout.bottom_sheets_layout, null);
        ButterKnife.bind(this, view);

            try {
                countryTv.setText(String.valueOf(data.get(0).getCountryName()));
                cityTv.setText((String.valueOf(data.get(0).getLocality()).contains("null"))?
                        String.valueOf(getResources().getString(R.string.warning_unknown_city_state)) :
                        String.valueOf(data.get(0).getLocality()));

                stateTv.setText((String.valueOf(data.get(0).getAdminArea()).contains("null"))?
                        String.valueOf(getResources().getString(R.string.warning_unknown_city_state)) :
                        String.valueOf(data.get(0).getAdminArea()));

                lat.setText((String.valueOf(data.get(0).getLatitude()).length() > 8)?
                        String.valueOf(data.get(0).getLatitude()).substring(0, 7) : String.valueOf(data.get(0).getLatitude()));

                lon.setText((String.valueOf(data.get(0).getLongitude()).length() > 8)?
                        String.valueOf(data.get(0).getLongitude()).substring(0, 7) : String.valueOf(data.get(0).getLongitude()));

                places.setSelected(isSelected);
            } catch (IndexOutOfBoundsException ioe) {
                countryTv.setText(getResources().getString(R.string.warning_unknown_location));
                cityTv.setText(getResources().getString(R.string.warning_unknown_city_state));
                stateTv.setText(getResources().getString(R.string.warning_unknown_city_state));
                lat.setText("0.0");
                lon.setText("0.0");
                places.setClickable(false);
                places.setBackgroundColor(Color.GRAY);
            }

            dismissBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (places.isChecked()) {
            Realm realm = Realm.getDefaultInstance();

            MyPlace place = new MyPlace();
            place.setId(UUID.randomUUID().toString());
            place.setCountry(String.valueOf(countryTv.getText()));
            place.setCity(String.valueOf(cityTv.getText()));
            place.setState(String.valueOf(stateTv.getText()));

            place.setLatitude(Double.valueOf(lat.getText().toString()));
            place.setLongitude(Double.valueOf(lon.getText().toString()));

            realm.beginTransaction();
            realm.insert(place);
            realm.commitTransaction();
        }
        this.dismiss();
    }
}

