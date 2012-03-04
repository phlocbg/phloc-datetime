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
package com.phloc.datetime.period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.joda.time.DateTimeConstants;
import org.joda.time.Period;
import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link LocalDateTimePeriod}.
 * 
 * @author philip
 */
public final class LocalDateTimePeriodTest
{
  @Test
  public void testAll ()
  {
    LocalDateTimePeriod p = new LocalDateTimePeriod ();
    assertNull (p.getStartLocalDateTime ());
    assertNull (p.getEndLocalDateTime ());

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setStartLocalDateTime (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 10));

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setEndLocalDateTime (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 11));

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
    assertNotNull (p.getStartLocalDateTime ());
    assertNull (p.getEndLocalDateTime ());

    p = new LocalDateTimePeriod (PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 10),
                                 PDTFactory.createLocalDateTime (2010, DateTimeConstants.FEBRUARY, 11));
    assertNotNull (p.getStartLocalDateTime ());
    assertNotNull (p.getEndLocalDateTime ());
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
}