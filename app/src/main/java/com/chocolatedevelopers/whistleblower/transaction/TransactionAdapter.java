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

import com.chocolatedevelopers.whistleblower.R;
import com.chocolatedevelopers.whistleblower.databinding.DialogTransactionDetailsBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionLayoutBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;

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

        if(transactionDetailsArrayList.get(position).isFlagged()){
            holder.binding.transactionImage.setBackground(context.getResources().getDrawable(R.drawable.ic_cancel));
        } else {
            holder.binding.transactionImage.setBackground(context.getResources().getDrawable(R.drawable.ic_done));
        }
        holder.binding.transactionUsername.setText(transactionDetailsArrayList.get(position).getUsername());
        holder.binding.transactionItem.setText(transactionDetailsArrayList.get(position).getQuantity() +
                " of " + transactionDetailsArrayList.get(position).getItem());
        holder.binding.transactionAmount.setText(transactionDetailsArrayList.get(position).getAmount());

        holder.binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDetailsDialog(transactionDetailsArrayList.get(position).getUsername(),
                        transactionDetailsArrayList.get(position).getItem(),
                        transactionDetailsArrayList.get(position).getQuantity(),
                        transactionDetailsArrayList.get(position).getAmount(),
                        transactionDetailsArrayList.get(position).getDate(),
                        transactionDetailsArrayList.get(position).getTime(),
                        transactionDetailsArrayList.get(position).isFlagged());
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

    private void showDetailsDialog(String username, String item, String quantity, String amount, String date, String time, boolean isFlagged) {
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

        if(isFlagged){
            dialogBinding.image.setBackground(context.getResources().getDrawable(R.drawable.ic_cancel));
            dialogBinding.moreInfoLayout.setBackgroundColor(context.getResources().getColor(R.color.red_400));
            dialogBinding.moreInfo.setText("This transaction has been flagged as malicious after running our diagnostics on it. Please, contact your admin in your organization.");
        } else {
            dialogBinding.image.setBackground(context.getResources().getDrawable(R.drawable.ic_done));
        }

        dialogBinding.btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
