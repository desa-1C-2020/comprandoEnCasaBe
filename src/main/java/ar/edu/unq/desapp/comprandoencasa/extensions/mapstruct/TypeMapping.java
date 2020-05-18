package ar.edu.unq.desapp.comprandoencasa.extensions.mapstruct;

import com.google.common.base.MoreObjects;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * This class represents a mapping direction between two types
 */
public class TypeMapping {

    private Type source;
    private Type destination;

    public static TypeMapping create(Type source, Type destination) {
        TypeMapping mapping = new TypeMapping();
        mapping.source = source;
        mapping.destination = destination;
        return mapping;
    }

    public Type getSource() {
        return source;
    }

    public Type getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeMapping that = (TypeMapping) o;
        return Objects.equals(source, that.source) &&
            Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .addValue(source.getTypeName())
            .addValue(" -> ")
            .addValue(destination.getTypeName())
            .toString();
    }
}
