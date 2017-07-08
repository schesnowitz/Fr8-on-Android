package com.chesnowitz.fr8quote;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by steve on 7/7/2017.
 */

public class Fr8QuoteApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("mX464ffkQabcuWAAq5kvcxiVb5QGUiZy0lV822Ea")
            .clientKey("mZORuNMhWYJJNA1e0D5Y6wNS4qWNWI9bxoqb82wf")
            .server("https://parseapi.back4app.com/")
            .build()
    );
    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);
  }
}
