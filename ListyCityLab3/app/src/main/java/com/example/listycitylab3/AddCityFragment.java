package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    interface AddCityDialogListener {
        void addCity (City city);
        void editCity(City city, int position);
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener)
        {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    public static AddCityFragment newInstance(String cityName, String provinceName, int position) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putString("city_name", cityName);
        args.putString("province_name", provinceName);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        Bundle args = getArguments();
        boolean isEditing = false;
        int position = -1;

        if (args != null)
        {
            String cityName = args.getString("city_name", "");
            String provinceName = args.getString("province_name", "");
            position = args.getInt("position", -1);
            if (!cityName.isEmpty() && !provinceName.isEmpty() && position != -1){
                editCityName.setText(cityName);
                editProvinceName.setText(provinceName);
                isEditing = true;
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        boolean finalIsEditing = isEditing;
        int finalPosition = position;

        return builder.setView(view).setTitle("Add a city").setNegativeButton("cancel", null).setPositiveButton("Add", (dialog, which) -> {
            String cityName = editCityName.getText().toString();
            String provinceName = editProvinceName.getText().toString();
            if (finalIsEditing) {
                listener.editCity(new City(cityName, provinceName), finalPosition);
            } else {
                listener.addCity(new City(cityName, provinceName));
            }
        }).create();
    }
}
