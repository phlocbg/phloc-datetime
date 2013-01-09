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
package com.phloc.datetime;

import java.util.Calendar;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTimeConstants;

import com.phloc.commons.id.IHasSimpleIntID;
import com.phloc.commons.lang.DateFormatSymbolsFactory;
import com.phloc.commons.lang.EnumHelper;

/**
 * Represents all known Gregorian Calendar month as a type-safe enum
 * 
 * @author philip
 */
public enum EMonth implements IHasSimpleIntID
{
  JANUARY (DateTimeConstants.JANUARY, Calendar.JANUARY),
  FEBRUARY (DateTimeConstants.FEBRUARY, Calendar.FEBRUARY),
  MARCH (DateTimeConstants.MARCH, Calendar.MARCH),
  APRIL (DateTimeConstants.APRIL, Calendar.APRIL),
  MAY (DateTimeConstants.MAY, Calendar.MAY),
  JUNE (DateTimeConstants.JUNE, Calendar.JUNE),
  JULY (DateTimeConstants.JULY, Calendar.JULY),
  AUGUST (DateTimeConstants.AUGUST, Calendar.AUGUST),
  SEPTEMBER (DateTimeConstants.SEPTEMBER, Calendar.SEPTEMBER),
  OCTOBER (DateTimeConstants.OCTOBER, Calendar.OCTOBER),
  NOVEMBER (DateTimeConstants.NOVEMBER, Calendar.NOVEMBER),
  DECEMBER (DateTimeConstants.DECEMBER, Calendar.DECEMBER);

  private final int m_nJodaID;
  private final int m_nCalID;

  private EMonth (final int nJodaID, final int nCalID)
  {
    m_nJodaID = nJodaID;
    m_nCalID = nCalID;
  }

  public int getID ()
  {
    return m_nJodaID;
  }

  /**
   * @return The joda time ID
   */
  public int getDateTimeConstant ()
  {
    return m_nJodaID;
  }

  /**
   * @return The java.util.Calendar ID
   */
  public int getCalendarConstant ()
  {
    return m_nCalID;
  }

  @Nullable
  public String getMonthName (@Nonnull final Locale aLocale)
  {
    return DateFormatSymbolsFactory.getInstance (aLocale).getMonths ()[m_nCalID];
  }

  @Nullable
  public String getMonthShortName (@Nonnull final Locale aLocale)
  {
    return DateFormatSymbolsFactory.getInstance (aLocale).getShortMonths ()[m_nCalID];
  }

  @Nullable
  public static EMonth getFromIDOrNull (final int nID)
  {
    return EnumHelper.getFromIDOrNull (EMonth.class, nID);
  }
}
