#!/bin/bash

if [ -z "$ANDROID_HOME" ]; then
  echo 'You must have an $ANDROID_HOME environment variable set and Android 4.1 downloaded.'
  exit 1
fi

rm -rf out *.jar
mkdir out
javac -d out -cp "$ANDROID_HOME/platforms/android-16/android.jar:libs/android-support-v4.jar" src/main/java/com/jakewharton/activitycompat2/*
$(cd out && jar cf ../ActivityCompat2-1.0.0.jar *)
rm -rf out
