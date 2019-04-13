package ftn.proj.sportcenters.activities;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ftn.proj.sportcenters.MainActivity;
import ftn.proj.sportcenters.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername = findViewById(R.id.Username);
        mPassword = findViewById(R.id.Password);
        Button mLogin = findViewById(R.id.LoginButton);
        Button mRegister = findViewById(R.id.RegisterButton);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(mUsername.getText().toString(),mPassword.getText().toString());
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //za sada samo prebacuje view
    private void login(String username, String password){
        Toast.makeText(this, "Uspešna prijava!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // zavrsava ovaj activity, na back ne ide na login opet
    }

}
