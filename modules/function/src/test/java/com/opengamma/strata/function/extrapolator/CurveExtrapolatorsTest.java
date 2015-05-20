/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.function.extrapolator;

import static com.opengamma.strata.collect.CollectProjectAssertions.assertThat;

import org.testng.annotations.Test;

import com.opengamma.analytics.math.interpolation.LinearExtrapolator1D;
import com.opengamma.analytics.math.interpolation.LogLinearExtrapolator1D;
import com.opengamma.strata.basics.interpolator.CurveExtrapolator;

@Test
public class CurveExtrapolatorsTest {

  /**
   * Test that the constants correctly resolve to interpolator instances.
   */
  public void constants() {
    CurveExtrapolator linear = CurveExtrapolators.LINEAR;
    assertThat(linear).isInstanceOf(LinearExtrapolator1D.class);

    CurveExtrapolator logLinear = CurveExtrapolators.LOG_LINEAR;
    assertThat(logLinear).isInstanceOf(LogLinearExtrapolator1D.class);
  }
}
