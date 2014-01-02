/**
 * Copyright (C) 2006-2014 phloc systems
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
package com.phloc.datetime.period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link LocalDateTimePeriod}.
 * 
 * @author Philip Helger
 */
public final class LocalDateTimePeriodTest
{
  @Test
  public void testAll ()
  {
    LocalDateTimePeriod p = new LocalDateTimePeriod ();
    assertNull (p.getStart ());
    assertNull (p.getEnd ());

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setStart (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 10));

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setEnd (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 11));

    final Period per = p.getAsPeriod ();
    assertNotNull (per);
    assertEquals (0, per.getYears ());
    assertEquals (0, per.getMonths ());
    assertEquals (1, per.getDays ());
    assertEquals (0, per.getHours ());
    assertEquals (0, per.getMinutes ());
    assertEquals (0, per.getSeconds ());
    assertEquals (0, per.getMillis ());

    p = new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 10));
    assertNotNull (p.getStart ());
    assertNull (p.getEnd ());

    p = new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 10),
                                 PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 11));
    assertNotNull (p.getStart ());
    assertNotNull (p.getEnd ());
    assertEquals (per, p.getAsPeriod ());

    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (p,
                                                                    new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010,
                                                                                                                             DateTimeConstants.FEBRUARY,
                                                                                                                             10),
                                                                                             PDTFactory.createLocalDateTime (2010,
                                                                                                                             DateTimeConstants.FEBRUARY,
                                                                                                                             11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 11),
                                                                                                 PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 10),
                                                                                                 PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 12)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalDateTimePeriod (null,
                                                                                                 PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010,
                                                                                                                                 DateTimeConstants.FEBRUARY,
                                                                                                                                 10),
                                                                                                 null));
  }

  @Test
  public void testValidity ()
  {
    LocalDateTimePeriod vr = new LocalDateTimePeriod (null, null);
    assertNull (vr.getStart ());
    assertNull (vr.getEnd ());
    assertTrue (vr.isValidForNow ());
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new LocalDateTimePeriod (null, null));

    try
    {
      vr.isValidFor (null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}

    final LocalDateTime aStart = PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 18, 12, 35);
    vr = new LocalDateTimePeriod (aStart, null);
    assertEquals (aStart, vr.getStart ());
    assertNull (vr.getEnd ());
    assertTrue (vr.isValidForNow ());
    // Start date
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 17)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 18, 12, 34)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 18, 12, 35)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 19)));
    // End date
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new LocalDateTimePeriod (aStart, null));

    final LocalDateTime aEnd = PDTFactory.createLocalDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 12);
    vr = new LocalDateTimePeriod (aStart, aEnd);
    assertEquals (aStart, vr.getStart ());
    assertEquals (aEnd, vr.getEnd ());
    assertFalse (vr.isValidForNow ());
    // Start date
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 17)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 18, 12, 34)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 18, 12, 35)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.JULY, 19)));
    // End date
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.NOVEMBER, 17)));
    assertTrue (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 12)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 13)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (2011, DateTimeConstants.NOVEMBER, 19)));
    assertFalse (vr.isValidFor (PDTFactory.createLocalDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new LocalDateTimePeriod (aStart, aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr,
                                                                        new LocalDateTimePeriod (aStart.plusDays (1),
                                                                                                 aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr,
                                                                        new LocalDateTimePeriod (aStart,
                                                                                                 aEnd.plusDays (1)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr, new LocalDateTimePeriod (null, aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr, new LocalDateTimePeriod (aStart, null));
  }
}
