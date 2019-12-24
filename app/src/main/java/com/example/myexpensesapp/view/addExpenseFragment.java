package com.example.myexpensesapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myexpensesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class addExpenseFragment extends Fragment {

    private addExpenseFragmentListener fragmentListener;

    @BindView(R.id.expense_title_et_add_fragment)
    EditText titleEditText;

    @BindView(R.id.cost_et_add_fragment)
    EditText costEditText;

    @BindView(R.id.date_et_add_fragment)
    EditText dateEditText;

    @BindView(R.id.description_et_add_fragment)
    EditText descriptionEditText;

    public interface addExpenseFragmentListener {
        void addExpenseFromFragment(String title, double price, String date, String description);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_expense_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        titleEditText.setText("");
        dateEditText.setText("");
        descriptionEditText.setText("");
        costEditText.setText("");

    }

    @OnClick(R.id.save_button_add_fragment)
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

            fragmentListener.addExpenseFromFragment(title, price, date, description);
        }
        else
            Toast.makeText(getActivity(), "Must Enter All Data", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity)
            this.fragmentListener = (MainActivity)context;
    }
}
