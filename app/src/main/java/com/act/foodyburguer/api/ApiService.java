package com.act.foodyburguer.api;

import com.act.foodyburguer.entities.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("filter.php") // Endpoint para filtrar por categoría
    Call<MealResponse> getMeals(@Query("c") String category);
}
