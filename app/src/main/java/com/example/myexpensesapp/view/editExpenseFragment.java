package com.example.myexpensesapp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myexpensesapp.R;
import com.example.myexpensesapp.database.ExpenseEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class editExpenseFragment extends Fragment {

    ExpenseEntity expenseEntity;
    private editExpenseFragmentListener fragmentListener;

    @BindView(R.id.expense_title_et_edit_fragment)
    EditText titleEditText;

    @BindView(R.id.cost_et_edit_fragment)
    EditText costEditText;

    @BindView(R.id.date_et_edit_fragment)
    EditText dateEditText;

    @BindView(R.id.description_et_edit_fragment)
    EditText descriptionEditText;

    public interface editExpenseFragmentListener {
        void editExpenseFromFragment(int id, String title, double price, String date, String description);
        void deleteExpenseFromFragment(ExpenseEntity expenseEntity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_expense_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        if(bundle != null){
            expenseEntity = bundle.getParcelable("my_parcel");

            Toast.makeText(view.getContext(), expenseEntity.getTitle(), Toast.LENGTH_SHORT).show();

            titleEditText.setText(expenseEntity.getTitle());
            costEditText.setText(expenseEntity.getPrice()+"");
            dateEditText.setText(expenseEntity.getDate());
            descriptionEditText.setText(expenseEntity.getDescription());
        }
        else
            Toast.makeText(view.getContext(), "bundle null", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainActivity){
            this.fragmentListener = (MainActivity)context;
        }
    }

    @OnClick(R.id.save_button_edit_fragment)
    public void saveExpense(View view){

        String title, date, description;
        double price;

        if (!titleEditText.getText().toString().equals("") || !dateEditText.getText().toString().equals("") ||
                !descriptionEditText.getText().toString().equals("") || !costEditText.getText().toString().equals("")) {

            title = titleEditText.getText().toString();
            date = dateEditText.getText().toString();
            description = descriptionEditText.getText().toString();
            price = Double.parseDouble(costEditText.getText().toString());

            titleEditText.setText("");
            costEditText.setText("");
            dateEditText.setText("");
            descriptionEditText.setText("");

            fragmentListener.editExpenseFromFragment(expenseEntity.getExpenseId(), title, price, date, description);
        }
        else
            Toast.makeText(getActivity(), "Must Enter All Data", Toast.LENGTH_SHORT).show();


    }

    @OnClick(R.id.delete_button_edit_fragment)
    public void deleteExpense(View view){
        Log.d("TAG_X",expenseEntity.getTitle() + " " + expenseEntity.getPrice() + " " +
                expenseEntity.getDate() + " " +expenseEntity.getDescription());

        fragmentListener.deleteExpenseFromFragment(expenseEntity);
    }
}