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

@Immutable
public final class PDTToString
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTToString s_aInstance = new PDTToString ();

  private PDTToString ()
  {}

  public static String defaultToString (@Nonnull final LocalDate aDate, @Nonnull final Locale aDisplayLocale)
  {
    return PDTFormatter.getDefaultFormatterDate (aDisplayLocale).print (aDate);
  }

  public static String defaultToString (@Nonnull final LocalTime aTime, @Nonnull final Locale aDisplayLocale)
  {
    return PDTFormatter.getDefaultFormatterTime (aDisplayLocale).print (aTime);
  }

  public static String defaultToString (@Nonnull final LocalDateTime aDateTime, @Nonnull final Locale aDisplayLocale)
  {
    return PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale).print (aDateTime);
  }

  public static String defaultToString (@Nonnull final DateTime aDate, @Nonnull final Locale aDisplayLocale)
  {
    return PDTFormatter.getDefaultFormatterDateTime (aDisplayLocale).print (aDate);
  }

  public static String toString (@Nonnull final String sFormatPattern, @Nonnull final ReadablePartial aDate)
  {
    return toString (sFormatPattern, aDate, (Locale) null);
  }

  public static String toString (@Nonnull final String sFormatPattern,
                                 @Nonnull final ReadablePartial aDate,
                                 @Nullable final Locale aDisplayLocale)
  {
    return PDTFormatter.getForPattern (sFormatPattern, aDisplayLocale).print (aDate);
  }

  public static String toString (@Nonnull final String sFormatPattern, @Nonnull final ReadableInstant aDate)
  {
    return toString (sFormatPattern, aDate, (Locale) null);
  }

  public static String toString (@Nonnull final String sFormatPattern,
                                 @Nonnull final ReadableInstant aDate,
                                 @Nullable final Locale aDisplayLocale)
  {
    return PDTFormatter.getForPattern (sFormatPattern, aDisplayLocale).print (aDate);
  }
}
