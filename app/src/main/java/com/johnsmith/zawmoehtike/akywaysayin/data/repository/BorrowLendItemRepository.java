package com.johnsmith.zawmoehtike.akywaysayin.data.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.johnsmith.zawmoehtike.akywaysayin.data.dao.BorrowLendItemDao;
import com.johnsmith.zawmoehtike.akywaysayin.data.database.AppDatabase;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;

import java.util.List;

public class BorrowLendItemRepository {
    AppDatabase appDatabase;

    BorrowLendItemDao borrowLendItemDao;

    LiveData<List<BorrowLendItem>> allBorrowLendItem;

    LiveData<List<BorrowLendItem>> allBorrowLendItemMeFromU;

    LiveData<List<BorrowLendItem>> allBorrowLendItemMeToU;

    public BorrowLendItemRepository(Application application){
        appDatabase = AppDatabase.getAppDatabase(application);

        borrowLendItemDao = appDatabase.borrowLendItemDAO();
        allBorrowLendItem = borrowLendItemDao.getAllBorrowLendItem();

        allBorrowLendItemMeFromU = borrowLendItemDao.getAllBorrowLendItemMeFromU();

        allBorrowLendItemMeToU = borrowLendItemDao.getAllBorrowLendItemMeToU();
    }

    public void insert(BorrowLendItem student){
        new BorrowLendItemRepository.insertAsyncTask(borrowLendItemDao).execute(student);
    }

    public void delete(BorrowLendItem student){
        new BorrowLendItemRepository.deleteAsyncTask(borrowLendItemDao).execute(student);
    }

    public void update(BorrowLendItem student){
        new BorrowLendItemRepository.updateAsyncTask(borrowLendItemDao).execute(student);
    }

    public LiveData<List<BorrowLendItem>> showAll(){
        return allBorrowLendItem;
    }

    public LiveData<List<BorrowLendItem>>  showAllMeFromU(){
        return allBorrowLendItemMeFromU;
    }

    public LiveData<List<BorrowLendItem>> showByQuery(String query) {
        return borrowLendItemDao.getBorrowLendItemByQuery(query);
    }

    public LiveData<List<BorrowLendItem>> showAllMeToU(){
        return allBorrowLendItemMeToU;
    }

    public BorrowLendItem show(int id){
        return appDatabase.borrowLendItemDAO().getBorrowLendItem(id);
    }

    private static class deleteAsyncTask extends AsyncTask<BorrowLendItem, Void, Void> {

        private BorrowLendItemDao borrowLendItemDao;

        deleteAsyncTask(BorrowLendItemDao borrowLendItemDao) {
            this.borrowLendItemDao = borrowLendItemDao;
        }

        @Override
        protected Void doInBackground(final BorrowLendItem... params) {
            borrowLendItemDao.deleteBorrowLendItem(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<BorrowLendItem, Void, Void> {

        private BorrowLendItemDao borrowLendItemDao;

        updateAsyncTask(BorrowLendItemDao borrowLendItemDao) {
            this.borrowLendItemDao = borrowLendItemDao;
        }

        @Override
        protected Void doInBackground(final BorrowLendItem... params) {
            borrowLendItemDao.updateBorrowLendItem(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<BorrowLendItem, Void, Void> {

        private BorrowLendItemDao borrowLendItemDao;

        insertAsyncTask(BorrowLendItemDao borrowLendItemDao) {
            this.borrowLendItemDao = borrowLendItemDao;
        }

        @Override
        protected Void doInBackground(final BorrowLendItem... params) {
            borrowLendItemDao.insertBorrowLendItem(params[0]);
            return null;
        }
    }
}
