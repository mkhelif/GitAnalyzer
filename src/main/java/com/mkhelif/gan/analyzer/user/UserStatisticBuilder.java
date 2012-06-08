package com.mkhelif.gan.analyzer.user;


/**
 * Statistic of a single user.
 *
 * @author mkhelif
 */
public final class UserStatisticBuilder {

	/**
	 * Number of commits authored by the user.
	 */
	private int authored;

	/**
	 * Number of commits committed by the user.
	 */
	private int committed;

	public UserStatisticBuilder() {
		this.authored = 0;
		this.committed = 0;
	}

	/**
	 * Update the number of authored commits.
	 * @return this statistic build to chain call.
	 */
	public UserStatisticBuilder authoredCommit() {
		authored++;
		return this;
	}

	/**
	 * Update the number of committed commits.
	 * @return this statistic build to chain call.
	 */
	public UserStatisticBuilder committedCommit() {
		committed++;
		return this;
	}

	/**
	 * Build the user statistic.
	 * @return a new instance of user statistic.
	 */
	public UserStatistic build() {
		return new UserStatistic(authored, committed);
	}
}