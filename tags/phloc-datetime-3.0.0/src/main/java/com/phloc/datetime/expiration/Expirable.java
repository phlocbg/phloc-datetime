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
package com.phloc.datetime.expiration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.phloc.commons.equals.EqualsUtils;
import com.phloc.commons.hash.HashCodeGenerator;
import com.phloc.commons.state.EChange;
import com.phloc.commons.string.ToStringGenerator;

/**
 * Default implementation of {@link IExpirable}
 * 
 * @author philip
 */
public final class Expirable implements IExpirable
{
  private DateTime m_aExpirationDateTime;

  public Expirable ()
  {}

  public Expirable (@Nullable final DateTime aExpirationDateTime)
  {
    m_aExpirationDateTime = aExpirationDateTime;
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
    if (!(o instanceof Expirable))
      return false;
    final Expirable rhs = (Expirable) o;
    return EqualsUtils.equals (m_aExpirationDateTime, rhs.m_aExpirationDateTime);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aExpirationDateTime).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("expirationDT", m_aExpirationDateTime).toString ();
  }
}
