package com.n22.infisecure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.n22.infisecure.adapters.IntroFeaturesAdapter;
import com.n22.infisecure.fragments.IFAuthFragment;
import com.n22.infisecure.fragments.IFProfileFragment;
import com.n22.infisecure.fragments.IFSecurityFragment;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class IntroFeaturesActivity extends AppCompatActivity {
    ViewPager viewPager;
    WormDotsIndicator dotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_features);
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.worm_dot);

        IntroFeaturesAdapter introFeaturesAdapter = new IntroFeaturesAdapter(getSupportFragmentManager());
        introFeaturesAdapter.addFragment(new IFAuthFragment(), "AuthFragment");
        introFeaturesAdapter.addFragment(new IFSecurityFragment(), "SecurityFragment");
        introFeaturesAdapter.addFragment(new IFProfileFragment(), "ProfileFragment");

        viewPager.setAdapter(introFeaturesAdapter);
        dotsIndicator.attachTo(viewPager);


    }
}