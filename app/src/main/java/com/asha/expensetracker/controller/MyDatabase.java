package com.asha.expensetracker.controller;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.asha.expensetracker.model.MyEntity;


@Database(entities = {MyEntity.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao myDao();

}
