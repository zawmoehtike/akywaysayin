package com.johnsmith.zawmoehtike.akywaysayin.activities;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddBorrowLendItemActivity extends AppCompatActivity {

    BorrowLendItemViewModel borrowLendItemViewModel;

    EditText editText1;
    EditText editText2;
    EditText etDate;
    RadioButton radioButton1;
    RadioButton radioButton2;
    Button button;

    String myFormat = "dd/MM/yyyy";
    SimpleDateFormat sdf;
    final Calendar myCalendar = Calendar.getInstance();

    public void init() {

        borrowLendItemViewModel = ViewModelProviders.of(this).get(BorrowLendItemViewModel.class);

        editText1 = findViewById(R.id.etItemName);
        editText2 = findViewById(R.id.etPersonName);
        etDate = findViewById(R.id.etDate);
        radioButton1 = findViewById(R.id.rbMeFromU);
        radioButton2 = findViewById(R.id.rbMeToU);
        button = findViewById(R.id.btnAdd);

        Date c = Calendar.getInstance().getTime();
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        String formattedDate = sdf.format(c);
        etDate.setText(formattedDate);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_borrow_lend_item);


        Toolbar toolbar = findViewById(R.id.tool_bar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddBorrowLendItemActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    public void addItem() {

        BorrowLendItem borrowLendItem = new BorrowLendItem();
        borrowLendItem.setItemName(editText1.getText().toString());
        borrowLendItem.setPersonName(editText2.getText().toString());
        borrowLendItem.setDate(etDate.getText().toString());
        if (radioButton1.isChecked()) {
            borrowLendItem.setType("1");

            addItemAndGoToList(borrowLendItem);
        } else if (radioButton2.isChecked()) {
            borrowLendItem.setType("0");

            addItemAndGoToList(borrowLendItem);
        } else {
            Toast.makeText(this, "Please Choose Borrow or Lend!", Toast.LENGTH_SHORT).show();
        }
    }

    public void addItemAndGoToList(BorrowLendItem borrowLendItem) {
        borrowLendItemViewModel.insertItem(borrowLendItem);

        Intent intent = new Intent(AddBorrowLendItemActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void updateLabel() {
        etDate.setText(sdf.format(myCalendar.getTime()));
    }
}
