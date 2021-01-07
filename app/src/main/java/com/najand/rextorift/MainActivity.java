package com.najand.rextorift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.najand.rextorift.adapter.ItemsAdapter;
import com.najand.rextorift.model.Items;
import com.najand.rextorift.retrofit.NasaApi;
import com.najand.rextorift.retrofit.RetrofitInstance;

import java.util.List;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private NasaApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isNetworkConnected()){
            Retrofit retrofit = RetrofitInstance.getInstance();
            api = retrofit.create(NasaApi.class);

            recyclerViewConfig();
            fetchItems();
        }else{
            Toast.makeText(this, "No Network", Toast.LENGTH_LONG).show();
        }


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void fetchItems() {
        compositeDisposable.add(api.getItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::itemSetup));
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i("tag_throw", "fetchItems: "+throwable);
        });
    }

    private void itemSetup(List<Items> items) {
        ItemsAdapter adapter = new ItemsAdapter(items, items1 -> {
            Intent intent = new Intent(MainActivity.this,ItemActivity.class);
            intent.putExtra("item",items1);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }


    private void recyclerViewConfig() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}