package com.chocolatedevelopers.whistleblower.transaction;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chocolatedevelopers.whistleblower.databinding.DialogTransactionDetailsBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionLayoutBinding;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    ArrayList<TransactionDetails> transactionDetailsArrayList;
    Context context;
    Dialog dialog;

    public TransactionAdapter(Context context, ArrayList<TransactionDetails> transactionDetailsArrayList){
        this.context = context;
        this.transactionDetailsArrayList = transactionDetailsArrayList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TransactionLayoutBinding binding = TransactionLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new TransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.binding.transactionItem.setText(transactionDetailsArrayList.get(position).getItem());
        holder.binding.transactionUsername.setText(transactionDetailsArrayList.get(position).getUsername());
        holder.binding.transactionAmount.setText("$ " +transactionDetailsArrayList.get(position).getAmount());

        holder.binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsDialog(transactionDetailsArrayList.get(position).getUsername(),
                        transactionDetailsArrayList.get(position).getItem(),
                        transactionDetailsArrayList.get(position).getAmount(),
                        transactionDetailsArrayList.get(position).getDate(),
                        transactionDetailsArrayList.get(position).getTime());
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionDetailsArrayList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TransactionLayoutBinding binding;
        public TransactionViewHolder(@NonNull TransactionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void showDetailsDialog(String username, String item, String amount, String date, String time) {
        dialog = new Dialog(context);
        DialogTransactionDetailsBinding dialogBinding = DialogTransactionDetailsBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialogBinding.transactionDetailsUsername.setText(username);
        dialogBinding.transactionDetailsItem.setText(item);
        dialogBinding.transactionDetailsAmount.setText("$ " + amount);
        dialogBinding.dateTime.setText(date +
                " by " + time);

        dialogBinding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}