package com.example.advisible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

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

public class DetailActivity extends AppCompatActivity {

    private ImageView mImage;
    private TextView mItemName;
    private TextView mPrice;
    private AppCompatButton mItemRating;
    private TextView mItemRatDesc;
    private TextView mItemDesc;
    private AppCompatButton mAddToCart;
    private AppCompatButton mBuyBtn;
    private ImageView mTrailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mImage = findViewById(R.id.item_img);
        mItemName = findViewById(R.id.item_name);
        mPrice = findViewById(R.id.item_price);
        mItemRating = findViewById(R.id.item_rating);
        mItemRatDesc = findViewById(R.id.item_rat_des);
        mItemDesc = findViewById(R.id.item_des);
        mAddToCart = findViewById(R.id.item_add_cart);
        mBuyBtn = findViewById(R.id.item_buy_now);
        mTrailImg = findViewById(R.id.trail_img);

        Feature feature;
        BestSell bestSell;
        Items items;

        Object obj = getIntent().getSerializableExtra("detail");
        if(obj instanceof Feature){
            feature = (Feature) obj;
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(mImage);
            mItemName.setText(feature.getName());
            mPrice.setText(feature.getPrice()+"₹");
            mItemRating.setText(feature.getRating()+"");
            if(feature.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(feature.getDescription());
        }
        else if(obj instanceof BestSell){
            bestSell = (BestSell) obj;
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(mImage);
            mItemName.setText(bestSell.getName());
            mPrice.setText(bestSell.getPrice()+"₹");
            mItemRating.setText(bestSell.getRating()+"");
            if(bestSell.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(bestSell.getDescription());
        }
        else if(obj instanceof Items){
            items = (Items) obj;
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(mImage);
            mItemName.setText(items.getName());
            mPrice.setText(items.getPrice()+"₹");
            mItemRating.setText(items.getRating()+"");
            if(items.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(items.getDescription());
        }

        mTrailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,TrailActivity.class));
            }
        });

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,AddressActivity.class));
            }
        });
    }
}