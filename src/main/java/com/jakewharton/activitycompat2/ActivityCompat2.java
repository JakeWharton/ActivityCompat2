package com.jakewharton.activitycompat2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.JELLY_BEAN;

public class ActivityCompat2 extends ActivityCompat {
  static final boolean YOU_JELLY = SDK_INT >= JELLY_BEAN;

  /**
   * Start a new activity with options, if able.
   *
   * @see Activity#startActivity(android.content.Intent, android.os.Bundle)
   *
   * @param activity Origin activity to launch from.
   * @param intent The description of the activity to start.
   * @param options Additional options for how the Activity should be started. May be null if there are no options. See
   *                {@link ActivityOptionsCompat2} for how to build the Bundle supplied here; there are no supported
   *                definitions for building it manually.
   */
  public static void startActivity(Activity activity, Intent intent, Bundle options) {
    if (YOU_JELLY) {
      JellyBeanImpl.startActivity(activity, intent, options);
    } else {
      activity.startActivity(intent);
    }
  }

  private static class JellyBeanImpl {
    static void startActivity(Activity activity, Intent intent, Bundle options) {
      activity.startActivity(intent, options);
    }
  }
}
