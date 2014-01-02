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
package com.phloc.datetime.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTimeZone;
import org.junit.Test;

/**
 * Test class for class {@link PDTConfig}.
 * 
 * @author Philip Helger
 */
public final class PDTConfigTest
{
  @Test
  public void testTimeZones ()
  {
    DateTimeZone aDTZ = PDTConfig.getDefaultDateTimeZone ();
    assertNotNull (aDTZ);

    aDTZ = PDTConfig.getDateTimeZoneUTC ();
    assertNotNull (aDTZ);
    assertEquals ("UTC", aDTZ.getID ());

    assertNotNull (PDTConfig.getDefaultChronologyUTC ());
    assertEquals (PDTConfig.getDateTimeZoneUTC (), PDTConfig.getDefaultChronologyUTC ().getZone ());

    assertNotNull (PDTConfig.getDefaultChronologyWithoutDateTimeZone ());
    assertEquals (DateTimeZone.getDefault (), PDTConfig.getDefaultChronologyWithoutDateTimeZone ().getZone ());

    try
    {
      // Invalid
      assertFalse (PDTConfig.setDefaultDateTimeZoneID ("does not exist").isSuccess ());

      // Regular
      assertTrue (PDTConfig.setDefaultDateTimeZoneID ("Europe/Berlin").isSuccess ());
      assertEquals ("Europe/Berlin", PDTConfig.getDefaultDateTimeZone ().getID ());

      // I hope this is not the standard time zone anywhere
      assertTrue (PDTConfig.setDefaultDateTimeZoneID ("UTC").isSuccess ());
      assertEquals ("UTC", PDTConfig.getDefaultDateTimeZone ().getID ());

      // The default date time zone was not modified
      assertEquals (DateTimeZone.getDefault (), PDTConfig.getDefaultChronologyWithoutDateTimeZone ().getZone ());

      if (!DateTimeZone.getDefault ().getID ().equals ("UTC"))
      {
        // And therefore this must not be equal
        assertTrue (!PDTConfig.getDefaultDateTimeZone ().equals (PDTConfig.getDefaultChronologyWithoutDateTimeZone ()
                                                                          .getZone ()));
      }
    }
    finally
    {
      assertTrue (PDTConfig.setDefaultDateTimeZoneID (PDTConfig.DEFAULT_DATETIMEZONEID).isSuccess ());
    }
  }
}
