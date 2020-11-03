package com.johnsmith.zawmoehtike.akywaysayin.fragments;

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
import com.johnsmith.zawmoehtike.akywaysayin.activities.EditBorrowLendActivity;
import com.johnsmith.zawmoehtike.akywaysayin.data.entity.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.adapters.BorrowLendItemListAdapter;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import me.myatminsoe.mdetect.MDetect;

public class MeToU extends Fragment implements BorrowLendItemListAdapter.DeleteClickListener, BorrowLendItemListAdapter.EditClickListener {

    private HomeViewModel homeViewModel;
    private BorrowLendItemListAdapter borrowLendItemListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout emptyLayout;

    public MeToU() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_to_u, container, false);

        borrowLendItemListAdapter = new BorrowLendItemListAdapter( getContext(), new ArrayList<BorrowLendItem>(), this, this);

        recyclerView = view.findViewById(R.id.rvMeToU);
        emptyLayout = view.findViewById(R.id.layoutMeToUEmptyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(borrowLendItemListAdapter);

        //  Live Data in View Model and Room
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        //  Delete and then Refresh, Update and then Refresh, Insert and then Refresh Data by Live Data
        homeViewModel.getAllBorrowLendItemMeToU().observe(MeToU.this, new Observer<List<BorrowLendItem>>() {
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
    public void onDeleteClickItem(final BorrowLendItem borrowLendItem) {
        new AlertDialog.Builder(getContext())
                .setMessage(MDetect.INSTANCE.getText(getString(R.string.are_you_delete)))
                .setPositiveButton(MDetect.INSTANCE.getText(getString(R.string.delete)), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        homeViewModel.deleteItem(borrowLendItem);
                    }})
                .setNegativeButton(MDetect.INSTANCE.getText(getString(R.string.no)), null).show();
    }

    @Override
    public void onEditClickItem(BorrowLendItem borrowLendItem) {
        Intent intent = new Intent(getContext(), EditBorrowLendActivity.class);
        intent.putExtra("borrow_lend_item_id", borrowLendItem.getId());
        startActivity(intent);
    }
}
