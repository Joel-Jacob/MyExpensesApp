package com.example.myexpensesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myexpensesapp.R;
import com.example.myexpensesapp.database.ExpenseEntity;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseHolder>{
    List<ExpenseEntity> expense;
    ExpenseDelegate expenseDelegate;

    public ExpenseAdapter(List<ExpenseEntity> expense, ExpenseDelegate expenseDelegate) {
        this.expense = expense;
        this.expenseDelegate = expenseDelegate;
    }

    @NonNull
    @Override
    public ExpenseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.expense_item_layout, parent, false);

        return new ExpenseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseHolder holder, int position) {
        holder.titleTextView.setText(expense.get(position).getTitle());
        holder.costTextView.setText("$ " +expense.get(position).getPrice());
        holder.dateTextView.setText(expense.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseDelegate.ExpenseSelected(expense.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense.size();
    }

    public interface ExpenseDelegate{
        void ExpenseSelected(ExpenseEntity expense);
    }

    class ExpenseHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView dateTextView;
        TextView costTextView;

        public ExpenseHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.title_tv_item);
            dateTextView = itemView.findViewById(R.id.date_tv_item);
            costTextView = itemView.findViewById(R.id.cost_tv_item);
        }
    }
}
