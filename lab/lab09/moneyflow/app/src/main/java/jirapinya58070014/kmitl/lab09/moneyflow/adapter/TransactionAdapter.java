package jirapinya58070014.kmitl.lab09.moneyflow.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import jirapinya58070014.kmitl.lab09.moneyflow.R;
import jirapinya58070014.kmitl.lab09.moneyflow.model.Transaction;


class Holder extends RecyclerView.ViewHolder {

    public View rootLayoutItem;
    public TextView textDescription, textAmount;
    public ImageView typeIcon;

    public Holder(View itemView) {
        super(itemView);

        rootLayoutItem = itemView.findViewById(R.id.rootLayoutItem);
        textDescription = itemView.findViewById(R.id.textDescription);
        textAmount = itemView.findViewById(R.id.textAmount);
        typeIcon = itemView.findViewById(R.id.typeIcon);
    }
}

public class TransactionAdapter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private List<Transaction> data;

    public TransactionAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item, null, false);
        return new Holder(itemView);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Transaction transaction = data.get(position);

        if (transaction.getType().equals("income")) {
            holder.textDescription.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            holder.typeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.plus));
            holder.rootLayoutItem.setBackgroundResource(R.color.incomeHolder);
            //Amount
            holder.textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
            holder.textAmount.setText("+ ".concat(NumberFormat.getNumberInstance().format(transaction.getAmount())));

        } else if (transaction.getType().equals("expense")) {
            holder.textDescription.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
            holder.typeIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.minus));
            holder.rootLayoutItem.setBackgroundResource(R.color.expenseHolder);

            //Amount
            holder.textAmount.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
            holder.textAmount.setText("- ".concat(NumberFormat.getNumberInstance().format(transaction.getAmount())));
        }

        //Desribe
        holder.textDescription.setText(transaction.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Transaction> getData() {
        return data;
    }

}
