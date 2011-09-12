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

import com.phloc.commons.name.IHasDisplayName;

public interface ICalendarEntry extends IHasDisplayName
{
  /**
   * Get the Start Date of this Calendar Entry.
   * 
   * @return The start date of a calendar entry
   */
  LocalDateTime getStartDate ();

  void setStartDate (LocalDateTime aStartDate);

  /**
   * Get the End Date of this Calendar Entry.
   * 
   * @return The end date of a calendar entry May be Null for punctual entries
   */
  LocalDateTime getEndDate ();

  void setEndDate (LocalDateTime aEndDate);

  /**
   * Get the Name to Display this entry.
   * 
   * @return the display name already localized.
   */
  String getDisplayName ();

  void setDisplayName (String aDisplayName);

  /**
   * @return The link for the calendar entry. May not be HTML escaped!
   */
  String getLink ();

  void setLink (String sLink);

  /**
   * Tells if this entry is the first of multiple entries belonging together.
   * 
   * @return true if it is the start of such a sequence, else false
   */
  boolean isStartOfSequence ();

  void setStartOfSequence (boolean bStart);

  /**
   * Tells if this entry is the last of multiple entries belonging together.
   * 
   * @return true if it is the end of such a sequence, else false
   */
  boolean isEndOfSequence ();

  void setEndOfSequence (boolean bEnd);
}
