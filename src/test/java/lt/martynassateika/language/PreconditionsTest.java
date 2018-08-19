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
import static lt.martynassateika.language.Preconditions.checkValueBetween;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Preconditions}.
 *
 * @author Martynas Sateika
 */
class PreconditionsTest {

  @Test
  void checkValueBetween_oneAllowedValue() {
    assertAll(
        () -> checkValueBetween(-1, -1, -1),
        () -> checkValueBetween(0, 0, 0),
        () -> checkValueBetween(1, 1, 1)
    );
  }

  @Test
  void checkValueBetween_smallerValueSupplied() {
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(-1, -1, -2));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(0, 0, -1));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(1, 1, 0));
  }

  @Test
  void checkValueBetween_largerValueSupplied() {
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(-1, -1, 0));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(0, 0, 1));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(1, 1, 2));
  }

  @Test
  void checkValueBetween_minGreaterThanMax() {
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(-1, -2, -1));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(0, -1, 0));
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(1, 0, 1));
  }

  @Test
  void checkValueBetween_rangeSupplied() {
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(-2, 2, -3));
    checkValueBetween(-2, 2, -1);
    checkValueBetween(-2, 2, 0);
    checkValueBetween(-2, 2, 1);
    checkValueBetween(-2, 2, 2);
    assertThrows(IllegalArgumentException.class, () -> checkValueBetween(-2, 2, 3));
  }

  @Test
  void checkNotNegative_negativeValue() {
    assertThrows(IllegalArgumentException.class, () -> checkNotNegative(-1));
  }

  @Test
  void checkNotNegative_zero() {
    checkNotNegative(0);
  }

  @Test
  void checkNotNegative_positiveValue() {
    checkNotNegative(1);
    checkNotNegative(Long.MAX_VALUE);
  }

}
