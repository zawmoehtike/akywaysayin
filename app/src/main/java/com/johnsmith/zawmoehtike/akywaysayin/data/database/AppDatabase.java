package com.johnsmith.zawmoehtike.akywaysayin.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.johnsmith.zawmoehtike.akywaysayin.data.dao.BorrowLendItemDao;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;

@Database(entities = {BorrowLendItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract BorrowLendItemDao borrowLendItemDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "a_kyway_sa_yin-db")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
