package com.jakewharton.activitycompat2;

import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import static com.jakewharton.activitycompat2.ActivityCompat2.YOU_JELLY;

/**
 * Helper class for building an options Bundle that can be used with
 * {@link ActivityCompat2#startActivity(android.app.Activity, android.content.Intent, android.os.Bundle)}.
 */
public class ActivityOptionsCompat2 {
  /**
   * Create an ActivityOptions specifying a custom animation to run when the activity is displayed.
   *
   * @param context    Who is defining this. This is the application that the animation resources will be loaded from.
   * @param enterResId A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
   * @param exitResId  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
   *
   * @return Returns a new ActivityOptions object that you can use to supply these options as the options Bundle when
   *         starting an activity.
   */
  public static ActivityOptionsCompat2 makeCustomAnimation(Context context, int enterResId, int exitResId) {
    if (YOU_JELLY) {
      return new JellyBeanImpl(context, enterResId, exitResId);
    }
    return new ActivityOptionsCompat2();
  }

  /**
   * Create an ActivityOptions specifying an animation where the new activity is scaled from a small originating area of
   * the screen to its final full representation.
   * <p/>
   * If the Intent this is being used with has not set its
   * {@link android.content.Intent#setSourceBounds(android.graphics.Rect)}, those bounds will be filled in for you based
   * on the initial bounds passed in here.
   *
   * @param source      The View that the new activity is animating from. This defines the coordinate space for startX
   *                    and startY.
   * @param startX      The x starting location of the new activity, relative to source.
   * @param startY      The y starting location of the activity, relative to source.
   * @param startWidth  The initial width of the new activity.
   * @param startHeight The initial height of the new activity.
   *
   * @return Returns a new ActivityOptions object that you can use to supply these options as the options Bundle when
   *         starting an activity.
   */
  public static ActivityOptionsCompat2 makeScaleUpAnimation(View source, int startX, int startY, int startWidth,
                                                            int startHeight) {
    if (YOU_JELLY) {
      return new JellyBeanImpl(source, startX, startY, startWidth, startHeight);
    }
    return new ActivityOptionsCompat2();
  }

  /**
   * Create an ActivityOptions specifying an animation where a thumbnail is scaled from a given position to the new
   * activity window that is being started.
   * <p/>
   * If the Intent this is being used with has not set its
   * {@link android.content.Intent#setSourceBounds(android.graphics.Rect)}, those bounds will be filled in for you based
   * on the initial thumbnail location and size provided here.
   *
   * @param source    The View that this thumbnail is animating from. This defines the coordinate space for startX
   *                  and startY.
   * @param thumbnail The bitmap that will be shown as the initial thumbnail of the animation.
   * @param startX    The x starting location of the bitmap, relative to source.
   * @param startY    The y starting location of the bitmap, relative to source.
   *
   * @return Returns a new ActivityOptions object that you can use to supply these options as the options Bundle when
   *         starting an activity.
   */
  public static ActivityOptionsCompat2 makeThumbnailScaleUpAnimation(View source, Bitmap thumbnail, int startX,
                                                                     int startY) {
    if (YOU_JELLY) {
      return new JellyBeanImpl(source, thumbnail, startX, startY);
    }
    return new ActivityOptionsCompat2();
  }

  private ActivityOptionsCompat2() {
  }

  /**
   * Returns the created options as a Bundle, which can be passed to
   * {@link ActivityCompat2#startActivity(android.app.Activity, android.content.Intent, android.os.Bundle)}. Note that
   * the returned Bundle is still owned by the ActivityOptions object; you must not modify it, but can supply it to the
   * startActivity methods that take an options Bundle.
   */
  public Bundle toBundle() {
    return null;
  }

  /**
   * Update the current values in this ActivityOptions from those supplied in otherOptions. Any values defined in
   * otherOptions replace those in the base options.
   */
  public void update(ActivityOptionsCompat2 otherOptions) {
    // Do nothing.
  }


  /** Actual implementation used on API 16+ only. */
  private static class JellyBeanImpl extends ActivityOptionsCompat2 {
    private final ActivityOptions activityOptions;

    JellyBeanImpl(Context context, int enterResId, int exitResId) {
      activityOptions = ActivityOptions.makeCustomAnimation(context, enterResId, exitResId);
    }

    JellyBeanImpl(View source, int startX, int startY, int startWidth, int startHeight) {
      activityOptions = ActivityOptions.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight);
    }

    JellyBeanImpl(View source, Bitmap thumbnail, int startX, int startY) {
      activityOptions = ActivityOptions.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY);
    }

    @Override public Bundle toBundle() {
      return activityOptions.toBundle();
    }

    @Override public void update(ActivityOptionsCompat2 otherOptions) {
      if (otherOptions != null && otherOptions instanceof JellyBeanImpl) {
        ActivityOptions realOther = ((JellyBeanImpl) otherOptions).activityOptions;
        activityOptions.update(realOther);
      }
    }
  }
}
