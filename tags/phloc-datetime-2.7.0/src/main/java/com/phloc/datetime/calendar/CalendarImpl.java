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
package com.phloc.datetime.calendar;

import org.joda.time.LocalDate;
import org.joda.time.MutableDateTime;

import com.phloc.commons.collections.pair.IReadonlyPair;
import com.phloc.datetime.PDTUtils;

public final class CalendarImpl extends AbstractCalendar
{
  private ICalendarHandler m_aProcessor;

  public CalendarImpl ()
  {}

  /**
   * Creates a new GenDBUIControlCalendar and initializes it with the passed
   * View Mode.
   * 
   * @param aViewMode
   *          The view mode to use.
   */
  public CalendarImpl (final ECalendarView aViewMode)
  {
    super (aViewMode);
  }

  /**
   * Request a new view using the passed calendar processor. The view will be
   * generated in the currently set view mode.
   * 
   * @param aProcessor
   *          processor to use
   * @param aEntryProvider
   *          the calendar entry provider
   */
  public void getView (final ICalendarHandler aProcessor, final ICalendarEntryProvider aEntryProvider)
  {
    final MutableDateTime aTmp = m_aCalendar;
    m_aProcessor = aProcessor;
    m_aProcessor.setSelectedDate (m_aCalendar.toDateTime ().toLocalDate ());
    m_aEntryProvider = aEntryProvider;
    m_aProcessor.onViewStart (getViewMode ());
    if (getViewMode () == ECalendarView.DAY)
      getViewDay (true);
    else
      if (getViewMode () == ECalendarView.WEEK)
        getViewWeek (true);
      else
        if (getViewMode () == ECalendarView.MONTH)
          getViewMonth (true);
    m_aProcessor.onViewEnd (getViewMode ());
    m_aCalendar = aTmp;
  }

  /**
   * Request a new view using the passed calendar processor. The view will be
   * generated in the passed view mode.
   * 
   * @param aProcessor
   *          processor to use
   * @param aEntryProvider
   *          entry provider
   * @param eViewMode
   *          view mode to use
   */
  public void getView (final ICalendarHandler aProcessor,
                       final ICalendarEntryProvider aEntryProvider,
                       final ECalendarView eViewMode)
  {
    setViewMode (eViewMode);
    getView (aProcessor, aEntryProvider);
  }

  protected void getViewMonth (final boolean bSetTimeFrame)
  {
    setTimeFrameMonth (bSetTimeFrame);
    goToStartOfMonth ();
    m_aProcessor.onMonthStart (m_aCalendar.toDateTime ().toLocalDate ());

    final IReadonlyPair <Integer, Integer> aWeeks = PDTUtils.getWeeksOfMonth (m_aCalendar.toDateTime ());
    final int nStartWeek = aWeeks.getFirst ().intValue ();
    final int nEndWeek = aWeeks.getSecond ().intValue ();
    final int nMonth = m_aCalendar.getMonthOfYear ();
    final int nYear = m_aCalendar.getYear ();
    for (int nWeek = nStartWeek; nWeek <= nEndWeek; nWeek++)
    {
      getViewWeek (false);
      if (m_aCalendar.dayOfMonth ().getMaximumValue () == m_aCalendar.getDayOfMonth () ||
          nMonth != m_aCalendar.getMonthOfYear () ||
          (nYear != m_aCalendar.getYear () && nWeek > 1))
        break;
      rollWeeks (1, false);
    }
    m_aProcessor.onMonthEnd (m_aCalendar.toDateTime ().toLocalDate ());
  }

  protected void getViewWeek (final boolean bSetTimeFrame)
  {
    setTimeFrameWeek (bSetTimeFrame);
    // go to end of week to get the next year in case of a year spanning week
    // (the week containing 31.12.2007 and 01.01.2008 must be week 1 2008)
    goToEndOfWeek ();

    m_aProcessor.onWeekStart (m_aCalendar.toDateTime ().toLocalDate ());

    final int nStartDay = m_aCalendar.dayOfWeek ().getMinimumValue ();
    final int nEndDay = m_aCalendar.dayOfWeek ().getMaximumValue ();
    goToStartOfWeek ();
    for (int nDay = nStartDay; nDay <= nEndDay; nDay++)
    {
      getViewDay (false);
      if (nDay < nEndDay)
        rollDays (1, false);
    }
    m_aProcessor.onWeekEnd (m_aCalendar.toDateTime ().toLocalDate ());
  }

  protected void getViewDay (final boolean bSetTimeFrame)
  {
    setTimeFrameDay (bSetTimeFrame);
    goToStartOfDay ();

    final LocalDate aCurDate = m_aCalendar.toDateTime ().toLocalDate ();
    if (isInTimeFrame (aCurDate))
      m_aProcessor.onDay (aCurDate, getEntries ());
  }

  @Override
  public String toString ()
  {
    final StringBuilder sOut = new StringBuilder ("entries{");
    if (m_aEntryProvider != null)
    {
      for (final ICalendarEntry aEntry : m_aEntryProvider.getCalendarItemsForTimeFrame (m_aTimeFrameStart,
                                                                                        m_aTimeFrameEnd))
      {
        if (isInTimeFrame (aEntry, false))
          sOut.append ('\n').append (aEntry.getDisplayName ()).append ('\n');
      }
    }
    sOut.append ('}');
    return sOut.toString ();
  }
}
