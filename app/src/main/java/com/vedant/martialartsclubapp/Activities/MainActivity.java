package com.vedant.martialartsclubapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.vedant.martialartsclubapp.MartialArtButton;
import com.vedant.martialartsclubapp.Models.DatabaseHandler;
import com.vedant.martialartsclubapp.Models.MartialArt;
import com.vedant.martialartsclubapp.R;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler mDatabaseHandler;
    private double totalMartialArtPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.vedant.martialartsclubapp.databinding.ActivityMainBinding binding = com.vedant.martialartsclubapp.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mDatabaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        scrollView = findViewById(R.id.ScrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterface();

    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> allArts = mDatabaseHandler.returnMartialArtObjects();
        scrollView.removeAllViewsInLayout();
        if (allArts.size() > 0) {

            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allArts.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allArts.size()];
            int index = 0;
            for (MartialArt art : allArts) {

                martialArtButtons[index] = new MartialArtButton(MainActivity.this, art);
                martialArtButtons[index].setText(MessageFormat.format("{0}\n{1}\n{2}", art.getMartialArtID(), art.getMartialArtName(), art.getMartialArtPrice()));

                switch (art.getMartialArtColor()) {

                    case "Red":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "Yellow":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Cyan":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);


                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index], martialArtButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

            }
            scrollView.addView(gridLayout);
        }

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

        if (id == R.id.del_martial_art) {
            startActivity(new Intent(this, deleteMartialArtActivity.class));
            return true;
        }
        if (id == R.id.upd_martial_art) {
            startActivity(new Intent(this, UpdateMartialArtActivity.class));
            return true;
        }
      if (id == R.id.reset){
          totalMartialArtPrice = 0.0;
          return true;
      }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       MartialArtButton martialArtButton = (MartialArtButton) v;
       totalMartialArtPrice = totalMartialArtPrice + martialArtButton.getMartialArtPrice();
       String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);
        Toast.makeText(MainActivity.this,martialArtPriceFormatted, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }
}
