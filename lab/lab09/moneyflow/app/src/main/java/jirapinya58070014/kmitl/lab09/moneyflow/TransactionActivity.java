package jirapinya58070014.kmitl.lab09.moneyflow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import jirapinya58070014.kmitl.lab09.moneyflow.database.MoneyFlowDB;
import jirapinya58070014.kmitl.lab09.moneyflow.model.Transaction;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener {

    private String type;
    private String description;
    private int amount;

    private MoneyFlowDB db;
    private Transaction transaction;

    private RadioGroup radioType;
    private EditText edDescription, edAmount;
    private Button btnSave, btnDelete;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initInstances();
    }

    private void initInstances() {
        db = Room.databaseBuilder(this, MoneyFlowDB.class, "MoneyFlowDB")
                .fallbackToDestructiveMigration()
                .build();

        radioType = findViewById(R.id.radioType);
        radioType.check(R.id.income);

        edDescription = findViewById(R.id.edDescription);

        edAmount = findViewById(R.id.edAmount);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);


        transaction = getIntent().getParcelableExtra("transaction");

        setData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            showProgressDialog();
            save();
        } else if (view.getId() == R.id.btnDelete) {
            showDialogDelete();
        }
    }

    private void setData() {
        if (transaction != null) {
            setTitle("Edit Transaction");
            edDescription.setText(transaction.getDescription());
            edAmount.setText(String.valueOf(transaction.getAmount()));
            btnDelete.setVisibility(View.VISIBLE);

            if (transaction.getType().equals("income")) {
                radioType.check(R.id.income);
            } else if (transaction.getType().equals("expense")) {
                radioType.check(R.id.expense);
            }
        } else {
            setTitle("New Transaction");
            btnDelete.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void save() {
        switch (radioType.getCheckedRadioButtonId()) {
            case R.id.income:
                type = "income";
                break;
            case R.id.expense:
                type = "expense";
                break;
            default:
                type = "";
        }

        description = edDescription.getText().toString();

        try {
            amount = Integer.parseInt(edAmount.getText().toString());
        } catch (IllegalArgumentException ignore) {
            showDialogWarning();
            return;
        }

        //------------------------------ UPDATE--------------------------------//

        if (transaction != null) {
            new AsyncTask<Void, Void, Transaction>() {
                @Override
                protected Transaction doInBackground(Void... params) {
                    transaction.updateData(new Transaction(type, description, amount));
                    db.transactionDAO().update(transaction);
                    goMainActivity();
                    return null;
                }
            }.execute();
            //------------------------------ ADD NEW --------------------------------//

        } else {
            new AsyncTask<Void, Void, Transaction>() {
                @Override
                protected Transaction doInBackground(Void... params) {
                    Transaction transaction = new Transaction(type, description, amount);
                    db.transactionDAO().insert(transaction);
                    goMainActivity();
                    return null;
                }
            }.execute();
        }

    }

    //------------------------------ DELETE --------------------------------//

    @SuppressLint("StaticFieldLeak")
    private void delete() {
        new AsyncTask<Void, Void, Transaction>() {
            @Override
            protected Transaction doInBackground(Void... params) {
                db.transactionDAO().delete(transaction);
                goMainActivity();
                return null;
            }
        }.execute();
    }

    public void showDialogDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.delete);
        builder.setTitle("Confirm Delete");
        builder.setMessage("Are you sure you want to delete?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                delete();
                goMainActivity();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    //------------------------------ Warning --------------------------------//
    public void showDialogWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.warning);
        builder.setTitle("Invalid input!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    //--------------------------- Back to MainActivity -----------------------------//
    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //------------------------------ ProgressDialog --------------------------------//
    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(TransactionActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }
}