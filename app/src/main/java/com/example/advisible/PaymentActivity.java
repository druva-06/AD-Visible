package com.example.advisible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.advisible.domain.BestSell;
import com.example.advisible.domain.Feature;
import com.example.advisible.domain.Items;

public class PaymentActivity extends AppCompatActivity {

    private TextView mSubTotal;
    private AppCompatButton payBtn;
    private TextView mTotal;
    private Feature feature;
    private BestSell bestSell;
    private Items items;
    private ImageView mPayImg;
    private double amount;
    private String img_url;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mToolbar = findViewById(R.id.payment_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Object obj = getIntent().getSerializableExtra("item");

        if(obj instanceof BestSell){
            bestSell = (BestSell) obj;
            amount = bestSell.getPrice();
            img_url = bestSell.getImg_url();
        }
        else if(obj instanceof Feature){
            feature = (Feature) obj;
            amount = feature.getPrice();
            img_url = feature.getImg_url();
        }
        else if(obj instanceof Items){
            items = (Items) obj;
            amount = items.getPrice();
            img_url = items.getImg_url();
        }

        mSubTotal = findViewById(R.id.sub_total);
        mTotal = findViewById(R.id.tot_amt);
        payBtn = findViewById(R.id.pay_btn);
        mPayImg = findViewById(R.id.payment_img);

        Glide.with(this).load(img_url).into(mPayImg);

        mSubTotal.setText(amount+"₹");
        mTotal.setText(amount+"₹");

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PaymentActivity.this,OrderActivity.class));
            }
        });

    }
}