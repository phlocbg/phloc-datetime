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
package com.phloc.datetime.calendar;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.phloc.commons.annotations.ReturnsMutableCopy;
import com.phloc.commons.collections.ArrayHelper;

public final class CalendarSettings
{
  private final int m_nStartHour;
  private final int m_nStartMinute;
  private final int m_nEndHour;
  private final int m_nEndMinute;
  private final Map <LocalDate, String> m_aHolidays = new HashMap <LocalDate, String> ();
  private int [] m_aWorkingDays = { DateTimeConstants.MONDAY,
                                   DateTimeConstants.TUESDAY,
                                   DateTimeConstants.WEDNESDAY,
                                   DateTimeConstants.THURSDAY,
                                   DateTimeConstants.FRIDAY };

  public CalendarSettings ()
  {
    this (6, 0, 20, 0);
  }

  public CalendarSettings (final int nStartHour, final int nStartMinute, final int nEndHour, final int nEndMinute)
  {
    if (nStartHour < 0 || nStartHour > 23)
      throw new IllegalArgumentException ("start hour is invalid: " + nStartHour);
    if (nStartMinute < 0 || nStartMinute > 59)
      throw new IllegalArgumentException ("start minute is invalid: " + nStartMinute);
    if (nEndHour < 0 || nEndHour > 23)
      throw new IllegalArgumentException ("end hour is invalid: " + nEndHour);
    if (nEndMinute < 0 || nEndMinute > 59)
      throw new IllegalArgumentException ("end minute is invalid: " + nEndMinute);
    m_nStartHour = nStartHour;
    m_nStartMinute = nStartMinute;
    m_nEndHour = nEndHour;
    m_nEndMinute = nEndMinute;
  }

  public void setWorkingDays (@Nonnull final int [] aWorkingDays)
  {
    if (aWorkingDays == null)
      throw new NullPointerException ("workingDays");
    if (aWorkingDays.length > DateTimeConstants.DAYS_PER_WEEK)
      throw new IllegalArgumentException ("too many working days");

    m_aWorkingDays = ArrayHelper.getCopy (aWorkingDays);
  }

  @Nonnull
  @ReturnsMutableCopy
  public int [] getWorkingDays ()
  {
    return ArrayHelper.getCopy (m_aWorkingDays);
  }

  public int getStartHour ()
  {
    return m_nStartHour;
  }

  public int getStartMinute ()
  {
    return m_nStartMinute;
  }

  public int getEndHour ()
  {
    return m_nEndHour;
  }

  public int getEndMinute ()
  {
    return m_nEndMinute;
  }

  public void setHolidays (final Map <LocalDate, String> aHolidays)
  {
    m_aHolidays.putAll (aHolidays);
  }

  public void setHoliday (final LocalDate aDay, final String sName)
  {
    m_aHolidays.put (aDay, sName);
  }

  public boolean isHoliday (final LocalDate aDay)
  {
    return getHoliday (aDay) != null;
  }

  public String getHoliday (final LocalDate aDay)
  {
    return m_aHolidays.get (aDay);
  }
}
