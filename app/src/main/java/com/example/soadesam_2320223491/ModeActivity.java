package com.example.soadesam_2320223491;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.soadesam_2320223491.databinding.ActivityModeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class ModeActivity extends AppCompatActivity {

    private ActivityModeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.viewPager.setAdapter(new ModesPagerAdapter(this));
        binding.viewPager.setSaveEnabled(false);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0: tab.setText(R.string.tab_1); break;
                        case 1: tab.setText(R.string.tab_2); break;
                        case 2: tab.setText(R.string.tab_3); break;
                    }
                }).attach();

        binding.buttonSetMode.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(ModeActivity.this, v);
            popup.getMenuInflater().inflate(R.menu.menu_mode, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                if (id == R.id.item_light) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (id == R.id.item_night) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else if (id == R.id.item_system) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                }
                return true;
            });
            popup.show();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                int currentTab = binding.viewPager.getCurrentItem();
                if (currentTab != 0) {
                    binding.viewPager.setCurrentItem(0, true);
                    if (binding.tabLayout.getTabAt(0) != null) {
                        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
                    }
                } else {
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class ModesPagerAdapter extends FragmentStateAdapter {
        public ModesPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return TabFragment.newInstance(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}