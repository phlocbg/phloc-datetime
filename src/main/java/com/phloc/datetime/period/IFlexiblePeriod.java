package com.phloc.datetime.period;

import javax.annotation.Nonnull;

/**
 * Base interface for an object that has a validity range based on arbitrary
 * objects.
 * 
 * @author philip
 * @param <DATATYPE>
 *        Date and time type
 */
public interface IFlexiblePeriod <DATATYPE> extends IPeriodProvider, IHasStartAndEnd <DATATYPE>
{
  /**
   * Check if this object is valid for this specific date.
   * 
   * @param aDate
   *        The date to be checked. May not be <code>null</code>.
   * @return <code>true</code> if this object is valid for this date,
   *         <code>false</code> otherwise.
   */
  boolean isValidFor (@Nonnull DATATYPE aDate);

  /**
   * This is a shortcut method for checking the validity of the object for the
   * current date and time.
   * 
   * @return <code>true</code> if this object is valid for the current date,
   *         <code>false</code> otherwise.
   */
  boolean isValidForNow ();
}
