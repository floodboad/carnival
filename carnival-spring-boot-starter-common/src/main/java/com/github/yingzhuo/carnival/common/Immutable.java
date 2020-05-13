/*
 *  ____    _    ____  _   _ _____     ___    _
 * / ___|  / \  |  _ \| \ | |_ _\ \   / / \  | |
 * | |    / _ \ | |_) |  \| || | \ \ / / _ \ | |
 * | |___/ ___ \|  _ <| |\  || |  \ V / ___ \| |___
 * \____/_/   \_\_| \_\_| \_|___|  \_/_/   \_\_____|
 *
 * https://github.com/yingzhuo/carnival
 */
package com.github.yingzhuo.carnival.common;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @since 1.6.7
 */
@SuppressWarnings("unchecked")
public interface Immutable {

    public static <T> Set<T> set(T... objs) {
        final Set<T> set = new HashSet<>();
        if (objs == null || objs.length == 0) {
            return Collections.emptySet();
        } else {
            Collections.addAll(set, objs);
        }
        return Collections.unmodifiableSet(set);
    }

    public static <E extends Enum<E>> Set<E> set(Class<E> enumType) {
        if (enumType == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(enumType.getEnumConstants())));
    }

    // ----------------------------------------------------------------------------------------------------------------

    public static <T> List<T> list(T... objs) {
        if (objs == null || objs.length == 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(objs));
    }

    public static <E extends Enum<E>> List<E> list(Class<E> enumType) {
        if (enumType == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(enumType.getEnumConstants()));
    }

    // ----------------------------------------------------------------------------------------------------------------

    public static <T> Stream<T> stream(T... objs) {
        return list(objs).stream();
    }

    public static <E extends Enum<E>> Stream<E> stream(Class<E> type) {
        return list(type).stream();
    }

    // ----------------------------------------------------------------------------------------------------------------

    public static <K, V> KeyValue<K, V> kv(K k, V v) {
        return new KeyValue<>(k, v);
    }

    public static <K, V> Map<K, V> map(KeyValue<K, V>... objs) {
        if (objs == null || objs.length == 0) {
            return Collections.emptyMap();
        }

        final Map<K, V> map = new HashMap<>();
        for (KeyValue kv : objs) {
            map.put((K) kv.getKey(), (V) kv.getValue());
        }
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> Map<K, V> map(K key, V value) {
        final Map<K, V> map = new HashMap<>();
        map.put(key, value);
        return Collections.unmodifiableMap(map);
    }

    // KV
    // ----------------------------------------------------------------------------------------------------------------

    public static class KeyValue<K, V> implements Map.Entry<K, V> {

        private final K k;
        private final V v;

        private KeyValue(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }
    }

}
