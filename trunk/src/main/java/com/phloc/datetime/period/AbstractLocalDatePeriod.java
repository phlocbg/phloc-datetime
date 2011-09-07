/**
 * Copyright (C) 2006-2011 phloc systems
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.phloc.commons.compare.CompareUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link ILocalDatePeriod} interface.
 * 
 * @author philip
 */
public abstract class AbstractLocalDatePeriod implements ILocalDatePeriod
{
  protected LocalDate m_aStartLocalDate;
  protected LocalDate m_aEndLocalDate;

  @Nullable
  public final LocalDate getStartLocalDate ()
  {
    return m_aStartLocalDate;
  }

  @Nullable
  public final LocalDate getEndLocalDate ()
  {
    return m_aEndLocalDate;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (m_aStartLocalDate == null)
      throw new IllegalStateException ("start local date is not specified!");
    if (m_aEndLocalDate == null)
      throw new IllegalStateException ("end local date is not specified!");
    return new Period (m_aStartLocalDate, m_aEndLocalDate);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractLocalDatePeriod rhs = (AbstractLocalDatePeriod) o;
    return CompareUtils.nullSafeEquals (m_aStartLocalDate, rhs.m_aStartLocalDate) &&
           CompareUtils.nullSafeEquals (m_aEndLocalDate, rhs.m_aEndLocalDate);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStartLocalDate).append (m_aEndLocalDate).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("startDate", m_aStartLocalDate)
                                       .append ("endDate", m_aEndLocalDate)
                                       .toString ();
  }
}
