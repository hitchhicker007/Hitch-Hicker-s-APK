package com.hitchhicker.hhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitchhicker.hhapp.Model.codes;

import com.hitchhicker.hhapp.R;

import com.hitchhicker.hhapp.ViewHolder.CodeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CodeActivity extends AppCompatActivity {

    private DatabaseReference codeRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        Toast.makeText(this,"code",Toast.LENGTH_SHORT).show();

        codeRef = FirebaseDatabase.getInstance().getReference().child("codes");
        FirebaseApp.initializeApp(this);

        recyclerView = findViewById(R.id.recycler_menu_code);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<codes> options =
                new FirebaseRecyclerOptions.Builder<codes>().
                        setQuery(codeRef,codes.class)
                .build();

        FirebaseRecyclerAdapter<codes, CodeViewHolder> adapter =
                new FirebaseRecyclerAdapter<codes, CodeViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CodeViewHolder holder, int position, @NonNull final codes model) {
                        holder.titleOfCode.setText(model.getCode_title());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clicked(model.getCode_link());
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public CodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_layout,parent,false);
                        CodeViewHolder holder = new CodeViewHolder(v);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public void clicked(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK)){
            finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
