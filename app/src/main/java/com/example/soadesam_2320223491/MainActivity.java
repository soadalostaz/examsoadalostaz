package com.example.soadesam_2320223491;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;

import com.example.soadesam_2320223491.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean backPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String email = binding.editTextEmail.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();

                boolean validEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
                boolean validPassword = password.length() >= 6;

                binding.buttonLogin.setEnabled(validEmail && validPassword);
            }
        };
        binding.editTextEmail.addTextChangedListener(watcher);
        binding.editTextPassword.addTextChangedListener(watcher);

        binding.buttonLogin.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ModeActivity.class));
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (backPressedOnce) {
                    finishAffinity();
                    return;
                }
                backPressedOnce = true;
                Snackbar.make(binding.getRoot(), R.string.press_back_again, Snackbar.LENGTH_SHORT).show();
                new Handler().postDelayed(() -> backPressedOnce = false, 2000);
            }
        });
    }
}