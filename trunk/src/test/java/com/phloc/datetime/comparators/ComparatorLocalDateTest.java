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
package com.phloc.datetime.comparators;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.phloc.commons.collections.ContainerHelper;
import com.phloc.commons.compare.ESortOrder;
import com.phloc.datetime.PDTFactory;

/**
 * Test class for class {@link ComparatorLocalDate}.
 * 
 * @author philip
 */
public final class ComparatorLocalDateTest
{
  @Test
  public void testAll ()
  {
    final LocalDate aObj1 = PDTFactory.createLocalDate (2000, 5, 5);
    final LocalDate aObj2 = PDTFactory.createLocalDate (1000, 5, 5);
    final List <LocalDate> aList = ContainerHelper.newList (aObj1, aObj2);
    List <LocalDate> aSorted = ContainerHelper.getSorted (aList, new ComparatorLocalDate ());
    assertEquals (aObj2, aSorted.get (0));
    assertEquals (aObj1, aSorted.get (1));
    aSorted = ContainerHelper.getSorted (aList, new ComparatorLocalDate (ESortOrder.DESCENDING));
    assertEquals (aObj1, aSorted.get (0));
    assertEquals (aObj2, aSorted.get (1));
  }
}
