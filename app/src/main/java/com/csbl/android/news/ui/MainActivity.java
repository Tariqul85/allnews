package com.csbl.android.news.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.csbl.android.news.R;
import com.csbl.android.news.databinding.ActivityMainBinding;
import com.csbl.android.news.utils.Constants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity  {

    private ActivityMainBinding mBinding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setHandler(new ClickHandler());

    }



    public class ClickHandler{
        public void onClicked(String url){

            Log.d("MainActivity", "clicked: "+url);
            //handle click event

            Intent intent = new Intent(MainActivity.this, LoaderActivity.class);
            intent.putExtra(Constants.TAG_URL, url);
            startActivity(intent);

        }
    }


}
