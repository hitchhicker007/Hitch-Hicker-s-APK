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

import com.hitchhicker.hhapp.Model.blogs;

import com.hitchhicker.hhapp.R;

import com.hitchhicker.hhapp.ViewHolder.BlogViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.squareup.picasso.Picasso;

public class BlogActivity extends AppCompatActivity {

    private DatabaseReference blogsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);


        blogsRef = FirebaseDatabase.getInstance().getReference().child("posts");
        FirebaseApp.initializeApp(this);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<blogs>options =
                new FirebaseRecyclerOptions.Builder<blogs>().
                        setQuery(blogsRef,blogs.class)
                .build();

        FirebaseRecyclerAdapter<blogs, BlogViewHolder> adapter =
                new FirebaseRecyclerAdapter<blogs, BlogViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BlogViewHolder holder, int position, @NonNull final blogs model) {

                        if (position%2==1){
                            holder.blog_title.setText(model.getPost_title());
                            Picasso.get().load(model.getThumbnail()).into(holder.blog_pic);

                            holder.blog_title_opp.setVisibility(View.GONE);
                            holder.blog_pic_opp.setVisibility(View.GONE);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    clicked(model.getUrl());
                                    Intent intent = new Intent(BlogActivity.this,ViewBlogActivity.class);
                                    intent.putExtra("url",model.getUrl());
                                    startActivity(intent);
                                }
                            });

                        }
                        else {
                            holder.blog_title_opp.setText(model.getPost_title());
                            Picasso.get().load(model.getThumbnail()).into(holder.blog_pic_opp);

                            holder.blog_title.setVisibility(View.GONE);
                            holder.blog_pic.setVisibility(View.GONE);

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    clicked(model.getUrl());
                                    Intent intent = new Intent(BlogActivity.this,ViewBlogActivity.class);
                                    intent.putExtra("url",model.getUrl());
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                    @NonNull
                    @Override
                    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_layout,parent,false);
                        BlogViewHolder holder = new BlogViewHolder(v);
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