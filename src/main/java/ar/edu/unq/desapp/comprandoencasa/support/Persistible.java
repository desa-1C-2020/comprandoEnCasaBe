package ar.edu.unq.desapp.comprandoencasa.support; /**
 * 11/03/2011 14:04:39 Copyright (C) 2011 10Pines S.R.L.
 */



/**
 * This interface represents a persistent entity that is identified by an object that represents your ID
 *
 *  @author D. García
 */
public interface Persistible<K> {

  /**
   * Returns the object that represents the ID of this entity
   *
   * @return The id of this entity or null if not yet persisted
   */
  K getId();

  /**
   * Set the ID of this entity. <br>
   * Normally it is not necessary to call this method, since hibernate assigns one to the
   * to persist
   *
   * @param id The new ID for this entity
   */
  void setId(K id);
}