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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link NumberConverter}.
 *
 * @author Martynas Sateika
 */
class NumberConverterTest {

  private NumberConverter converter;

  @BeforeEach
  void setUp() {
    converter = NumberConverter.create();
  }

  @Test
  void convert_zeroThroughNineteen() {
    assertAll(
        () -> check("nulis", 0),
        () -> check("vienas", 1),
        () -> check("du", 2),
        () -> check("trys", 3),
        () -> check("keturi", 4),
        () -> check("penki", 5),
        () -> check("šeši", 6),
        () -> check("septyni", 7),
        () -> check("aštuoni", 8),
        () -> check("devyni", 9),
        () -> check("dešimt", 10),
        () -> check("vienuolika", 11),
        () -> check("dvylika", 12),
        () -> check("trylika", 13),
        () -> check("keturiolika", 14),
        () -> check("penkiolika", 15),
        () -> check("šešiolika", 16),
        () -> check("septyniolika", 17),
        () -> check("aštuoniolika", 18),
        () -> check("devyniolika", 19)
    );
  }

  @Test
  void convert_tens() {
    assertAll(
        () -> check("dvidešimt", 20),
        () -> check("trisdešimt", 30),
        () -> check("keturiasdešimt", 40),
        () -> check("penkiasdešimt", 50),
        () -> check("šešiasdešimt", 60),
        () -> check("septyniasdešimt", 70),
        () -> check("aštuoniasdešimt", 80),
        () -> check("devyniasdešimt", 90),
        () -> check("dvidešimt vienas", 21),
        () -> check("dvidešimt devyni", 29)
    );
  }

  @Test
  void convert_hundreds() {
    assertAll(
        () -> check("vienas šimtas", 100),
        () -> check("du šimtai", 200),
        () -> check("trys šimtai", 300),
        () -> check("keturi šimtai", 400),
        () -> check("penki šimtai", 500),
        () -> check("šeši šimtai", 600),
        () -> check("septyni šimtai", 700),
        () -> check("aštuoni šimtai", 800),
        () -> check("devyni šimtai", 900)
    );
  }

  @Test
  void convert_thousands() {
    assertAll(
        () -> check("vienas tūkstantis", 1_000),
        () -> check("du tūkstančiai", 2_000),
        () -> check("trys tūkstančiai", 3_000),
        () -> check("keturi tūkstančiai", 4_000),
        () -> check("penki tūkstančiai", 5_000),
        () -> check("šeši tūkstančiai", 6_000),
        () -> check("septyni tūkstančiai", 7_000),
        () -> check("aštuoni tūkstančiai", 8_000),
        () -> check("devyni tūkstančiai", 9_000),
        () -> check("dešimt tūkstančių", 10_000),
        () -> check("vienas šimtas tūkstančių", 100_000),
        () -> check(
            "devyni šimtai devyniasdešimt devyni tūkstančiai devyni šimtai devyniasdešimt devyni",
            999_999)
    );
  }

  @Test
  void convert_millions() {
    assertAll(
        () -> check("vienas milijonas", 1_000_000),
        () -> check("du milijonai", 2_000_000),
        () -> check("trys milijonai", 3_000_000),
        () -> check("keturi milijonai", 4_000_000),
        () -> check("penki milijonai", 5_000_000),
        () -> check("šeši milijonai", 6_000_000),
        () -> check("septyni milijonai", 7_000_000),
        () -> check("aštuoni milijonai", 8_000_000),
        () -> check("devyni milijonai", 9_000_000),
        () -> check("dešimt milijonų", 10_000_000),
        () -> check("dvidešimt milijonų", 20_000_000),
        () -> check("devyniasdešimt milijonų", 90_000_000),
        () -> check("vienas šimtas milijonų", 100_000_000),
        () -> check("devyni šimtai milijonų", 900_000_000),
        () -> check("devyni šimtai devyniasdešimt devyni milijonai", 999_000_000)
    );
  }

  @Test
  void convert_billions() {
    assertAll(
        () -> check("vienas milijardas", 1_000_000_000L),
        () -> check("du milijardai", 2_000_000_000L),
        () -> check("trys milijardai", 3_000_000_000L),
        () -> check("keturi milijardai", 4_000_000_000L),
        () -> check("penki milijardai", 5_000_000_000L),
        () -> check("šeši milijardai", 6_000_000_000L),
        () -> check("septyni milijardai", 7_000_000_000L),
        () -> check("aštuoni milijardai", 8_000_000_000L),
        () -> check("devyni milijardai", 9_000_000_000L),
        () -> check("dešimt milijardų", 10_000_000_000L),
        () -> check(
            "devyni šimtai devyniasdešimt devyni milijardai devyni šimtai devyniasdešimt devyni milijonai devyni šimtai devyniasdešimt devyni tūkstančiai devyni šimtai devyniasdešimt devyni",
            999_999_999_999L)
    );
  }

  @Test
  void convert_trillions() {
    assertAll(
        () -> check("vienas trilijonas", 1_000_000_000_000L),
        () -> check("du trilijonai", 2_000_000_000_000L),
        () -> check("trys trilijonai", 3_000_000_000_000L),
        () -> check("keturi trilijonai", 4_000_000_000_000L),
        () -> check("penki trilijonai", 5_000_000_000_000L),
        () -> check("šeši trilijonai", 6_000_000_000_000L),
        () -> check("septyni trilijonai", 7_000_000_000_000L),
        () -> check("aštuoni trilijonai", 8_000_000_000_000L),
        () -> check("devyni trilijonai", 9_000_000_000_000L),
        () -> check("dešimt trilijonų", 10_000_000_000_000L),
        () -> check(
            "devyni šimtai devyniasdešimt devyni trilijonai devyni šimtai devyniasdešimt devyni milijardai devyni šimtai devyniasdešimt devyni milijonai devyni šimtai devyniasdešimt devyni tūkstančiai devyni šimtai devyniasdešimt devyni",
            999_999_999_999_999L)
    );
  }

  @Test
  void convert_quadrillions() {
    assertAll(
        () -> check("vienas kvadrilijonas", 1_000_000_000_000_000L),
        () -> check("du kvadrilijonai", 2_000_000_000_000_000L),
        () -> check("trys kvadrilijonai", 3_000_000_000_000_000L),
        () -> check("keturi kvadrilijonai", 4_000_000_000_000_000L),
        () -> check("penki kvadrilijonai", 5_000_000_000_000_000L),
        () -> check("šeši kvadrilijonai", 6_000_000_000_000_000L),
        () -> check("septyni kvadrilijonai", 7_000_000_000_000_000L),
        () -> check("aštuoni kvadrilijonai", 8_000_000_000_000_000L),
        () -> check("devyni kvadrilijonai", 9_000_000_000_000_000L),
        () -> check("dešimt kvadrilijonų", 10_000_000_000_000_000L),
        () -> check(
            "devyni šimtai devyniasdešimt devyni kvadrilijonai devyni šimtai devyniasdešimt devyni trilijonai devyni šimtai devyniasdešimt devyni milijardai devyni šimtai devyniasdešimt devyni milijonai devyni šimtai devyniasdešimt devyni tūkstančiai devyni šimtai devyniasdešimt devyni",
            999_999_999_999_999_999L)
    );
  }

  @Test
  void convert_quintillions() {
    assertAll(
        () -> check("vienas kvintilijonas", 1_000_000_000_000_000_000L),
        () -> check("du kvintilijonai", 2_000_000_000_000_000_000L),
        () -> check("trys kvintilijonai", 3_000_000_000_000_000_000L),
        () -> check("keturi kvintilijonai", 4_000_000_000_000_000_000L),
        () -> check("penki kvintilijonai", 5_000_000_000_000_000_000L),
        () -> check("šeši kvintilijonai", 6_000_000_000_000_000_000L),
        () -> check("septyni kvintilijonai", 7_000_000_000_000_000_000L),
        () -> check("aštuoni kvintilijonai", 8_000_000_000_000_000_000L),
        () -> check("devyni kvintilijonai", 9_000_000_000_000_000_000L)
    );
  }

  @Test
  void convert_negativeValues() {
    assertAll(
        () -> check("nulis", -0),
        () -> check("minus vienas", -1),
        () -> check("minus dešimt", -10),
        () -> check("minus devyni šimtai devyniasdešimt devyni", -999)
    );
  }

  @Test
  void convert_extremeLongValues() {
    assertAll(
        () -> check(
            "devyni kvintilijonai du šimtai dvidešimt trys kvadrilijonai trys šimtai septyniasdešimt du trilijonai trisdešimt šeši milijardai aštuoni šimtai penkiasdešimt keturi milijonai septyni šimtai septyniasdešimt penki tūkstančiai aštuoni šimtai septyni",
            Long.MAX_VALUE),
        () -> check(
            "minus devyni kvintilijonai du šimtai dvidešimt trys kvadrilijonai trys šimtai septyniasdešimt du trilijonai trisdešimt šeši milijardai aštuoni šimtai penkiasdešimt keturi milijonai septyni šimtai septyniasdešimt penki tūkstančiai aštuoni šimtai aštuoni",
            Long.MIN_VALUE)
    );
  }

  @Test
  void convert_startingWithOne() {
    assertAll(
        () -> check("vienas", 1),
        () -> check("dvylika", 12),
        () -> check("vienas šimtas dvidešimt trys", 123),
        () -> check("vienas tūkstantis du šimtai trisdešimt keturi", 1234),
        () -> check("dvylika tūkstančių trys šimtai keturiasdešimt penki", 12345),
        () -> check("vienas šimtas dvidešimt trys tūkstančiai keturi šimtai penkiasdešimt šeši",
            123456),
        () -> check(
            "vienas milijonas du šimtai trisdešimt keturi tūkstančiai penki šimtai šešiasdešimt septyni",
            1234567),
        () -> check(
            "dvylika milijonų trys šimtai keturiasdešimt penki tūkstančiai šeši šimtai septyniasdešimt aštuoni",
            12345678),
        () -> check(
            "vienas šimtas dvidešimt trys milijonai keturi šimtai penkiasdešimt šeši tūkstančiai septyni šimtai aštuoniasdešimt devyni",
            123456789),
        () -> check(
            "vienas milijardas du šimtai trisdešimt keturi milijonai penki šimtai šešiasdešimt septyni tūkstančiai aštuoni šimtai devyniasdešimt",
            1234567890)
    );
  }

  @Test
  void convert_cycleOneThroughNine() {
    assertAll(
        () -> check(
            "vienas šimtas dvidešimt trys milijonai keturi šimtai penkiasdešimt šeši tūkstančiai septyni šimtai aštuoniasdešimt devyni",
            123456789),
        () -> check(
            "du šimtai trisdešimt keturi milijonai penki šimtai šešiasdešimt septyni tūkstančiai aštuoni šimtai devyniasdešimt vienas",
            234567891),
        () -> check(
            "trys šimtai keturiasdešimt penki milijonai šeši šimtai septyniasdešimt aštuoni tūkstančiai devyni šimtai dvylika",
            345678912),
        () -> check(
            "keturi šimtai penkiasdešimt šeši milijonai septyni šimtai aštuoniasdešimt devyni tūkstančiai vienas šimtas dvidešimt trys",
            456789123),
        () -> check(
            "penki šimtai šešiasdešimt septyni milijonai aštuoni šimtai devyniasdešimt vienas tūkstantis du šimtai trisdešimt keturi",
            567891234),
        () -> check(
            "šeši šimtai septyniasdešimt aštuoni milijonai devyni šimtai dvylika tūkstančių trys šimtai keturiasdešimt penki",
            678912345),
        () -> check(
            "septyni šimtai aštuoniasdešimt devyni milijonai vienas šimtas dvidešimt trys tūkstančiai keturi šimtai penkiasdešimt šeši",
            789123456),
        () -> check(
            "aštuoni šimtai devyniasdešimt vienas milijonas du šimtai trisdešimt keturi tūkstančiai penki šimtai šešiasdešimt septyni",
            891234567),
        () -> check(
            "devyni šimtai dvylika milijonų trys šimtai keturiasdešimt penki tūkstančiai šeši šimtai septyniasdešimt aštuoni",
            912345678)
    );
  }

  private void check(String expected, long number) {
    assertEquals(expected, converter.toLithuanian(number));
  }

}
