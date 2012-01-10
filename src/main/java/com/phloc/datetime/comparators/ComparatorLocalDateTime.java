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
package com.phloc.datetime.comparators;

import javax.annotation.Nonnull;

import org.joda.time.LocalDateTime;

import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.compare.ESortOrder;

/**
 * Comparator for {@link LocalDateTime} objects.
 * 
 * @author philip
 */
public final class ComparatorLocalDateTime extends AbstractComparator <LocalDateTime>
{
  public ComparatorLocalDateTime ()
  {}

  public ComparatorLocalDateTime (@Nonnull final ESortOrder eSortOrder)
  {
    super (eSortOrder);
  }

  @Override
  protected int mainCompare (@Nonnull final LocalDateTime aDateTime1, @Nonnull final LocalDateTime aDateTime2)
  {
    return aDateTime1.compareTo (aDateTime2);
  }
}
