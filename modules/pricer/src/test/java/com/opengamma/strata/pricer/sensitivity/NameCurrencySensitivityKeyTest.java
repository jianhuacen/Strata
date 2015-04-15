/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.sensitivity;

import static com.opengamma.strata.basics.currency.Currency.EUR;
import static com.opengamma.strata.basics.currency.Currency.GBP;
import static com.opengamma.strata.collect.TestHelper.coverBeanEquals;
import static com.opengamma.strata.collect.TestHelper.coverImmutableBean;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Test {@link NameCurrencySensitivityKey}.
 */
@Test
public class NameCurrencySensitivityKeyTest {

  private static final String NAME = "USD-LIBOR-3M";

  public void test_of() {
    NameCurrencySensitivityKey test = NameCurrencySensitivityKey.of(NAME, EUR);
    assertEquals(test.getCurveName(), NAME);
    assertEquals(test.getCurrency(), EUR);
  }

  public void coverage() {
    NameCurrencySensitivityKey test = NameCurrencySensitivityKey.of(NAME, EUR);
    coverImmutableBean(test);
    NameCurrencySensitivityKey test2 = NameCurrencySensitivityKey.of("Other", GBP);
    coverBeanEquals(test, test2);
  }

}