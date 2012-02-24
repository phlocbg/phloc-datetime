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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
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
import com.phloc.commons.string.StringHelper;
import com.phloc.commons.typeconvert.ITypeConverter;
import com.phloc.commons.typeconvert.ITypeConverterRegistrarSPI;
import com.phloc.commons.typeconvert.ITypeConverterRegistry;
import com.phloc.commons.typeconvert.TypeConverterRegistry;
import com.phloc.datetime.PDTFactory;

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

  public void registerTypeConverter (@Nonnull final ITypeConverterRegistry aRegistry)
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
    aRegistry.registerTypeConverter (String.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (GregorianCalendar.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Date.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (AtomicInteger.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (AtomicLong.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (BigDecimal.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (BigInteger.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Byte.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Double.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Float.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Integer.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Long.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (Short.class, DateTime.class, aConverterToDateTime);
    aRegistry.registerTypeConverter (LocalDate.class, DateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public DateTime convert (final Object aSource)
      {
        return PDTFactory.createDateTime ((LocalDate) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalTime.class, DateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public DateTime convert (final Object aSource)
      {
        return PDTFactory.createDateTime ((LocalTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDateTime.class, DateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public DateTime convert (final Object aSource)
      {
        return PDTFactory.createDateTime ((LocalDateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (DateTime.class, String.class, aConverterToString);

    // LocalDateTime
    final ITypeConverter aConverterToLocalDateTime = new ITypeConverter ()
    {
      @Nonnull
      public LocalDateTime convert (final Object aSource)
      {
        return new LocalDateTime (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    aRegistry.registerTypeConverter (String.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (GregorianCalendar.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Date.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (AtomicInteger.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (AtomicLong.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (BigDecimal.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (BigInteger.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Byte.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Double.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Float.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Integer.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Long.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (Short.class, LocalDateTime.class, aConverterToLocalDateTime);
    aRegistry.registerTypeConverter (DateTime.class, LocalDateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalDateTime convert (final Object aSource)
      {
        return PDTFactory.createLocalDateTime ((DateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDate.class, LocalDateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalDateTime convert (final Object aSource)
      {
        return PDTFactory.createLocalDateTime ((LocalDate) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalTime.class, LocalDateTime.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalDateTime convert (final Object aSource)
      {
        return PDTFactory.createLocalDateTime ((LocalTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDateTime.class, String.class, aConverterToString);

    // LocalDate
    final ITypeConverter aConverterToLocalDate = new ITypeConverter ()
    {
      @Nonnull
      public LocalDate convert (final Object aSource)
      {
        return new LocalDate (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    aRegistry.registerTypeConverter (String.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (GregorianCalendar.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Date.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (AtomicInteger.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (AtomicLong.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (BigDecimal.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (BigInteger.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Byte.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Double.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Float.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Integer.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Long.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (Short.class, LocalDate.class, aConverterToLocalDate);
    aRegistry.registerTypeConverter (DateTime.class, LocalDate.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalDate convert (final Object aSource)
      {
        return PDTFactory.createLocalDate ((DateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDateTime.class, LocalDate.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalDate convert (final Object aSource)
      {
        return PDTFactory.createLocalDate ((LocalDateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDate.class, String.class, aConverterToString);

    // LocalTime
    final ITypeConverter aConverterToLocalTime = new ITypeConverter ()
    {
      @Nonnull
      public LocalTime convert (final Object aSource)
      {
        return new LocalTime (aSource, PDTConfig.getDefaultChronology ());
      }
    };
    aRegistry.registerTypeConverter (String.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (GregorianCalendar.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Date.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (AtomicInteger.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (AtomicLong.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (BigDecimal.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (BigInteger.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Byte.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Double.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Float.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Integer.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Long.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (Short.class, LocalTime.class, aConverterToLocalTime);
    aRegistry.registerTypeConverter (DateTime.class, LocalTime.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalTime convert (final Object aSource)
      {
        return PDTFactory.createLocalTime ((DateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalDateTime.class, LocalTime.class, new ITypeConverter ()
    {
      @Nonnull
      public LocalTime convert (final Object aSource)
      {
        return PDTFactory.createLocalTime ((LocalDateTime) aSource);
      }
    });
    aRegistry.registerTypeConverter (LocalTime.class, String.class, aConverterToString);

    // Duration
    final ITypeConverter aConverterToDuration = new ITypeConverter ()
    {
      @Nonnull
      public Duration convert (final Object aSource)
      {
        return new Duration (aSource);
      }
    };
    aRegistry.registerTypeConverter (String.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (AtomicInteger.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (AtomicLong.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (BigDecimal.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (BigInteger.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Byte.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Double.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Float.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Integer.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Long.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Short.class, Duration.class, aConverterToDuration);
    aRegistry.registerTypeConverter (Duration.class, String.class, aConverterToString);

    // Duration
    final ITypeConverter aConverterToPeriod = new ITypeConverter ()
    {
      @Nonnull
      public Period convert (final Object aSource)
      {
        return new Period (aSource);
      }
    };
    aRegistry.registerTypeConverter (String.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (AtomicInteger.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (AtomicLong.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (BigDecimal.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (BigInteger.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Byte.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Double.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Float.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Integer.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Long.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Short.class, Period.class, aConverterToPeriod);
    aRegistry.registerTypeConverter (Period.class, String.class, aConverterToString);

    // Date and Calendar
    aRegistry.registerTypeConverter (Calendar.class, String.class, new ITypeConverter ()
    {
      @Nonnull
      public String convert (final Object aSource)
      {
        return Long.toString (((Calendar) aSource).getTimeInMillis ());
      }
    });
    aRegistry.registerTypeConverter (Calendar.class, Date.class, new ITypeConverter ()
    {
      @Nonnull
      public Date convert (final Object aSource)
      {
        return ((Calendar) aSource).getTime ();
      }
    });
    aRegistry.registerTypeConverter (String.class, Calendar.class, new ITypeConverter ()
    {
      @Nonnull
      public Calendar convert (final Object aSource)
      {
        final Calendar aCal = Calendar.getInstance ();
        aCal.setTimeInMillis (StringHelper.parseLong ((String) aSource, 0));
        return aCal;
      }
    });
    aRegistry.registerTypeConverter (Date.class, Calendar.class, new ITypeConverter ()
    {
      @Nonnull
      public Calendar convert (final Object aSource)
      {
        final Calendar aCal = Calendar.getInstance ();
        aCal.setTime ((Date) aSource);
        return aCal;
      }
    });
    aRegistry.registerTypeConverter (Date.class, String.class, new ITypeConverter ()
    {
      @Nonnull
      public String convert (final Object aSource)
      {
        return Long.toString (((Date) aSource).getTime ());
      }
    });
    aRegistry.registerTypeConverter (String.class, Date.class, new ITypeConverter ()
    {
      @Nonnull
      public Date convert (final Object aSource)
      {
        return new Date (StringHelper.parseLong ((String) aSource, 0));
      }
    });
  }
}
