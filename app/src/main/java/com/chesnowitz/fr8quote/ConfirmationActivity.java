package com.chesnowitz.fr8quote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseUser;

public class ConfirmationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
//      case R.id.action_add:
//        if (user != null && auth != null) {
//          startActivity(new Intent(PostListActivity.this, AddPostActivity.class));
//          finish(); // removes old activities if user goes back
//        }
      case R.id.action_signout:
      if (ParseUser.getCurrentUser() != null ) {
        ParseUser.logOut();
        startActivity(new Intent(ConfirmationActivity.this, MainActivity.class));
        Toast.makeText(this, "You have signed out.", Toast.LENGTH_LONG).show();
        finish(); // removes old activities if user goes back
      }
    }
    return super.onOptionsItemSelected(item);
  }
}