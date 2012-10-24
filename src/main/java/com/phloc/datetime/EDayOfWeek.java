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
package com.phloc.datetime;

import javax.annotation.Nullable;

import org.joda.time.DateTimeConstants;

import com.phloc.commons.id.IHasSimpleIntID;
import com.phloc.commons.lang.EnumHelper;

/**
 * Represents all known Gregorian Calendar days of a week as a type-safe enum
 * 
 * @author philip
 */
public enum EDayOfWeek implements IHasSimpleIntID
{
  MONDAY (DateTimeConstants.MONDAY),
  TUESDAY (DateTimeConstants.TUESDAY),
  WEDNESDAY (DateTimeConstants.WEDNESDAY),
  THURSDAY (DateTimeConstants.THURSDAY),
  FRIDAY (DateTimeConstants.FRIDAY),
  SATURDAY (DateTimeConstants.SATURDAY),
  SUNDAY (DateTimeConstants.SUNDAY);

  private final int m_nID;

  private EDayOfWeek (final int nID)
  {
    m_nID = nID;
  }

  public int getID ()
  {
    return m_nID;
  }

  @Nullable
  public static EDayOfWeek getFromIDOrNull (final int nID)
  {
    return EnumHelper.getFromIDOrNull (EDayOfWeek.class, nID);
  }
}
