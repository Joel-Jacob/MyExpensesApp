package com.example.myexpensesapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Query("SELECT * FROM expenses")
    public List<ExpenseEntity> getAllExpenses();

    @Query("Select SUM(price) FROM expenses")
    double getTotalCost();

    @Insert
    void insertNewExpense(ExpenseEntity expenseEntity);

    @Delete
    void deleteExpense(ExpenseEntity expenseEntity);

    @Update
    void updateEntity(ExpenseEntity expenseEntity);
}
