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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.phloc.commons.annotations.PresentForCodeCoverage;
import com.phloc.datetime.config.PDTConfig;

/**
 * Create common {@link DateTimeFormatter} objects used for printing and parsing
 * date and time objects.
 * 
 * @author philip
 */
@Immutable
public final class PDTFormatter
{
  public static final int DEFAULT_STYLE_DATE = DateFormat.MEDIUM;
  public static final int DEFAULT_STYLE_TIME = DateFormat.MEDIUM;

  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final PDTFormatter s_aInstance = new PDTFormatter ();

  private PDTFormatter ()
  {}

  @Nonnull
  private static DateTimeFormatter _withLocaleAndChrono (@Nonnull final DateTimeFormatter aFormatter,
                                                         @Nullable final Locale aDisplayLocale)
  {
    return aFormatter.withLocale (aDisplayLocale).withChronology (PDTConfig.getDefaultChronology ());
  }

  @Nonnull
  public static DateTimeFormatter getDefaultFormatterDate (@Nullable final Locale aDisplayLocale)
  {
    return _withLocaleAndChrono (DateTimeFormat.mediumDate (), aDisplayLocale);
  }

  @Nonnull
  public static DateTimeFormatter getDefaultFormatterTime (@Nullable final Locale aDisplayLocale)
  {
    return _withLocaleAndChrono (DateTimeFormat.mediumTime (), aDisplayLocale);
  }

  @Nonnull
  public static DateTimeFormatter getDefaultFormatterDateTime (@Nullable final Locale aDisplayLocale)
  {
    return _withLocaleAndChrono (DateTimeFormat.mediumDateTime (), aDisplayLocale);
  }

  /**
   * Get the {@link DateTimeFormatter} for the given pattern, using our default
   * chronology.
   * 
   * @param sPattern
   *        The pattern to be parsed
   * @return The formatter object.
   * @throws IllegalArgumentException
   *         If the pattern is illegal
   */
  @Nonnull
  public static DateTimeFormatter getForPattern (@Nonnull final String sPattern) throws IllegalArgumentException
  {
    return getForPattern (sPattern, null);
  }

  /**
   * Get the {@link DateTimeFormatter} for the given pattern and locale, using
   * our default chronology.
   * 
   * @param sPattern
   *        The pattern to be parsed
   * @param aDisplayLocale
   *        The locale to be used. May be <code>null</code>.
   * @return The formatter object.
   * @throws IllegalArgumentException
   *         If the pattern is illegal
   */
  @Nonnull
  public static DateTimeFormatter getForPattern (@Nonnull final String sPattern, @Nullable final Locale aDisplayLocale) throws IllegalArgumentException
  {
    return _withLocaleAndChrono (DateTimeFormat.forPattern (sPattern), aDisplayLocale);
  }

  public static String getDefaultPatternDate (@Nonnull final Locale aDisplayLocale)
  {
    // Not nice but it works
    return ((SimpleDateFormat) DateFormat.getDateInstance (DEFAULT_STYLE_DATE, aDisplayLocale)).toPattern ();
  }

  @Nonnull
  public static String getDefaultPatternTime (@Nonnull final Locale aDisplayLocale)
  {
    // Not nice but it works
    return ((SimpleDateFormat) DateFormat.getTimeInstance (DEFAULT_STYLE_TIME, aDisplayLocale)).toPattern ();
  }

  @Nonnull
  public static String getDefaultPatternDateTime (@Nonnull final Locale aDisplayLocale)
  {
    // Not nice but it works
    return ((SimpleDateFormat) DateFormat.getDateTimeInstance (DEFAULT_STYLE_DATE, DEFAULT_STYLE_TIME, aDisplayLocale)).toPattern ();
  }
}
