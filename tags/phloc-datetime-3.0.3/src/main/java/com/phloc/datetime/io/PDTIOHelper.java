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
package com.phloc.datetime.io;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.datetime.PDTFactory;
import com.phloc.datetime.format.PDTToString;

/**
 * Some date time specific routines especially helpful for IO.
 * 
 * @author philip
 */
@Immutable
public final class PDTIOHelper
{
  /** Date pattern suitable for generic sorting */
  public static final String PATTERN_DATE = "yyyyMMdd";
  /** Time pattern suitable for generic sorting */
  public static final String PATTERN_TIME = "HHmmss";
  /** Date and time pattern suitable for generic sorting */
  public static final String PATTERN_DATETIME = PATTERN_DATE + '_' + PATTERN_TIME;

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTIOHelper s_aInstance = new PDTIOHelper ();

  private PDTIOHelper ()
  {}

  /**
   * @return The current date time formatted for usage in a file name.
   */
  @Nonnull
  public static String getCurrentDateTimeForFilename ()
  {
    return getDateTimeForFilename (PDTFactory.getCurrentDateTime ());
  }

  /**
   * Get the passed date time formatted suitable for a file name.
   * 
   * @param aDT
   *        The date time to be formatted. May not be <code>null</code>.
   * @return The formatted string.
   */
  @Nonnull
  public static String getDateTimeForFilename (@Nonnull final DateTime aDT)
  {
    return PDTToString.getAsString (PATTERN_DATETIME, aDT);
  }

  /**
   * @return The current local date time formatted for usage in a file name.
   */
  @Nonnull
  public static String getCurrentLocalDateTimeForFilename ()
  {
    return getLocalDateTimeForFilename (PDTFactory.getCurrentLocalDateTime ());
  }

  /**
   * Get the passed local date time formatted suitable for a file name.
   * 
   * @param aDT
   *        The local date time to be formatted. May not be <code>null</code>.
   * @return The formatted string.
   */
  @Nonnull
  public static String getLocalDateTimeForFilename (@Nonnull final LocalDateTime aDT)
  {
    return PDTToString.getAsString (PATTERN_DATETIME, aDT);
  }

  /**
   * @return The current date formatted for usage in a file name.
   */
  public static String getCurrentDateForFilename ()
  {
    return getDateForFilename (PDTFactory.getCurrentLocalDate ());
  }

  /**
   * Get the passed date formatted suitable for a file name.
   * 
   * @param aLocalDate
   *        The date to be formatted. May not be <code>null</code>.
   * @return The formatted string.
   */
  public static String getDateForFilename (@Nonnull final LocalDate aLocalDate)
  {
    return PDTToString.getAsString (PATTERN_DATE, aLocalDate);
  }

  /**
   * @return The current time formatted for usage in a file name.
   */
  public static String getCurrentTimeForFilename ()
  {
    return getTimeForFilename (PDTFactory.getCurrentLocalTime ());
  }

  /**
   * Get the passed time formatted suitable for a file name.
   * 
   * @param aLocalTime
   *        The time to be formatted. May not be <code>null</code>.
   * @return The formatted string.
   */
  public static String getTimeForFilename (@Nonnull final LocalTime aLocalTime)
  {
    return PDTToString.getAsString (PATTERN_TIME, aLocalTime);
  }
}
