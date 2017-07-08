package model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by steve on 7/8/2017.
 */
@ParseClassName("Confirmation")
public class Confirmation extends ParseObject {
  public String getUserId() {
    return getString("userId");
  }

  public String getBody() {
    return getString("body");
  }

  public void setuserId(String userId) {
    put("userId", userId);
  }

  public void setBody(String body) {
    put("body", body);
  }
}
