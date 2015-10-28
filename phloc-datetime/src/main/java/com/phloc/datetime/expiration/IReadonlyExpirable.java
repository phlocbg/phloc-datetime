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

/**
 * Read-only interface for objects that can expire.
 * 
 * @author Philip Helger
 */
public interface IReadonlyExpirable
{
  /**
   * Check if the object has an expiration date defined. To check if the page is
   * already expired, use {@link #isExpired()} instead.
   * 
   * @return <code>true</code> if an expiration date is defined,
   *         <code>false</code> otherwise.
   */
  boolean isExpirationDefined ();

  /**
   * Check if this object is already expired. This is only possible if an
   * expiration date is defined.
   * 
   * @return <code>true</code> if an expiration date is defined and the
   *         expiration date is in the past, <code>false</code> otherwise.
   * @see #isExpirationDefined()
   */
  boolean isExpired ();

  /**
   * @return The date time when the object will expire/expired. May be
   *         <code>null</code> if no expiration is defined.
   */
  @Nullable
  DateTime getExpirationDateTime ();
}
