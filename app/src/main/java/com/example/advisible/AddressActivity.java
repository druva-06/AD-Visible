package com.example.advisible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advisible.adapter.AddressAdapter;
import com.example.advisible.domain.Address;
import com.example.advisible.domain.BestSell;
import com.example.advisible.domain.Feature;
import com.example.advisible.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView mAddressRecyclerView;
    private AddressAdapter mAddressAdapter;
    private AppCompatButton paymentBtn;
    private AppCompatButton mAddAddress;
    List<Address> mAddressList;
    private FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;
    private BestSell bestSell;
    private Feature feature;
    private Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Object obj = getIntent().getSerializableExtra("item");

        mAddressRecyclerView = findViewById(R.id.address_recycler);
        paymentBtn = findViewById(R.id.payment_btn);
        mAddAddress = findViewById(R.id.add_address_btn);
        mAddressList = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(getApplicationContext(),mAddressList);
        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mToolbar = findViewById(R.id.address_toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAddressRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAddressRecyclerView.setAdapter(mAddressAdapter);

        mStore.collection("User").document(mAuth.getCurrentUser().getUid())
                .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc:task.getResult()){
                                Address address = doc.toObject(Address.class);
                                mAddressList.add(address);
                                mAddressAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });

        mAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj instanceof BestSell){
                    bestSell = (BestSell) obj;
                }
                else if(obj instanceof Feature){
                    feature = (Feature) obj;
                }
                else if(obj instanceof Items){
                    items = (Items) obj;
                }
                Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
                if(feature!=null){
                    intent.putExtra("item", feature);
                }
                else if(bestSell!=null){
                    intent.putExtra("item", bestSell);
                }
                else if(items!=null){
                    intent.putExtra("item", items);
                }
                startActivity(intent);
            }
        });
    }
}