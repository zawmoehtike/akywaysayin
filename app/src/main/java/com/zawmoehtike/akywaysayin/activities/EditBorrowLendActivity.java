package com.zawmoehtike.akywaysayin.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.zawmoehtike.akywaysayin.R;
import com.zawmoehtike.akywaysayin.data.entity.BorrowLendItem;
import com.zawmoehtike.akywaysayin.viewmodel.HomeViewModel;

public class EditBorrowLendActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;

    private BorrowLendItem borrowLendItem;

    private EditText etItemName;
    private EditText etPersonName;
    private EditText etDate;
    private RadioButton rbMeFromU;
    private RadioButton rbMeToU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_borrow_lend);

        Toolbar toolbar = findViewById(R.id.tool_bar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        initViewVariables();

        getDataFromViewModel();

        setDataToInputField();
    }

    private void getDataFromViewModel() {
        int borrowLendItemId = getIntent().getIntExtra("borrow_lend_item_id", 0);
        borrowLendItem = homeViewModel.getBorrowLendItem(borrowLendItemId);
    }

    private void setDataToInputField() {
        etItemName.setText(borrowLendItem.getItemName());
        etPersonName.setText(borrowLendItem.getPersonName());
        etDate.setText(borrowLendItem.getDate());

        if(borrowLendItem.getType().equals("1")) {
            rbMeFromU.setChecked(true);
            rbMeToU.setChecked(false);
        } else if(borrowLendItem.getType().equals("0")) {
            rbMeToU.setChecked(true);
            rbMeFromU.setChecked(false);
        }
    }

    private void initViewVariables() {
        etItemName = findViewById(R.id.etItemName);
        etPersonName = findViewById(R.id.etPersonName);
        etDate = findViewById(R.id.etDate);
        rbMeFromU = findViewById(R.id.rbMeFromU);
        rbMeToU = findViewById(R.id.rbMeToU);
    }

    public void updateBorrowLendItem(View view) {
        BorrowLendItem borrowLendItem = new BorrowLendItem();

        borrowLendItem.setId(this.borrowLendItem.getId());
        borrowLendItem.setItemName(etItemName.getText().toString());
        borrowLendItem.setPersonName(etPersonName.getText().toString());
        borrowLendItem.setDate(etDate.getText().toString());
        if (rbMeFromU.isChecked()) {
            borrowLendItem.setType("1");
        } else if (rbMeToU.isChecked()) {
            borrowLendItem.setType("0");
        }

        homeViewModel.updateItem(borrowLendItem);

        finish();
    }
}
