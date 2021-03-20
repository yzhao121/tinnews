package com.ita.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ita.tinnews.databinding.ActivityMainBinding;
import com.ita.tinnews.model.NewsResponse;
import com.ita.tinnews.network.NewsApi;
import com.ita.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        NewsApi api = RetrofitClient.newInstance(this)
                .create(NewsApi.class);
        api.getTopHeadlines("US")
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        // response return error
                        if (response.isSuccessful()) {
                            Log.d("getTopHeadlines", response.body().toString());
                        } else {
                            Log.d("getTopHeadlines", response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        // request send error
                        Log.d("getTopHeadlines", t.toString());
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

}