/**
 * Copyright (C) 2006-2015 phloc systems
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
import javax.annotation.concurrent.NotThreadSafe;

import org.joda.time.LocalDate;
import org.joda.time.Period;

import com.phloc.datetime.PDTFactory;

/**
 * Default implementation of the {@link ILocalDatePeriod} interface.
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public class LocalDatePeriod extends AbstractFlexiblePeriod <LocalDate> implements ILocalDatePeriod
{
  public LocalDatePeriod ()
  {
    this (null, null);
  }

  public LocalDatePeriod (@Nullable final LocalDate aStart)
  {
    this (aStart, null);
  }

  public LocalDatePeriod (@Nullable final LocalDate aStart, @Nullable final LocalDate aEnd)
  {
    super (aStart, aEnd);
  }

  public final boolean isValidFor (@Nonnull final LocalDate aDate)
  {
    if (aDate == null)
      throw new NullPointerException ("date");

    final LocalDate aStart = getStart ();
    if (aStart != null && aStart.isAfter (aDate))
      return false;
    final LocalDate aEnd = getEnd ();
    if (aEnd != null && aEnd.isBefore (aDate))
      return false;
    return true;
  }

  public final boolean isValidForNow ()
  {
    return isValidFor (PDTFactory.getCurrentLocalDate ());
  }

  public boolean canConvertToPeriod ()
  {
    return getStart () != null && getEnd () != null;
  }

  @Nonnull
  public final Period getAsPeriod ()
  {
    if (!canConvertToPeriod ())
      throw new IllegalStateException ("Cannot convert to a Period!");
    return new Period (getStart (), getEnd ());
  }
}
