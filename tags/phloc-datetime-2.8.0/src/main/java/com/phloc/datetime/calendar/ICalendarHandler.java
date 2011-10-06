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

import org.joda.time.LocalDate;


public interface ICalendarHandler
{
  void onDay (LocalDate aDay, List <ICalendarEntry> aEntries);

  void onWeekStart (LocalDate aDate);

  void onWeekEnd (LocalDate aDate);

  void onMonthStart (LocalDate aDate);

  void onMonthEnd (LocalDate aDate);

  void onViewStart (ECalendarView aView);

  void onViewEnd (ECalendarView aView);

  CalendarSettings getCalendarSettings ();

  void setSelectedDate (LocalDate aDate);

  LocalDate getSelectedDate ();
}
