package com.hitchhicker.hhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitchhicker.hhapp.Model.services;

import com.hitchhicker.hhapp.R;

import com.hitchhicker.hhapp.ViewHolder.ServiceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ServicesActivity extends AppCompatActivity {

    private DatabaseReference serviceRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Toast.makeText(this,"services",Toast.LENGTH_SHORT).show();

        serviceRef = FirebaseDatabase.getInstance().getReference().child("services");
        FirebaseApp.initializeApp(this);

        recyclerView = findViewById(R.id.recycler_menu_service);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<services>options =
                new FirebaseRecyclerOptions.Builder<services>().
                        setQuery(serviceRef,services.class)
                        .build();

        FirebaseRecyclerAdapter<services, ServiceViewHolder> adapter =
                new FirebaseRecyclerAdapter<services, ServiceViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ServiceViewHolder holder, int position, @NonNull final services model) {

                        holder.service_name.setText(model.getService_name());
                        Picasso.get().load(model.getService_logo()).into(holder.service_logo);
                        holder.service_desc.setText(model.getService_desc());

                    }
                    @NonNull
                    @Override
                    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_layout,parent,false);
                        ServiceViewHolder holder = new ServiceViewHolder(v);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK)){
            finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
