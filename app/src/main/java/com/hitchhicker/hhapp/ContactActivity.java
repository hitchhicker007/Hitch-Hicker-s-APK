package com.hitchhicker.hhapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hitchhicker.hhapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ContactActivity extends AppCompatActivity {

    private Button submit;
    private EditText name,phone,msg;
    private DatabaseReference ContactRef;
    private Boolean flag=false;
    private String saveCurrentDate,saveCurrentTime,userRandomKey;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toast.makeText(this,"contact",Toast.LENGTH_SHORT).show();

        submit = (Button)findViewById(R.id.contact_btn);
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        msg = (EditText)findViewById(R.id.contact_message);
        loadingBar = new ProgressDialog(this);

        ContactRef = FirebaseDatabase.getInstance().getReference().child("contacts");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || msg.getText().toString().isEmpty()){
                    Toast.makeText(ContactActivity.this,"All fields are required!!",Toast.LENGTH_SHORT).show();
                }else {
                    AddToDatabse();
                }
            }
        });
    }

    private void AddToDatabse() {

        loadingBar.setTitle("Saving Info");
        loadingBar.setMessage("Please Wait, while we are saving your info.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        userRandomKey = saveCurrentDate + saveCurrentTime;

        HashMap<String,Object> usermap = new HashMap<>();

        usermap.put("user_name",name.getText().toString());
        usermap.put("user_phone",phone.getText().toString());
        usermap.put("user_msg",msg.getText().toString());
        usermap.put("flag",flag);
        usermap.put("time",saveCurrentTime);
        usermap.put("date",saveCurrentDate);

        ContactRef.child(userRandomKey).updateChildren(usermap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(ContactActivity.this,MainActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(ContactActivity.this,"Info saved Successfully :)",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(ContactActivity.this,"Error :( " + message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK)){
            finish();
        }
        return super.onKeyDown(keyCode,event);
    }
}
