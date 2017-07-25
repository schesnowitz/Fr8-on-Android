package com.chesnowitz.fr8quote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginAccount extends AppCompatActivity {
  private EditText emailText;
  private EditText passwordText;
  private Button loginButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_account);
    emailText = (EditText) findViewById(R.id.loginEmailField);
    passwordText = (EditText) findViewById(R.id.loginPasswordField);
    loginButton = (Button) findViewById(R.id.loginAccountButton);

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String userName = emailText.getText().toString();
        String userEmail = emailText.getText().toString();
        String userPassword = passwordText.getText().toString();

        if (!userEmail.equals("") || !userPassword.equals("")) {
          ParseUser.logInInBackground(userName, userPassword, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
              if (e == null) {
                Toast.makeText(LoginAccount.this, "Login Successful!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginAccount.this, ConfirmationActivity.class));

              } else {
                Toast.makeText(LoginAccount.this, "Check Credentials!", Toast.LENGTH_LONG).show();
                }
            }
          });
        } else {
          Toast.makeText(LoginAccount.this, "Please Enter Email and Passwowrd!", Toast.LENGTH_LONG).show();
        }
      }
    });
  }
}
