package com.example.myexpensesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myexpensesapp.R;
import com.example.myexpensesapp.adapter.ExpenseAdapter;
import com.example.myexpensesapp.database.ExpenseEntity;
import com.example.myexpensesapp.presenter.Contract;
import com.example.myexpensesapp.presenter.MainPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements addExpenseFragment.addExpenseFragmentListener, editExpenseFragment.editExpenseFragmentListener, Contract.ExpenseView, ExpenseAdapter.ExpenseDelegate {

    private Fragment addExpenseFragment = new addExpenseFragment();
    private Fragment editExpenseFragment ;
    private double expenseLimit = 200.00;
    public String return_key = "return_key";
    private Contract.ExpensePresenter expensePresenter;

    @BindView(R.id.expenses_rv_main)
    RecyclerView expensesRecyclerView;

    @BindView(R.id.totalData_tv_main)
    TextView totalTextView;

    @BindView(R.id.main_activity)
    ConstraintLayout mainActivty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        expensePresenter = new MainPresenter(this);

        refreshList();

        RecyclerView.ItemDecoration decorator = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        expensesRecyclerView.addItemDecoration(decorator);
    }

    void refreshList(){
        expensePresenter.getExpense();
        expensePresenter.getTotalExpense();

        if(Double.parseDouble(totalTextView.getText().toString()) > expenseLimit)
            mainActivty.setBackgroundResource(R.drawable.main_bg);

    }

    @OnClick(R.id.add_button_main)
    public void addExpense(View veiw){
        Log.d("TAG_X", "Clicked Save");

        Bundle expenseBundle = new Bundle();
        expenseBundle.putString(return_key, "");

        addExpenseFragment.setArguments(expenseBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .add(R.id.frame_layout_main, addExpenseFragment)
                .addToBackStack(addExpenseFragment.getTag())
                .commit();
    }

    @Override
    public void addExpenseFromFragment(String title, double price, String date, String description) {
        getSupportFragmentManager().popBackStack();

        expensePresenter.insertExpense(new ExpenseEntity(title, price, date, description));
        refreshList();
        //Log.d("TAG_X", title);
    }

    @Override
    public void displayExpense(List<ExpenseEntity> expense) {
        ExpenseAdapter adapter = new ExpenseAdapter(expense, this);
        expensesRecyclerView.setAdapter(adapter);
        expensesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void expenseIsEmpty() {
        Toast.makeText(this, "database is empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayTotal(double total) {
        totalTextView.setText(total+"");
    }

    @Override
    public void displayError(String errorString) {
        Log.d("Tag_X","Error: "+ errorString);

    }

    @Override
    public void ExpenseSelected(ExpenseEntity expense) {
        //Log.d("TAG_X", "Clicked");
        editExpenseFragment = new editExpenseFragment();

        Bundle expenseBundle = new Bundle();
        expenseBundle.putParcelable("my_parcel", expense);
        //Log.d("TAG_X", expense.getTitle());

        editExpenseFragment.setArguments(expenseBundle);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                .add(R.id.frame_layout_main, editExpenseFragment)
                .addToBackStack(editExpenseFragment.getTag())
                .commit();
    }

    @Override
    public void editExpenseFromFragment(int id, String title, double price, String date, String description) {
        getSupportFragmentManager().popBackStack();

        expensePresenter.modifyExpense(new ExpenseEntity(id, title, price, date, description));
        refreshList();

    }

    @Override
    public void deleteExpenseFromFragment(ExpenseEntity expenseEntity) {
        getSupportFragmentManager().popBackStack();

        expensePresenter.deleteExpense(expenseEntity);
        refreshList();

    }
}
