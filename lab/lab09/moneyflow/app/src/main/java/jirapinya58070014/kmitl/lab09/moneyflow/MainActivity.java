package jirapinya58070014.kmitl.lab09.moneyflow;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import jirapinya58070014.kmitl.lab09.moneyflow.adapter.TransactionAdapter;
import jirapinya58070014.kmitl.lab09.moneyflow.database.MoneyFlowDB;
import jirapinya58070014.kmitl.lab09.moneyflow.model.Transaction;

public class MainActivity extends AppCompatActivity {

    private MoneyFlowDB db;
    private TextView textBalance;
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private FloatingActionButton btnAdd;
    private ProgressDialog mProgressDialog;
    private int summary;
    private int total_income;
    private int total_expense;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
        getIncome();
        getExpense();
        showList();

    }

    private void initInstances() {
        db = Room.databaseBuilder(this, MoneyFlowDB.class, "MoneyFlowDB")
                .fallbackToDestructiveMigration()
                .build();

        summary = total_income - total_expense;
        textBalance = findViewById(R.id.textBalance);

        recyclerView = findViewById(R.id.list);

        btnAdd = findViewById(R.id.addBtn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTransactionActivity(null);
            }
        });

        adapter = new TransactionAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener
                (new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Transaction transaction = adapter.getData().get(position);
                        goTransactionActivity(transaction);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }));

    }

    private void initPieChart(int total_income, int total_expense) {
        pieChart = findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.5f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(58f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(total_income, "INCOME"));
        yValues.add(new PieEntry(total_expense, "EXPENSE"));
        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(6f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }

    private void updateData(List<Transaction> list_transactions) {

        summary = total_income - total_expense;
        textBalance.setText("฿ " + summary);
        if ((float) summary / total_income <= 0.25) {
            textBalance.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light));
        } else if ((float) summary / total_income <= 0.5) {
            textBalance.setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        } else {
            textBalance.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_light));
        }

        initPieChart(total_income, total_expense);

        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(list_transactions);
        adapter.notifyDataSetChanged();
    }

    //------------------------------ Show List --------------------------------//
    @SuppressLint("StaticFieldLeak")
    private void showList() {
        new AsyncTask<Void, Void, List<Transaction>>() {
            @Override
            protected List<Transaction> doInBackground(Void... voids) {
                List<Transaction> transactions = db.transactionDAO().getData();
                updateData(transactions);
                return transactions;
            }

            @Override
            protected void onPostExecute(List<Transaction> transactions) {
                updateData(transactions);
            }
        }.execute();
    }

    //------------------------------ Income --------------------------------//
    @SuppressLint("StaticFieldLeak")
    private void getIncome() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                total_income = db.transactionDAO().getIncome();
                return total_income;
            }
        }.execute();
    }

    //------------------------------ Expense --------------------------------//
    @SuppressLint("StaticFieldLeak")
    private void getExpense() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                total_expense = db.transactionDAO().getExpense();
                return total_expense;
            }
        }.execute();
    }

    //------------------------- goTransactionActivity ----------------------------//
    private void goTransactionActivity(@Nullable Transaction transaction) {
        Intent intent = new Intent(MainActivity.this, TransactionActivity.class);
        intent.putExtra("transaction", transaction);
        startActivity(intent);
    }
}
