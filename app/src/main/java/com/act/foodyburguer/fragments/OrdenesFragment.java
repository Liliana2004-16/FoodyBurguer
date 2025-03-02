package com.act.foodyburguer.fragments;

import static com.act.foodyburguer.R.id.tvOrderDescription;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.act.foodyburguer.R;
import com.act.foodyburguer.activities.OrdenesCompraActivity;
import com.google.android.material.button.MaterialButton;


public class OrdenesFragment extends Fragment {
    private ImageView ivOrderOffer;
    private TextView tvOrderDescription, tvOrderPrice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ordenes, container, false);

        ivOrderOffer = view.findViewById(R.id.ivOrderOffer);
        tvOrderDescription = view.findViewById(R.id.tvOrderDescription);
        tvOrderPrice = view.findViewById(R.id.tvOrderPrice);

        // Recuperar los datos enviados desde HomeFragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            int imageRes = bundle.getInt("imageRes", R.drawable.img1sanwich);
            String description = bundle.getString("description", "Sin descripción");
            String price = bundle.getString("price", "$0.00");
            Log.d("HomeFragment", "Enviando Bundle: imageRes=" + imageRes + ", description=" + description + ", price=" + price);


            // Asignar los valores a la interfaz
            ivOrderOffer.setImageResource(imageRes);
            tvOrderDescription.setText(description);
            tvOrderPrice.setText(price);
        } else {
            Log.d("OrdenesFragment", "No se recibió Bundle");
        }
        return view;
    }

}