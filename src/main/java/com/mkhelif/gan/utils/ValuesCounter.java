package com.mkhelif.gan.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableSortedMap;

/**
 * Object used to build a map of counted values.
 *
 * @author mkhelif
 */
public final class ValuesCounter<K> {

	/**
	 * Counted values by key.
	 */
	private final Map<K, Integer> values;

	public ValuesCounter() {
		values = new HashMap<>();
	}

	/**
	 * Increment the value for the key.
	 * @param key the key to increment.
	 */
	public void increment(final K key) {
		if (!values.containsKey(key)) {
			values.put(key, 0);
		}
		values.put(key, values.get(key) + 1);
	}

	/**
	 * @return the map of values.
	 */
	public Map<K, Integer> build() {
		return ImmutableSortedMap.copyOf(values);
	}
}