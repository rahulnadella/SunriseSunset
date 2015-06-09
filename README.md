## Sunrise-Sunset [![MIT License][license-image]][license-url] [![Build Status](https://travis-ci.org/rahulnadella/SunriseSunset.svg?branch=master)](https://travis-ci.org/rahulnadella/SunriseSunset)

The SunriseSunset class calculates the sunrise, sunset, and twilight times based on the user's time zone, latitude and longitude.

######Version
> Version -> 1.0 -> Design and development of SunriseSunset library

######Build
> Build -> Oracle JDK 8.0

######Dependencies
> None

###Gradle

Add the following to your ``build.gradle`` file:

```
repositories {
    mavenCentral()
}

dependencies {
    compile group: 'main.java', name: 'SunriseSunset', version: '1.0'
}
```

###API

The SunriseSunset library is broken down into 4 classes:

- [SunriseSunset.java](https://github.com/rahulnadella/SunriseSunset/blob/master/src/main/java/SunriseSunset.java) -> Main class of the library where the user will create a new SunriseSunset object with the appropriate time zone, latitude, and longitude.
- [SunriseSunsetCalculator.java](https://github.com/rahulnadella/SunriseSunset/blob/master/src/main/java/SunriseSunsetCalculator.java) -> This class provides the underlying implementation point for calculating the correct sunrise and sunset times.
- [Twilight.java](https://github.com/rahulnadella/SunriseSunset/blob/master/src/main/java/Twilight.java) -> This class holds the relationship of the different phases (astronomical, nautical, civil, and offfical) of sunrise and sunset.
- [MathUtility.java](https://github.com/rahulnadella/SunriseSunset/blob/master/src/main/java/MathUtility.java) -> A simple utility class to hand generic math calculations (cosine, sin, etc.)

###Usage

Example (retrieve the sunrise and sunset):
```
SunriseSunset *london = new SunriseSunset("GMT", 51.507351, -0.127758);
String sunrise = london.getOfficialSunrise(Calendar.getInstance());
String sunset = london.getOfficialSunset(Calendar.getInstance());
```

###License

MIT License --> A short, permissive software license. Basically, you can do whatever you want as long as you include the original copyright and license notice in any copy of the software/source. There are many variations of this license in use.

[license-image]: http://img.shields.io/badge/license-MIT-blue.svg?style=flat
[license-url]: LICENSE
