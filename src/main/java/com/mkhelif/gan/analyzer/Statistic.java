package com.mkhelif.gan.analyzer;

import java.util.Map;

import com.google.common.base.Objects;

/**
 * Statistic of a Git repository.
 *
 * @author mkhelif
 */
public final class Statistic {

	/**
	 * Number of commits by date.
	 */
	private final Map<String, Object> counters;

	public Statistic(final Map<String, Object> counters) {
		this.counters = counters;
	}

	/**
	 * Retrieve the values of a specific counter.
	 * @param counter the counter to retrieve.
	 * @return the extracted values of the counter.
	 */
	public Object getCounter(final String counter) {
		return counters.get(counter);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(counters);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (object instanceof Statistic) {
			return this.hashCode() == object.hashCode();
		}
		return false;
	}
}