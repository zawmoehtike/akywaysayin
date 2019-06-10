package com.johnsmith.zawmoehtike.akywaysayin.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.johnsmith.zawmoehtike.akywaysayin.data.repository.BorrowLendItemRepository;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;

import java.util.List;

public class BorrowLendItemViewModel extends AndroidViewModel {
    private BorrowLendItemRepository borrowLendItemRepository;

    private final LiveData<List<BorrowLendItem>> borrowLendItemList;

    private final LiveData<List<BorrowLendItem>> borrowLendItemListMeFromU;

    private final LiveData<List<BorrowLendItem>> borrowLendItemListMeToU;

    public BorrowLendItemViewModel(Application application) {
        super(application);

        borrowLendItemRepository = new BorrowLendItemRepository(application);

        //  Get Live Data of List of Borrow Lend Item
        borrowLendItemList = borrowLendItemRepository.showAll();

        //  Get Live Data of List of Borrow Lend Item
        borrowLendItemListMeFromU = borrowLendItemRepository.showAllMeFromU();

        //  Get Live Data of List of Borrow Lend Item
        borrowLendItemListMeToU = borrowLendItemRepository.showAllMeToU();
    }

    public LiveData<List<BorrowLendItem>> getAllBorrowLendItem() {
        return borrowLendItemList;
    }

    public LiveData<List<BorrowLendItem>> getAllBorrowLendItemMeFromU() {
        return borrowLendItemListMeFromU;
    }

    public LiveData<List<BorrowLendItem>> getBorrowLendItemByQuery(String query) {
        return borrowLendItemRepository.showByQuery(query);
    }

    public LiveData<List<BorrowLendItem>> getAllBorrowLendItemMeToU() {
        return borrowLendItemListMeToU;
    }

    public BorrowLendItem getBorrowLendItem(int id) {
        return borrowLendItemRepository.show(id);
    }

    public void deleteItem(BorrowLendItem borrowLendItem) {
        borrowLendItemRepository.delete(borrowLendItem);
    }

    public void updateItem(BorrowLendItem borrowLendItem) {
        borrowLendItemRepository.update(borrowLendItem);
    }

    public void insertItem(BorrowLendItem borrowLendItem) {
        borrowLendItemRepository.insert(borrowLendItem);
    }
}
