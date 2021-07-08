package com.chocolatedevelopers.whistleblower.flagged_transaction;

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
import com.chocolatedevelopers.whistleblower.databinding.FlaggedLayoutBinding;
import com.chocolatedevelopers.whistleblower.data.model.TransactionDetails;

import java.util.ArrayList;

public class FlaggedAdapter extends RecyclerView.Adapter<FlaggedAdapter.FlaggedViewHolder> {

    ArrayList<TransactionDetails> transactionDetailsArrayList;
    Context context;
    Dialog dialog;

    public FlaggedAdapter(Context context, ArrayList<TransactionDetails> transactionDetailsArrayList){
        this.context = context;
        this.transactionDetailsArrayList = transactionDetailsArrayList;
    }

    @NonNull
    @Override
    public FlaggedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FlaggedLayoutBinding binding = FlaggedLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new FlaggedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlaggedViewHolder holder, int position) {
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

    public static class FlaggedViewHolder extends RecyclerView.ViewHolder {
        FlaggedLayoutBinding binding;
        public FlaggedViewHolder(@NonNull FlaggedLayoutBinding binding) {
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
        dialogBinding.moreInfoLayout.setBackgroundColor(context.getResources().getColor(R.color.red_400));
        dialogBinding.moreInfo.setText("This transaction has been flagged as malicious after running our diagnostics on it. Please, contact your admin in your organization.");
    }
}
