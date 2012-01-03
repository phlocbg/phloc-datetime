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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import com.phloc.commons.compare.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link ILocalDateTimePeriod} interface.
 * 
 * @author philip
 */
public abstract class AbstractLocalDateTimePeriod implements ILocalDateTimePeriod
{
  protected LocalDateTime m_aStartLocalDateTime;
  protected LocalDateTime m_aEndLocalDateTime;

  @Nullable
  public final LocalDateTime getStartLocalDateTime ()
  {
    return m_aStartLocalDateTime;
  }

  @Nullable
  public final LocalDateTime getEndLocalDateTime ()
  {
    return m_aEndLocalDateTime;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (m_aStartLocalDateTime == null)
      throw new IllegalStateException ("start date time is not specified!");
    if (m_aEndLocalDateTime == null)
      throw new IllegalStateException ("end date time is not specified!");
    return new Period (m_aStartLocalDateTime, m_aEndLocalDateTime);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractLocalDateTimePeriod rhs = (AbstractLocalDateTimePeriod) o;
    return EqualsUtils.nullSafeEquals (m_aStartLocalDateTime, rhs.m_aStartLocalDateTime) &&
           EqualsUtils.nullSafeEquals (m_aEndLocalDateTime, rhs.m_aEndLocalDateTime);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStartLocalDateTime).append (m_aEndLocalDateTime).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("startDT", m_aStartLocalDateTime)
                                       .append ("endDT", m_aEndLocalDateTime)
                                       .toString ();
  }
}
