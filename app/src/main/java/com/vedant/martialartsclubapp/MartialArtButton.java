package com.vedant.martialartsclubapp;

import android.content.Context;

import com.vedant.martialartsclubapp.Models.MartialArt;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class MartialArtButton extends AppCompatButton {

    private final MartialArt martialArtObject;
    public MartialArtButton(@NonNull Context context , MartialArt art) {
        super(context);
        martialArtObject = art;
    }
    public String getMartialArtColors() {
        return martialArtObject.getMartialArtColor();
    }

    public double getMartialArtPrice(){
        return martialArtObject.getMartialArtPrice();
    }
}
