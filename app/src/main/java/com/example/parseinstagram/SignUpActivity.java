package com.example.parseinstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private EditText etSetUsername;
    private EditText etSetPassword;
    private EditText etSetEmail;
    private Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSetUsername = findViewById(R.id.etSetUsername);
        etSetPassword = findViewById(R.id.etSetPassword);
        etSetEmail = findViewById(R.id.etSetEmail);
        btnCreateUser = findViewById(R.id.btnCreateUser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etSetUsername.getText().toString();
                String password = etSetPassword.getText().toString();
                String email = etSetEmail.getText().toString();
                if(username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "One or more fields is missing", Toast.LENGTH_SHORT).show();
                    return;
                }
                createUser(username, password, email);
            }
        });
    }

    private void createUser(String username, String password, String email){
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Failed to create user", e);
                    e.printStackTrace();
                    Toast.makeText(SignUpActivity.this, "Account not made", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "Account Created ! Welcome !", Toast.LENGTH_SHORT).show();
                goHomeActivity();
            }

        });
    }
    private void goHomeActivity() {
        Log.i(TAG, "goHome");
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
