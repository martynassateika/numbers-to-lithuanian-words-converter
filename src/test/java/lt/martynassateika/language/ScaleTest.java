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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Scale}.
 *
 * @author Martynas Sateika
 */
class ScaleTest {

  @Test
  void formForCount_hundred() {
    Scale scale = Scale.HUNDRED;
    assertThrows(IllegalArgumentException.class, () -> scale.formForCount(Integer.MIN_VALUE));
    assertThrows(IllegalArgumentException.class, () -> scale.formForCount(-1));
    assertAll(
        () -> assertString(scale, scale.pluralForm2, 0),
        () -> assertString(scale, scale.singularForm, 1),
        () -> assertString(scale, scale.pluralForm, 2),
        () -> assertString(scale, scale.pluralForm, 9),
        () -> assertString(scale, scale.pluralForm2, 10),
        () -> assertString(scale, scale.pluralForm2, 11),
        () -> assertString(scale, scale.pluralForm2, 12),
        () -> assertString(scale, scale.pluralForm2, 19),
        () -> assertString(scale, scale.pluralForm2, 20),
        () -> assertString(scale, scale.singularForm, 21),
        () -> assertString(scale, scale.pluralForm, 22),
        () -> assertString(scale, scale.pluralForm2, 30),
        () -> assertString(scale, scale.pluralForm2, 90),
        () -> assertString(scale, scale.pluralForm2, 100),
        () -> assertString(scale, scale.singularForm, 101),
        () -> assertString(scale, scale.pluralForm, 909),
        () -> assertString(scale, scale.pluralForm2, 910),
        () -> assertString(scale, scale.pluralForm2, 911),
        () -> assertString(scale, scale.pluralForm2, 912),
        () -> assertString(scale, scale.pluralForm2, 919),
        () -> assertString(scale, scale.pluralForm, 999),
        () -> assertString(scale, scale.pluralForm, Integer.MAX_VALUE) // ends with 7
    );
  }

  // Displays input next to error message in JUnit's output
  private void assertString(Scale scale, String expected, int input) {
    assertEquals(expected, scale.formForCount(input), String.valueOf(input));
  }

}
