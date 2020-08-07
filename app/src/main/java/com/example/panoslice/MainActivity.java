package com.example.panoslice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.example.panoslice.data.model.GitModel;
import com.example.panoslice.data.model.ItemModel;
import com.example.panoslice.ui.RecyclerViewAdapter;
import com.example.panoslice.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    LinearLayout linearLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    UserViewModel viewModel;
    String who;
    List<ItemModel> tmpList;
    Context context;
    ArrayList<ItemModel> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.welcome);
        context = getApplicationContext();
        setUpRecyclerView();

        tmpList = new ArrayList<ItemModel>();

        viewModel = viewModel = new ViewModelProvider(this,
                new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
    }

    private void doSearchUser(String who) {

        viewModel.getResponse(who);

        viewModel.getSearchResult().observe(this, new Observer<ArrayList<ItemModel>>() {
            @Override
            public void onChanged(ArrayList<ItemModel> theList) {

                recyclerViewAdapter.updateData(theList);

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {

                        recyclerViewAdapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }
    private void setUpRecyclerView() {


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerViewAdapter = new RecyclerViewAdapter(this,mData) ;

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(!recyclerView.canScrollVertically(1) && dy != 0) {

                    doSearchUser(who);
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                who = query;
                recyclerViewAdapter.clear();
                viewModel.clear();
                doSearchUser(query);
                recyclerViewAdapter.getFilter().filter(query);

                linearLayout.setVisibility(View.GONE);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}
