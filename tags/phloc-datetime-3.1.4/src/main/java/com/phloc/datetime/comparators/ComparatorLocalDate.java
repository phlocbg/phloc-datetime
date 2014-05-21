/**
 * Copyright (C) 2006-2014 phloc systems
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
import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.compare.CompareUtils;
import com.phloc.commons.compare.ESortOrder;

/**
 * Comparator for {@link LocalDate} objects.
 * 
 * @author Philip Helger
 */
public class ComparatorLocalDate extends AbstractComparator <LocalDate>
{
  private final boolean m_bNullValueComeFirst;

  public ComparatorLocalDate ()
  {
    this (CompareUtils.DEFAULT_NULL_VALUES_COME_FIRST);
  }

  public ComparatorLocalDate (@Nonnull final ESortOrder eSortOrder)
  {
    this (eSortOrder, CompareUtils.DEFAULT_NULL_VALUES_COME_FIRST);
  }

  public ComparatorLocalDate (final boolean bNullValueComeFirst)
  {
    m_bNullValueComeFirst = bNullValueComeFirst;
  }

  public ComparatorLocalDate (@Nonnull final ESortOrder eSortOrder, final boolean bNullValueComeFirst)
  {
    super (eSortOrder);
    m_bNullValueComeFirst = bNullValueComeFirst;
  }

  @Override
  protected int mainCompare (@Nullable final LocalDate aDateTime1, @Nullable final LocalDate aDateTime2)
  {
    return CompareUtils.nullSafeCompare (aDateTime1, aDateTime2, m_bNullValueComeFirst);
  }
}
