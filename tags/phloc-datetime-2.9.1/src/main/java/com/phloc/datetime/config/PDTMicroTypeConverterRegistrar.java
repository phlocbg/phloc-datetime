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
package com.phloc.datetime.config;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistry;
import com.phloc.commons.microdom.convert.impl.StringBasedMicroTypeConverter;

@Immutable
@IsSPIImplementation
public final class PDTMicroTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter (@Nonnull final IMicroTypeConverterRegistry aRegistry)
  {
    // Register XML converters based on the String converter
    aRegistry.registerMicroElementTypeConverter (LocalDate.class, new StringBasedMicroTypeConverter (LocalDate.class));
    aRegistry.registerMicroElementTypeConverter (LocalTime.class, new StringBasedMicroTypeConverter (LocalTime.class));
    aRegistry.registerMicroElementTypeConverter (LocalDateTime.class,
                                                 new StringBasedMicroTypeConverter (LocalDateTime.class));
    aRegistry.registerMicroElementTypeConverter (DateTime.class, new StringBasedMicroTypeConverter (DateTime.class));
    aRegistry.registerMicroElementTypeConverter (Duration.class, new StringBasedMicroTypeConverter (Duration.class));
    aRegistry.registerMicroElementTypeConverter (Period.class, new StringBasedMicroTypeConverter (Period.class));
    // J2SE stuff
    aRegistry.registerMicroElementTypeConverter (Date.class, new StringBasedMicroTypeConverter (Date.class));
    aRegistry.registerMicroElementTypeConverter (GregorianCalendar.class,
                                                 new StringBasedMicroTypeConverter (Calendar.class));
  }
}
