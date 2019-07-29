package com.johnsmith.zawmoehtike.akywaysayin.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.activities.EditBorrowLendItemActivity;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.view.adapter.BorrowLendItemListAdapter;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MeFromU extends Fragment implements BorrowLendItemListAdapter.DeleteClickListener, BorrowLendItemListAdapter.EditClickListener {

    private BorrowLendItemViewModel borrowLendItemViewModel;
    private BorrowLendItemListAdapter borrowLendItemListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout emptyLayout;

    public MeFromU() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_from_u, container, false);

        borrowLendItemListAdapter = new BorrowLendItemListAdapter( getContext(), new ArrayList<BorrowLendItem>(), this, this);
        recyclerView = view.findViewById(R.id.rvMeFromU);
        emptyLayout = view.findViewById(R.id.layoutMeFromUEmptyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(borrowLendItemListAdapter);

        //  Live Data in View Model and Room
        borrowLendItemViewModel = ViewModelProviders.of(this).get(BorrowLendItemViewModel.class);
        //  Delete and then Refresh, Update and then Refresh, Insert and then Refresh Data by Live Data
        borrowLendItemViewModel.getAllBorrowLendItemMeFromU().observe(MeFromU.this, new Observer<List<BorrowLendItem>>() {
            @Override
            public void onChanged(@Nullable List<BorrowLendItem> borrowLendItemList) {
                if(borrowLendItemList.size() > 0) {
                    borrowLendItemListAdapter.addItems(borrowLendItemList);

                    recyclerView.setVisibility(View.VISIBLE);
                    emptyLayout.setVisibility(View.GONE);
                }else{

                    recyclerView.setVisibility(View.GONE);
                    emptyLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDeleteClickItem(final BorrowLendItem borrowLendItem) {
        new AlertDialog.Builder(getContext())
                .setMessage("ဖ်က္မွာလား")
                .setPositiveButton("ဖ်က္မည္", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        borrowLendItemViewModel.deleteItem(borrowLendItem);
                    }})
                .setNegativeButton("မဖ်က္ပါ", null).show();
    }

    @Override
    public void onEditClickItem(BorrowLendItem borrowLendItem) {
        Intent intent = new Intent(getContext(), EditBorrowLendItemActivity.class);
        intent.putExtra("borrow_lend_item_id", borrowLendItem.getId());
        startActivity(intent);
    }
}
