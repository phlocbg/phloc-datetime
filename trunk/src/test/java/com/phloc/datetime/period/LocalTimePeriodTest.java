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
package com.phloc.datetime.period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.joda.time.Period;
import org.junit.Test;

import com.phloc.commons.mock.PhlocTestUtils;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link LocalTimePeriod}.
 * 
 * @author philip
 */
public final class LocalTimePeriodTest
{
  @Test
  public void testAll ()
  {
    LocalTimePeriod p = new LocalTimePeriod ();
    assertNull (p.getStartLocalTime ());
    assertNull (p.getEndLocalTime ());

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setStartLocalTime (PDTFactory.createLocalTime (17, 15, 30));

    try
    {
      p.getAsPeriod ();
      fail ();
    }
    catch (final IllegalStateException ex)
    {}

    p.setEndLocalTime (PDTFactory.createLocalTime (17, 16, 30));

    final Period per = p.getAsPeriod ();
    assertNotNull (per);
    assertEquals (0, per.getYears ());
    assertEquals (0, per.getMonths ());
    assertEquals (0, per.getDays ());
    assertEquals (0, per.getHours ());
    assertEquals (1, per.getMinutes ());
    assertEquals (0, per.getSeconds ());
    assertEquals (0, per.getMillis ());

    p = new LocalTimePeriod (PDTFactory.createLocalTime (17, 15, 30));
    assertNotNull (p.getStartLocalTime ());
    assertNull (p.getEndLocalTime ());

    p = new LocalTimePeriod (PDTFactory.createLocalTime (17, 15, 30), PDTFactory.createLocalTime (17, 16, 30));
    assertNotNull (p.getStartLocalTime ());
    assertNotNull (p.getEndLocalTime ());
    assertEquals (per, p.getAsPeriod ());

    PhlocTestUtils.testDefaultImplementationWithEqualContentObject (p,
                                                                    new LocalTimePeriod (PDTFactory.createLocalTime (17,
                                                                                                                     15,
                                                                                                                     30),
                                                                                         PDTFactory.createLocalTime (17,
                                                                                                                     16,
                                                                                                                     30)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalTimePeriod (PDTFactory.createLocalTime (17,
                                                                                                                         15,
                                                                                                                         30),
                                                                                             PDTFactory.createLocalTime (17,
                                                                                                                         15,
                                                                                                                         30)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalTimePeriod (PDTFactory.createLocalTime (17,
                                                                                                                         15,
                                                                                                                         30),
                                                                                             PDTFactory.createLocalTime (17,
                                                                                                                         17,
                                                                                                                         30)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalTimePeriod (null,
                                                                                             PDTFactory.createLocalTime (17,
                                                                                                                         16,
                                                                                                                         30)));
    PhlocTestUtils.testDefaultImplementationWithDifferentContentObject (p,
                                                                        new LocalTimePeriod (PDTFactory.createLocalTime (17,
                                                                                                                         15,
                                                                                                                         30),
                                                                                             null));
  }
}
