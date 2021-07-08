package com.chocolatedevelopers.whistleblower.verified_transaction;

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
import com.chocolatedevelopers.whistleblower.databinding.VerificationTransactionLayoutBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;

import java.util.ArrayList;

public class VerifiedTransactionAdapter extends RecyclerView.Adapter<VerifiedTransactionAdapter.VerifiedTransactionViewHolder> {

    ArrayList<TransactionDetails> transactionDetailsArrayList;
    Context context;
    Dialog dialog;

    public VerifiedTransactionAdapter(Context context, ArrayList<TransactionDetails> transactionDetailsArrayList){
        this.context = context;
        this.transactionDetailsArrayList = transactionDetailsArrayList;
    }

    @NonNull
    @Override
    public VerifiedTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VerificationTransactionLayoutBinding binding = VerificationTransactionLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new VerifiedTransactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VerifiedTransactionViewHolder holder, int position) {
        holder.binding.transactionUsername.setText(transactionDetailsArrayList.get(position).getUsername());
        holder.binding.transactionItem.setText(transactionDetailsArrayList.get(position).getQuantity() +
                " of " + transactionDetailsArrayList.get(position).getItem());
        holder.binding.transactionAmount.setText(transactionDetailsArrayList.get(position).getAmount());

        holder.binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
            }

    @Override
    public int getItemCount() {
        return transactionDetailsArrayList.size();
    }

    public static class VerifiedTransactionViewHolder extends RecyclerView.ViewHolder {
        VerificationTransactionLayoutBinding binding;
        public VerifiedTransactionViewHolder(@NonNull VerificationTransactionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void showDetailsDialog(String username, String item, String quantity, String amount, String date, String time) {
        dialog = new Dialog(context);
        DialogTransactionDetailsBinding dialogBinding = DialogTransactionDetailsBinding.inflate(dialog.getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);
        dialog.getWindow().setBackgroundDrawable(inset);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialogBinding.transactionDetailsUsername.setText(username);
        dialogBinding.transactionDetailsItem.setText(quantity + " of " + item);
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
