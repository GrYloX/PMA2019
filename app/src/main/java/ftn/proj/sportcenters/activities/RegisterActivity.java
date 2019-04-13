package ftn.proj.sportcenters.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ftn.proj.sportcenters.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mLastname;
    private EditText mPassword;
    private EditText mRepeatPassword;
    private EditText mUsername;
    private EditText mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = findViewById(R.id.Name);
        mLastname = findViewById(R.id.Lastname);
        mPassword = findViewById(R.id.Password);
        mRepeatPassword = findViewById(R.id.RepeatPassword);
        mUsername = findViewById(R.id.Username);
        mCity = findViewById(R.id.City);
        Button mRegister = findViewById(R.id.RegisterButton);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(mName.getText().toString(),mLastname.getText().toString(),
                        mPassword.getText().toString(),mRepeatPassword.getText().toString(),
                        mUsername.getText().toString(),mCity.getText().toString());

            }
        });
    }

    // za sada nista ne radi samo view prebacuje
    private void register(String name, String lastname,
                          String password, String repeat_password,
                          String username, String city) {
        Toast.makeText(this, "Uspešna registracija!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
