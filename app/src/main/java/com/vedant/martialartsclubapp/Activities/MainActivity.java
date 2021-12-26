package com.vedant.martialartsclubapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vedant.martialartsclubapp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.vedant.martialartsclubapp.databinding.ActivityMainBinding binding = com.vedant.martialartsclubapp.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_martial_art) {
             startActivity(new Intent(this, AddMartialArtActivity.class));
             return true;
        }

        if(id==R.id.del_martial_art)
        {
            startActivity(new Intent(this, deleteMartialArtActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
