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
package com.phloc.datetime.expiration;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of {@link IReadonlyExpirableWithReplacement}
 * 
 * @author Philip Helger
 * @param <DATATYPE>
 *        The type of the object use for defining a replacement.
 */
public final class ReadonlyExpirableWithReplacement <DATATYPE> implements IReadonlyExpirableWithReplacement <DATATYPE>
{
  private final DateTime m_aExpirationDateTime;
  private final DATATYPE m_aReplacement;

  public ReadonlyExpirableWithReplacement (@Nullable final DateTime aExpirationDateTime,
                                           @Nullable final DATATYPE aReplacement)
  {
    m_aExpirationDateTime = aExpirationDateTime;
    m_aReplacement = aReplacement;
  }

  public boolean isExpirationDefined ()
  {
    return m_aExpirationDateTime != null;
  }

  public boolean isExpired ()
  {
    return isExpirationDefined () && getExpirationDateTime ().isBeforeNow ();
  }

  @Nullable
  public DateTime getExpirationDateTime ()
  {
    return m_aExpirationDateTime;
  }

  @Nullable
  public DATATYPE getReplacement ()
  {
    return m_aReplacement;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof ReadonlyExpirableWithReplacement <?>))
      return false;
    final ReadonlyExpirableWithReplacement <?> rhs = (ReadonlyExpirableWithReplacement <?>) o;
    return EqualsUtils.equals (m_aExpirationDateTime, rhs.m_aExpirationDateTime) &&
           EqualsUtils.equals (m_aReplacement, rhs.m_aReplacement);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExpirationDateTime).append (m_aReplacement).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expirationDT", m_aExpirationDateTime)
                                       .append ("replacement", m_aReplacement)
                                       .toString ();
  }
}
