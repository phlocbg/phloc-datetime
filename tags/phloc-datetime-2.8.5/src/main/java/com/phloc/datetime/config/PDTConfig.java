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
package com.phloc.datetime.config;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GJChronology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.commons.state.ESuccess;

/**
 * This class provides the most basic settings for date time operating: the
 * date-time-zone and the chronology to use.
 * 
 * @author philip
 */
@ThreadSafe
public final class PDTConfig
{
  /**
   * The default date time zone.
   */
  public static final String DEFAULT_DATETIMEZONEID = "Europe/Vienna";

  private static final Logger s_aLogger = LoggerFactory.getLogger (PDTConfig.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  private static DateTimeZone s_aDateTimeZone = DateTimeZone.forID (DEFAULT_DATETIMEZONEID);

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTConfig s_aInstance = new PDTConfig ();

  private PDTConfig ()
  {}

  @Nonnull
  public static ESuccess setDefaultDateTimeZoneID (final String sDateTimeZoneID)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      // Try to resolve ID -> throws IAE if unknown
      s_aDateTimeZone = DateTimeZone.forID (sDateTimeZoneID);
      return ESuccess.SUCCESS;
    }
    catch (final IllegalArgumentException ex)
    {
      // time zone ID is unknown
      s_aLogger.warn ("Unsupported dateTimeZone ID '" + sDateTimeZoneID + "'");
      return ESuccess.FAILURE;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public static DateTimeZone getDefaultDateTimeZone ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aDateTimeZone;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public static DateTimeZone getDateTimeZoneUTC ()
  {
    return DateTimeZone.UTC;
  }

  @Nonnull
  public static Chronology getDefaultChronology ()
  {
    return GJChronology.getInstance (getDefaultDateTimeZone ());
  }

  /**
   * @return The default chronology with the system date time zone
   */
  @Nonnull
  public static Chronology getDefaultChronologyWithoutDateTimeZone ()
  {
    return GJChronology.getInstance ();
  }

  /**
   * @return The default chronology with UTC date time zone
   */
  @Nonnull
  public static Chronology getDefaultChronologyUTC ()
  {
    return GJChronology.getInstanceUTC ();
  }
}