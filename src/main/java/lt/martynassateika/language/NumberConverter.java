/*
 * Copyright 2018 Martynas Sateika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lt.martynassateika.language;

import static lt.martynassateika.language.Preconditions.checkValueBetween;

/**
 * Converts numbers into Lithuanian words.
 *
 * <p>Set up instances of the converter using the {@link #create()} method:</p>
 *
 * <pre>{@code
 * NumberConverter converter = NumberConverter.create();
 * }</pre>
 *
 * <p>The converter can then operate on any {@code long}. See {@link #toLithuanian(long)}.</p>
 *
 * @author Martynas Sateika
 * @since 1.0
 */
public class NumberConverter {

  /**
   * Cached words representing numbers 0 to 19.
   */
  private static String[] ZERO_TO_NINETEEN = {
      "nulis",
      "vienas",
      "du",
      "trys",
      "keturi",
      "penki",
      "šeši",
      "septyni",
      "aštuoni",
      "devyni",
      "dešimt",
      "vienuolika",
      "dvylika",
      "trylika",
      "keturiolika",
      "penkiolika",
      "šešiolika",
      "septyniolika",
      "aštuoniolika",
      "devyniolika",
  };

  /**
   * Cached words representing multiples of 10, from 0 to 90.
   */
  private static String[] TENS = {
      ZERO_TO_NINETEEN[0],
      ZERO_TO_NINETEEN[10],
      "dvidešimt",
      "trisdešimt",
      "keturiasdešimt",
      "penkiasdešimt",
      "šešiasdešimt",
      "septyniasdešimt",
      "aštuoniasdešimt",
      "devyniasdešimt"
  };

  /**
   * Returns a new instance of this converter.
   *
   * @return a new instance of this converter
   */
  public static NumberConverter create() {
    return new NumberConverter();
  }

  /**
   * Internal constructor. Users should use {@link #create()}.
   */
  private NumberConverter() {
  }

  /**
   * Converts a number into words.
   *
   * <p>Examples:</p>
   *
   * <pre>{@code
   * toLithuanian(0);
   * -> "nulis"
   *
   * toLithuanian(123);
   * -> "vienas šimtas dvidešimt trys"
   *
   * toLithuanian(1993);
   * -> "tūkstantis devyni šimtai devyniasdešimt trys"
   *
   * toLithuanian(-5);
   * -> "minus penki"
   *
   * toLithuanian(Long.MAX_VALUE);
   * -> "devyni kvintilijonai du šimtai dvidešimt trys kvadrilijonai trys šimtai septyniasdešimt du
   *     trilijonai trisdešimt šeši milijardai aštuoni šimtai penkiasdešimt keturi milijonai septyni
   *     šimtai septyniasdešimt penki tūkstančiai aštuoni šimtai septyni"
   * }</pre>
   *
   * @param number a number
   * @return string representation of the number
   */
  public String toLithuanian(long number) {
    return fromLong(number);
  }

  private static String fromLong(final long number) {
    if (number == Long.MIN_VALUE) {
      // -9223372036854775808
      return fromLong(-9223372036854775800L) + ' ' + ZERO_TO_NINETEEN[8];
    }

    if (number < 0) {
      return "minus " + fromLong(-number);
    }

    if (number < ZERO_TO_NINETEEN.length) {
      return ZERO_TO_NINETEEN[(int) number];
    }

    StringBuilder sb = new StringBuilder();

    // A long can be over 9 quintillion
    long remainder = number;
    remainder = formatScale(remainder, Scale.QUINTILLION, sb);
    remainder = formatScale(remainder, Scale.QUADRILLION, sb);
    remainder = formatScale(remainder, Scale.TRILLION, sb);
    remainder = formatScale(remainder, Scale.BILLION, sb);
    remainder = formatScale(remainder, Scale.MILLION, sb);
    remainder = formatScale(remainder, Scale.THOUSAND, sb);
    remainder = formatScale(remainder, Scale.HUNDRED, sb);
    if (remainder > 0) {
      // The word for zero is printed only if zero is supplied.
      // That case is handled near the start of this method.
      sb.append(upToOneHundred((int) remainder));
    }

    return sb.toString().trim();
  }

  /**
   * Returns the input number without the {@code scale} part, and appends the translation of the
   * {@code scale} part of {@code number} to {@code sb}.
   *
   * For example, if {@code scale} is {@link Scale#THOUSAND}, and the number is {@code 2018}, then
   * the translation of {@code 2000} will be appended to {@code sb}, and {@code 18} will be
   * returned.
   *
   * If there is output, an additional space character is appended.
   *
   * @param number a non-negative number
   * @param scale the scale we're searching for in {@code number}
   * @param sb string builder to append result to
   * @return the remainder
   */
  private static long formatScale(long number, Scale scale, StringBuilder sb) {
    long scaleValue = scale.getAsLong();
    if (number >= scaleValue) {
      int count = (int) (number / scaleValue);
      sb.append(upToOneThousand(count));
      sb.append(' ');
      sb.append(scale.formForCount(count));
      sb.append(' ');
      return number - count * scaleValue;
    }
    return number;
  }

  /**
   * @param number number to convert
   * @return the number in Lithuanian words
   * @throws IllegalArgumentException if {@code number} is negative or greater than 999
   */
  private static String upToOneThousand(final int number) {
    checkValueBetween(0, 999, number);
    if (number < ZERO_TO_NINETEEN.length) {
      return ZERO_TO_NINETEEN[number];
    } else {
      StringBuilder sb = new StringBuilder();
      // Format hundreds and return remainder
      int withoutHundreds = (int) formatScale(number, Scale.HUNDRED, sb);
      if (withoutHundreds > 0) {
        sb.append(upToOneHundred(withoutHundreds));
      }
      return sb.toString().trim();
    }
  }

  /**
   * @param number number to convert
   * @return the number in Lithuanian words
   * @throws IllegalArgumentException if {@code number} is negative or greater than 99
   */
  private static String upToOneHundred(final int number) {
    checkValueBetween(0, 99, number);
    if (number < ZERO_TO_NINETEEN.length) {
      return ZERO_TO_NINETEEN[number];
    } else {
      int tens = number / 10;
      int last = number % 10;
      if (last > 0) {
        return TENS[tens] + ' ' + ZERO_TO_NINETEEN[last];
      } else {
        return TENS[tens];
      }
    }
  }

}
