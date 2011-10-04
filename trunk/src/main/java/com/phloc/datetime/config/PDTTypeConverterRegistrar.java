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
package com.phloc.datetime.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.convert.ConverterManager;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.typeconvert.ITypeConverter;
import com.phloc.commons.typeconvert.ITypeConverterRegistrarSPI;
import com.phloc.commons.typeconvert.TypeConverterRegistry;

/**
 * Register all {@link ITypeConverter} objects to the central
 * {@link TypeConverterRegistry}.
 * 
 * @author philip
 */
@Immutable
@IsSPIImplementation
public final class PDTTypeConverterRegistrar implements ITypeConverterRegistrarSPI
{
  private static void _registerJodaConverter ()
  {
    // Register generic Number converters, that work with Long, Integer,
    // BigInteger etc.
    ConverterManager.getInstance ().addInstantConverter (PDTJodaNumberConverter.INSTANCE);
    ConverterManager.getInstance ().addPartialConverter (PDTJodaNumberConverter.INSTANCE);
    ConverterManager.getInstance ().addDurationConverter (PDTJodaNumberConverter.INSTANCE);
  }

  public void registerTypeConverter ()
  {
    // Register Joda native converters
    _registerJodaConverter ();

    final ITypeConverter aConverterToString = new ITypeConverter ()
    {
      @Nonnull
      public String convert (final Object aSource)
      {
        return aSource.toString ();
      }
    };

    // DateTime
    final ITypeConverter aConverterToDateTime = new ITypeConverter ()
    {
      @Nonnull
      public DateTime convert (final Object aSource)
      {
        return new DateTime (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (GregorianCalendar.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Date.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Byte.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Double.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Float.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Integer.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Long.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (Short.class, DateTime.class, aConverterToDateTime);
    TypeConverterRegistry.registerTypeConverter (DateTime.class, String.class, aConverterToString);

    // LocalDateTime
    final ITypeConverter aConverterToLocalDateTime = new ITypeConverter ()
    {
      @Nonnull
      public LocalDateTime convert (final Object aSource)
      {
        return new LocalDateTime (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (GregorianCalendar.class,
                                                 LocalDateTime.class,
                                                 aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Date.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Byte.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Double.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Float.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Integer.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Long.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (Short.class, LocalDateTime.class, aConverterToLocalDateTime);
    TypeConverterRegistry.registerTypeConverter (LocalDateTime.class, String.class, aConverterToString);

    // LocalDate
    final ITypeConverter aConverterToLocalDate = new ITypeConverter ()
    {
      @Nonnull
      public LocalDate convert (final Object aSource)
      {
        return new LocalDate (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (GregorianCalendar.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Date.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Byte.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Double.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Float.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Integer.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Long.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (Short.class, LocalDate.class, aConverterToLocalDate);
    TypeConverterRegistry.registerTypeConverter (LocalDate.class, String.class, aConverterToString);

    // LocalTime
    final ITypeConverter aConverterToLocalTime = new ITypeConverter ()
    {
      @Nonnull
      public LocalTime convert (final Object aSource)
      {
        return new LocalTime (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (GregorianCalendar.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Date.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Byte.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Double.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Float.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Integer.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Long.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (Short.class, LocalTime.class, aConverterToLocalTime);
    TypeConverterRegistry.registerTypeConverter (LocalTime.class, String.class, aConverterToString);

    // Duration
    final ITypeConverter aConverterToDuration = new ITypeConverter ()
    {
      @Nonnull
      public Duration convert (final Object aSource)
      {
        return new Duration (aSource);
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Byte.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Double.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Float.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Integer.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Long.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Short.class, Duration.class, aConverterToDuration);
    TypeConverterRegistry.registerTypeConverter (Duration.class, String.class, aConverterToString);

    // Duration
    final ITypeConverter aConverterToPeriod = new ITypeConverter ()
    {
      @Nonnull
      public Period convert (final Object aSource)
      {
        return new Period (aSource);
      }
    };
    TypeConverterRegistry.registerTypeConverter (String.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (AtomicInteger.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (AtomicLong.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (BigDecimal.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (BigInteger.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Byte.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Double.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Float.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Integer.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Long.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Short.class, Period.class, aConverterToPeriod);
    TypeConverterRegistry.registerTypeConverter (Period.class, String.class, aConverterToString);
  }
}
