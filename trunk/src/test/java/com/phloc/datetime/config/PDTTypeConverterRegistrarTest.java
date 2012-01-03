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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.phloc.commons.typeconvert.TypeConverter;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link PDTTypeConverterRegistrar}.
 * 
 * @author philip
 */
public final class PDTTypeConverterRegistrarTest
{
  private static final Object [] NUMBERS = new Object [] { new AtomicInteger (17),
                                                          new AtomicLong (1234567890),
                                                          new BigDecimal ("11238712367812368712368.32123213"),
                                                          new BigInteger ("23127893819732"),
                                                          Byte.valueOf ((byte) 5),
                                                          Double.valueOf (123.234234),
                                                          Float.valueOf (123433.324f),
                                                          Integer.valueOf (567),
                                                          Long.valueOf (213687123617283L),
                                                          Short.valueOf ((short) 12345) };

  @Test
  public void testDateTime ()
  {
    assertNotNull (TypeConverter.convertIfNecessary (new GregorianCalendar (), DateTime.class));
    assertNotNull (TypeConverter.convertIfNecessary (new Date (), DateTime.class));
    for (final Object aNumber : NUMBERS)
      assertNotNull (TypeConverter.convertIfNecessary (aNumber, DateTime.class));

    // Test auto conversion to and from string
    final DateTime aNow = PDTFactory.getCurrentDateTime ();
    final String sNow = TypeConverter.convertIfNecessary (aNow, String.class);
    final DateTime aNow2 = TypeConverter.convertIfNecessary (sNow, aNow.getClass ());
    assertEquals (aNow, aNow2);
  }

  @Test
  public void testLocalDateTime ()
  {
    assertNotNull (TypeConverter.convertIfNecessary (new GregorianCalendar (), LocalDateTime.class));
    assertNotNull (TypeConverter.convertIfNecessary (new Date (), LocalDateTime.class));
    for (final Object aNumber : NUMBERS)
      assertNotNull (TypeConverter.convertIfNecessary (aNumber, LocalDateTime.class));

    // Test auto conversion to and from string
    final LocalDateTime aNow = PDTFactory.getCurrentLocalDateTime ();
    final String sNow = TypeConverter.convertIfNecessary (aNow, String.class);
    final LocalDateTime aNow2 = TypeConverter.convertIfNecessary (sNow, aNow.getClass ());
    assertEquals (aNow, aNow2);
  }

  @Test
  public void testLocalDate ()
  {
    assertNotNull (TypeConverter.convertIfNecessary (new GregorianCalendar (), LocalDate.class));
    assertNotNull (TypeConverter.convertIfNecessary (new Date (), LocalDate.class));
    for (final Object aNumber : NUMBERS)
      assertNotNull (TypeConverter.convertIfNecessary (aNumber, LocalDate.class));

    // Test auto conversion to and from string
    final LocalDate aNow = PDTFactory.getCurrentLocalDate ();
    final String sNow = TypeConverter.convertIfNecessary (aNow, String.class);
    final LocalDate aNow2 = TypeConverter.convertIfNecessary (sNow, aNow.getClass ());
    assertEquals (aNow, aNow2);
  }

  @Test
  public void testLocalTime ()
  {
    assertNotNull (TypeConverter.convertIfNecessary (new GregorianCalendar (), LocalTime.class));
    assertNotNull (TypeConverter.convertIfNecessary (new Date (), LocalTime.class));
    for (final Object aNumber : NUMBERS)
      assertNotNull (TypeConverter.convertIfNecessary (aNumber, LocalTime.class));

    // Test auto conversion to and from string
    final LocalTime aNow = PDTFactory.getCurrentLocalTime ();
    final String sNow = TypeConverter.convertIfNecessary (aNow, String.class);
    final LocalTime aNow2 = TypeConverter.convertIfNecessary (sNow, aNow.getClass ());
    assertEquals (aNow, aNow2);
  }

  @Test
  public void testDuration ()
  {
    for (final Object aNumber : NUMBERS)
      assertNotNull (TypeConverter.convertIfNecessary (aNumber, Duration.class));
    assertNotNull (TypeConverter.convertIfNecessary ("PT12345677777S", Duration.class));
  }
}
