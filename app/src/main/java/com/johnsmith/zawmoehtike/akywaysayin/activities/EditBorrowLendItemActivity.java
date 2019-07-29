package com.johnsmith.zawmoehtike.akywaysayin.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.johnsmith.zawmoehtike.akywaysayin.R;
import com.johnsmith.zawmoehtike.akywaysayin.model.BorrowLendItem;
import com.johnsmith.zawmoehtike.akywaysayin.viewmodel.BorrowLendItemViewModel;

public class EditBorrowLendItemActivity extends AppCompatActivity {

    private BorrowLendItemViewModel borrowLendItemViewModel;

    private BorrowLendItem borrowLendItem;

    private EditText etItemName;
    private EditText etPersonName;
    private EditText etDate;
    private RadioButton rbMeFromU;
    private RadioButton rbMeToU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_borrow_lend_item);

        borrowLendItemViewModel = ViewModelProviders.of(this).get(BorrowLendItemViewModel.class);

        initViewVariables();

        getDataFromViewModel();

        setDataToInputField();
    }

    private void getDataFromViewModel() {
        int borrowLendItemId = getIntent().getIntExtra("borrow_lend_item_id", 0);
        borrowLendItem = borrowLendItemViewModel.getBorrowLendItem(borrowLendItemId);
    }

    private void setDataToInputField() {
        etItemName.setText(borrowLendItem.getItemName());
        etPersonName.setText(borrowLendItem.getPersonName());
        etDate.setText(borrowLendItem.getDate());

        if(borrowLendItem.getType() == "0") {
            rbMeFromU.setChecked(true);
        } else {
            rbMeToU.setChecked(true);
        }
    }

    private void initViewVariables() {
        etItemName = findViewById(R.id.etItemName);
        etPersonName = findViewById(R.id.etPersonName);
        etDate = findViewById(R.id.etDate);
        rbMeFromU = findViewById(R.id.rbMeFromU);
        rbMeToU = findViewById(R.id.rbMeToU);
    }
}
