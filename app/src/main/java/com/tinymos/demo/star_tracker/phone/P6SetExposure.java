package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P6SetExposure extends Activity {


    Button mButton;
    EditText mSearchBar;
    TextView mTitle,mOption;
    String exposure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_input);


        mTitle = (TextView)findViewById(R.id.title);
        mButton = (Button)findViewById(R.id.search_button);
        mSearchBar = (EditText)findViewById(R.id.search_bar);
        mOption = (TextView)findViewById(R.id.option);

        mTitle.setText("Star Found!");
        mSearchBar.setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
        mSearchBar.setHint("");
        mOption.setText("Exposure Time");
        mButton.setText("Start Taking Photo");




        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        exposure = mSearchBar.getText().toString();
                        if (exposure.equals("")){
                            Toast.makeText(getApplication(),"Exposure time can't be empty!", Toast.LENGTH_LONG).show();
                        }else {
                            takePhoto(view);
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }

    public void takePhoto(View view)
    {
        Intent intent = new Intent(P6SetExposure.this, P7TakingPhoto.class);

        Thread thread = new Thread(){
            public void run(){
                Global.piWriter.println("TRACKING "+exposure);
            }
        };
        thread.start();

        startActivity(intent);
    }
}
