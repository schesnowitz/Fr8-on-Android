package com.chesnowitz.fr8quote;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ConfirmationActivity extends AppCompatActivity {

  private LocationManager locationManager;
  private LocationListener locationListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_confirmation);
    locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
    locationListener = new LocationListener() {
      @Override
      public void onLocationChanged(Location location) {
        Log.i("Location", location.toString());
        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseGeoPoint point = new ParseGeoPoint(location.getLatitude(), location.getLongitude());

        ParseObject userLocation = new ParseObject("DriverLocation");
        userLocation.put("location", point);
        userLocation.put("email", currentUser.getEmail());
        userLocation.put("latitude", location.getLatitude());
        userLocation.put("longitude", location.getLongitude());
        userLocation.put("speed", location.getSpeed());
        userLocation.put("bearing", location.getBearing());
        userLocation.saveEventually();

      }

      @Override
      public void onStatusChanged(String s, int i, Bundle bundle) {

      }

      @Override
      public void onProviderEnabled(String s) {

      }

      @Override
      public void onProviderDisabled(String s) {

      }
    };
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    } else {
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locationListener);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

      if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
              PackageManager.PERMISSION_GRANTED) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locationListener);
      }
      finish(); // removes old activities if user goes back
    }
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
  @Override
  public void onBackPressed() {
  }
}