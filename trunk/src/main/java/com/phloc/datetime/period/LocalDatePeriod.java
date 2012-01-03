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

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

/**
 * Default implementation of the {@link ILocalDatePeriod} interface.
 * 
 * @author philip
 */
public final class LocalDatePeriod extends AbstractLocalDatePeriod
{
  public LocalDatePeriod ()
  {}

  public LocalDatePeriod (@Nullable final LocalDate aStartLocalDate)
  {
    setStartLocalDate (aStartLocalDate);
  }

  public LocalDatePeriod (@Nullable final LocalDate aStartLocalDate, @Nullable final LocalDate aEndLocalDate)
  {
    setStartLocalDate (aStartLocalDate);
    setEndLocalDate (aEndLocalDate);
  }

  public void setStartLocalDate (@Nullable final LocalDate aStartLocalDate)
  {
    m_aStartLocalDate = aStartLocalDate;
  }

  public void setEndLocalDate (@Nullable final LocalDate aEndLocalDate)
  {
    m_aEndLocalDate = aEndLocalDate;
  }
}
