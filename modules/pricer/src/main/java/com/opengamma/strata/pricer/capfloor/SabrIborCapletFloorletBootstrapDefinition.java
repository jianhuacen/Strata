package com.opengamma.strata.pricer.capfloor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
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

import com.google.common.collect.ImmutableList;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.index.IborIndex;
import com.opengamma.strata.market.ValueType;
import com.opengamma.strata.market.curve.ConstantCurve;
import com.opengamma.strata.market.curve.Curve;
import com.opengamma.strata.market.curve.CurveMetadata;
import com.opengamma.strata.market.curve.Curves;
import com.opengamma.strata.market.curve.interpolator.CurveInterpolator;
import com.opengamma.strata.market.surface.SurfaceMetadata;
import com.opengamma.strata.market.surface.Surfaces;
import com.opengamma.strata.pricer.model.SabrVolatilityFormula;
import com.opengamma.strata.pricer.option.RawOptionData;

@BeanDefinition
public final class SabrIborCapletFloorletBootstrapDefinition
    implements IborCapletFloorletDefinition, ImmutableBean, Serializable {

  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final IborCapletFloorletVolatilitiesName name;

  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final IborIndex index;

  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final DayCount dayCount;

  @PropertyDefinition(get = "optional")
  private final Curve shiftCurve;

  @PropertyDefinition(get = "optional")
  private final Curve betaCurve;

  @PropertyDefinition(validate = "notNull")
  private final CurveInterpolator timeInterpolator;

  @PropertyDefinition(validate = "notNull")
  private final SabrVolatilityFormula sabrVolatilityFormula;

//  @PropertyDefinition(validate = "notNull", overrideGet = true)
//  private final BusinessDayAdjustment businessDayAdjustment;

  public static SabrIborCapletFloorletBootstrapDefinition of(
      IborCapletFloorletVolatilitiesName name,
      IborIndex index,
      DayCount dayCount,
      CurveInterpolator timeInterpolator,
      SabrVolatilityFormula sabrVolatilityFormula) {

    return new SabrIborCapletFloorletBootstrapDefinition(name, index, dayCount, null, null,
        timeInterpolator, sabrVolatilityFormula);
  }

  public static SabrIborCapletFloorletBootstrapDefinition of(
      IborCapletFloorletVolatilitiesName name,
      IborIndex index,
      DayCount dayCount,
      double beta,
      CurveInterpolator timeInterpolator,
      SabrVolatilityFormula sabrVolatilityFormula) {

    ConstantCurve betaCurve =
        ConstantCurve.of(Curves.sabrParameterByExpiry(name.getName() + "-Beta", dayCount, ValueType.SABR_BETA), beta);
    return new SabrIborCapletFloorletBootstrapDefinition(name, index, dayCount, null, betaCurve,
        timeInterpolator, sabrVolatilityFormula);
  }

  public static SabrIborCapletFloorletBootstrapDefinition of(
      IborCapletFloorletVolatilitiesName name,
      IborIndex index,
      DayCount dayCount,
      double beta,
      double shift,
      CurveInterpolator timeInterpolator,
      SabrVolatilityFormula sabrVolatilityFormula) {

    ConstantCurve shiftCurve = ConstantCurve.of("Shift curve", shift);
    ConstantCurve betaCurve =
        ConstantCurve.of(Curves.sabrParameterByExpiry(name.getName() + "-Beta", dayCount, ValueType.SABR_BETA), beta);
    return new SabrIborCapletFloorletBootstrapDefinition(name, index, dayCount, shiftCurve, betaCurve,
        timeInterpolator, sabrVolatilityFormula);
  }

  public SurfaceMetadata createMetadata(RawOptionData capFloorData) {
    SurfaceMetadata metadata;
    if (capFloorData.getDataType().equals(ValueType.BLACK_VOLATILITY)) {
      metadata = Surfaces.blackVolatilityByExpiryStrike(name.getName(), dayCount);
    } else if (capFloorData.getDataType().equals(ValueType.NORMAL_VOLATILITY)) {
      metadata = Surfaces.normalVolatilityByExpiryStrike(name.getName(), dayCount);
    } else {
      throw new IllegalArgumentException("Data type not supported");
    }
    return metadata;
  }

  public List<CurveMetadata> createSabrParameterMetadata(RawOptionData capFloorData) {
    CurveMetadata alphaMetadata = Curves.sabrParameterByExpiry(name.getName() + "-Alpha", dayCount, ValueType.SABR_ALPHA);
    CurveMetadata betaMetadata = Curves.sabrParameterByExpiry(name.getName() + "-Beta", dayCount, ValueType.SABR_BETA);
    CurveMetadata rhoMetadata = Curves.sabrParameterByExpiry(name.getName() + "-Nu", dayCount, ValueType.SABR_RHO);
    CurveMetadata nuMetadata = Curves.sabrParameterByExpiry(name.getName() + "-Rho", dayCount, ValueType.SABR_NU); // TODO shift?
    return ImmutableList.of(alphaMetadata, betaMetadata, rhoMetadata, nuMetadata);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SabrIborCapletFloorletBootstrapDefinition}.
   * @return the meta-bean, not null
   */
  public static SabrIborCapletFloorletBootstrapDefinition.Meta meta() {
    return SabrIborCapletFloorletBootstrapDefinition.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SabrIborCapletFloorletBootstrapDefinition.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static SabrIborCapletFloorletBootstrapDefinition.Builder builder() {
    return new SabrIborCapletFloorletBootstrapDefinition.Builder();
  }

  private SabrIborCapletFloorletBootstrapDefinition(
      IborCapletFloorletVolatilitiesName name,
      IborIndex index,
      DayCount dayCount,
      Curve shiftCurve,
      Curve betaCurve,
      CurveInterpolator timeInterpolator,
      SabrVolatilityFormula sabrVolatilityFormula) {
    JodaBeanUtils.notNull(name, "name");
    JodaBeanUtils.notNull(index, "index");
    JodaBeanUtils.notNull(dayCount, "dayCount");
    JodaBeanUtils.notNull(timeInterpolator, "timeInterpolator");
    JodaBeanUtils.notNull(sabrVolatilityFormula, "sabrVolatilityFormula");
    this.name = name;
    this.index = index;
    this.dayCount = dayCount;
    this.shiftCurve = shiftCurve;
    this.betaCurve = betaCurve;
    this.timeInterpolator = timeInterpolator;
    this.sabrVolatilityFormula = sabrVolatilityFormula;
  }

  @Override
  public SabrIborCapletFloorletBootstrapDefinition.Meta metaBean() {
    return SabrIborCapletFloorletBootstrapDefinition.Meta.INSTANCE;
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
   * Gets the name.
   * @return the value of the property, not null
   */
  @Override
  public IborCapletFloorletVolatilitiesName getName() {
    return name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the index.
   * @return the value of the property, not null
   */
  @Override
  public IborIndex getIndex() {
    return index;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the dayCount.
   * @return the value of the property, not null
   */
  @Override
  public DayCount getDayCount() {
    return dayCount;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the shiftCurve.
   * @return the optional value of the property, not null
   */
  public Optional<Curve> getShiftCurve() {
    return Optional.ofNullable(shiftCurve);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the betaCurve.
   * @return the optional value of the property, not null
   */
  public Optional<Curve> getBetaCurve() {
    return Optional.ofNullable(betaCurve);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the timeInterpolator.
   * @return the value of the property, not null
   */
  public CurveInterpolator getTimeInterpolator() {
    return timeInterpolator;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the sabrVolatilityFormula.
   * @return the value of the property, not null
   */
  public SabrVolatilityFormula getSabrVolatilityFormula() {
    return sabrVolatilityFormula;
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
      SabrIborCapletFloorletBootstrapDefinition other = (SabrIborCapletFloorletBootstrapDefinition) obj;
      return JodaBeanUtils.equal(name, other.name) &&
          JodaBeanUtils.equal(index, other.index) &&
          JodaBeanUtils.equal(dayCount, other.dayCount) &&
          JodaBeanUtils.equal(shiftCurve, other.shiftCurve) &&
          JodaBeanUtils.equal(betaCurve, other.betaCurve) &&
          JodaBeanUtils.equal(timeInterpolator, other.timeInterpolator) &&
          JodaBeanUtils.equal(sabrVolatilityFormula, other.sabrVolatilityFormula);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(name);
    hash = hash * 31 + JodaBeanUtils.hashCode(index);
    hash = hash * 31 + JodaBeanUtils.hashCode(dayCount);
    hash = hash * 31 + JodaBeanUtils.hashCode(shiftCurve);
    hash = hash * 31 + JodaBeanUtils.hashCode(betaCurve);
    hash = hash * 31 + JodaBeanUtils.hashCode(timeInterpolator);
    hash = hash * 31 + JodaBeanUtils.hashCode(sabrVolatilityFormula);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("SabrIborCapletFloorletBootstrapDefinition{");
    buf.append("name").append('=').append(name).append(',').append(' ');
    buf.append("index").append('=').append(index).append(',').append(' ');
    buf.append("dayCount").append('=').append(dayCount).append(',').append(' ');
    buf.append("shiftCurve").append('=').append(shiftCurve).append(',').append(' ');
    buf.append("betaCurve").append('=').append(betaCurve).append(',').append(' ');
    buf.append("timeInterpolator").append('=').append(timeInterpolator).append(',').append(' ');
    buf.append("sabrVolatilityFormula").append('=').append(JodaBeanUtils.toString(sabrVolatilityFormula));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SabrIborCapletFloorletBootstrapDefinition}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<IborCapletFloorletVolatilitiesName> name = DirectMetaProperty.ofImmutable(
        this, "name", SabrIborCapletFloorletBootstrapDefinition.class, IborCapletFloorletVolatilitiesName.class);
    /**
     * The meta-property for the {@code index} property.
     */
    private final MetaProperty<IborIndex> index = DirectMetaProperty.ofImmutable(
        this, "index", SabrIborCapletFloorletBootstrapDefinition.class, IborIndex.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCount> dayCount = DirectMetaProperty.ofImmutable(
        this, "dayCount", SabrIborCapletFloorletBootstrapDefinition.class, DayCount.class);
    /**
     * The meta-property for the {@code shiftCurve} property.
     */
    private final MetaProperty<Curve> shiftCurve = DirectMetaProperty.ofImmutable(
        this, "shiftCurve", SabrIborCapletFloorletBootstrapDefinition.class, Curve.class);
    /**
     * The meta-property for the {@code betaCurve} property.
     */
    private final MetaProperty<Curve> betaCurve = DirectMetaProperty.ofImmutable(
        this, "betaCurve", SabrIborCapletFloorletBootstrapDefinition.class, Curve.class);
    /**
     * The meta-property for the {@code timeInterpolator} property.
     */
    private final MetaProperty<CurveInterpolator> timeInterpolator = DirectMetaProperty.ofImmutable(
        this, "timeInterpolator", SabrIborCapletFloorletBootstrapDefinition.class, CurveInterpolator.class);
    /**
     * The meta-property for the {@code sabrVolatilityFormula} property.
     */
    private final MetaProperty<SabrVolatilityFormula> sabrVolatilityFormula = DirectMetaProperty.ofImmutable(
        this, "sabrVolatilityFormula", SabrIborCapletFloorletBootstrapDefinition.class, SabrVolatilityFormula.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "index",
        "dayCount",
        "shiftCurve",
        "betaCurve",
        "timeInterpolator",
        "sabrVolatilityFormula");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return name;
        case 100346066:  // index
          return index;
        case 1905311443:  // dayCount
          return dayCount;
        case 1908090253:  // shiftCurve
          return shiftCurve;
        case 1607020767:  // betaCurve
          return betaCurve;
        case -587914188:  // timeInterpolator
          return timeInterpolator;
        case -683564541:  // sabrVolatilityFormula
          return sabrVolatilityFormula;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public SabrIborCapletFloorletBootstrapDefinition.Builder builder() {
      return new SabrIborCapletFloorletBootstrapDefinition.Builder();
    }

    @Override
    public Class<? extends SabrIborCapletFloorletBootstrapDefinition> beanType() {
      return SabrIborCapletFloorletBootstrapDefinition.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public MetaProperty<IborCapletFloorletVolatilitiesName> name() {
      return name;
    }

    /**
     * The meta-property for the {@code index} property.
     * @return the meta-property, not null
     */
    public MetaProperty<IborIndex> index() {
      return index;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public MetaProperty<DayCount> dayCount() {
      return dayCount;
    }

    /**
     * The meta-property for the {@code shiftCurve} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Curve> shiftCurve() {
      return shiftCurve;
    }

    /**
     * The meta-property for the {@code betaCurve} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Curve> betaCurve() {
      return betaCurve;
    }

    /**
     * The meta-property for the {@code timeInterpolator} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CurveInterpolator> timeInterpolator() {
      return timeInterpolator;
    }

    /**
     * The meta-property for the {@code sabrVolatilityFormula} property.
     * @return the meta-property, not null
     */
    public MetaProperty<SabrVolatilityFormula> sabrVolatilityFormula() {
      return sabrVolatilityFormula;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).getName();
        case 100346066:  // index
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).getIndex();
        case 1905311443:  // dayCount
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).getDayCount();
        case 1908090253:  // shiftCurve
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).shiftCurve;
        case 1607020767:  // betaCurve
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).betaCurve;
        case -587914188:  // timeInterpolator
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).getTimeInterpolator();
        case -683564541:  // sabrVolatilityFormula
          return ((SabrIborCapletFloorletBootstrapDefinition) bean).getSabrVolatilityFormula();
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
   * The bean-builder for {@code SabrIborCapletFloorletBootstrapDefinition}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<SabrIborCapletFloorletBootstrapDefinition> {

    private IborCapletFloorletVolatilitiesName name;
    private IborIndex index;
    private DayCount dayCount;
    private Curve shiftCurve;
    private Curve betaCurve;
    private CurveInterpolator timeInterpolator;
    private SabrVolatilityFormula sabrVolatilityFormula;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(SabrIborCapletFloorletBootstrapDefinition beanToCopy) {
      this.name = beanToCopy.getName();
      this.index = beanToCopy.getIndex();
      this.dayCount = beanToCopy.getDayCount();
      this.shiftCurve = beanToCopy.shiftCurve;
      this.betaCurve = beanToCopy.betaCurve;
      this.timeInterpolator = beanToCopy.getTimeInterpolator();
      this.sabrVolatilityFormula = beanToCopy.getSabrVolatilityFormula();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return name;
        case 100346066:  // index
          return index;
        case 1905311443:  // dayCount
          return dayCount;
        case 1908090253:  // shiftCurve
          return shiftCurve;
        case 1607020767:  // betaCurve
          return betaCurve;
        case -587914188:  // timeInterpolator
          return timeInterpolator;
        case -683564541:  // sabrVolatilityFormula
          return sabrVolatilityFormula;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          this.name = (IborCapletFloorletVolatilitiesName) newValue;
          break;
        case 100346066:  // index
          this.index = (IborIndex) newValue;
          break;
        case 1905311443:  // dayCount
          this.dayCount = (DayCount) newValue;
          break;
        case 1908090253:  // shiftCurve
          this.shiftCurve = (Curve) newValue;
          break;
        case 1607020767:  // betaCurve
          this.betaCurve = (Curve) newValue;
          break;
        case -587914188:  // timeInterpolator
          this.timeInterpolator = (CurveInterpolator) newValue;
          break;
        case -683564541:  // sabrVolatilityFormula
          this.sabrVolatilityFormula = (SabrVolatilityFormula) newValue;
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
    public SabrIborCapletFloorletBootstrapDefinition build() {
      return new SabrIborCapletFloorletBootstrapDefinition(
          name,
          index,
          dayCount,
          shiftCurve,
          betaCurve,
          timeInterpolator,
          sabrVolatilityFormula);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the name.
     * @param name  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder name(IborCapletFloorletVolatilitiesName name) {
      JodaBeanUtils.notNull(name, "name");
      this.name = name;
      return this;
    }

    /**
     * Sets the index.
     * @param index  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder index(IborIndex index) {
      JodaBeanUtils.notNull(index, "index");
      this.index = index;
      return this;
    }

    /**
     * Sets the dayCount.
     * @param dayCount  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder dayCount(DayCount dayCount) {
      JodaBeanUtils.notNull(dayCount, "dayCount");
      this.dayCount = dayCount;
      return this;
    }

    /**
     * Sets the shiftCurve.
     * @param shiftCurve  the new value
     * @return this, for chaining, not null
     */
    public Builder shiftCurve(Curve shiftCurve) {
      this.shiftCurve = shiftCurve;
      return this;
    }

    /**
     * Sets the betaCurve.
     * @param betaCurve  the new value
     * @return this, for chaining, not null
     */
    public Builder betaCurve(Curve betaCurve) {
      this.betaCurve = betaCurve;
      return this;
    }

    /**
     * Sets the timeInterpolator.
     * @param timeInterpolator  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder timeInterpolator(CurveInterpolator timeInterpolator) {
      JodaBeanUtils.notNull(timeInterpolator, "timeInterpolator");
      this.timeInterpolator = timeInterpolator;
      return this;
    }

    /**
     * Sets the sabrVolatilityFormula.
     * @param sabrVolatilityFormula  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder sabrVolatilityFormula(SabrVolatilityFormula sabrVolatilityFormula) {
      JodaBeanUtils.notNull(sabrVolatilityFormula, "sabrVolatilityFormula");
      this.sabrVolatilityFormula = sabrVolatilityFormula;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(256);
      buf.append("SabrIborCapletFloorletBootstrapDefinition.Builder{");
      buf.append("name").append('=').append(JodaBeanUtils.toString(name)).append(',').append(' ');
      buf.append("index").append('=').append(JodaBeanUtils.toString(index)).append(',').append(' ');
      buf.append("dayCount").append('=').append(JodaBeanUtils.toString(dayCount)).append(',').append(' ');
      buf.append("shiftCurve").append('=').append(JodaBeanUtils.toString(shiftCurve)).append(',').append(' ');
      buf.append("betaCurve").append('=').append(JodaBeanUtils.toString(betaCurve)).append(',').append(' ');
      buf.append("timeInterpolator").append('=').append(JodaBeanUtils.toString(timeInterpolator)).append(',').append(' ');
      buf.append("sabrVolatilityFormula").append('=').append(JodaBeanUtils.toString(sabrVolatilityFormula));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
