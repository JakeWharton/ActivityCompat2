ActivityCompat2
===============

An extension of the `ActivityCompat` helper in the support library which
supports the new `startActivity` and `ActivityOptions` features of the Android
4.1 (Jelly Bean) release.

This library is meant as an extension of the [support library][support] and
requires it as a dependency.

Downloadable .jars can be found on the [GitHub download page][dl].



Usage
-----

Use `ActivityOptionsCompat2` just as you would [`ActivityOptions`][api] and use
`ActivityCompat2` for calling `startActivity`.

```java
Intent intent = new Intent(this, OtherActivity.class);
ActivityOptionsCompat2 options =
    ActivityOptions.makeThumbnailScaleUpAnimation(view, thumb, 0, 0);
ActivityCompat2.startActivity(this, intent, options.toBundle());
```

Both `makeCustomAnimation` and `makeScaleUpAnimation` methods are also
available for use.



License
-------

    Copyright 2012 Jake Wharton
    Copyright 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [dl]: http://github.com/JakeWharton/ActivityCompat2/downloads
 [support]: http://developer.android.com/tools/extras/support-library.html
 [api]: http://developer.android.com/reference/android/app/ActivityOptions.html
