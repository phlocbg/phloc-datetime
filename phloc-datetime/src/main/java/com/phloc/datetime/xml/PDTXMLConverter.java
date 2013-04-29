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
package com.phloc.datetime.xml;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.phloc.commons.CGlobal;
import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.exceptions.InitializationException;
import com.phloc.datetime.PDTFactory;

/**
 * Utility class for XML date/time data type handling.
 * 
 * @author Philip Helger
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
   * Get the timezone offset to UTC of the passed calendar in minutes to be used
   * in {@link XMLGregorianCalendar}.
   * 
   * @param aCalendar
   *        The calendar to use. May not be <code>null</code>.
   * @return 0 for no offset to UTC, the minutes otherwise. Usually in 60minutes
   *         steps :)
   */
  public static int getTimezoneOffsetInMinutes (@Nonnull final Calendar aCalendar)
  {
    final int nOffsetInMillis = aCalendar.getTimeZone ().getOffset (aCalendar.getTimeInMillis ());
    return nOffsetInMillis / (int) CGlobal.MILLISECONDS_PER_MINUTE;
  }

  /**
   * Get the passed date as {@link GregorianCalendar}.
   * 
   * @param aDate
   *        The source date. May be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static GregorianCalendar getCalendar (@Nonnull final Date aDate)
  {
    final GregorianCalendar aCalendar = new GregorianCalendar ();
    aCalendar.setTime (aDate);
    return aCalendar;
  }

  /**
   * Get the passed milli seconds as {@link GregorianCalendar}.
   * 
   * @param nMillis
   *        Milli seconds since 1.1.1970
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static GregorianCalendar getCalendar (final long nMillis)
  {
    final GregorianCalendar aCalendar = new GregorianCalendar ();
    aCalendar.setTimeInMillis (nMillis);
    return aCalendar;
  }

  /**
   * @return A new XML calendar instance, with all fields uninitialized. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static XMLGregorianCalendar createNewCalendar ()
  {
    return s_aDTFactory.newXMLGregorianCalendar ();
  }

  /**
   * Get the current date as {@link XMLGregorianCalendar}.
   * 
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarDateNow ()
  {
    return getXMLCalendarDate (PDTFactory.getCurrentLocalDate ());
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} date (without a
   * time).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDate (@Nullable final LocalDate aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarDate (aBase.getYear (),
                                                                            aBase.getMonthOfYear (),
                                                                            aBase.getDayOfMonth (),
                                                                            0);
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} date (without a
   * time).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDate (@Nullable final Date aBase)
  {
    return aBase == null ? null : getXMLCalendarDate (getCalendar (aBase));
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} date (without a
   * time).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDate (@Nullable final GregorianCalendar aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarDate (aBase.get (Calendar.YEAR),
                                                                            aBase.get (Calendar.MONTH),
                                                                            aBase.get (Calendar.DAY_OF_MONTH),
                                                                            getTimezoneOffsetInMinutes (aBase));
  }

  /**
   * <p>
   * Create a Java representation of XML Schema builtin datatype
   * <code>date</code> or <code>g*</code>.
   * </p>
   * <p>
   * For example, an instance of <code>gYear</code> can be created invoking this
   * factory with <code>month</code> and <code>day</code> parameters set to
   * {@link DatatypeConstants#FIELD_UNDEFINED}.
   * </p>
   * <p>
   * A {@link DatatypeConstants#FIELD_UNDEFINED} value indicates that field is
   * not set.
   * </p>
   * 
   * @param nYear
   *        Year to be created.
   * @param nMonth
   *        Month to be created.
   * @param nDay
   *        Day to be created.
   * @return <code>XMLGregorianCalendar</code> created from parameter values.
   * @see DatatypeConstants#FIELD_UNDEFINED
   * @throws IllegalArgumentException
   *         If any individual parameter's value is outside the maximum value
   *         constraint for the field as determined by the Date/Time Data
   *         Mapping table in {@link XMLGregorianCalendar} or if the composite
   *         values constitute an invalid <code>XMLGregorianCalendar</code>
   *         instance as determined by {@link XMLGregorianCalendar#isValid()}.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarDate (final int nYear, final int nMonth, final int nDay)
  {
    return getXMLCalendarDate (nYear, nMonth, nDay, DatatypeConstants.FIELD_UNDEFINED);
  }

  /**
   * <p>
   * Create a Java representation of XML Schema builtin datatype
   * <code>date</code> or <code>g*</code>.
   * </p>
   * <p>
   * For example, an instance of <code>gYear</code> can be created invoking this
   * factory with <code>month</code> and <code>day</code> parameters set to
   * {@link DatatypeConstants#FIELD_UNDEFINED}.
   * </p>
   * <p>
   * A {@link DatatypeConstants#FIELD_UNDEFINED} value indicates that field is
   * not set.
   * </p>
   * 
   * @param nYear
   *        Year to be created.
   * @param nMonth
   *        Month to be created.
   * @param nDay
   *        Day to be created.
   * @param nTimezone
   *        Offset in minutes. {@link DatatypeConstants#FIELD_UNDEFINED}
   *        indicates optional field is not set.
   * @return <code>XMLGregorianCalendar</code> created from parameter values.
   * @see DatatypeConstants#FIELD_UNDEFINED
   * @throws IllegalArgumentException
   *         If any individual parameter's value is outside the maximum value
   *         constraint for the field as determined by the Date/Time Data
   *         Mapping table in {@link XMLGregorianCalendar} or if the composite
   *         values constitute an invalid <code>XMLGregorianCalendar</code>
   *         instance as determined by {@link XMLGregorianCalendar#isValid()}.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarDate (final int nYear,
                                                         final int nMonth,
                                                         final int nDay,
                                                         final int nTimezone)
  {
    return s_aDTFactory.newXMLGregorianCalendarDate (nYear, nMonth, nDay, nTimezone);
  }

  /**
   * Get the current time as {@link XMLGregorianCalendar}.
   * 
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarTimeNow ()
  {
    return getXMLCalendarTime (PDTFactory.getCurrentLocalTime ());
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} time (without a
   * date).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarTime (@Nullable final LocalTime aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarTime (aBase.getHourOfDay (),
                                                                            aBase.getMinuteOfHour (),
                                                                            aBase.getSecondOfMinute (),
                                                                            aBase.getMillisOfSecond (),
                                                                            0);
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} time (without a
   * date).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarTime (@Nullable final Date aBase)
  {
    return aBase == null ? null : getXMLCalendarTime (getCalendar (aBase));
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} time (without a
   * date).
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarTime (@Nullable final GregorianCalendar aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendarTime (aBase.get (Calendar.HOUR_OF_DAY),
                                                                            aBase.get (Calendar.MINUTE),
                                                                            aBase.get (Calendar.SECOND),
                                                                            aBase.get (Calendar.MILLISECOND),
                                                                            getTimezoneOffsetInMinutes (aBase));
  }

  /**
   * <p>
   * Create a Java representation of XML Schema builtin datatype
   * <code>date</code> or <code>g*</code>.
   * </p>
   * <p>
   * For example, an instance of <code>gYear</code> can be created invoking this
   * factory with <code>month</code> and <code>day</code> parameters set to
   * {@link DatatypeConstants#FIELD_UNDEFINED}.
   * </p>
   * <p>
   * A {@link DatatypeConstants#FIELD_UNDEFINED} value indicates that field is
   * not set.
   * </p>
   * 
   * @param nHour
   *        Hour to be created.
   * @param nMinute
   *        Minute to be created.
   * @param nSecond
   *        Second to be created.
   * @param nMilliSecond
   *        Milli second to be created.
   * @return <code>XMLGregorianCalendar</code> created from parameter values.
   * @see DatatypeConstants#FIELD_UNDEFINED
   * @throws IllegalArgumentException
   *         If any individual parameter's value is outside the maximum value
   *         constraint for the field as determined by the Date/Time Data
   *         Mapping table in {@link XMLGregorianCalendar} or if the composite
   *         values constitute an invalid <code>XMLGregorianCalendar</code>
   *         instance as determined by {@link XMLGregorianCalendar#isValid()}.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarTime (final int nHour,
                                                         final int nMinute,
                                                         final int nSecond,
                                                         final int nMilliSecond)
  {
    return getXMLCalendarTime (nHour, nMinute, nSecond, nMilliSecond, DatatypeConstants.FIELD_UNDEFINED);
  }

  /**
   * <p>
   * Create a Java representation of XML Schema builtin datatype
   * <code>date</code> or <code>g*</code>.
   * </p>
   * <p>
   * For example, an instance of <code>gYear</code> can be created invoking this
   * factory with <code>month</code> and <code>day</code> parameters set to
   * {@link DatatypeConstants#FIELD_UNDEFINED}.
   * </p>
   * <p>
   * A {@link DatatypeConstants#FIELD_UNDEFINED} value indicates that field is
   * not set.
   * </p>
   * 
   * @param nHour
   *        Hour to be created.
   * @param nMinute
   *        Minute to be created.
   * @param nSecond
   *        Second to be created.
   * @param nMilliSecond
   *        Milli second to be created.
   * @param nTimezone
   *        Offset in minutes. {@link DatatypeConstants#FIELD_UNDEFINED}
   *        indicates optional field is not set.
   * @return <code>XMLGregorianCalendar</code> created from parameter values.
   * @see DatatypeConstants#FIELD_UNDEFINED
   * @throws IllegalArgumentException
   *         If any individual parameter's value is outside the maximum value
   *         constraint for the field as determined by the Date/Time Data
   *         Mapping table in {@link XMLGregorianCalendar} or if the composite
   *         values constitute an invalid <code>XMLGregorianCalendar</code>
   *         instance as determined by {@link XMLGregorianCalendar#isValid()}.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarTime (final int nHour,
                                                         final int nMinute,
                                                         final int nSecond,
                                                         final int nMilliSecond,
                                                         final int nTimezone)
  {
    return s_aDTFactory.newXMLGregorianCalendarTime (nHour, nMinute, nSecond, nMilliSecond, nTimezone);
  }

  /**
   * Get the current date and time as {@link XMLGregorianCalendar}.
   * 
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendarNow ()
  {
    return getXMLCalendar (PDTFactory.getCurrentDateTime ());
  }

  /**
   * @deprecated Use {@link #getXMLCalendar(LocalDateTime)} instead
   */
  @Deprecated
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDateTime (@Nullable final LocalDateTime aBase)
  {
    return getXMLCalendar (aBase);
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} with date and time.
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendar (@Nullable final LocalDateTime aBase)
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

  /**
   * @deprecated Use {@link #getXMLCalendar(DateTime)} instead
   */
  @Deprecated
  @Nullable
  public static XMLGregorianCalendar getXMLCalendarDateTime (@Nullable final DateTime aBase)
  {
    return getXMLCalendar (aBase);
  }

  /**
   * Get the passed object as {@link XMLGregorianCalendar} with date and time.
   * 
   * @param aBase
   *        The source object. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendar (@Nullable final DateTime aBase)
  {
    return aBase == null ? null : s_aDTFactory.newXMLGregorianCalendar (aBase.toGregorianCalendar ());
  }

  /**
   * Get the passed {@link GregorianCalendar} as {@link XMLGregorianCalendar}.
   * 
   * @param aCal
   *        Source calendar. May be <code>null</code>.
   * @return <code>null</code> if the passed calendar is <code>null</code>.
   * @deprecated Use {@link #getXMLCalendar(GregorianCalendar)} instead
   */
  @Deprecated
  @Nullable
  public static XMLGregorianCalendar getConverted (@Nullable final GregorianCalendar aCal)
  {
    return getXMLCalendar (aCal);
  }

  /**
   * Get the passed {@link GregorianCalendar} as {@link XMLGregorianCalendar}.
   * 
   * @param aCal
   *        Source calendar. May be <code>null</code>.
   * @return <code>null</code> if the passed calendar is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendar (@Nullable final GregorianCalendar aCal)
  {
    return aCal == null ? null : s_aDTFactory.newXMLGregorianCalendar (aCal);
  }

  /**
   * Get the passed milli seconds as {@link XMLGregorianCalendar}.
   * 
   * @param nMillis
   *        Milli seconds since 1.1.1970
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static XMLGregorianCalendar getXMLCalendar (final long nMillis)
  {
    return s_aDTFactory.newXMLGregorianCalendar (getCalendar (nMillis));
  }

  /**
   * Get the passed {@link Date} as {@link XMLGregorianCalendar}.
   * 
   * @param aDate
   *        Source date. May be <code>null</code>.
   * @return <code>null</code> if the passed date is <code>null</code>.
   */
  @Nullable
  public static XMLGregorianCalendar getXMLCalendar (@Nullable final Date aDate)
  {
    return aDate == null ? null : s_aDTFactory.newXMLGregorianCalendar (getCalendar (aDate));
  }

  /**
   * Convert the passed {@link XMLGregorianCalendar} to a
   * {@link GregorianCalendar}.
   * 
   * @param aCal
   *        Source calendar. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   * @deprecated Use {@link #getGregorianCalendar(XMLGregorianCalendar)} instead
   */
  @Deprecated
  @Nullable
  public static GregorianCalendar getConverted (@Nullable final XMLGregorianCalendar aCal)
  {
    return getGregorianCalendar (aCal);
  }

  /**
   * Convert the passed {@link XMLGregorianCalendar} to a
   * {@link GregorianCalendar}.
   * 
   * @param aCal
   *        Source calendar. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static GregorianCalendar getGregorianCalendar (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : aCal.toGregorianCalendar (aCal.getTimeZone (aCal.getTimezone ()), null, null);
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as {@link LocalDate}.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static LocalDate getLocalDate (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalDate (getGregorianCalendar (aCal));
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as {@link LocalTime}.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static LocalTime getLocalTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalTime (getGregorianCalendar (aCal));
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as {@link LocalDateTime}.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static LocalDateTime getLocalDateTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createLocalDateTime (getGregorianCalendar (aCal));
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as {@link DateTime}.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static DateTime getDateTime (@Nullable final XMLGregorianCalendar aCal)
  {
    return aCal == null ? null : PDTFactory.createDateTime (getGregorianCalendar (aCal));
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as {@link Date}.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>null</code> if the parameter is <code>null</code>.
   */
  @Nullable
  public static Date getDate (@Nullable final XMLGregorianCalendar aCal)
  {
    final GregorianCalendar aGregorianCalendar = getGregorianCalendar (aCal);
    return aGregorianCalendar == null ? null : aGregorianCalendar.getTime ();
  }

  /**
   * Get the passed {@link XMLGregorianCalendar} as milli seconds.
   * 
   * @param aCal
   *        The source {@link XMLGregorianCalendar}. May be <code>null</code>.
   * @return <code>{@link CGlobal#ILLEGAL_ULONG}</code> if the parameter is
   *         <code>null</code>.
   */
  @CheckForSigned
  public static long getMillis (@Nullable final XMLGregorianCalendar aCal)
  {
    final GregorianCalendar aGregorianCalendar = getGregorianCalendar (aCal);
    return aGregorianCalendar == null ? CGlobal.ILLEGAL_ULONG : aGregorianCalendar.getTimeInMillis ();
  }
}