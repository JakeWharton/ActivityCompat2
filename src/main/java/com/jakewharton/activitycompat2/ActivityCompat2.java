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

  /**
   * Start new activity with options, if able, for which you would like a result when it finished.
   *
   * @see Activity#startActivityForResult(android.content.Intent, int requestCode, android.os.Bundle)
   *
   * @param activity Origin activity to launch from.
   * @param intent The description of the activity to start.
   * @param requestCode If >= 0, this code will be returned in onActivityResult() when the activity exits.
   * @param options Additional options for how the Activity should be started. May be null if there are no options. See
   *                {@link ActivityOptionsCompat2} for how to build the Bundle supplied here; there are no supported
   *                definitions for building it manually.
   */
  public static void startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle options) {
    if (YOU_JELLY) {
      JellyBeanImpl.startActivityForResult(activity, intent, requestCode, options);
    } else {
      activity.startActivityForResult(intent, requestCode);
    }
  }

  private static class JellyBeanImpl {
    static void startActivity(Activity activity, Intent intent, Bundle options) {
      activity.startActivity(intent, options);
    }

    static void startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle options) {
      activity.startActivityForResult(intent, requestCode, options);
    }
  }
}
