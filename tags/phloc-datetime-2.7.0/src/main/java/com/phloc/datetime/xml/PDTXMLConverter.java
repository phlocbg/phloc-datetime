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
package com.phloc.datetime.xml;

import java.util.GregorianCalendar;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.exceptions.InitializationException;
import com.phloc.datetime.PDTFactory;

/**
 * Utility class for XML date/time data type handling.
 * 
 * @author philip
 */
@Immutable
public final class PDTXMLConverter
{
  private static final DatatypeFactory s_aDTFactory;

  static
  {
    try
    {
      // required for the Gregorian calendar
      s_aDTFactory = DatatypeFactory.newInstance ();
    }
    catch (final DatatypeConfigurationException ex)
    {
      throw new InitializationException ("Failed to init DataTypeFactory", ex);
    }
  }

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTXMLConverter s_aInstance = new PDTXMLConverter ();

  private PDTXMLConverter ()
  {}

  /**
   * @return A new XML calendar instance, with all fields uninitialized.
   */
  @Nonnull
  public static XMLGregorianCalendar createNewCalendar ()
  {
    return s_aDTFactory.newXMLGregorianCalendar ();
  }

  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDate (@Nullable final LocalDate aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarDate (aBase.getYear (),
                                                                            aBase.getMonthOfYear (),
                                                                            aBase.getDayOfMonth (),
                                                                            0);
  }

  @Nullable
  public static XMLGregorianCalendar getXMLCalendarTime (@Nullable final LocalTime aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarTime (aBase.getHourOfDay (),
                                                                            aBase.getMinuteOfHour (),
                                                                            aBase.getSecondOfMinute (),
                                                                            aBase.getMillisOfSecond (),
                                                                            0);
  }

  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDateTime (@Nullable final LocalDateTime aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendar (aBase.getYear (),
                                                                        aBase.getMonthOfYear (),
                                                                        aBase.getDayOfMonth (),
                                                                        aBase.getHourOfDay (),
                                                                        aBase.getMinuteOfHour (),
                                                                        aBase.getSecondOfMinute (),
                                                                        aBase.getMillisOfSecond (),
                                                                        0);
  }

  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDateTime (@Nullable final DateTime aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendar (aBase.toGregorianCalendar ());
  }

  @Nonnull
  public static GregorianCalendar convert (final XMLGregorianCalendar aCal)
  {
    return aCal.toGregorianCalendar (aCal.getTimeZone (aCal.getTimezone ()), null, null);
  }

  @Nullable
  public static LocalDate getLocalDate (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalDate (convert (aCal));
  }

  @Nullable
  public static LocalTime getLocalTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalTime (convert (aCal));
  }

  @Nullable
  public static LocalDateTime getLocalDateTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalDateTime (convert (aCal));
  }

  @Nullable
  public static DateTime getDateTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createDateTime (convert (aCal));
  }
}
