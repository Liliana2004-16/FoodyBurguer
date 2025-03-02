package com.act.foodyburguer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.act.foodyburguer.R;
import com.act.foodyburguer.adapters.MealAdapter;
import com.act.foodyburguer.api.ApiClient;
import com.act.foodyburguer.api.ApiService;
import com.act.foodyburguer.entities.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        loadMeals();
        return view;
    }
    private void loadMeals() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MealResponse> call = apiService.getMeals("Seafood"); // Puedes cambiar la categoría

        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List meals = response.body().getMeals();
                    mealAdapter = new MealAdapter(getContext(), meals);
                    recyclerView.setAdapter(mealAdapter);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}