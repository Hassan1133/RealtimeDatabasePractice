package com.example.databasepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button studentBtn, teacherBtn;
    Intent intent;
    String referenceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.students:
                intent = new Intent(this, AddActivity.class);
                referenceType = "students";
                intent.putExtra("referenceType",referenceType);
                startActivity(intent);
                break;
            case R.id.teachers:
                intent = new Intent(this, AddActivity.class);
                referenceType = "teachers";
                intent.putExtra("referenceType",referenceType);
                startActivity(intent);
                break;

        }

    }

    void initialize()
    {
        studentBtn = findViewById(R.id.students);
        teacherBtn = findViewById(R.id.teachers);
        studentBtn.setOnClickListener(this);
        teacherBtn.setOnClickListener(this);
    }
}