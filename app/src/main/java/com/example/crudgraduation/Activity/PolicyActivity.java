package com.example.crudgraduation.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.crudgraduation.R;

import java.util.Objects;


public class PolicyActivity extends AppCompatActivity {
    WebView webView;
    public String fileName = "test.html";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        webView = findViewById(R.id.policyWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/"+fileName);
    }
}