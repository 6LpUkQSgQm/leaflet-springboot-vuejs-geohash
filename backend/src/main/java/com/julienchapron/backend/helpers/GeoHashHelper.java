package com.julienchapron.backend.helpers;

import com.julienchapron.backend.model.Geohash;

public class GeoHashHelper {

  private static final int MAX_BIT_PRECISION = 64;
  private static final int MAX_GEOHASH_CHARACTER_PRECISION = 12;
  private static final int[] BITS = { 16, 8, 4, 2, 1 };
  private static final int BASE32_BITS = 5;

  public static String getGeohash(Geohash geohash) {
    return getGeohash(geohash, MAX_GEOHASH_CHARACTER_PRECISION);
  }

  public static String getGeohash(Geohash geohash, int numberOfCharacters) {
    int desiredPrecision = getDesiredPrecsion(numberOfCharacters);
    GeoHashString geohashString = new GeoHashString();

    boolean isEvenBit = true;
    double[] latitudeRange = { -90, 90 };
    double[] longitudeRange = { -180, 180 };

    while (geohash.getSignificantBits() < desiredPrecision) {
      if (isEvenBit) {
        divideRangeEncode(geohash, longitudeRange, location.lng());
      } else {
        divideRangeEncode(geohash, latitudeRange, location.lat());
      }
      isEvenBit = !isEvenBit;
    }

    geohash.leftShift(MAX_BIT_PRECISION - desiredPrecision);
    return geohash.toBase32();
  }

  private static int getDesiredPrecsion(int numberOfCharacters) {
    if (numberOfCharacters > MAX_GEOHASH_CHARACTER_PRECISION) {
      throw new IllegalArgumentException(
        "A geohash can only be " +
        MAX_GEOHASH_CHARACTER_PRECISION +
        " character long."
      );
    }
    int desiredPrecision = 60;
    if (numberOfCharacters * 5 <= 60) {
      desiredPrecision = numberOfCharacters * 5;
    }
    return desiredPrecision = Math.min(desiredPrecision, MAX_BIT_PRECISION);
  }

  private static void divideRangeEncode(
    GeoHash geohash,
    double[] range,
    double value
  ) {
    double mid = (range[0] + range[1]) / 2;
    if (value >= mid) {
      geohash.addOnBitToEnd();
      range[0] = mid;
    } else {
      geohash.addOffBitToEnd();
      range[1] = mid;
    }
  }

  public static Location getLocation(String encodedGeohash) {
    double[] latitudeRange = { -90.0, 90.0 };
    double[] longitudeRange = { -180.0, 180.0 };

    boolean isEvenBit = true;
    GeoHash geohash = new GeoHash();

    for (int i = 0; i < encodedGeohash.length(); i++) {
      int characterToEncodeInBinary =
        Constants.BASE32_INV[encodedGeohash.charAt(i)];

      for (int j = 0; j < BASE32_BITS; j++) {
        int mask = BITS[j];
        boolean isBitActive = (characterToEncodeInBinary & mask) != 0;
        if (isEvenBit) {
          divideRangeDecode(geohash, longitudeRange, isBitActive);
        } else {
          divideRangeDecode(geohash, latitudeRange, isBitActive);
        }
        isEvenBit = !isEvenBit;
      }
    }

    double latitude = (latitudeRange[0] + latitudeRange[1]) / 2;
    double longitude = (longitudeRange[0] + longitudeRange[1]) / 2;

    return new Location(encodedGeohash, latitude, longitude);
  }

  private static void divideRangeDecode(
    GeoHash geohash,
    double[] range,
    boolean bitIsOn
  ) {
    double mid = (range[0] + range[1]) / 2;
    if (bitIsOn) {
      geohash.addOnBitToEnd();
      range[0] = mid;
    } else {
      geohash.addOffBitToEnd();
      range[1] = mid;
    }
  }
}
