package com.example.panoslice;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.panoslice.data.model.GitModel;
import com.example.panoslice.data.remote.APIService;
import com.example.panoslice.data.remote.RetrofitCall;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<GitModel>> mutableLiveData = new MutableLiveData<>();

    private ArrayList<GitModel> savedData = new ArrayList<GitModel>();

    private ArrayList<GitModel> mAvatimage = new ArrayList<>();
    private ArrayList<GitModel> mName = new ArrayList<>();
    private ArrayList<GitModel> mFullname = new ArrayList<>();
    private ArrayList<GitModel> mWatchercount = new ArrayList<>();


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


                 for (int i =0;i<model.getItems().size();i++)
//                 {
//                     mAvatimage.add(model.getItems().get(i).getOwner().getAvatarURL().toString());
//                     mFullname.add(model.getItems().get(i).getFullName());
//                     mName.add(model.getItems().get(i).getName());
//                     mWatchercount.add(model.getItems().get(i).getName());
//
//                 }

                    savedData.add(model);
                    mutableLiveData.setValue(savedData);
                    Log.d("DBG", "OK");
                    Log.d("DBG", "OK::"+savedData.toString());
                    System.out.println("q1"+savedData);
                    System.out.println("q2"+response.message());
                    System.out.println("q3"+response.raw());


                }
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.d("DBG", "Failed: "+t.getMessage());

            }
        });
    }

    public LiveData<ArrayList<GitModel>> getSearchResult(){
        return mutableLiveData;
    }

    public void clear(){
        savedData.clear();
        mutableLiveData.setValue(new ArrayList<GitModel>());
    }




}
