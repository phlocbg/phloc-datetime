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
package com.phloc.datetime.period;

import javax.annotation.Nullable;

import org.joda.time.LocalTime;

/**
 * Default implementation of the {@link ILocalTimePeriod} interface.
 * 
 * @author philip
 */
public final class LocalTimePeriod extends AbstractLocalTimePeriod
{
  public LocalTimePeriod ()
  {}

  public LocalTimePeriod (@Nullable final LocalTime aStartLocalTime)
  {
    setStartLocalTime (aStartLocalTime);
  }

  public LocalTimePeriod (@Nullable final LocalTime aStartLocalTime, @Nullable final LocalTime aEndLocalTime)
  {
    setStartLocalTime (aStartLocalTime);
    setEndLocalTime (aEndLocalTime);
  }

  public void setStartLocalTime (@Nullable final LocalTime aStartLocalTime)
  {
    m_aStartLocalTime = aStartLocalTime;
  }

  public void setEndLocalTime (@Nullable final LocalTime aEndLocalTime)
  {
    m_aEndLocalTime = aEndLocalTime;
  }
}
