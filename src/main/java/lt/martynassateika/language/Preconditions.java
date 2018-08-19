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

/**
 * Utility methods for pre-condition checks.
 *
 * @author Martynas Sateika
 * @since 1.0
 */
class Preconditions {

  /**
   * @param min minimum allowed value
   * @param max maximum allowed value
   * @param value received value
   * @throws IllegalArgumentException if {@code min} is greater than {@code max}
   * @throws IllegalArgumentException if {@code value} is less than {@code min}
   * @throws IllegalArgumentException if {@code value} is more than {@code max}
   */
  static void checkValueBetween(long min, long max, long value) {
    if (min > max) {
      throw new IllegalArgumentException(String.format("min (%d) > max (%d)", min, max));
    }
    if (value < min) {
      throw new IllegalArgumentException(String.format("value (%d) < min (%d)", value, min));
    }
    if (value > max) {
      throw new IllegalArgumentException(String.format("value (%d) > max (%d)", value, max));
    }
  }

  /**
   * @param value received value
   * @throws IllegalArgumentException if {@code value} is negative
   */
  static void checkNotNegative(long value) {
    if (value < 0) {
      throw new IllegalArgumentException(String.format("negative value (%d)", value));
    }
  }

}
