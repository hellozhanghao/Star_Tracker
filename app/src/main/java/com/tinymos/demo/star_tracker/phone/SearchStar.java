package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.R;

public class SearchStar extends Activity {


    Button   mButton;
    EditText mEdit;
    String starName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_search_star);


        mButton = (Button)findViewById(R.id.search_button);
        mEdit   = (EditText)findViewById(R.id.search_bar);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        starName = mEdit.getText().toString();
                        if (starName.equals("")){
                            Toast.makeText(getApplication(),"Star can't be empty!", Toast.LENGTH_LONG).show();
                        }else {
                            startTracking(view);
                        }
                    }
                });

    }

    public void startTracking(View view)
    {
        Intent intent = new Intent(SearchStar.this, TrackingProgress.class);
        intent.putExtra("name",starName);
        startActivity(intent);
    }
}
