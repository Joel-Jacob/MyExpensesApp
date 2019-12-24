package com.example.myexpensesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {ExpenseEntity.class})
public abstract class ExpenseDatabase extends RoomDatabase {

    public abstract ExpenseDAO expenseDAO();
}
