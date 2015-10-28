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

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

import com.phloc.commons.annotations.PresentForCodeCoverage;

/**
 * Standard API to convert a date, time or date time to a {@link String}.
 * 
 * @author Philip Helger
 */
@Immutable
public final class PDTToString
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTToString s_aInstance = new PDTToString ();

  private PDTToString ()
  {}

  @Nullable
  public static String getAsString (@Nullable final LocalDate aDate, @Nonnull final Locale aDisplayLocale)
  {
    return aDate == null ? null : PDTFormatter.getDefaultFormatterDate (aDisplayLocale).print (aDate);
  }

  @Nullable
  public static String getAsString (@Nullable final LocalTime aTime, @Nonnull final Locale aDisplayLocale)
  {
    return aTime == null ? null : PDTFormatter.getDefaultFormatterTime (aDisplayLocale).print (aTime);
  }

  @Nullable
  public static String getAsString (@Nullable final LocalDateTime aDateTime, @Nonnull final Locale aDisplayLocale)
  {
    return aDateTime == null ? null : PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale).print (aDateTime);
  }

  @Nullable
  public static String getAsString (@Nullable final DateTime aDateTime, @Nonnull final Locale aDisplayLocale)
  {
    return aDateTime == null ? null : PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale).print (aDateTime);
  }

  @Nullable
  public static String getAsString (@Nonnull final String sFormatPattern, @Nullable final ReadablePartial aPartial)
  {
    return getAsString (sFormatPattern, aPartial, (Locale) null);
  }

  @Nullable
  public static String getAsString (@Nonnull final String sFormatPattern,
                                    @Nullable final ReadablePartial aPartial,
                                    @Nullable final Locale aDisplayLocale)
  {
    return aPartial == null ? null : PDTFormatter.getForPattern (sFormatPattern, aDisplayLocale).print (aPartial);
  }

  @Nullable
  public static String getAsString (@Nonnull final String sFormatPattern, @Nullable final ReadableInstant aInstant)
  {
    return getAsString (sFormatPattern, aInstant, (Locale) null);
  }

  @Nullable
  public static String getAsString (@Nonnull final String sFormatPattern,
                                    @Nullable final ReadableInstant aInstant,
                                    @Nullable final Locale aDisplayLocale)
  {
    return aInstant == null ? null : PDTFormatter.getForPattern (sFormatPattern, aDisplayLocale).print (aInstant);
  }
}
