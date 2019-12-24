package com.example.myexpensesapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses")
public class ExpenseEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int expenseId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "description")
    private String description;

    public ExpenseEntity(int expenseId, String title, double price, String date, String description) {
        this.expenseId = expenseId;
        this.title = title;
        this.price = price;
        this.date = date;
        this.description = description;
    }

    @Ignore
    public ExpenseEntity(String title, double price, String date, String description) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.description = description;
    }

    public ExpenseEntity(Parcel in) {
        expenseId = in.readInt();
        title = in.readString();
        price = in.readDouble();
        date = in.readString();
        description = in.readString();
    }

    public static final Creator<ExpenseEntity> CREATOR = new Creator<ExpenseEntity>() {
        @Override
        public ExpenseEntity createFromParcel(Parcel in) {
            return new ExpenseEntity(in);
        }

        @Override
        public ExpenseEntity[] newArray(int size) {
            return new ExpenseEntity[size];
        }
    };

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(expenseId);
        dest.writeString(title);
        dest.writeDouble(price);
        dest.writeString(date);
        dest.writeString(description);
    }
}
