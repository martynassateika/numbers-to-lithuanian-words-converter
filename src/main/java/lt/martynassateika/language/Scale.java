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

import static lt.martynassateika.language.Preconditions.checkNotNegative;

import java.util.function.LongSupplier;

/**
 * Enumerates number scales useful in conversion from {@link Long}s.
 *
 * @author Martynas Sateika
 * @since 1.0
 */
enum Scale implements LongSupplier {

  HUNDRED(100L, "šimtas", "šimtai", "šimtų", false),

  THOUSAND(1_000L, "tūkstantis", "tūkstančiai", "tūkstančių"),

  MILLION(1_000_000L, "milijonas", "milijonai", "milijonų"),

  BILLION(1_000_000_000L, "milijardas", "milijardai", "milijardų"),

  TRILLION(1_000_000_000_000L, "trilijonas", "trilijonai", "trilijonų"),

  QUADRILLION(1_000_000_000_000_000L, "kvadrilijonas", "kvadrilijonai", "kvadrilijonų"),

  QUINTILLION(1_000_000_000_000_000_000L, "kvintilijonas", "kvintilijonai", "kvintilijonų");

  // Numerical value of this scale
  private final long numericalValue;

  // Singular form of this scale
  final String singularForm;

  // Plural form of this scale
  final String pluralForm;

  // Plural form to use if count is between 11 and 19, or a multiple of 10
  final String pluralForm2;

  // Indicates whether the converted count should be printed if it's 1
  private final boolean printCountIfOne;

  Scale(long numericalValue, String singularForm, String pluralForm, String pluralForm2) {
    this(numericalValue, singularForm, pluralForm, pluralForm2, true);
  }

  Scale(long numericalValue, String singularForm, String pluralForm, String pluralForm2,
      boolean printCountIfOne) {
    this.numericalValue = numericalValue;
    this.singularForm = singularForm;
    this.pluralForm = pluralForm;
    this.pluralForm2 = pluralForm2;
    this.printCountIfOne = printCountIfOne;
  }

  /**
   * @return whether the converted count should be printed if it's 1
   */
  boolean printCountIfOne() {
    return printCountIfOne;
  }

  /**
   * @param count a count of this scale
   * @return one of {@link #singularForm}, {@link #pluralForm}, {@link #pluralForm2}, depending on
   * the value of {@code count}
   */
  String formForCount(int count) {
    checkNotNegative(count);
    int lastTwoDigits = count % 100;
    int lastDigit = count % 10;
    if (lastTwoDigits > 10 && lastTwoDigits < 20) {
      // 11 to 19
      return pluralForm2;
    }
    if (lastDigit == 0) {
      // Shares form with 11-19
      return pluralForm2;
    }
    if (lastDigit == 1) {
      return singularForm;
    }
    return pluralForm;
  }

  /**
   * The numerical value of this scale.
   *
   * For example, this method returns the number {@code 100} when invoked on {@link Scale#HUNDRED}.
   *
   * @return numerical value of this scale
   */
  @Override
  public long getAsLong() {
    return numericalValue;
  }

}
