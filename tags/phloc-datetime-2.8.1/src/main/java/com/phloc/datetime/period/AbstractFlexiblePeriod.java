/**
 * Copyright (C) 2006-2012 phloc systems
 * http://www.phloc.com
 * office[at]phloc[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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