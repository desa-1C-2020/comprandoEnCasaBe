package ar.edu.unq.desapp.comprandoencasa.support; /**
 * 11/03/2011 14:27:00 Copyright (C) 2006 Darío L. García
 * <p>
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Text"
 * property="dct:title" rel="dct:type">Software</span> by <span
 * xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Darío García</span> is
 * licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative
 * Commons Attribution 3.0 Unported License</a>.
 */

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Superclass for persistent entities that implements their ID with a Long.
 * The equals and hashcode are redefined to use the ID as a reference for equality
 *
 * @author D. García
 */
@MappedSuperclass
public class PersistibleSupport implements Persistible<Long> {
    public static final String id_FIELD = "id";
    private static final Logger LOG = LoggerFactory.getLogger(PersistibleSupport.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * This field allows to detect concurrent modifications on the entities in form
     * optimistic. If the lockout fails, the last transaction to close will have a
     * {@link HibernateOptimisticLockingFailureException}, indicating that another transaction
     * modified the data
     */
    @Version
    private Long persistenceVersion;

    public Long getPersistenceVersion() {
        return persistenceVersion;
    }

    public void setPersistenceVersion(final Long persistenceVersion) {
        this.persistenceVersion = persistenceVersion;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Compare this entity with another considering it by ID.
     * They will be considered equal only if they are the same instance or if being of the same type they have
     * the same ID.
     * If any of the entities does not have an ID, then different entities will be considered. (to the
     * persist each one will have its different ID)
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            // Stops in the easiest and most common case
            return true;
        }
        if (obj == null) {
            // never are equal to a null
            return false;
        }
        final Long thisId = this.getId();
        if (thisId == null) {
            return false;
        }
        if (!this.getClass().isAssignableFrom(obj.getClass()) && !obj.getClass().isAssignableFrom(this.getClass())) {
            // If it is not of the same type we assume that they are different tables and therefore objects
            // different, the id could match but they are different entities
            LOG.debug("Considering [{}] and [{}] as being different classes", this, obj);
            return false;
        }
        final Persistible<?> other = (Persistible<?>) obj;
        // Finally, are considered equal if has the same ID and is of the same type
        return thisId.equals(other.getId());
    }

    /**
     * Hashcode implementation using the ID as a reference hashcode. <br>
     * This implementation uses the traditional hashcode if this entity has no ID, so there is
     * Be careful when adding entities without persisting in collections that use the hashcode.
     * For example a HashSet, which you can consider as different from the same entity before and after persisted
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (this.getId() != null) {
            return this.getId().hashCode();
        } else {
            LOG.debug("Using Object.hashCode () for a persistible because it has no ID");
            return super.hashCode();
        }
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add(id_FIELD, id)
            .toString();
    }

}
