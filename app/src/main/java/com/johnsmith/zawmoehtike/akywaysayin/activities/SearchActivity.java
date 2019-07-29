package com.johnsmith.zawmoehtike.akywaysayin.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.view.adapter.BorrowLendItemListAdapter;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements BorrowLendItemListAdapter.DeleteClickListener, BorrowLendItemListAdapter.EditClickListener {

    private BorrowLendItemViewModel borrowLendItemViewModel;
    private BorrowLendItemListAdapter borrowLendItemListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout layoutEmptyList;

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

        borrowLendItemListAdapter = new BorrowLendItemListAdapter( getApplicationContext(), new ArrayList<BorrowLendItem>(), this, this);
        recyclerView = findViewById(R.id.recyclerViewSearch);
        layoutEmptyList = findViewById(R.id.layoutSearchEmptyList);
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
                            layoutEmptyList.setVisibility(View.GONE);
                        }else{

                            recyclerView.setVisibility(View.GONE);
                            layoutEmptyList.setVisibility(View.VISIBLE);
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
    public void onDeleteClickItem(final BorrowLendItem borrowLendItem) {

        new AlertDialog.Builder(this)
                .setMessage("ဖ်က္မွာလား")
                .setPositiveButton("ဖ်က္မည္", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrowLendItemViewModel.deleteItem(borrowLendItem);
                    }})
                .setNegativeButton("မဖ်က္ပါ", null).show();
    }

    @Override
    public void onEditClickItem(BorrowLendItem borrowLendItem) {
        Intent intent = new Intent(this, EditBorrowLendItemActivity.class);
        intent.putExtra("borrow_lend_item_id", borrowLendItem.getId());
        startActivity(intent);
    }
}