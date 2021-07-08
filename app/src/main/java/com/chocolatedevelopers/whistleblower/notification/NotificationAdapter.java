package com.chocolatedevelopers.whistleblower.notification;

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
import com.chocolatedevelopers.whistleblower.databinding.NotificationLayoutBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionLayoutBinding;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    ArrayList<TransactionDetails> transactionDetailsArrayList;
    Context context;
    Dialog dialog;

    public NotificationAdapter(Context context, ArrayList<TransactionDetails> transactionDetailsArrayList){
        this.context = context;
        this.transactionDetailsArrayList = transactionDetailsArrayList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationLayoutBinding binding = NotificationLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        if(transactionDetailsArrayList.get(position).getIsFlagged()){
            holder.binding.notificationImage.setBackground(context.getResources().getDrawable(R.drawable.ic_done));
        }  else {
            holder.binding.notificationImage.setBackground(context.getResources().getDrawable(R.drawable.ic_cancel));
        }
        holder.binding.notificationUsernameItem.setText(transactionDetailsArrayList.get(position).getUsername()
        + " " + transactionDetailsArrayList.get(position).getItem());
        holder.binding.notificationDateTime.setText(transactionDetailsArrayList.get(position).getDate()
        + " at " + transactionDetailsArrayList.get(position).getTime());
        holder.binding.notificationAmount.setText("$ "+ transactionDetailsArrayList.get(position).getAmount());
            }

    @Override
    public int getItemCount() {
        return transactionDetailsArrayList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        NotificationLayoutBinding binding;
        public NotificationViewHolder(@NonNull NotificationLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
