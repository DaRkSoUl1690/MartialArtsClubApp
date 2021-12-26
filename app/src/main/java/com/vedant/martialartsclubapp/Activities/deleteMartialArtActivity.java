package com.vedant.martialartsclubapp.Activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.vedant.martialartsclubapp.Models.DatabaseHandler;
import com.vedant.martialartsclubapp.Models.MartialArt;
import com.vedant.martialartsclubapp.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class deleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDatabaseHandler = new DatabaseHandler(deleteMartialArtActivity.this);

        updateTheUserInterfaces();
    }

    private void updateTheUserInterfaces() {
        ArrayList<MartialArt> allMartialArtObjects =
                mDatabaseHandler.returnMartialArtObjects();

        RelativeLayout relativeLayout = new RelativeLayout(deleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(deleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(deleteMartialArtActivity.this);

        for (MartialArt art : allMartialArtObjects) {
            RadioButton radioButton = new RadioButton(deleteMartialArtActivity.this);
            radioButton.setId(art.getMartialArtID());
            radioButton.setText(art.toString());
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(deleteMartialArtActivity.this);

        Button btnBack = new Button(deleteMartialArtActivity.this);
        btnBack.setText(R.string.Simon_go_back);
        btnBack.setOnClickListener(v -> finish());

        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 70);
        relativeLayout.addView(btnBack, params);

        ScrollView.LayoutParams scrollViewParams =
                new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

        relativeLayout.addView(scrollView, scrollViewParams);

        setContentView(relativeLayout);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        mDatabaseHandler.deleteMartialObjectFromDatabaseByID(checkedId);
        Toast.makeText(deleteMartialArtActivity.this,
                "object is Deleted ", Toast.LENGTH_SHORT).show();
        updateTheUserInterfaces();

    }
}
