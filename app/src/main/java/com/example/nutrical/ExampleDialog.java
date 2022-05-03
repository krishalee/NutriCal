package com.example.nutrical;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editCalories, editNotes;
    private ExampleDialogListener listener;

    public boolean valCal(){
        String SvalHeight = editCalories.getText().toString();
        if(SvalHeight.isEmpty()){
            editCalories.setError("Field can not be empty");
            return false;
        }else
            return true;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view);
        builder.setTitle("Add Calories");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!valCal()){
                    Toast.makeText(getContext(), "Field empty, cannot add calories", Toast.LENGTH_SHORT).show();
                }else{
                    double calories = Double.parseDouble(editCalories.getText().toString());
                    String notes = editNotes.getText().toString();
                    newcaloriesModel newcaloriesModel= new newcaloriesModel();
                    listener.applyText(calories,notes);
                }
            }
        });
        editCalories = view.findViewById(R.id.edit_calories);
        editNotes = view.findViewById(R.id.edit_notes);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyText(double calories, String notes);

    }



}
