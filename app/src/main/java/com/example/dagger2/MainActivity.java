package com.example.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ApiComponent apiComponent;
    ListView listView;
    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(getApplication()))
                .apiModule(new ApiModule("https://restcountries.eu/rest/v2/"))
                .build();
        apiComponent.inject(this);
        listView = (ListView) findViewById(R.id.listViewCountries);
        getCountries();
    }

    private void getCountries() {
        Api api = retrofit.create(Api.class);
        Call<List<Country>> countryCall = api.getCountries();
        countryCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countryList = response.body();

                String[] countries = new String[countryList.size()];


                for (int i = 0; i < countryList.size(); i++) {
                    countries[i] = countryList.get(i).getName();
                }

                listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, countries));
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}