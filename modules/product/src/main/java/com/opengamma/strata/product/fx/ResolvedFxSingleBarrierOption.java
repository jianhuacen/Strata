package com.opengamma.strata.product.fx;

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

import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.opengamma.strata.basics.currency.Payment;
import com.opengamma.strata.product.ResolvedProduct;

@BeanDefinition(builderScope = "private")
public final class ResolvedFxSingleBarrierOption
    implements ResolvedProduct, ImmutableBean, Serializable {

  /**
   * The underlying FX vanilla option.
   */
  @PropertyDefinition(validate = "notNull")
  private final ResolvedFxVanillaOption underlyingOption;
  /**
   * The amount paid back to the option holder in case the option expires inactive.
   * <p>
   * The barrier definition for the barrier option is given within this field. 
   */
  @PropertyDefinition
  private final ResolvedFxOneTouchOption rebate;

  //-------------------------------------------------------------------------
  /**
   * Obtains FX single barrier option with rebate. 
   * 
   * @param underlyingOption  the underlying FX vanilla option
   * @param barrier  the barrier
   * @param rebate  the rebate
   * @return the instance
   */
  public static ResolvedFxSingleBarrierOption of(
      ResolvedFxVanillaOption underlyingOption,
      Barrier barrier,
      CurrencyAmount rebate) {
    ResolvedFxOneTouchOption rebateResolved = ResolvedFxOneTouchOption.of(
        underlyingOption.getLongShort(),
        underlyingOption.getExpiry(),
        Payment.of(rebate, underlyingOption.getUnderlying().getPaymentDate()),
        underlyingOption.getUnderlying().getCurrencyPair(), barrier);
    return new ResolvedFxSingleBarrierOption(underlyingOption, rebateResolved);
  }

  /**
   * Obtains FX single barrier option without rebate. 
   * 
   * @param underlyingOption  the underlying FX vanilla option
   * @param barrier  the barrier
   * @return the instance
   */
  public static ResolvedFxSingleBarrierOption of(ResolvedFxVanillaOption underlyingOption, Barrier barrier) {
    ResolvedFxOneTouchOption rebateResolved = ResolvedFxOneTouchOption.of(
        underlyingOption.getLongShort(),
        underlyingOption.getExpiry(),
        Payment.of(underlyingOption.getCounterCurrency(), 0d, underlyingOption.getUnderlying().getPaymentDate()),
        underlyingOption.getUnderlying().getCurrencyPair(), barrier);
    return new ResolvedFxSingleBarrierOption(underlyingOption, rebateResolved);
  }

  //-------------------------------------------------------------------------
  /**
   * Obtains the barrier. 
   * 
   * @return the barrier
   */
  public Barrier getBarrier() {
    return rebate.getBarrier();
  }

  /**
   * The premium is rebated or not. 
   * 
   * @return true if rebated, false otherwise
   */
  public boolean hasRebate() {
    return rebate.getNotional() != 0d ? true : false;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedFxSingleBarrierOption}.
   * @return the meta-bean, not null
   */
  public static ResolvedFxSingleBarrierOption.Meta meta() {
    return ResolvedFxSingleBarrierOption.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedFxSingleBarrierOption.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private ResolvedFxSingleBarrierOption(
      ResolvedFxVanillaOption underlyingOption,
      ResolvedFxOneTouchOption rebate) {
    JodaBeanUtils.notNull(underlyingOption, "underlyingOption");
    this.underlyingOption = underlyingOption;
    this.rebate = rebate;
  }

  @Override
  public ResolvedFxSingleBarrierOption.Meta metaBean() {
    return ResolvedFxSingleBarrierOption.Meta.INSTANCE;
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
   * Gets the underlying FX vanilla option.
   * @return the value of the property, not null
   */
  public ResolvedFxVanillaOption getUnderlyingOption() {
    return underlyingOption;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the amount paid back to the option holder in case the option expires inactive.
   * <p>
   * The barrier definition for the barrier option is given within this field.
   * @return the value of the property
   */
  public ResolvedFxOneTouchOption getRebate() {
    return rebate;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedFxSingleBarrierOption other = (ResolvedFxSingleBarrierOption) obj;
      return JodaBeanUtils.equal(underlyingOption, other.underlyingOption) &&
          JodaBeanUtils.equal(rebate, other.rebate);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(underlyingOption);
    hash = hash * 31 + JodaBeanUtils.hashCode(rebate);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("ResolvedFxSingleBarrierOption{");
    buf.append("underlyingOption").append('=').append(underlyingOption).append(',').append(' ');
    buf.append("rebate").append('=').append(JodaBeanUtils.toString(rebate));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedFxSingleBarrierOption}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code underlyingOption} property.
     */
    private final MetaProperty<ResolvedFxVanillaOption> underlyingOption = DirectMetaProperty.ofImmutable(
        this, "underlyingOption", ResolvedFxSingleBarrierOption.class, ResolvedFxVanillaOption.class);
    /**
     * The meta-property for the {@code rebate} property.
     */
    private final MetaProperty<ResolvedFxOneTouchOption> rebate = DirectMetaProperty.ofImmutable(
        this, "rebate", ResolvedFxSingleBarrierOption.class, ResolvedFxOneTouchOption.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "underlyingOption",
        "rebate");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 87556658:  // underlyingOption
          return underlyingOption;
        case -934952029:  // rebate
          return rebate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ResolvedFxSingleBarrierOption> builder() {
      return new ResolvedFxSingleBarrierOption.Builder();
    }

    @Override
    public Class<? extends ResolvedFxSingleBarrierOption> beanType() {
      return ResolvedFxSingleBarrierOption.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code underlyingOption} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedFxVanillaOption> underlyingOption() {
      return underlyingOption;
    }

    /**
     * The meta-property for the {@code rebate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedFxOneTouchOption> rebate() {
      return rebate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 87556658:  // underlyingOption
          return ((ResolvedFxSingleBarrierOption) bean).getUnderlyingOption();
        case -934952029:  // rebate
          return ((ResolvedFxSingleBarrierOption) bean).getRebate();
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
   * The bean-builder for {@code ResolvedFxSingleBarrierOption}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<ResolvedFxSingleBarrierOption> {

    private ResolvedFxVanillaOption underlyingOption;
    private ResolvedFxOneTouchOption rebate;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 87556658:  // underlyingOption
          return underlyingOption;
        case -934952029:  // rebate
          return rebate;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 87556658:  // underlyingOption
          this.underlyingOption = (ResolvedFxVanillaOption) newValue;
          break;
        case -934952029:  // rebate
          this.rebate = (ResolvedFxOneTouchOption) newValue;
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
    public ResolvedFxSingleBarrierOption build() {
      return new ResolvedFxSingleBarrierOption(
          underlyingOption,
          rebate);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("ResolvedFxSingleBarrierOption.Builder{");
      buf.append("underlyingOption").append('=').append(JodaBeanUtils.toString(underlyingOption)).append(',').append(' ');
      buf.append("rebate").append('=').append(JodaBeanUtils.toString(rebate));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
