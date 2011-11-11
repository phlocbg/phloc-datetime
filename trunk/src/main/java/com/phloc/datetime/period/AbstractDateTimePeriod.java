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

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.phloc.commons.compare.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link IDateTimePeriod} interface.
 * 
 * @author philip
 */
public abstract class AbstractDateTimePeriod implements IDateTimePeriod
{
  protected DateTime m_aStartDateTime;
  protected DateTime m_aEndDateTime;

  @Nullable
  public final DateTime getStartDateTime ()
  {
    return m_aStartDateTime;
  }

  @Nullable
  public final DateTime getEndDateTime ()
  {
    return m_aEndDateTime;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (m_aStartDateTime == null)
      throw new IllegalStateException ("start date time is not specified!");
    if (m_aEndDateTime == null)
      throw new IllegalStateException ("end date time is not specified!");
    return new Period (m_aStartDateTime, m_aEndDateTime);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractDateTimePeriod rhs = (AbstractDateTimePeriod) o;
    return EqualsUtils.nullSafeEquals (m_aStartDateTime, rhs.m_aStartDateTime) &&
           EqualsUtils.nullSafeEquals (m_aEndDateTime, rhs.m_aEndDateTime);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStartDateTime).append (m_aEndDateTime).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("startDT", m_aStartDateTime)
                                       .append ("endDT", m_aEndDateTime)
                                       .toString ();
  }
}
