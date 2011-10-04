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
package com.phloc.datetime.config;

import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.phloc.commons.annotations.IsSPIImplementation;
import com.phloc.commons.microdom.convert.IMicroTypeConverterRegistrarSPI;
import com.phloc.commons.microdom.convert.MicroTypeConverterRegistry;
import com.phloc.commons.microdom.convert.impl.StringBasedMicroTypeConverter;

@Immutable
@IsSPIImplementation
public final class PDTMicroTypeConverterRegistrar implements IMicroTypeConverterRegistrarSPI
{
  public void registerMicroTypeConverter ()
  {
    // Register XML converters based on the String converter
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (LocalDate.class,
                                                                  new StringBasedMicroTypeConverter (LocalDate.class));
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (LocalTime.class,
                                                                  new StringBasedMicroTypeConverter (LocalTime.class));
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (LocalDateTime.class,
                                                                  new StringBasedMicroTypeConverter (LocalDateTime.class));
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (DateTime.class,
                                                                  new StringBasedMicroTypeConverter (DateTime.class));
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (Duration.class,
                                                                  new StringBasedMicroTypeConverter (Duration.class));
    MicroTypeConverterRegistry.registerMicroElementTypeConverter (Period.class,
                                                                  new StringBasedMicroTypeConverter (Period.class));
  }
}
