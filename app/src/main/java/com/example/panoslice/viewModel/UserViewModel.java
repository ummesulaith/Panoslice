package com.example.panoslice.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.panoslice.data.model.GitModel;
import com.example.panoslice.data.model.ItemModel;
import com.example.panoslice.data.remote.APIService;
import com.example.panoslice.data.remote.RetrofitCall;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ItemModel>> mutableLiveData = new MutableLiveData<ArrayList<ItemModel>>();

    private ArrayList<ItemModel> savedData = new ArrayList<>();




    public void getResponse(String query)
    {
        Retrofit retrofit = APIService.getRetrofitService();
        RetrofitCall apiService = retrofit.create(RetrofitCall.class);
        Call<GitModel> call = apiService.getRepoDetails(query);
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                if (response.body()!=null)
                {

                    GitModel model = response.body();

                    savedData.addAll(response.body().getItems());
//                    savedData.add(model);
                    mutableLiveData.setValue(savedData);
                    Log.d("DBG", "OK");



                }
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.d("DBG", "Failed: "+t.getMessage());

            }
        });
    }

    public LiveData<ArrayList<ItemModel>> getSearchResult(){
        return mutableLiveData;
    }

    public void clear(){
        savedData.clear();
        mutableLiveData.setValue(new ArrayList<ItemModel>());
    }




}
