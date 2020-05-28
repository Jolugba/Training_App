package com.tinuade.nougat_home_teach.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayout;
import com.tinuade.nougat_home_teach.R;
import com.tinuade.nougat_home_teach.adapter.OnboardingPagerAdapter;
import com.tinuade.nougat_home_teach.databinding.ActivityOnboardingScreenBinding;
import com.tinuade.nougat_home_teach.sharedpref.SharePref;
import com.tinuade.nougat_home_teach.util.OnboardingListItem;

import java.util.ArrayList;
import java.util.List;

public class OnboardingScreenActivity extends AppCompatActivity {
    public static final String HAS_LAUNCHED_BEFORE = "HAS_LAUNCHED_BEFORE";


    private OnboardingPagerAdapter onboardingPagerAdapter;
    private List<OnboardingListItem> list;
    private int position = 0;
    private Animation buttonAnimation;
    private ActivityOnboardingScreenBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding_screen);

        buttonAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.onboarding_button_animation);

        list = new ArrayList<>();
        list.add(new OnboardingListItem("Teachers register their service", "", R.drawable.ic_undraw_educator_oxfm));
        list.add(new OnboardingListItem("Parent searches for teacher", "", R.drawable.ic_undraw_back_to_school_inwc));

        //Set up view pager
        onboardingPagerAdapter = new OnboardingPagerAdapter(this, list);
        binding.onboardingViewpager.setAdapter(onboardingPagerAdapter);

        //Set up tabLayout with viewPager
        binding.tabIndicator.setupWithViewPager(binding.onboardingViewpager);


        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = binding.onboardingViewpager.getCurrentItem();
                if (position < list.size()) {
                    position++;
                    binding.onboardingViewpager.setCurrentItem(position);
                }

                if (position == list.size() - 1) {
                    // On getting to the last page of the pagerAdapter
                    loadScreen();
                }
            }
        });

        //tabLayout change listener
        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size() - 1) {
                    loadScreen();
                } else {
                    binding.buttonNext.setVisibility(View.VISIBLE);
                    binding.tabIndicator.setVisibility(View.VISIBLE);
                    binding.btnStartApp.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size() - 1) {
                    loadScreen();
                } else {
                    binding.buttonNext.setVisibility(View.VISIBLE);
                    binding.tabIndicator.setVisibility(View.VISIBLE);
                    binding.btnStartApp.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.btnStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefData();
                startActivity(new Intent(OnboardingScreenActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        return SharePref.getINSTANCE(getApplicationContext()).getBooleanData(HAS_LAUNCHED_BEFORE);
    }

    private void savePrefData() {
        SharePref sharePref = SharePref.getINSTANCE(getApplicationContext());
        sharePref.setBooleanData(HAS_LAUNCHED_BEFORE, true);
    }


    private void loadScreen() {
        binding.btnStartApp.setAnimation(buttonAnimation);
        binding.buttonNext.setVisibility(View.INVISIBLE);
        binding.tabIndicator.setVisibility(View.INVISIBLE);
        binding.btnStartApp.setVisibility(View.VISIBLE);
    }
}



