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

import java.util.List;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.annotations.OverrideOnDemand;
import com.phloc.datetime.PDTFactory;

public abstract class AbstractCalendarHandler implements ICalendarHandler
{
  protected LocalDate m_aSelectedDate = PDTFactory.getCurrentLocalDate ();
  protected final CalendarSettings m_aCalendarSettings;
  protected final LocalDate m_aToday = PDTFactory.getCurrentLocalDate ();

  public AbstractCalendarHandler (@Nullable final CalendarSettings aCalendarSettings)
  {
    m_aCalendarSettings = aCalendarSettings == null ? new CalendarSettings () : aCalendarSettings;
  }

  public void setSelectedDate (final LocalDate aSelectedDate)
  {
    m_aSelectedDate = aSelectedDate;
  }

  public LocalDate getSelectedDate ()
  {
    return m_aSelectedDate;
  }

  public final CalendarSettings getCalendarSettings ()
  {
    return m_aCalendarSettings;
  }

  @OverrideOnDemand
  public void onViewStart (final ECalendarView aView)
  {}

  @OverrideOnDemand
  public void onViewEnd (final ECalendarView eView)
  {}

  @OverrideOnDemand
  public void onMonthStart (final LocalDate aDate)
  {}

  @OverrideOnDemand
  public void onMonthEnd (final LocalDate aDate)
  {}

  @OverrideOnDemand
  public void onWeekStart (final LocalDate aDate)
  {}

  @OverrideOnDemand
  public void onWeekEnd (final LocalDate aDate)
  {}

  @OverrideOnDemand
  public void onDay (final LocalDate aDay, final List <ICalendarEntry> aEntries)
  {}
}
