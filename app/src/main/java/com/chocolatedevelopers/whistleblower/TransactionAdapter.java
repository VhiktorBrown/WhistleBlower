package com.chocolatedevelopers.whistleblower;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.whistleblower.model.TransactionDetails;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    ArrayList<TransactionDetails> transactionDetailsArrayList;
    Context context;
    String transaction_type;

    public TransactionAdapter(Context context, ArrayList<TransactionDetails> transactionDetailsArrayList){
        this.context = context;
        this.transactionDetailsArrayList = transactionDetailsArrayList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.transaction_layout, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        if(transactionDetailsArrayList.get(position).getTransaction_type().equals("transfer")){
            transaction_type = "transferred";
        } else if(transactionDetailsArrayList.get(position).getTransaction_type().equals("deposit")){
            transaction_type = "deposited";
        }
       holder.transactionDetails.setText(transactionDetailsArrayList.get(position).getName() + " " + transaction_type + " the sum of " +
                transactionDetailsArrayList.get(position).getAmount() + " into your account.");
        holder.transactionDate.setText(transactionDetailsArrayList.get(position).getDate());
        holder.isVerified.setText(transactionDetailsArrayList.get(position).getIsVerified());

        switch (transactionDetailsArrayList.get(position).getIsVerified()) {
            case "verified":
                holder.isVerified.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_transaction_verified, 0, 0, 0);
                break;
            case "pending":
                holder.isVerified.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_transaction_pending, 0, 0, 0);
                break;
            case "denied":
                holder.isVerified.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_transaction_denied, 0, 0, 0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return transactionDetailsArrayList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView transactionDetails, transactionDate, isVerified;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transactionDetails = itemView.findViewById(R.id.transaction_details);
            transactionDate = itemView.findViewById(R.id.transaction_date);
            isVerified = itemView.findViewById(R.id.is_verified);
        }
    }
}
