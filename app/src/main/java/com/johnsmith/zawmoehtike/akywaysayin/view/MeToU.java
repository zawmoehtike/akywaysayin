package com.johnsmith.zawmoehtike.akywaysayin.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.view.adapter.BorrowLendItemListAdapter;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class MeToU extends Fragment implements BorrowLendItemListAdapter.DeleteClickListener {

    private BorrowLendItemViewModel borrowLendItemViewModel;
    private BorrowLendItemListAdapter borrowLendItemListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout emptyLayout;

    public MeToU() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_to_u, container, false);

        borrowLendItemListAdapter = new BorrowLendItemListAdapter( getContext(), new ArrayList<BorrowLendItem>(), this);

        recyclerView = view.findViewById(R.id.rvMeToU);
        emptyLayout = view.findViewById(R.id.layoutMeToUEmptyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(borrowLendItemListAdapter);

        //  Live Data in View Model and Room
        borrowLendItemViewModel = ViewModelProviders.of(this).get(BorrowLendItemViewModel.class);
        //  Delete and then Refresh, Update and then Refresh, Insert and then Refresh Data by Live Data
        borrowLendItemViewModel.getAllBorrowLendItemMeToU().observe(MeToU.this, new Observer<List<BorrowLendItem>>() {
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
    public void onDeleteClickItem(BorrowLendItem borrowLendItem) {

        borrowLendItemViewModel.deleteItem(borrowLendItem);
    }
}
