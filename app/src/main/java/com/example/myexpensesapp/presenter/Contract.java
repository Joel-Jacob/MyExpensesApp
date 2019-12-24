package com.example.myexpensesapp.presenter;

import com.example.myexpensesapp.database.ExpenseEntity;

import java.util.List;

public interface Contract {

    interface ExpensePresenter {
        void insertExpense(ExpenseEntity expenseEntity);
        void modifyExpense(ExpenseEntity expenseEntity);
        void deleteExpense(ExpenseEntity expenseEntity);
        void getTotalExpense();
        void getExpense();
    }

    interface ExpenseView{
        void displayExpense(List<ExpenseEntity> expense);
        void expenseIsEmpty();
        void displayTotal(double total);
        void displayError(String errorString);
    }
}
