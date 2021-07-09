package com.chocolatedevelopers.whistleblower.report;

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
import com.chocolatedevelopers.whistleblower.databinding.ReportLayoutBinding;
import com.chocolatedevelopers.whistleblower.databinding.TransactionLayoutBinding;
import com.chocolatedevelopers.whistleblower.model.ReportDetails;
import com.chocolatedevelopers.whistleblower.model.TransactionDetails;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    ArrayList<ReportDetails> reportDetailsArrayList;
    Context context;
    Dialog dialog;

    public ReportAdapter(Context context, ArrayList<ReportDetails> reportDetailsArrayList){
        this.context = context;
        this.reportDetailsArrayList = reportDetailsArrayList;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReportLayoutBinding binding = ReportLayoutBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new ReportViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.binding.reportMonth.setText(reportDetailsArrayList.get(position).getReportMonth());
        holder.binding.startBalance.setText("$ " + reportDetailsArrayList.get(position).getStartBalance());
        holder.binding.endBalance.setText("$ " + reportDetailsArrayList.get(position).getEndBalance());
        holder.binding.profit.setText("$ " +reportDetailsArrayList.get(position).getProfit());
        holder.binding.flaggedTransactions.setText(reportDetailsArrayList.get(position).getFlaggedTransactions());


    }

    @Override
    public int getItemCount() {
        return reportDetailsArrayList.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        ReportLayoutBinding binding;
        public ReportViewHolder(@NonNull ReportLayoutBinding binding) {
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
