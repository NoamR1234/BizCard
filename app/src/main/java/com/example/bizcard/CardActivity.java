package com.example.bizcard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {

    private TextView tvName;
    private TextView tvJob;
    private TextView tvPhone;
    private TextView tvSite;
    private Button btnDial;
    private Button btnWebsite;
    private Button btnShare;
    private Button btnBack;

    private String name;
    private String job;
    private String phone;
    private String site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        tvName = findViewById(R.id.tvName);
        tvJob = findViewById(R.id.tvJob);
        tvPhone = findViewById(R.id.tvPhone);
        tvSite = findViewById(R.id.tvSite);
        btnDial = findViewById(R.id.btnDial);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnShare = findViewById(R.id.btnShare);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.KEY_NAME);
        job = intent.getStringExtra(MainActivity.KEY_JOB);
        phone = intent.getStringExtra(MainActivity.KEY_PHONE);
        site = intent.getStringExtra(MainActivity.KEY_SITE);

        tvName.setText(name);
        tvJob.setText(job);
        tvPhone.setText(phone);
        tvSite.setText(site);

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                try {
                    startActivity(dialIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(CardActivity.this, getString(R.string.toast_no_dialer), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = site;
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url;
                }
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                try {
                    startActivity(webIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(CardActivity.this, getString(R.string.toast_no_browser), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardText = name + "\n" + job + "\n" + phone + "\n" + site;
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, cardText);
                try {
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_chooser_title)));
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(CardActivity.this, getString(R.string.toast_no_share), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
