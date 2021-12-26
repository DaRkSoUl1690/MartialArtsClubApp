package com.vedant.martialartsclubapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vedant.martialartsclubapp.Models.DatabaseHandler;
import com.vedant.martialartsclubapp.Models.MartialArt;
import com.vedant.martialartsclubapp.R;
import com.vedant.martialartsclubapp.databinding.ActivityAddMartialArtBinding;

import androidx.appcompat.app.AppCompatActivity;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addBtn) {
            AddMartialArtObjectToDatabase();
        }
        if (v.getId() == R.id.backBtn) {
            this.finish();
        }
    }

    private ActivityAddMartialArtBinding binding;
    DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddMartialArtBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        binding.addBtn.setOnClickListener(AddMartialArtActivity.this);
        binding.backBtn.setOnClickListener(AddMartialArtActivity.this);
    }

    private void AddMartialArtObjectToDatabase() {

        String nameValue = binding.name.getText().toString();
        String priceValue = binding.price.getText().toString();
        String colorValue = binding.color.getText().toString();

        try {
            MartialArt martialArt = new MartialArt(0, nameValue,
                    Double.parseDouble(priceValue), colorValue);
            mDatabaseHandler.addMartialArt(martialArt);
            Toast.makeText(AddMartialArtActivity.this,  martialArt + "\n saved to database",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(AddMartialArtActivity.this, "failed to save to database",
                    Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }


    }

}
