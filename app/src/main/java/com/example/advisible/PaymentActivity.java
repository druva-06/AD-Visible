package com.example.advisible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    private TextView mSubTotal;
    private AppCompatButton payBtn;
    private TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        double amount = getIntent().getDoubleExtra("amount",0.0);

        mSubTotal = findViewById(R.id.sub_total);
        mTotal = findViewById(R.id.tot_amt);
        payBtn = findViewById(R.id.pay_btn);

        mSubTotal.setText(amount+"₹");
        mTotal.setText(amount+"₹");

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaymentActivity.this, "Toast", Toast.LENGTH_SHORT).show();
            }
        });

    }
}