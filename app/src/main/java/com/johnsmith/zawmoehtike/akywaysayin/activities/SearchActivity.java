package com.johnsmith.zawmoehtike.akywaysayin.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.view.adapter.BorrowLendItemListAdapter;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements BorrowLendItemListAdapter.DeleteClickListener {

    private BorrowLendItemViewModel borrowLendItemViewModel;
    private BorrowLendItemListAdapter borrowLendItemListAdapter;
    private RecyclerView recyclerView;

    EditText editText;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.etSearch);

        borrowLendItemListAdapter = new BorrowLendItemListAdapter( getApplicationContext(), new ArrayList<BorrowLendItem>(), this);
        recyclerView = findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(borrowLendItemListAdapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Live Data in View Model and Room
                borrowLendItemViewModel = ViewModelProviders.of(SearchActivity.this).get(BorrowLendItemViewModel.class);
                //  Delete and then Refresh, Update and then Refresh, Insert and then Refresh Data by Live Data
                borrowLendItemViewModel.getBorrowLendItemByQuery(editText.getText().toString()).observe(SearchActivity.this, new Observer<List<BorrowLendItem>>() {
                    @Override
                    public void onChanged(@Nullable List<BorrowLendItem> borrowLendItemList) {
                        if(borrowLendItemList.size() > 0) {
                            borrowLendItemListAdapter.addItems(borrowLendItemList);

                            recyclerView.setVisibility(View.VISIBLE);
                        }else{

                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDeleteClickItem(BorrowLendItem borrowLendItem) {

        borrowLendItemViewModel.deleteItem(borrowLendItem);
    }
}