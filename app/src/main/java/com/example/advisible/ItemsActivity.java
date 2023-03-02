package com.example.advisible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.advisible.adapter.ItemsRecyclerAdapter;
import com.example.advisible.domain.Items;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ItemsActivity extends AppCompatActivity {

    private FirebaseFirestore mStore;
    List<Items> mItemsList;
    private RecyclerView itemRecyclerView;
    private ItemsRecyclerAdapter itemsRecyclerAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        String type = getIntent().getStringExtra("type");

        mToolbar = findViewById(R.id.item_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(type);


        mStore = FirebaseFirestore.getInstance();
        mItemsList = new ArrayList<>();
        itemRecyclerView = findViewById(R.id.items_recycler);
        itemRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        itemsRecyclerAdapter = new ItemsRecyclerAdapter(this,mItemsList);
        itemRecyclerView.setAdapter(itemsRecyclerAdapter);

        Log.i("MSG",type);

        if(type.equals("all")){
            mStore.collection("AllItems").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            Items items = documentSnapshot.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
        else{
            mStore.collection("AllItems").whereEqualTo("type",type).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                            Items items = documentSnapshot.toObject(Items.class);
                            mItemsList.add(items);
                            itemsRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}