package jirapinya58070014.kmitl.lab09.moneyflow.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import jirapinya58070014.kmitl.lab09.moneyflow.model.Transaction;

@Dao
public interface TransactionDAO {

    @Query("SELECT * FROM TransactionTable  ORDER BY id ASC")
    List<Transaction> getData();

    @Insert
    void insert(Transaction transaction);

    @Update
    void update(Transaction transaction);

    @Delete
    void delete(Transaction transaction);

    @Query("SELECT SUM(AMOUNT) FROM TransactionTable WHERE TYPE ='income'")
    int getIncome();

    @Query("SELECT SUM(AMOUNT) FROM TransactionTable WHERE TYPE ='expense'")
    int getExpense();

}
