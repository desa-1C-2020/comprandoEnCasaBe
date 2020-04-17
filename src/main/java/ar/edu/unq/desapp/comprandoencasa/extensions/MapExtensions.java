package ar.edu.unq.desapp.comprandoencasa.extensions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Helpful methods to interact with maps!
 */
public class MapExtensions {

    @SafeVarargs
    public static <K, V> Map<K, V> mapOf(Pair<K, V>... pairs) {
        HashMap<K, V> map = new HashMap<>();
        Arrays.stream(pairs).forEach(kvPair -> map.put(kvPair.getFirst(), kvPair.getSecond()));
        return map;
    }

    public static <K, V> Pair<K, V> pair(K key, V value) {
        return new Pair<>(key, value);
    }

    public static class Pair<K, V> {

        private final V second;

        private final K first;

        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public V getSecond() {
            return second;
        }

        public K getFirst() {
            return first;
        }
    }
}
