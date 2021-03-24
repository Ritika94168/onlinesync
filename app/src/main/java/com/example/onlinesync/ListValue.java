package com.example.onlinesync;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ListValue extends AppCompatActivity {
EditText value;
    Button save;
    OwnerDBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered_value);
        db = new OwnerDBAdapter(getApplicationContext());
        value = (EditText) findViewById(R.id.owner_address);
        save=(Button)findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             db.addOwner(new OwnerListAd(value.getText().toString()+""));
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("property_id",PropertyIdSTR);
                startActivity(intent1);
            }
        });
    }
}
