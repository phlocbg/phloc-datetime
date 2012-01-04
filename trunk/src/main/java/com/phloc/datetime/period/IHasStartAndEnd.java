package com.phloc.datetime.period;

import javax.annotation.Nullable;

/**
 * Base interface for an object that has a start and an end.
 * 
 * @author philip
 * @param <DATATYPE>
 *        Date and time type
 */
public interface IHasStartAndEnd <DATATYPE>
{
  /**
   * @return The start. May be <code>null</code>.
   */
  @Nullable
  DATATYPE getStart ();

  /**
   * @return The end. May be <code>null</code>.
   */
  @Nullable
  DATATYPE getEnd ();
}
