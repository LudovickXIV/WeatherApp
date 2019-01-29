package com.test.weatherapp.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.test.weatherapp.R;
import com.test.weatherapp.Settings;
import com.test.weatherapp.SettingsChange;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsDialog extends DialogFragment {

    private SettingsChange mCallBack;

    public <T extends Activity> void setSettingChangeListener(SettingsChange<T> listener) {
        this.mCallBack = listener;
    }

    @BindView(R.id.radioGroup)
    RadioGroup group;
    @BindView(R.id.standard_map)
    RadioButton standard;
    @BindView(R.id.dark_map)
    RadioButton dark;
    @BindView(R.id.retro_map)
    RadioButton retro;

    @BindView(R.id.settings_btn_cancel)
    Button cancel;
    @BindView(R.id.settings_btn_ok)
    Button ok;

    @BindView(R.id.show_search)
    CheckBox showSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_dialog, null);
        ButterKnife.bind(this, view);

        showSearch.setChecked(Settings.isShowSearch());

        try {
            switch (Settings.getStyle()) {
                case R.raw.map_standard:
                    standard.setChecked(true);
                    break;
                case R.raw.map_dark:
                    dark.setChecked(true);
                    break;
                case R.raw.map_retro:
                    retro.setChecked(true);
                    break;

                default:
                    standard.setChecked(true);
            }
        } catch(NullPointerException npe) {}

        group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.standard_map:
                    Settings.setMapStyle(R.raw.map_standard);
                    break;
                case R.id.dark_map:
                    Settings.setMapStyle(R.raw.map_dark);
                    break;
                case R.id.retro_map:
                    Settings.setMapStyle(R.raw.map_retro);
                    break;

                    default:
                        Settings.setMapStyle(R.raw.map_standard);
            }
        });

        showSearch.setOnCheckedChangeListener((buttonView, isChecked) -> Settings.setShowSearch(isChecked));

        cancel.setOnClickListener(v -> {
            mCallBack.onNonChanged(getActivity());
            dismiss();
        });
        ok.setOnClickListener(v -> {
            mCallBack.onChanged(getActivity());
            dismiss();
        });
        return view;
    }
}
