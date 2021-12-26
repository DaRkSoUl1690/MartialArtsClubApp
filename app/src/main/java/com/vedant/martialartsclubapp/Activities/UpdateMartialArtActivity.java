package com.vedant.martialartsclubapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vedant.martialartsclubapp.Models.DatabaseHandler;
import com.vedant.martialartsclubapp.Models.MartialArt;
import com.vedant.martialartsclubapp.R;

import java.util.ArrayList;

public class UpdateMartialArtActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_art);
        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);
        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArt = databaseHandler.returnMartialArtObjects();
        if (martialArt.size()>0)
        {
            ScrollView scroll = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArt.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArt.size()];
            EditText[][] namePriceAndColor = new EditText[martialArt.size()][3];
            

        }
    }
}
