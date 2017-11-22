/**
 * Copyright (C) 2006-2015 phloc systems
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

import org.joda.time.DateTime;

import com.phloc.commons.compare.AbstractComparator;
import com.phloc.commons.compare.CompareUtils;
import com.phloc.commons.compare.ESortOrder;
import com.phloc.commons.compare.GlobalCompareSettings;

/**
 * Comparator for {@link DateTime} objects.
 * 
 * @author Philip Helger
 */
public class ComparatorDateTime extends AbstractComparator <DateTime>
{
  private final boolean m_bNullValueComeFirst;

  public ComparatorDateTime ()
  {
    this (GlobalCompareSettings.getInstance ().isSortNullValuesFirst ());
  }

  public ComparatorDateTime (@Nonnull final ESortOrder eSortOrder)
  {
    this (eSortOrder, GlobalCompareSettings.getInstance ().isSortNullValuesFirst ());
  }

  public ComparatorDateTime (final boolean bNullValueComeFirst)
  {
    this.m_bNullValueComeFirst = bNullValueComeFirst;
  }

  public ComparatorDateTime (@Nonnull final ESortOrder eSortOrder, final boolean bNullValueComeFirst)
  {
    super (eSortOrder);
    this.m_bNullValueComeFirst = bNullValueComeFirst;
  }

  @Override
  protected int mainCompare (@Nullable final DateTime aDateTime1, @Nullable final DateTime aDateTime2)
  {
    return CompareUtils.nullSafeCompare (aDateTime1, aDateTime2, this.m_bNullValueComeFirst);
  }
}
