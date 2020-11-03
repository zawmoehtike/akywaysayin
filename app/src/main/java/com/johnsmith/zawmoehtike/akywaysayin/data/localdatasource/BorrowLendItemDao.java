package com.johnsmith.zawmoehtike.akywaysayin.data.localdatasource;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.johnsmith.zawmoehtike.akywaysayin.data.entity.BorrowLendItem;

import java.util.List;

@Dao
public interface BorrowLendItemDao {
    @Query("SELECT * FROM borrow_lend_items")
    LiveData<List<BorrowLendItem>> getAllBorrowLendItem();

    @Query("SELECT * FROM borrow_lend_items WHERE type = '1' ORDER BY date ASC")
    LiveData<List<BorrowLendItem>> getAllBorrowLendItemMeFromU();

    @Query("SELECT * FROM borrow_lend_items WHERE type = '0' ORDER BY date ASC")
    LiveData<List<BorrowLendItem>> getAllBorrowLendItemMeToU();

    @Query("SELECT * FROM borrow_lend_items WHERE person_name LIKE '%' || :query || '%' " +
            " OR item_name LIKE '%' || :query || '%' ORDER BY date ASC")
    LiveData<List<BorrowLendItem>> getBorrowLendItemByQuery(String query);

    @Query("SELECT * FROM borrow_lend_items where id = :id")
    BorrowLendItem getBorrowLendItem(int id);

    @Query("SELECT COUNT(*) from borrow_lend_items")
    int countBorrowLendItem();

    @Insert
    void insertBorrowLendItem(BorrowLendItem borrowLendItem);

    @Insert
    void insertAllBorrowLendItem(BorrowLendItem... borrowLendItems);

    @Update
    void updateBorrowLendItem(BorrowLendItem borrowLendItem);

//    @Query("UPDATE borrow_lend_items SET item_name = :itemName, person_name = :personName," +
//            " date = :date, type = :type WHERE id = :id")
//    void updateBorrowLendItem(int id, String itemName, String personName, String date, String type);

    @Delete
    void deleteBorrowLendItem(BorrowLendItem borrowLendItem);
}
