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

import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.MutableDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.datetime.PDTFactory;

public abstract class AbstractCalendar
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractCalendar.class);

  protected LocalDate m_aTimeFrameStart;
  protected LocalDate m_aTimeFrameEnd;
  protected LocalDate m_aTimeFramePointerStart;
  protected LocalDate m_aTimeFramePointerEnd;

  protected MutableDateTime m_aCalendar = PDTFactory.getCurrentMutableDateTime ();
  private ECalendarView m_eViewMode;
  protected ICalendarEntryProvider m_aEntryProvider;

  public AbstractCalendar ()
  {
    this (ECalendarView.WEEK);
  }

  /**
   * Creates a new GenDBUIControlCalendar and initializes it with the passed
   * View Mode
   *
   * @param aViewMode
   *        view mode to use
   */
  public AbstractCalendar (final ECalendarView aViewMode)
  {
    m_eViewMode = aViewMode;
  }

  /**
   * Get the current view mode {@link ECalendarView}.
   *
   * @return the current view mode ({@link ECalendarView})
   */
  public ECalendarView getViewMode ()
  {
    return m_eViewMode;
  }

  /**
   * Set current view mode.
   *
   * @param eViewMode
   *        view mode to use
   */
  public void setViewMode (final ECalendarView eViewMode)
  {
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Switching ViewMode to " + eViewMode);
    m_eViewMode = eViewMode;
    if (m_eViewMode == ECalendarView.DAY)
      setTimeFrameDay (true);
    else
      if (m_eViewMode == ECalendarView.WEEK)
        setTimeFrameWeek (true);
      else
        if (m_eViewMode == ECalendarView.MONTH)
          setTimeFrameMonth (true);
  }

  public int getCurrentYear ()
  {
    return m_aCalendar.getYear ();
  }

  protected List <ICalendarEntry> getEntries ()
  {
    if (m_aEntryProvider == null)
      return null;

    return m_aEntryProvider.getCalendarItemsForTimeFrame (m_aTimeFramePointerStart, m_aTimeFramePointerEnd);
  }

  protected boolean isInTimeFrame (final ICalendarEntry aEntry, final boolean bUsePointer)
  {
    final LocalDateTime aEntryStart = aEntry.getStartDate ();
    final LocalDateTime aEntryEnd = aEntry.getEndDate ();

    if (bUsePointer)
    {
      // starts within time frame
      if ((m_aTimeFramePointerStart == null || aEntryStart.isAfter (m_aTimeFramePointerStart)) &&
          (m_aTimeFramePointerEnd == null || aEntryStart.isBefore (m_aTimeFramePointerEnd)))
        return true;

      // ends within time frame
      if ((m_aTimeFramePointerStart == null || aEntryEnd.isAfter (m_aTimeFramePointerStart)) &&
          (m_aTimeFramePointerEnd == null || aEntryEnd.isBefore (m_aTimeFramePointerEnd)))
        return true;

      // spans time frame
      if ((m_aTimeFramePointerStart == null || aEntryStart.isBefore (m_aTimeFramePointerStart)) &&
          (m_aTimeFramePointerEnd == null || aEntryEnd.isAfter (m_aTimeFramePointerEnd)))
        return true;
    }
    else
    {
      // starts within time frame
      if ((m_aTimeFrameStart == null || aEntryStart.isAfter (m_aTimeFrameStart)) &&
          (m_aTimeFrameEnd == null || aEntryStart.isBefore (m_aTimeFrameEnd)))
        return true;

      // ends within time frame
      if ((m_aTimeFrameStart == null || aEntryEnd.isAfter (m_aTimeFrameStart)) &&
          (m_aTimeFrameEnd == null || aEntryEnd.isBefore (m_aTimeFrameEnd)))
        return true;

      // spans time frame
      if ((m_aTimeFrameStart == null || aEntryStart.isBefore (m_aTimeFrameStart)) &&
          (m_aTimeFrameEnd == null || aEntryEnd.isAfter (m_aTimeFrameEnd)))
        return true;
    }
    return false;
  }

  protected boolean isInTimeFrame (final LocalDate aDate)
  {
    // no time frames set -> is always in frame
    if (m_aTimeFrameStart == null && m_aTimeFrameEnd == null)
      return true;

    // is date before the end of a frame which is open downwards
    if (m_aTimeFrameStart == null)
    {
      if (aDate.isBefore (m_aTimeFrameEnd))
        return true;
    }
    else
    {
      // is date after the start of a frame which is open upwards
      if (m_aTimeFrameEnd == null)
      {
        if (aDate.isAfter (m_aTimeFrameStart))
          return true;
      }
      else
      {
        // is date well in between the borders?
        if (aDate.isAfter (m_aTimeFrameStart) && aDate.isBefore (m_aTimeFrameEnd))
          return true;
      }
    }

    // is date exactly matching one of the borders?
    if (aDate.equals (m_aTimeFrameStart) || aDate.equals (m_aTimeFrameEnd))
      return true;

    return false;
  }

  /**
   * Sets the current time frame to start on the first day of the week currently
   * containing the pointer and to end on the respective last week day.
   *
   * @param bSetTimeFrame
   *        If true, the real time frame borders will be adapted, else only the
   *        temporary pointer borders
   */
  protected void setTimeFrameWeek (final boolean bSetTimeFrame)
  {
    final MutableDateTime aPointer = m_aCalendar;
    goToStartOfWeek ();
    _setTimeFrameStart (bSetTimeFrame);
    goToEndOfWeek ();
    _setTimeFrameEnd (bSetTimeFrame);
    m_aCalendar = aPointer;
  }

  protected void setTimeFrameMonth (final boolean bSetTimeFrame)
  {
    final MutableDateTime aPointer = m_aCalendar;
    s_aLogger.debug (aPointer.toString ());
    goToStartOfMonth ();
    _setTimeFrameStart (bSetTimeFrame);
    goToEndOfMonth ();
    _setTimeFrameEnd (bSetTimeFrame);
    m_aCalendar = aPointer;
  }

  protected void setTimeFrameDay (final boolean bSetTimeFrame)
  {
    final MutableDateTime aPointer = m_aCalendar;
    goToStartOfDay ();
    _setTimeFrameStart (bSetTimeFrame);
    goToEndOfDay ();
    _setTimeFrameEnd (bSetTimeFrame);
    goToStartOfDay ();
    m_aCalendar.setTime (aPointer);
  }

  private void _setTimeFrameStart (final boolean bSetTimeFrame)
  {
    m_aTimeFramePointerStart = m_aCalendar.toDateTime ().toLocalDate ();
    if (bSetTimeFrame)
      m_aTimeFrameStart = m_aTimeFramePointerStart;
  }

  private void _setTimeFrameEnd (final boolean bSetTimeFrame)
  {
    m_aTimeFramePointerEnd = m_aCalendar.toDateTime ().toLocalDate ();
    if (bSetTimeFrame)
      m_aTimeFrameEnd = m_aTimeFramePointerEnd;
  }

  public void roll (final int nCount)
  {
    if (m_eViewMode == ECalendarView.DAY)
      rollDays (nCount, true);
    else
      if (m_eViewMode == ECalendarView.WEEK)
        rollWeeks (nCount, true);
      else
        if (m_eViewMode == ECalendarView.MONTH)
          rollMonths (nCount, true);
  }

  protected void rollDays (final int nCount, final boolean bSetTimeFrame)
  {
    setTimeFrameDay (bSetTimeFrame);
    m_aCalendar.addDays (nCount);
  }

  protected void rollWeeks (final int nCount, final boolean bSetTimeFrame)
  {
    setTimeFrameWeek (bSetTimeFrame);
    m_aCalendar.addWeeks (nCount);
  }

  protected void rollMonths (final int nCount, final boolean bSetTimeFrame)
  {
    setTimeFrameMonth (bSetTimeFrame);
    m_aCalendar.addMonths (nCount);
  }

  protected void goToStartOfDay ()
  {
    m_aCalendar.setTime (m_aCalendar.hourOfDay ().getMinimumValue (),
                         m_aCalendar.minuteOfHour ().getMinimumValue (),
                         m_aCalendar.secondOfMinute ().getMinimumValue (),
                         m_aCalendar.millisOfSecond ().getMinimumValue ());
  }

  protected void goToEndOfDay ()
  {
    m_aCalendar.setTime (m_aCalendar.hourOfDay ().getMaximumValue (),
                         m_aCalendar.minuteOfHour ().getMaximumValue (),
                         m_aCalendar.secondOfMinute ().getMaximumValue (),
                         m_aCalendar.millisOfSecond ().getMaximumValue ());
  }

  protected void goToStartOfWeek ()
  {
    m_aCalendar.setDayOfWeek (m_aCalendar.dayOfWeek ().getMinimumValue ());
    goToStartOfDay ();
  }

  protected void goToEndOfWeek ()
  {
    m_aCalendar.setDayOfWeek (m_aCalendar.dayOfWeek ().getMaximumValue ());
    goToEndOfDay ();
  }

  protected void goToStartOfMonth ()
  {
    m_aCalendar.setDayOfMonth (m_aCalendar.dayOfMonth ().getMinimumValue ());
    goToStartOfDay ();
  }

  protected void goToEndOfMonth ()
  {
    m_aCalendar.setDayOfMonth (m_aCalendar.dayOfMonth ().getMaximumValue ());
    goToEndOfDay ();
  }

  protected void goToEndOfYear ()
  {
    m_aCalendar.setDayOfYear (m_aCalendar.dayOfYear ().getMaximumValue ());
    goToEndOfDay ();
  }

  /**
   * Sets the internal pointer to now.
   */
  public void goToday ()
  {
    m_aCalendar = PDTFactory.getCurrentMutableDateTime ();
  }

  /**
   * Sets the internal pointer to the passed date.
   *
   * @param aDay
   *        may not be null
   */
  public void goTo (final LocalDate aDay)
  {
    m_aCalendar.setDate (aDay.getYear (), aDay.getMonthOfYear (), aDay.getDayOfMonth ());
  }

  /**
   * Get the year to which the current calendar week is counted. A week
   * containing the first day of a year will always be counted to that year and
   * not to the previous one.
   *
   * @return the year (e.g. 2007) to which this week belongs
   */
  public int getYearOfWeek ()
  {
    return m_aCalendar.getWeekOfWeekyear ();
  }

  /**
   * Get the month to which the current week is counted. A week containing the
   * first day of a year will always be counted to the first month of the
   * starting year. Otherwise the month will be the one holding the majority of
   * days in the current week.
   *
   * @return the month (e.g. 10) starting with 0 to which this week belongs.
   */
  public int getMonthOfWeek ()
  {
    // trivial case
    if (m_aCalendar.getWeekOfWeekyear () == 1)
      return DateTimeConstants.JANUARY;

    // evaluate the number of days belonging to the current month and the
    // next/previous
    int nMonth = m_aCalendar.getMonthOfYear ();
    {
      final MutableDateTime aPointer = m_aCalendar;

      // store a calendar at the start of the week
      goToStartOfWeek ();
      final int nMonthStart = m_aCalendar.getMonthOfYear ();
      final int nDaysInCurrentMonth = m_aCalendar.dayOfMonth ().getMaximumValue () - m_aCalendar.getDayOfMonth ();
      goToEndOfWeek ();
      final int nMonthEnd = m_aCalendar.getMonthOfYear ();
      if (nMonthStart != nMonthEnd)
        nMonth = nDaysInCurrentMonth > 2 ? nMonthStart : nMonthEnd;
      m_aCalendar = aPointer;
    }
    return nMonth;
  }
}
