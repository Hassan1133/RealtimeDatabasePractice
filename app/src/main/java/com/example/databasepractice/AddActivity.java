package com.example.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    Button addBtn;
    String referenceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        referenceType = getIntent().getStringExtra("referenceType");

        initialize();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addBtn:
                Intent intent = new Intent(this, AddForm.class);
                intent.putExtra("referenceType",referenceType);
                startActivity(intent);
        }
    }

    void initialize()
    {
        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

}