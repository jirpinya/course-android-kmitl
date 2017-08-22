package com.jirpinya.homework_week2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework2);
    }
    public void onClickAPPS(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick APPS",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickMOVIES(View view){
        Toast.makeText(
                getBaseContext(),
                "onClick MOVIES",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickGAMES(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick GAMES",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickBOOKS(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick BOOKS",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickMore1(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick More1",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickMore2(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick More2",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickSKIP(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick SKIP",
                Toast.LENGTH_LONG)
                .show();
    }
    public void onClickSeeNewTopics(View view){
        Toast.makeText(
                getBaseContext(),
                "OnClick SeeNewTopics",
                Toast.LENGTH_LONG)
                .show();
    }
}
