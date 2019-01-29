package com.test.weatherapp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.test.weatherapp.MyPlace;
import com.test.weatherapp.OnPlaceSelected;
import com.test.weatherapp.R;
import com.test.weatherapp.adapters.MyPlaceAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class PlacesListDialog extends DialogFragment {

    public interface OnAdapterChanged {
        void onEmpty();
        void onSelect(MyPlace place);
    }

    private OnPlaceSelected mCallBack;

    public <T extends Activity> void setSettingChangeListener(OnPlaceSelected<T> listener) {
        this.mCallBack = listener;
    }

    @BindView(R.id.place_list)
    RecyclerView recyclerView;
    private MyPlaceAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.places_list_dialog, null);
        ButterKnife.bind(this, view);

        adapter = new MyPlaceAdapter(findAllPlaceObject());
        recyclerView.setAdapter(adapter);
        manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        adapter.setListener(new OnAdapterChanged() {
            @Override
            public void onEmpty() {
                dismiss();
            }

            @Override
            public void onSelect(MyPlace place) {
                mCallBack.onSelected(getActivity(), place);
                dismiss();
            }
        });

        return view;
    }

    private RealmResults<MyPlace> findAllPlaceObject() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(MyPlace.class).findAll();
    }

}
