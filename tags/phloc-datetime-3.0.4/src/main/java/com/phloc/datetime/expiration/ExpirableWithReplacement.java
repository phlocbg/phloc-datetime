/**
 * Copyright (C) 2006-2013 phloc systems
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of {@link IExpirableWithReplacement}
 * 
 * @author philip
 * @param <DATATYPE>
 *        The type of the object use for defining a replacement.
 */
public final class ExpirableWithReplacement <DATATYPE> implements IExpirableWithReplacement <DATATYPE>
{
  private DateTime m_aExpirationDateTime;
  private DATATYPE m_aReplacement;

  public ExpirableWithReplacement ()
  {}

  public ExpirableWithReplacement (@Nullable final DateTime aExpirationDateTime, @Nullable final DATATYPE aReplacement)
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

  @Nonnull
  public EChange setExpirationDateTime (@Nullable final DateTime aExpirationDateTime)
  {
    if (EqualsUtils.equals (aExpirationDateTime, m_aExpirationDateTime))
      return EChange.UNCHANGED;
    m_aExpirationDateTime = aExpirationDateTime;
    return EChange.CHANGED;
  }

  @Nullable
  public DATATYPE getReplacement ()
  {
    return m_aReplacement;
  }

  @Nonnull
  public EChange setReplacement (@Nullable final DATATYPE aReplacement)
  {
    if (EqualsUtils.equals (aReplacement, m_aReplacement))
      return EChange.UNCHANGED;
    m_aReplacement = aReplacement;
    return EChange.CHANGED;
  }

  @Override
  public EChange resetExpiration ()
  {
    return setExpirationDateTime (null);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!(o instanceof ExpirableWithReplacement <?>))
      return false;
    final ExpirableWithReplacement <?> rhs = (ExpirableWithReplacement <?>) o;
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
