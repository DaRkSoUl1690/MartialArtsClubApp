package com.vedant.martialartsclubapp.Activities;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.vedant.martialartsclubapp.Models.DatabaseHandler;
import com.vedant.martialartsclubapp.Models.MartialArt;
import com.vedant.martialartsclubapp.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_update_martial_art);
        databaseHandler = new DatabaseHandler(UpdateMartialArtActivity.this);
        modifyUserInterface();
    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> martialArt = databaseHandler.returnMartialArtObjects();
        if (martialArt.size() > 0) {
            ScrollView scroll = new ScrollView(UpdateMartialArtActivity.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArtActivity.this);
            gridLayout.setRowCount(martialArt.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArt.size()];
            EditText[][] namePriceAndColor = new EditText[martialArt.size()][3];
            Button[] modifyButton = new Button[martialArt.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);

            int screenWidth = screenSize.x;
            int index = 0;

            for (MartialArt art : martialArt) {
                idTextViews[index] = new TextView(UpdateMartialArtActivity.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(art.getMartialArtID() + "");

                namePriceAndColor[index][0] = new EditText(UpdateMartialArtActivity.this);
                namePriceAndColor[index][1] = new EditText(UpdateMartialArtActivity.this);
                namePriceAndColor[index][2] = new EditText(UpdateMartialArtActivity.this);

                namePriceAndColor[index][0].setText(art.getMartialArtName());
                namePriceAndColor[index][1].setText(art.getMartialArtPrice() + "");
                namePriceAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                namePriceAndColor[index][2].setText(art.getMartialArtColor());

                namePriceAndColor[index][0].setId(art.getMartialArtID() + 10);
                namePriceAndColor[index][1].setId(art.getMartialArtID() + 20);
                namePriceAndColor[index][2].setId(art.getMartialArtID() + 30);

                modifyButton[index] = new Button(UpdateMartialArtActivity.this);
                modifyButton[index].setText(R.string.modify);
                modifyButton[index].setId(art.getMartialArtID());
                modifyButton[index].setOnClickListener(UpdateMartialArtActivity.this);

                gridLayout.addView(idTextViews[index], (int) (screenWidth * 0.05),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namePriceAndColor[index][0], (int) (screenWidth * 0.28),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namePriceAndColor[index][1], (int) (screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(namePriceAndColor[index][2], (int) (screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButton[index], (int) (screenWidth * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);


                index++;
            }
            scroll.addView(gridLayout);
            setContentView(scroll);
        }
    }

    @Override
    public void onClick(View v) {
        int martialArtObjectID = v.getId();

        EditText edtMartialArtName = findViewById(martialArtObjectID + 10);
        EditText edtMartialArtPrice = findViewById(martialArtObjectID + 20);
        EditText edtMartialArtColor = findViewById(martialArtObjectID + 30);

        String martialArtNameStringValue = edtMartialArtName.getText().toString();
        String martialArtPriceStringValue = edtMartialArtPrice.getText().toString();
        String martialArtColorStringValue = edtMartialArtColor.getText().toString();

        try {

            double martialArtPriceDoubleValue =
                    Double.parseDouble(martialArtPriceStringValue);

            databaseHandler.modifyMartialArtObject(martialArtObjectID,
                    martialArtNameStringValue,
                    martialArtPriceDoubleValue,
                    martialArtColorStringValue);

            Toast.makeText(UpdateMartialArtActivity.this, "This martial art Object is updated",
                    Toast.LENGTH_SHORT).show();


        } catch (NumberFormatException e) {

            e.printStackTrace();


        }


    }
}
