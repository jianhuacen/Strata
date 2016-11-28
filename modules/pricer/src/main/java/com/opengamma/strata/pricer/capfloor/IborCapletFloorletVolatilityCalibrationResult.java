/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.capfloor;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Calibration result for Ibor caplet/floorlet volatilities.
 * <p>
 * This stores caplet volatilities {@link IborCapletFloorletVolatilities} and chi square value {@code chiSquare}. 
 */
@BeanDefinition(builderScope = "private")
public final class IborCapletFloorletVolatilityCalibrationResult
    implements ImmutableBean, Serializable {

  /**
   * The caplet volatilities.
   */
  @PropertyDefinition(validate = "notNull")
  private final IborCapletFloorletVolatilities volatilities;

  /**
   * The chi-square value.
   * <p>
   * The chi square is 0 if the volatilities are computed by root-finding. 
   * The chi square is generally non-zeoro if the volatilities are computed by least square method.
   */
  @PropertyDefinition(validate = "notNull")
  private final double chiSquare;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance of least square result. 
   * 
   * @param volatilities  the caplet volatilities
   * @param chiSquare  the chi-square value
   * @return the instance
   */
  public static IborCapletFloorletVolatilityCalibrationResult ofLestSquare(
      IborCapletFloorletVolatilities volatilities,
      double chiSquare) {

    return new IborCapletFloorletVolatilityCalibrationResult(volatilities, chiSquare);
  }

  /**
   * Obtains an instance of root-finding result. 
   * 
   * @param volatilities  the caplet volatilities
   * @return the instance
   */
  public static IborCapletFloorletVolatilityCalibrationResult ofRootFind(IborCapletFloorletVolatilities volatilities) {

    return new IborCapletFloorletVolatilityCalibrationResult(volatilities, 0d);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IborCapletFloorletVolatilityCalibrationResult}.
   * @return the meta-bean, not null
   */
  public static IborCapletFloorletVolatilityCalibrationResult.Meta meta() {
    return IborCapletFloorletVolatilityCalibrationResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IborCapletFloorletVolatilityCalibrationResult.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private IborCapletFloorletVolatilityCalibrationResult(
      IborCapletFloorletVolatilities volatilities,
      double chiSquare) {
    JodaBeanUtils.notNull(volatilities, "volatilities");
    JodaBeanUtils.notNull(chiSquare, "chiSquare");
    this.volatilities = volatilities;
    this.chiSquare = chiSquare;
  }

  @Override
  public IborCapletFloorletVolatilityCalibrationResult.Meta metaBean() {
    return IborCapletFloorletVolatilityCalibrationResult.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the caplet volatilities.
   * @return the value of the property, not null
   */
  public IborCapletFloorletVolatilities getVolatilities() {
    return volatilities;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the chi-square value.
   * <p>
   * The chi square is 0 if the volatilities are computed by root-finding.
   * The chi square is generally non-zeoro if the volatilities are computed by least square method.
   * @return the value of the property, not null
   */
  public double getChiSquare() {
    return chiSquare;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IborCapletFloorletVolatilityCalibrationResult other = (IborCapletFloorletVolatilityCalibrationResult) obj;
      return JodaBeanUtils.equal(volatilities, other.volatilities) &&
          JodaBeanUtils.equal(chiSquare, other.chiSquare);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(volatilities);
    hash = hash * 31 + JodaBeanUtils.hashCode(chiSquare);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("IborCapletFloorletVolatilityCalibrationResult{");
    buf.append("volatilities").append('=').append(volatilities).append(',').append(' ');
    buf.append("chiSquare").append('=').append(JodaBeanUtils.toString(chiSquare));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IborCapletFloorletVolatilityCalibrationResult}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code volatilities} property.
     */
    private final MetaProperty<IborCapletFloorletVolatilities> volatilities = DirectMetaProperty.ofImmutable(
        this, "volatilities", IborCapletFloorletVolatilityCalibrationResult.class, IborCapletFloorletVolatilities.class);
    /**
     * The meta-property for the {@code chiSquare} property.
     */
    private final MetaProperty<Double> chiSquare = DirectMetaProperty.ofImmutable(
        this, "chiSquare", IborCapletFloorletVolatilityCalibrationResult.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "volatilities",
        "chiSquare");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -625639549:  // volatilities
          return volatilities;
        case -797918495:  // chiSquare
          return chiSquare;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IborCapletFloorletVolatilityCalibrationResult> builder() {
      return new IborCapletFloorletVolatilityCalibrationResult.Builder();
    }

    @Override
    public Class<? extends IborCapletFloorletVolatilityCalibrationResult> beanType() {
      return IborCapletFloorletVolatilityCalibrationResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code volatilities} property.
     * @return the meta-property, not null
     */
    public MetaProperty<IborCapletFloorletVolatilities> volatilities() {
      return volatilities;
    }

    /**
     * The meta-property for the {@code chiSquare} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> chiSquare() {
      return chiSquare;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -625639549:  // volatilities
          return ((IborCapletFloorletVolatilityCalibrationResult) bean).getVolatilities();
        case -797918495:  // chiSquare
          return ((IborCapletFloorletVolatilityCalibrationResult) bean).getChiSquare();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code IborCapletFloorletVolatilityCalibrationResult}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<IborCapletFloorletVolatilityCalibrationResult> {

    private IborCapletFloorletVolatilities volatilities;
    private double chiSquare;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -625639549:  // volatilities
          return volatilities;
        case -797918495:  // chiSquare
          return chiSquare;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -625639549:  // volatilities
          this.volatilities = (IborCapletFloorletVolatilities) newValue;
          break;
        case -797918495:  // chiSquare
          this.chiSquare = (Double) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public IborCapletFloorletVolatilityCalibrationResult build() {
      return new IborCapletFloorletVolatilityCalibrationResult(
          volatilities,
          chiSquare);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("IborCapletFloorletVolatilityCalibrationResult.Builder{");
      buf.append("volatilities").append('=').append(JodaBeanUtils.toString(volatilities)).append(',').append(' ');
      buf.append("chiSquare").append('=').append(JodaBeanUtils.toString(chiSquare));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
