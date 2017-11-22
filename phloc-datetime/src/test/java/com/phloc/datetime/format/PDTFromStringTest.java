/**
 * Copyright (C) 2006-2015 phloc systems
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
package com.phloc.datetime.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.datetime.PDTFactory;
import com.phloc.datetime.config.PDTConfig;

/**
 * Test class for class {@link PDTFromString}.
 * 
 * @author Philip Helger
 */
public final class PDTFromStringTest
{
  private static final Logger LOG = LoggerFactory.getLogger (PDTFromStringTest.class);

  @Test
  public void testFromString ()
  {
    // No chronology
    DateTimeFormatter aDTF = DateTimeFormat.forPattern ("yyyy/MM/dd HH:mm:ss");
    assertNotNull (aDTF);
    DateTime aDT = PDTFromString.getDateTimeFromString ("2009/03/28 15:06:34", aDTF);
    assertNotNull (aDT);
    assertEquals (ISOChronology.getInstance (), aDT.getChronology ());

    // Our default chronology
    aDTF = PDTFormatter.getForPattern ("yyyy/MM/dd HH:mm:ss");
    assertNotNull (aDTF);
    aDT = PDTFromString.getDateTimeFromString ("2009/03/28 15:06:34", aDTF);
    assertNotNull (aDT);
    assertEquals (PDTConfig.getDefaultChronology (), aDT.getChronology ());
  }

  @Test
  public void testGetDefaultDateFromString ()
  {
    {
      final DateTime aDT = PDTFactory.createDateTime (1979, 04, 8);
      final DateTime aBDT = PDTFromString.getDefaultDateTimeFromString (PDTToString.getAsString (aDT, Locale.GERMAN),
                                                                        Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }
    {
      final DateTime aDT = PDTFactory.createDateTime (1979, 04, 8);
      final DateTime aBDT = PDTFromString.getDefaultDateFromString (PDTFormatter.getDefaultFormatterDate (Locale.GERMAN)
                                                                                .print (aDT),
                                                                    Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }

    /* ----- */

    {
      final DateTime aDT = PDTFactory.createDateTime (1980, 04, 6);
      final DateTime aBDT = PDTFromString.getDefaultDateTimeFromString (PDTToString.getAsString (aDT, Locale.GERMAN),
                                                                        Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }
    {
      final DateTime aDT = PDTFactory.createDateTime (1980, 04, 6);
      final DateTime aBDT = PDTFromString.getDefaultDateFromString (PDTFormatter.getDefaultFormatterDate (Locale.GERMAN)
                                                                                .print (aDT),
                                                                    Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }
  }

  @Test
  public void testGetDefaultDateTimeFromStringDST ()
  {
    {
      final DateTime aDT = PDTFactory.createDateTime (1980, 04, 6, 0, 0, 0);
      final DateTime aBDT = PDTFromString.getDefaultDateTimeFromString (PDTToString.getAsString (aDT, Locale.GERMAN),
                                                                        Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }
    {
      final DateTime aDT = PDTFactory.createDateTime (1980, 04, 6, 1, 23, 34);
      final DateTime aBDT = PDTFromString.getDefaultDateTimeFromString (PDTFormatter.getDefaultFormatterDateTime (Locale.GERMAN)
                                                                                    .print (aDT),
                                                                        Locale.GERMAN);
      Assert.assertTrue (EqualsUtils.equals (aDT, aBDT));
    }
  }

  @Test
  public void testDateTimeFromString ()
  {
    assertEquals (PDTFactory.createDateTime (1980, DateTimeConstants.APRIL, 6),
                  PDTFromString.getDateTimeFromString ("1980.04.06", "yyyy.MM.dd"));
    assertNull (PDTFromString.getDateTimeFromString ("1980.04.06 abc", "yyyy.MM.dd"));
    assertNull (PDTFromString.getDateTimeFromString (null, "yyyy.MM.dd"));

    try
    {
      // Illegal DT pattern
      PDTFromString.getDateTimeFromString ("2000.07.06", "abc");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // null DT pattern
      PDTFromString.getDateTimeFromString ("2000.07.06", (DateTimeFormatter) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
  }

  @Test
  public void testLocalDateFromString ()
  {
    assertEquals (PDTFactory.createLocalDate (2000, DateTimeConstants.JULY, 6),
                  PDTFromString.getLocalDateFromString ("2000.07.06", "yyyy.MM.dd"));
    assertNull (PDTFromString.getLocalDateFromString ("2000.07.06 abc", "yyyy.MM.dd"));
    assertNull (PDTFromString.getLocalDateFromString (null, "yyyy.MM.dd"));

    try
    {
      // Illegal DT pattern
      PDTFromString.getLocalDateFromString ("2000.07.06", "abc");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // null DT pattern
      PDTFromString.getLocalDateFromString ("2000.07.06", (DateTimeFormatter) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
  }

  @Test
  public void testDefaultToStringAndBack ()
  {
    final DateTime aDT = PDTFactory.getCurrentDateTime ();
    String sDT = aDT.toString ();
    assertEquals (aDT, PDTFactory.createDateTime (sDT));

    final LocalDateTime aLDT = PDTFactory.getCurrentLocalDateTime ();
    sDT = aLDT.toString ();
    assertEquals (aLDT, PDTFactory.createLocalDateTime (sDT));

    final LocalDate aLD = PDTFactory.getCurrentLocalDate ();
    sDT = aLD.toString ();
    assertEquals (aLD, PDTFactory.createLocalDate (sDT));

    final LocalTime aLT = PDTFactory.getCurrentLocalTime ();
    sDT = aLT.toString ();
    assertEquals (aLT, PDTFactory.createLocalTime (sDT));
  }
}
