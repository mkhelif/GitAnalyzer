package com.mkhelif.gan.analyzer.commit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import com.mkhelif.gan.analyzer.Counter;

/**
 * Analyzer of commits activities on a Git repository, extract the top 5 days.
 *
 * @author mkhelif
 */
@Component
final class TopCommitsDateStatisticsAnalyzer extends CommitsStatisticsAnalyzer {

	/**
	 * The number of entries in the top to compute.
	 */
	private static final int TOP_COUNT = 5;

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#getCounter()
	 */
	@Override
	public Counter getCounter() {
		return Counter.TOP_COMMITS_BY_DATE;
	}

	/**
	 * @see com.mkhelif.gan.analyzer.commit.AbstractCountingAnalyzer#build()
	 */
	@Override
	public Map<Long, Integer> build() {
		final Map<Long, Integer> commits = super.build();
		if (commits.isEmpty()) {
			return commits;
		}

		// Order map on commits count
		final Ordering<Long> ordering = Ordering.natural().onResultOf(Functions.forMap(commits)).reverse();
		final Iterator<Long> keys = ordering.sortedCopy(commits.keySet()).iterator();

		// Keep only the first ones
		final Map<Long, Integer> statistics = new HashMap<>(TOP_COUNT);
		for (int index = 0 ; keys.hasNext() && index < TOP_COUNT ; index++) {
			final long interval = keys.next();
			statistics.put(interval, commits.get(interval));
		}

		return ImmutableSortedMap.copyOf(statistics);
	}
}