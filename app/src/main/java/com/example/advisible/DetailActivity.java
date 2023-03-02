package com.example.advisible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.advisible.domain.BestSell;
import com.example.advisible.domain.Feature;
import com.example.advisible.domain.Items;

import java.util.ArrayList;

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
    private String model;
    private String texture;
    private AppCompatButton small_sz_btn;
    private AppCompatButton medium_sz_btn;
    private AppCompatButton large_sz_btn;

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
        small_sz_btn = findViewById(R.id.small_sz_btn);
        medium_sz_btn = findViewById(R.id.medium_sz_btn);
        large_sz_btn = findViewById(R.id.large_sz_btn);

        Feature feature;
        BestSell bestSell;
        Items items;

        Object obj = getIntent().getSerializableExtra("detail");
        if(obj instanceof Feature){
            feature = (Feature) obj;
            Glide.with(getApplicationContext()).load(feature.getImg_url()).into(mImage);
            mItemName.setText(feature.getName());
            mPrice.setText(feature.getPrice()+"₹");
            mItemRating.setText(feature.getRating()+".0");
            if(feature.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(feature.getDescription());
            model = feature.getModel();
            texture = feature.getTexture();
        }
        else if(obj instanceof BestSell){
            bestSell = (BestSell) obj;
            Glide.with(getApplicationContext()).load(bestSell.getImg_url()).into(mImage);
            mItemName.setText(bestSell.getName());
            mPrice.setText(bestSell.getPrice()+"₹");
            mItemRating.setText(bestSell.getRating()+".0");
            if(bestSell.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(bestSell.getDescription());
            model = bestSell.getModel();
            texture = bestSell.getTexture();
        }
        else if(obj instanceof Items){
            items = (Items) obj;
            Glide.with(getApplicationContext()).load(items.getImg_url()).into(mImage);
            mItemName.setText(items.getName());
            mPrice.setText(items.getPrice()+"₹");
            mItemRating.setText(items.getRating()+".0");
            if(items.getRating() > 3) mItemRatDesc.setText("Very Good!");
            else mItemRatDesc.setText("Good!");
            mItemDesc.setText(items.getDescription());
            model = items.getModel();
            texture = items.getTexture();

        }

        /* Perform the action by clicking the Try Now! Image */

        mTrailImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.isEmpty() || texture.isEmpty()){
                    Toast.makeText(DetailActivity.this,"Coming Soon!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(DetailActivity.this,TrailActivity.class);
                ArrayList<String> trail = new ArrayList<>();
                trail.add(model);
                trail.add(texture);
                intent.putExtra("trail",trail);
                startActivity(intent);
            }
        });

        /* Changing the Text Color and Background of the Size chart */

        small_sz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small_sz_btn.setBackgroundResource(R.drawable.login_btn_bg);
                small_sz_btn.setTextColor(Color.WHITE);
                medium_sz_btn.setBackgroundResource(0);
                large_sz_btn.setBackgroundResource(0);
                medium_sz_btn.setTextColor(Color.BLACK);
                large_sz_btn.setTextColor(Color.BLACK);
            }
        });

        medium_sz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medium_sz_btn.setBackgroundResource(R.drawable.login_btn_bg);
                medium_sz_btn.setTextColor(Color.WHITE);
                small_sz_btn.setBackgroundResource(0);
                large_sz_btn.setBackgroundResource(0);
                small_sz_btn.setTextColor(Color.BLACK);
                large_sz_btn.setTextColor(Color.BLACK);
            }
        });

        large_sz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                large_sz_btn.setBackgroundResource(R.drawable.login_btn_bg);
                large_sz_btn.setTextColor(Color.WHITE);
                small_sz_btn.setBackgroundResource(0);
                medium_sz_btn.setBackgroundResource(0);
                small_sz_btn.setTextColor(Color.BLACK);
                medium_sz_btn.setTextColor(Color.BLACK);
            }
        });

        /* Onclick the add to Cart Button */

        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /* Onclick the Buy Now Button */

        mBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailActivity.this,AddressActivity.class));
            }
        });
    }
}