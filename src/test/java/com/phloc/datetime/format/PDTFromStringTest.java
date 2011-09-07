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
package com.phloc.datetime.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import com.phloc.datetime.PDTFactory;
import com.phloc.datetime.config.PDTConfig;

/**
 * Test class for class {@link PDTFromString}.
 * 
 * @author philip
 */
public final class PDTFromStringTest
{
  @Test
  public void testFromString ()
  {
    // No chronology
    DateTimeFormatter aDTF = DateTimeFormat.forPattern ("yyyy/MM/dd HH:mm:ss");
    assertNotNull (aDTF);
    DateTime aDT = PDTFromString.dateTimeFromString ("2009/03/28 15:06:34", aDTF);
    assertNotNull (aDT);
    assertEquals (ISOChronology.getInstance (), aDT.getChronology ());

    // Our default chronology
    aDTF = PDTFormatter.getForPattern ("yyyy/MM/dd HH:mm:ss");
    assertNotNull (aDTF);
    aDT = PDTFromString.dateTimeFromString ("2009/03/28 15:06:34", aDTF);
    assertNotNull (aDT);
    assertEquals (PDTConfig.getDefaultChronology (), aDT.getChronology ());
  }

  @Test
  public void testDateTimeFromString ()
  {
    assertEquals (PDTFactory.createDateTime (2000, DateTimeConstants.JULY, 6),
                  PDTFromString.dateTimeFromString ("2000.07.06", "yyyy.MM.dd"));
    assertNull (PDTFromString.dateTimeFromString ("2000.07.06 abc", "yyyy.MM.dd"));
    assertNull (PDTFromString.dateTimeFromString (null, "yyyy.MM.dd"));

    try
    {
      // Illegal DT pattern
      PDTFromString.dateTimeFromString ("2000.07.06", "abc");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // null DT pattern
      PDTFromString.dateTimeFromString ("2000.07.06", (DateTimeFormatter) null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}
  }

  @Test
  public void testLocalDateFromString ()
  {
    assertEquals (PDTFactory.createLocalDate (2000, DateTimeConstants.JULY, 6),
                  PDTFromString.localDateFromString ("2000.07.06", "yyyy.MM.dd"));
    assertNull (PDTFromString.localDateFromString ("2000.07.06 abc", "yyyy.MM.dd"));
    assertNull (PDTFromString.localDateFromString (null, "yyyy.MM.dd"));

    try
    {
      // Illegal DT pattern
      PDTFromString.localDateFromString ("2000.07.06", "abc");
      fail ();
    }
    catch (final IllegalArgumentException ex)
    {}

    try
    {
      // null DT pattern
      PDTFromString.localDateFromString ("2000.07.06", (DateTimeFormatter) null);
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
