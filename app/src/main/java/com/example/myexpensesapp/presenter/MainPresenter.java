package com.example.myexpensesapp.presenter;

import androidx.room.Room;

import com.example.myexpensesapp.database.ExpenseDatabase;
import com.example.myexpensesapp.database.ExpenseEntity;
import com.example.myexpensesapp.view.MainActivity;

import java.util.List;

public class MainPresenter implements Contract.ExpensePresenter {

    private Contract.ExpenseView expenseView;
    private ExpenseDatabase expenseDatabase;
    private List<ExpenseEntity> expense;

    public MainPresenter(Contract.ExpenseView expenseView) {
        this.expenseView = expenseView;

        try{
                expenseDatabase = Room.databaseBuilder(
                        ((MainActivity)expenseView).getApplicationContext(),
                        ExpenseDatabase.class,
                        "room.db")
                        .allowMainThreadQueries()
                        .build();

        }catch (Exception e){
            expenseView.displayError("Failed to create database");
        }
    }

    @Override
    public void insertExpense(ExpenseEntity expenseEntity) {
        try{
            expenseDatabase.expenseDAO().insertNewExpense(expenseEntity);
        }catch (Exception e){
            expenseView.displayError("Failed to insert" + expenseEntity.getTitle());
        }
    }

    @Override
    public void modifyExpense(ExpenseEntity expenseEntity) {
        try{
            expenseDatabase.expenseDAO().updateEntity(expenseEntity);
        }catch (Exception e){
            expenseView.displayError("Failed to modify " + expenseEntity.getTitle());
        }
    }

    @Override
    public void deleteExpense(ExpenseEntity expenseEntity) {
        try{
            expenseDatabase.expenseDAO().deleteExpense(expenseEntity);
        }catch (Exception e){
            expenseView.displayError("Failed to delete "+expenseEntity.getTitle());
        }
    }

    @Override
    public void getTotalExpense() {
        if(expenseDatabase != null) {
            expense = expenseDatabase.expenseDAO().getAllExpenses();

            if (expense.isEmpty())
                expenseView.expenseIsEmpty();
            else
                expenseView.displayTotal(expenseDatabase.expenseDAO().getTotalCost());
        }
        else {
            expenseView.displayError("could not get receipts");
            //Log.d("TAG_X", "here");
        }
    }

    @Override
    public void getExpense() {
        expense = expenseDatabase.expenseDAO().getAllExpenses();

        if(expense.isEmpty())
            expenseView.expenseIsEmpty();
        else
            expenseView.displayExpense(expense);
    }
}
