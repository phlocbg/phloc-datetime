package com.phloc.datetime.period;

import javax.annotation.Nullable;

import com.phloc.commons.compare.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Abstract base implementation for {@link IFlexiblePeriod}.
 * 
 * @author philip
 * @param <DATATYPE>
 *        Date and time type
 */
public abstract class AbstractFlexiblePeriod <DATATYPE> implements IFlexiblePeriod <DATATYPE>
{
  protected DATATYPE m_aStart;
  protected DATATYPE m_aEnd;

  public AbstractFlexiblePeriod (@Nullable final DATATYPE aStart, @Nullable final DATATYPE aEnd)
  {
    setStart (aStart);
    setEnd (aEnd);
  }

  @Nullable
  public final DATATYPE getStart ()
  {
    return m_aStart;
  }

  public void setStart (@Nullable final DATATYPE aStart)
  {
    m_aStart = aStart;
  }

  public final DATATYPE getEnd ()
  {
    return m_aEnd;
  }

  public void setEnd (@Nullable final DATATYPE aEnd)
  {
    m_aEnd = aEnd;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (this == o)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractFlexiblePeriod <?> rhs = (AbstractFlexiblePeriod <?>) o;
    return EqualsUtils.nullSafeEquals (m_aStart, rhs.m_aStart) && EqualsUtils.nullSafeEquals (m_aEnd, rhs.m_aEnd);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStart).append (m_aEnd).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("start", m_aStart).append ("end", m_aEnd).toString ();
  }
}
