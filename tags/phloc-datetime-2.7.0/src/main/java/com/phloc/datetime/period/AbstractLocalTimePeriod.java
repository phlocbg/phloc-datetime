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

import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.phloc.commons.compare.CompareUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link ILocalTimePeriod} interface.
 * 
 * @author philip
 */
public abstract class AbstractLocalTimePeriod implements ILocalTimePeriod
{
  protected LocalTime m_aStartLocalTime;
  protected LocalTime m_aEndLocalTime;

  @Nullable
  public final LocalTime getStartLocalTime ()
  {
    return m_aStartLocalTime;
  }

  @Nullable
  public final LocalTime getEndLocalTime ()
  {
    return m_aEndLocalTime;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (m_aStartLocalTime == null)
      throw new IllegalStateException ("start local time is not specified!");
    if (m_aEndLocalTime == null)
      throw new IllegalStateException ("end local time is not specified!");
    return new Period (m_aStartLocalTime, m_aEndLocalTime);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AbstractLocalTimePeriod rhs = (AbstractLocalTimePeriod) o;
    return CompareUtils.nullSafeEquals (m_aStartLocalTime, rhs.m_aStartLocalTime) &&
           CompareUtils.nullSafeEquals (m_aEndLocalTime, rhs.m_aEndLocalTime);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aStartLocalTime).append (m_aEndLocalTime).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("startTime", m_aStartLocalTime)
                                       .append ("endTime", m_aEndLocalTime)
                                       .toString ();
  }
}
