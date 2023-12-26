package com.n22.infisecure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.n22.infisecure.adapters.IntroFeaturesAdapter;
import com.n22.infisecure.fragments.IFAuthFragment;
import com.n22.infisecure.fragments.IFProfileFragment;
import com.n22.infisecure.fragments.IFSecurityFragment;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class IntroFeaturesActivity extends AppCompatActivity {
    ViewPager viewPager;
    WormDotsIndicator dotsIndicator;
    Button skipButton;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_features);
        layout = findViewById(R.id.layout);
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.worm_dot);
        skipButton = findViewById(R.id.btnSkip);
        IntroFeaturesAdapter introFeaturesAdapter = new IntroFeaturesAdapter(getSupportFragmentManager());
        introFeaturesAdapter.addFragment(new IFAuthFragment(), "AuthFragment");
        introFeaturesAdapter.addFragment(new IFSecurityFragment(), "SecurityFragment");
        introFeaturesAdapter.addFragment(new IFProfileFragment(), "ProfileFragment");

        viewPager.setAdapter(introFeaturesAdapter);
        dotsIndicator.attachTo(viewPager);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotoUserExistencePage();}
        });

    }

    private void gotoUserExistencePage() {
//        final Dialog dialog = new Dialog(IntroFeaturesActivity.this);
//       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.activity_user_existence);
//        dialog.show();
        Intent intent = new Intent(IntroFeaturesActivity.this, UserExistenceActivity.class);
        startActivity(intent);
        finish();
    }
}