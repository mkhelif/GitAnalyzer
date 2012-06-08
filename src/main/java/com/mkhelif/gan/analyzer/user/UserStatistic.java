package com.mkhelif.gan.analyzer.user;

import com.google.common.base.Objects;

/**
 * Statistic of a single user.
 *
 * @author mkhelif
 */
public final class UserStatistic {

	/**
	 * Number of commits authored by the user.
	 */
	private final int authored;

	/**
	 * Number of commits committed by the user.
	 */
	private final int committed;

	public UserStatistic(final int authored, final int committed) {
		this.authored = authored;
		this.committed = committed;
	}

	public int getAuthored() { return authored; }
	public int getCommitted() { return committed; }

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(authored, committed);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (object instanceof UserStatistic) {
			return this.hashCode() == object.hashCode();
		}
		return false;
	}
}