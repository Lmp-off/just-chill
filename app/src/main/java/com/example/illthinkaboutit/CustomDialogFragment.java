package com.example.illthinkaboutit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    Item item;
    String accountId;
    public CustomDialogFragment(Item item,String accountId){
        this.item=item;
        this.accountId=accountId;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Are you sure you want to delete this task?")
                .setMessage("it will be impossible to restore")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager.removeItem(item.getId());
                DBManager manager = new DBManager();
                manager.RemoveStar(item.getId(),accountId);
                manager.setAllTasksData();
                getActivity().finish();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
    }
}
