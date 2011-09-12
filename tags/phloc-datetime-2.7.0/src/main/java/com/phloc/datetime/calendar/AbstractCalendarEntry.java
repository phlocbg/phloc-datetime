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
package com.phloc.datetime.calendar;

import org.joda.time.LocalDateTime;

public abstract class AbstractCalendarEntry implements ICalendarEntry
{
  protected String m_sDisplayName;
  protected LocalDateTime m_aStartDate;
  protected LocalDateTime m_aEndDate;
  protected String m_sLink;

  protected boolean m_bStartOfEntrySequence = false;
  protected boolean m_bEndOfEntrySequence = false;

  public boolean isStartOfSequence ()
  {
    return m_bStartOfEntrySequence;
  }

  public void setStartOfSequence (final boolean bStart)
  {
    m_bStartOfEntrySequence = bStart;
  }

  public boolean isEndOfSequence ()
  {
    return m_bEndOfEntrySequence;
  }

  public void setEndOfSequence (final boolean bEnd)
  {
    m_bEndOfEntrySequence = bEnd;
  }

  public String getDisplayName ()
  {
    return m_sDisplayName;
  }

  public LocalDateTime getEndDate ()
  {
    return m_aEndDate;
  }

  public String getLink ()
  {
    return m_sLink;
  }

  public LocalDateTime getStartDate ()
  {
    return m_aStartDate;
  }

  public void setDisplayName (final String sDisplayName)
  {
    m_sDisplayName = sDisplayName;
  }

  public void setEndDate (final LocalDateTime aEndDate)
  {
    m_aEndDate = aEndDate;
  }

  public void setLink (final String sLink)
  {
    m_sLink = sLink;
  }

  public void setStartDate (final LocalDateTime aStartDate)
  {
    m_aStartDate = aStartDate;
  }
}
