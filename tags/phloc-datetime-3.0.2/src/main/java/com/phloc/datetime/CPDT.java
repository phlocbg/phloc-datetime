/**
 * Copyright (C) 2006-2013 phloc systems
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
package com.phloc.datetime;

import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.phloc.commons.annotations.PresentForCodeCoverage;

/**
 * Some date/time related constants.
 * 
 * @author philip
 */
@Immutable
public final class CPDT
{
  public static final int START_OF_WEEK_DAY = DateTimeConstants.MONDAY;
  public static final int END_OF_WEEK_DAY = DateTimeConstants.SUNDAY;

  public static final LocalDate NULL_LOCAL_DATE = PDTFactory.createLocalDate (1970, DateTimeConstants.JANUARY, 1);
  public static final LocalTime NULL_LOCAL_TIME = PDTFactory.createLocalTime (0, 0, 0);
  public static final LocalDateTime NULL_LOCAL_DATETIME = NULL_LOCAL_DATE.toLocalDateTime (NULL_LOCAL_TIME);
  public static final DateTime NULL_DATETIME = PDTFactory.createDateTimeFromMillis (0);
  public static final Period NULL_PERIOD = new Period ();
  public static final Duration NULL_DURATION = new Duration (0);

  public static final int LAST_JULIAN_YEAR = 1583;

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final CPDT s_aInstance = new CPDT ();

  private CPDT ()
  {}
}
