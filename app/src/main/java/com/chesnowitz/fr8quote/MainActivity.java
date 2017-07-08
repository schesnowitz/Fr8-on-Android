package com.chesnowitz.fr8quote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  private Button goToLogin;
  private Button goToSignUp;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

  goToSignUp = (Button) findViewById(R.id.signUpButton);
    goToSignUp.setOnClickListener(this);

  goToLogin = (Button) findViewById(R.id.loginButton);
    goToLogin.setOnClickListener(this);


    //Test code to see if the APP is working properly.
    ParseObject object = new ParseObject("Sex");
    object.put("user","steve");

//    object.saveEventually(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e == null) {
//          Log.i("Parse","SUCCESS");
//        } else {
//          Log.i("Parse","FAILED");
//        }
//      }
//    });



  }

    @Override
    public void onClick(View view) {
    switch (view.getId()) {
      case R.id.loginButton:
        startActivity(new Intent(MainActivity.this, LoginAccount.class));
        break;
      case R.id.signUpButton:
        startActivity(new Intent(MainActivity.this, CreateAccount.class));
    }
  }
}
