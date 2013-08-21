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
package com.phloc.datetime.period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link DateTimePeriod}.
 * 
 * @author Philip Helger
 */
public final class DateTimePeriodTest
{
  @Test
  public void testAll ()
  {
    DateTimePeriod p = new DateTimePeriod ();
    assertNull (p.getStart ());
    assertNull (p.getEnd ());

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}
    try
    {
      p.getAsInterval ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setStart (PDTFactory.createDateTime (2010, DateTimeConstants.FEBRUARY, 10));

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}
    try
    {
      p.getAsInterval ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setEnd (PDTFactory.createDateTime (2010, DateTimeConstants.FEBRUARY, 11));

    final Period per = p.getAsPeriod ();
    assertNotNull (per);
    assertEquals (0, per.getYears ());
    assertEquals (0, per.getMonths ());
    assertEquals (1, per.getDays ());
    assertEquals (0, per.getHours ());
    assertEquals (0, per.getMinutes ());
    assertEquals (0, per.getSeconds ());
    assertEquals (0, per.getMillis ());

    final Interval aInterval = p.getAsInterval ();
    assertNotNull (aInterval);
    assertEquals (p.getStart (), aInterval.getStart ());
    assertEquals (p.getEnd (), aInterval.getEnd ());

    p = new DateTimePeriod (PDTFactory.createDateTime (2010, DateTimeConstants.FEBRUARY, 10));
    assertNotNull (p.getStart ());
    assertNull (p.getEnd ());

    p = new DateTimePeriod (PDTFactory.createDateTime (2010, DateTimeConstants.FEBRUARY, 10),
                            PDTFactory.createDateTime (2010, DateTimeConstants.FEBRUARY, 11));
    assertNotNull (p.getStart ());
    assertNotNull (p.getEnd ());
    assertEquals (per, p.getAsPeriod ());

    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (p,
                                                                    new DateTimePeriod (PDTFactory.createDateTime (2010,
                                                                                                                   DateTimeConstants.FEBRUARY,
                                                                                                                   10),
                                                                                        PDTFactory.createDateTime (2010,
                                                                                                                   DateTimeConstants.FEBRUARY,
                                                                                                                   11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new DateTimePeriod (PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       11),
                                                                                            PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new DateTimePeriod (PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       10),
                                                                                            PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       12)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new DateTimePeriod (null,
                                                                                            PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       11)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new DateTimePeriod (PDTFactory.createDateTime (2010,
                                                                                                                       DateTimeConstants.FEBRUARY,
                                                                                                                       10),
                                                                                            null));
  }

  @Test
  public void testValidity ()
  {
    DateTimePeriod vr = new DateTimePeriod (null, null);
    assertNull (vr.getStart ());
    assertNull (vr.getEnd ());
    assertTrue (vr.isValidForNow ());
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new DateTimePeriod (null, null));

    try
    {
      vr.isValidFor (null);
      fail ();
    }
    catch (final NullPointerException ex)
    {}

    final DateTime aStart = PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 18, 12, 35);
    vr = new DateTimePeriod (aStart, null);
    assertEquals (aStart, vr.getStart ());
    assertNull (vr.getEnd ());
    assertTrue (vr.isValidForNow ());
    // Start date
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 17)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 18, 12, 34)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 18, 12, 35)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 19)));
    // End date
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new DateTimePeriod (aStart, null));

    final DateTime aEnd = PDTFactory.createDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 12);
    vr = new DateTimePeriod (aStart, aEnd);
    assertEquals (aStart, vr.getStart ());
    assertEquals (aEnd, vr.getEnd ());
    assertFalse (vr.isValidForNow ());
    // Start date
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2000, DateTimeConstants.JANUARY, 1)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 17)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 18, 12, 34)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 18, 12, 35)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.JULY, 19)));
    // End date
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.NOVEMBER, 17)));
    assertTrue (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 12)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.NOVEMBER, 18, 15, 13)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (2011, DateTimeConstants.NOVEMBER, 19)));
    assertFalse (vr.isValidFor (PDTFactory.createDateTime (9999, DateTimeConstants.DECEMBER, 31)));
    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (vr, new DateTimePeriod (aStart, aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr, new DateTimePeriod (aStart.plusDays (1),
                                                                                                aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr,
                                                                        new DateTimePeriod (aStart, aEnd.plusDays (1)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr, new DateTimePeriod (null, aEnd));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (vr, new DateTimePeriod (aStart, null));
  }
}
