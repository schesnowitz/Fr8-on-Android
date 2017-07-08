package com.chesnowitz.fr8quote;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccount extends AppCompatActivity {
  private EditText emailAddress;
  private EditText passwordText;
  private Button createAccountButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_account);
    emailAddress = (EditText) findViewById(R.id.createEmailField);
    passwordText = (EditText) findViewById(R.id.createPasswordField);
    createAccountButton = (Button) findViewById(R.id.createAccountButton);

    createAccountButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        createAccount(); // calls method below
      }
    });
  }

  private void createAccount() {
    final String email = emailAddress.getText().toString();
//    final String uesername = emailAddress.getText().toString();
    final String password = passwordText.getText().toString();

    if (email.equals("") || password.equals("")) {
      final AlertDialog.Builder dialog = new AlertDialog.Builder(CreateAccount.this);
      dialog.setTitle("Empty Inputs");
      dialog.setMessage("Fields can't be blank!");
      dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          dialogInterface.dismiss();
        }
      });
      dialog.show();
    } else {
      ParseUser user = new ParseUser();
      user.setEmail(email);
      user.setUsername(email);
      user.setPassword(password);

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            createAccountButton.setEnabled(false);
            emailAddress.setEnabled(false);
            passwordText.setEnabled(false);

            logUserIn(email, password);
          }
        }
      });
    }
  }

  private void logUserIn(String email, String password) {
    if (!email.equals("") || !password.equals("")) {
      ParseUser.logInInBackground(email, password, new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {

        }
      });
    }
  }
}
