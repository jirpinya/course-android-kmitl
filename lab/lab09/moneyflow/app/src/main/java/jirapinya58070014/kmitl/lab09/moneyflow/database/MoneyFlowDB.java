package jirapinya58070014.kmitl.lab09.moneyflow.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import jirapinya58070014.kmitl.lab09.moneyflow.model.Transaction;

import jirapinya58070014.kmitl.lab09.moneyflow.dao.TransactionDAO;

@Database(entities = Transaction.class, version = 1)
public abstract class MoneyFlowDB extends RoomDatabase {

    public abstract TransactionDAO transactionDAO();

}
