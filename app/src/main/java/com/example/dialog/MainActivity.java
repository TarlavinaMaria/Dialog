package com.example.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCustomDialog = findViewById(R.id.btnCustomDialog);
        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnTimePicker = findViewById(R.id.btnTimePicker);

        tvResult = findViewById(R.id.tvResult);

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_layout, null);

        final EditText etInput = dialogView.findViewById(R.id.etInput);

        builder.setView(dialogView)
                .setTitle("Введите значение")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputValue = etInput.getText().toString();
                        tvResult.setText(tvResult.getText() + "\nВведенное значение: " + inputValue);
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Метод для показа диалога выбора даты
    private void showDatePickerDialog() {
        Calendar selectedDateTime = Calendar.getInstance();
        int year = selectedDateTime.get(Calendar.YEAR);
        int month = selectedDateTime.get(Calendar.MONTH);
        int day = selectedDateTime.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
            String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            tvResult.setText("Выбранная дата: " + selectedDate);
        }, year, month, day);
        datePickerDialog.show();
    }

    // Метод для показа TimePickerDialog
    private void showTimePickerDialog() {
        Calendar selectedDateTime = Calendar.getInstance();
        int hour = selectedDateTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDateTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, selectedHour, selectedMinute) -> {
            String selectedTime = selectedHour + ":" + selectedMinute;
            tvResult.setText(tvResult.getText() + "\nВыбранное время: " + selectedTime);
        }, hour, minute, true);
        timePickerDialog.show();
    }
}