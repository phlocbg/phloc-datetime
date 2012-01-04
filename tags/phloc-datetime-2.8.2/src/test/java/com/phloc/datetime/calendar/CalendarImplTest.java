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

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link CalendarImpl}.
 * 
 * @author philip
 */
public final class CalendarImplTest implements ICalendarEntryProvider
{
  private final CalendarImpl m_aCalendar = new CalendarImpl ();

  private CalendarSettings _getTestCalendarSettings ()
  {
    final CalendarSettings aCalendarSettings = new CalendarSettings ();
    aCalendarSettings.setHoliday (PDTFactory.createLocalDate (2007, DateTimeConstants.OCTOBER, 26), "Nationalfeiertag");
    aCalendarSettings.setHoliday (PDTFactory.createLocalDate (2007, DateTimeConstants.OCTOBER, 19), "Heiliger Boris");
    return aCalendarSettings;
  }

  @Test
  public void testSetTimeFrame ()
  {
    m_aCalendar.goToday ();
    m_aCalendar.setViewMode (ECalendarView.MONTH);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.goToday ();
    m_aCalendar.setViewMode (ECalendarView.WEEK);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.goToday ();
    m_aCalendar.setViewMode (ECalendarView.DAY);
    assertNotNull (m_aCalendar.toString ());
  }

  @Test
  public void testRollMonth ()
  {
    m_aCalendar.goToday ();
    m_aCalendar.setViewMode (ECalendarView.MONTH);
    m_aCalendar.goToday ();
    m_aCalendar.roll (1);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.goToday ();
    m_aCalendar.roll (3);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.goToday ();
    m_aCalendar.roll (-20);
    assertNotNull (m_aCalendar.toString ());
  }

  @Test
  public void testRollWeek ()
  {
    m_aCalendar.setViewMode (ECalendarView.WEEK);

    m_aCalendar.roll (1);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.roll (3);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.roll (-20);
    assertNotNull (m_aCalendar.toString ());
  }

  @Test
  public void testRollDay ()
  {
    m_aCalendar.setViewMode (ECalendarView.DAY);

    m_aCalendar.roll (1);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.roll (3);
    assertNotNull (m_aCalendar.toString ());

    m_aCalendar.roll (-20);
    assertNotNull (m_aCalendar.toString ());
  }

  @Test
  public void testViewModeMonth ()
  {
    m_aCalendar.setViewMode (ECalendarView.MONTH);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.getView (aProcessor, this);
  }

  @Test
  public void testViewModeWeek ()
  {
    m_aCalendar.setViewMode (ECalendarView.WEEK);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.getView (aProcessor, this);
  }

  @Test
  public void testViewModeDay ()
  {
    m_aCalendar.setViewMode (ECalendarView.DAY);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.getView (aProcessor, this);
  }

  @Test
  public void testViewModeHoliday ()
  {
    m_aCalendar.setViewMode (ECalendarView.DAY);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.goTo (PDTFactory.createLocalDate (2007, DateTimeConstants.OCTOBER, 26));
    m_aCalendar.getView (aProcessor, this);
  }

  @Test
  public void testViewModeWorkDay ()
  {
    m_aCalendar.setViewMode (ECalendarView.DAY);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.goTo (PDTFactory.createLocalDate (2007, DateTimeConstants.OCTOBER, 25));
    m_aCalendar.getView (aProcessor, this);
  }

  @Test
  public void testViewModeFreeDay ()
  {
    m_aCalendar.setViewMode (ECalendarView.DAY);
    final MockCalendarHandler aProcessor = new MockCalendarHandler (_getTestCalendarSettings ());
    m_aCalendar.goTo (PDTFactory.createLocalDate (2007, DateTimeConstants.OCTOBER, 27));
    m_aCalendar.getView (aProcessor, this);
  }

  public List <ICalendarEntry> getCalendarItemsForTimeFrame (final LocalDate aFromDate, final LocalDate aToDate)
  {
    LocalDateTime aStartDate;
    LocalDateTime aEndDate;

    aStartDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 26, 10, 30, 0, 0);
    aEndDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 26, 15, 0, 0, 0);
    final MockCalenderEntry aCE1 = new MockCalenderEntry ("TestEntry 1", aStartDate, aEndDate, "-1-");

    aStartDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 27, 07, 0, 0, 0);
    aEndDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 27, 13, 30, 0, 0);
    final MockCalenderEntry aCE2 = new MockCalenderEntry ("TestEntry 2", aStartDate, aEndDate, "-2-");

    aStartDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 29, 16, 0, 0, 0);
    aEndDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 29, 18, 30, 0, 0);
    final MockCalenderEntry aCE3 = new MockCalenderEntry ("TestEntry 3", aStartDate, aEndDate, "-3-");

    aStartDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 4, 10, 0, 0, 0);
    aEndDate = PDTFactory.createLocalDateTime (2007, DateTimeConstants.OCTOBER, 31, 23, 30, 0, 0);
    final MockCalenderEntry aCE4 = new MockCalenderEntry ("TestEntry 4", aStartDate, aEndDate, "-4-");

    final List <ICalendarEntry> aEntries = new ArrayList <ICalendarEntry> ();
    aEntries.add (aCE1);
    aEntries.add (aCE2);
    aEntries.add (aCE3);
    aEntries.add (aCE4);
    return aEntries;
  }
}
