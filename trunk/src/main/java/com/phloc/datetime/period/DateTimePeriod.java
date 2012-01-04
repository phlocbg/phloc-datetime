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

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

import com.phloc.datetime.PDTFactory;

/**
 * Default implementation of the {@link IDateTimePeriod} interface.
 * 
 * @author philip
 */
public final class DateTimePeriod extends AbstractFlexiblePeriod <DateTime> implements IDateTimePeriod
{
  public DateTimePeriod ()
  {
    this (null, null);
  }

  public DateTimePeriod (@Nullable final DateTime aStart)
  {
    this (aStart, null);
  }

  public DateTimePeriod (@Nullable final DateTime aStart, @Nullable final DateTime aEnd)
  {
    super (aStart, aEnd);
  }

  public final boolean isValidFor (@Nonnull final DateTime aDate)
  {
    if (aDate == null)
      throw new NullPointerException ("date");

    if (m_aStart != null && m_aStart.isAfter (aDate))
      return false;
    if (m_aEnd != null && m_aEnd.isBefore (aDate))
      return false;
    return true;
  }

  public final boolean isValidForNow ()
  {
    return isValidFor (PDTFactory.getCurrentDateTime ());
  }

  public boolean canConvertToPeriod ()
  {
    return m_aStart != null && m_aEnd != null;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (!canConvertToPeriod ())
      throw new IllegalStateException ("Cannot convert to a Period!");
    return new Period (m_aStart, m_aEnd);
  }

  public boolean canConvertToInterval ()
  {
    return m_aStart != null && m_aEnd != null;
  }

  @Nonnull
  public Interval getAsInterval ()
  {
    if (!canConvertToInterval ())
      throw new IllegalStateException ("Cannot convert to an Interval!");
    return new Interval (m_aStart, m_aEnd);
  }
}
