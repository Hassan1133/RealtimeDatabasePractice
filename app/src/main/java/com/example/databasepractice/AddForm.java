package com.example.databasepractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AddForm extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference reference;

    EditText txt,phone;
    Button addBtn;

    SimpleDateFormat dateFormat;

    String name,phn,date,referenceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form);

        initialize();

        referenceType = getIntent().getStringExtra("referenceType");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(referenceType);
    }


    @Override
    public void onClick(View v) {

        name = txt.getText().toString().trim();
        phn = phone.getText().toString().trim();

        dateFormat = new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a");
        date = dateFormat.format(Calendar.getInstance().getTime());

        User user = new User();
        user.setName(name);
        user.setPhone(phn);
        user.setDate(date);

        if(isValid(user))
        {
            addToDb(user);
        }
    }

    void initialize()
    {
        txt = findViewById(R.id.txt);
        phone = findViewById(R.id.phone);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

    void addToDb(User user)
    {
        reference.
                child(user.getName()).
                setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(AddForm.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                            txt.setText("");
                            phone.setText("");
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    boolean isValid(User user)
    {
        boolean valid = true;
        if(user.getName().isEmpty() && user.getName().length()<3)
        {
            txt.setError("Enter valid name");
            valid = false;
        }
        if(user.getPhone().isEmpty() && user.getPhone().length()<11)
        {
            phone.setError("Enter valid phone number");
            valid = false;
        }
        return valid;
    }
}























