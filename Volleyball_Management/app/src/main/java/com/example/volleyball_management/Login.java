package com.example.volleyball_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Login extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        spinner=findViewById(R.id.spinner);
        ArrayAdapter <CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Usertype,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
