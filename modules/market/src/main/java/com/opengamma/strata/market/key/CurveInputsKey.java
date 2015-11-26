/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.market.key;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
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

import com.opengamma.strata.basics.market.MarketDataFeed;
import com.opengamma.strata.basics.market.MarketDataId;
import com.opengamma.strata.basics.market.SimpleMarketDataKey;
import com.opengamma.strata.market.curve.CurveGroupName;
import com.opengamma.strata.market.curve.CurveInputs;
import com.opengamma.strata.market.curve.CurveName;
import com.opengamma.strata.market.id.CurveInputsId;

/**
 * Market data key identifying the input data used when calibrating a curve.
 */
@BeanDefinition
public final class CurveInputsKey implements SimpleMarketDataKey<CurveInputs>, ImmutableBean {

  /** The name of the curve group containing the curve. */
  @PropertyDefinition(validate = "notNull")
  private final CurveGroupName curveGroupName;

  /** The name of the curve. */
  @PropertyDefinition(validate = "notNull")
  private final CurveName curveName;

  @Override
  public MarketDataId<CurveInputs> toMarketDataId(MarketDataFeed marketDataFeed) {
    return CurveInputsId.of(curveGroupName, curveName, marketDataFeed);
  }

  @Override
  public Class<CurveInputs> getMarketDataType() {
    return CurveInputs.class;
  }

  /**
   * Returns an key identifying the input data used when calibrating the specified curve.
   *
   * @param curveGroupName  the name of the curve group containing the curve
   * @param curveName  the name of the curve
   * @return an key for the input data used when calibrating the specified curve
   */
  public static CurveInputsKey of(CurveGroupName curveGroupName, CurveName curveName) {
    return new CurveInputsKey(curveGroupName, curveName);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CurveInputsKey}.
   * @return the meta-bean, not null
   */
  public static CurveInputsKey.Meta meta() {
    return CurveInputsKey.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CurveInputsKey.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static CurveInputsKey.Builder builder() {
    return new CurveInputsKey.Builder();
  }

  private CurveInputsKey(
      CurveGroupName curveGroupName,
      CurveName curveName) {
    JodaBeanUtils.notNull(curveGroupName, "curveGroupName");
    JodaBeanUtils.notNull(curveName, "curveName");
    this.curveGroupName = curveGroupName;
    this.curveName = curveName;
  }

  @Override
  public CurveInputsKey.Meta metaBean() {
    return CurveInputsKey.Meta.INSTANCE;
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
   * Gets the name of the curve group containing the curve.
   * @return the value of the property, not null
   */
  public CurveGroupName getCurveGroupName() {
    return curveGroupName;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the curve.
   * @return the value of the property, not null
   */
  public CurveName getCurveName() {
    return curveName;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      CurveInputsKey other = (CurveInputsKey) obj;
      return JodaBeanUtils.equal(curveGroupName, other.curveGroupName) &&
          JodaBeanUtils.equal(curveName, other.curveName);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(curveGroupName);
    hash = hash * 31 + JodaBeanUtils.hashCode(curveName);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("CurveInputsKey{");
    buf.append("curveGroupName").append('=').append(curveGroupName).append(',').append(' ');
    buf.append("curveName").append('=').append(JodaBeanUtils.toString(curveName));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CurveInputsKey}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code curveGroupName} property.
     */
    private final MetaProperty<CurveGroupName> curveGroupName = DirectMetaProperty.ofImmutable(
        this, "curveGroupName", CurveInputsKey.class, CurveGroupName.class);
    /**
     * The meta-property for the {@code curveName} property.
     */
    private final MetaProperty<CurveName> curveName = DirectMetaProperty.ofImmutable(
        this, "curveName", CurveInputsKey.class, CurveName.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "curveGroupName",
        "curveName");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -382645893:  // curveGroupName
          return curveGroupName;
        case 771153946:  // curveName
          return curveName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public CurveInputsKey.Builder builder() {
      return new CurveInputsKey.Builder();
    }

    @Override
    public Class<? extends CurveInputsKey> beanType() {
      return CurveInputsKey.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code curveGroupName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurveGroupName> curveGroupName() {
      return curveGroupName;
    }

    /**
     * The meta-property for the {@code curveName} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurveName> curveName() {
      return curveName;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -382645893:  // curveGroupName
          return ((CurveInputsKey) bean).getCurveGroupName();
        case 771153946:  // curveName
          return ((CurveInputsKey) bean).getCurveName();
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
   * The bean-builder for {@code CurveInputsKey}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<CurveInputsKey> {

    private CurveGroupName curveGroupName;
    private CurveName curveName;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(CurveInputsKey beanToCopy) {
      this.curveGroupName = beanToCopy.getCurveGroupName();
      this.curveName = beanToCopy.getCurveName();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -382645893:  // curveGroupName
          return curveGroupName;
        case 771153946:  // curveName
          return curveName;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -382645893:  // curveGroupName
          this.curveGroupName = (CurveGroupName) newValue;
          break;
        case 771153946:  // curveName
          this.curveName = (CurveName) newValue;
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
    public CurveInputsKey build() {
      return new CurveInputsKey(
          curveGroupName,
          curveName);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the name of the curve group containing the curve.
     * @param curveGroupName  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder curveGroupName(CurveGroupName curveGroupName) {
      JodaBeanUtils.notNull(curveGroupName, "curveGroupName");
      this.curveGroupName = curveGroupName;
      return this;
    }

    /**
     * Sets the name of the curve.
     * @param curveName  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder curveName(CurveName curveName) {
      JodaBeanUtils.notNull(curveName, "curveName");
      this.curveName = curveName;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("CurveInputsKey.Builder{");
      buf.append("curveGroupName").append('=').append(JodaBeanUtils.toString(curveGroupName)).append(',').append(' ');
      buf.append("curveName").append('=').append(JodaBeanUtils.toString(curveName));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}