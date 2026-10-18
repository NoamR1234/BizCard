package com.example.bizcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NAME = "name";
    public static final String KEY_JOB = "job";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_SITE = "site";

    private EditText etName;
    private EditText etJob;
    private EditText etPhone;
    private EditText etSite;
    private Button btnCreate;
    private Button btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etJob = findViewById(R.id.etJob);
        etPhone = findViewById(R.id.etPhone);
        etSite = findViewById(R.id.etSite);
        btnCreate = findViewById(R.id.btnCreate);
        btnAbout = findViewById(R.id.btnAbout);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String job = etJob.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String site = etSite.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_enter_name), Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, CardActivity.class);
                intent.putExtra(KEY_NAME, name);
                intent.putExtra(KEY_JOB, job);
                intent.putExtra(KEY_PHONE, phone);
                intent.putExtra(KEY_SITE, site);
                startActivity(intent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
