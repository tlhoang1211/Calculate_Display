package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    private int op1, op2;   // Toan hang 1 va 2
    private int op;         // Toan tu: 1 - add, 2 - sub, 3 - mul, 4 - div
    private int state;      // Trang thai: 1 - nhap op1, 2 - nhap op2

    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        state = 1;
        op = 0;
        op1 = 0;
        op2 = 0;

        textResult = findViewById(R.id.text_result);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);

        findViewById(R.id.btn_equal).setOnClickListener(this);

        findViewById(R.id.btn_rev).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_bs).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_0)
            addDigit(0);
        else if (id == R.id.btn_1)
            addDigit(1);
        else if (id == R.id.btn_2)
            addDigit(2);
        else if (id == R.id.btn_3)
            addDigit(3);
        else if (id == R.id.btn_4)
            addDigit(4);
        else if (id == R.id.btn_5)
            addDigit(5);
        else if (id == R.id.btn_6)
            addDigit(6);
        else if (id == R.id.btn_7)
            addDigit(7);
        else if (id == R.id.btn_8)
            addDigit(8);
        else if (id == R.id.btn_9)
            addDigit(9);
        else if (id == R.id.btn_add)
            selectOperator(1);
        else if (id == R.id.btn_sub)
            selectOperator(2);
        else if (id == R.id.btn_mul)
            selectOperator(3);
        else if (id == R.id.btn_div)
            selectOperator(4);
        else if (id == R.id.btn_equal)
            calculateResult();
        else if (id == R.id.btn_bs)
            removeDigit();
        else if (id == R.id.btn_ce)
            clearEntry();
        else if (id == R.id.btn_c)
            clearOperator();
    }

    private void removeDigit() {
        if (state == 1) {
            op1 = op1 / 10;
            textResult.setText(String.valueOf(op1));
        } else {
            op2 = op2 / 10;
            textResult.setText(String.valueOf(op2));
        }
    }

    private void clearEntry() {
        if (state == 1) {
            op1 = 0;
            textResult.setText(String.valueOf(op1));
        } else {
            op2 = 0;
            textResult.setText(String.valueOf(op2));
        }
    }

    private void clearOperator() {
        // Reset
        state = 1;
        op1 = 0;
        op2 = 0;
        op = 0;

        textResult.setText(String.valueOf(op1));
    }

    private void calculateResult() {
        int result = 0;
        if (op == 1)
            result = op1 + op2;
        else if (op == 2)
            result = op1 - op2;
        else if (op == 3)
            result = op1 * op2;
        else if (op == 4) {
            if (op2 == 0) {
                // Khong thuc hien duoc phep chia
                textResult.setText("ERROR!");

                // Reset
                state = 1;
                op1 = 0;
                op2 = 0;
                op = 0;

                return;
            } else {
                result = op1 / op2;
            }
        }

        textResult.setText(String.valueOf(result));

        // Reset
        state = 1;
        op1 = 0;
        op2 = 0;
        op = 0;
    }

    private void selectOperator(int operator) {
        op = operator;
        state = 2;
        textResult.setText(String.valueOf(op2));
    }

    private void addDigit(int digit) {
        if (state == 1) {
            op1 = op1 * 10 + digit;
            textResult.setText(String.valueOf(op1));
        } else {
            op2 = op2 * 10 + digit;
            textResult.setText(String.valueOf(op2));
        }
    }
}
