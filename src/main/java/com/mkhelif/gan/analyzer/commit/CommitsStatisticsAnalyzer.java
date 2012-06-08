package com.mkhelif.gan.analyzer.commit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Component;

import com.google.common.collect.ImmutableSortedMap;
import com.mkhelif.gan.analyzer.Counter;

/**
 * Analyzer of commits activities on a Git repository.
 *
 * @author mkhelif
 */
@Component
class CommitsStatisticsAnalyzer extends AbstractCountingAnalyzer<Long> {

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#getCounter()
	 */
	@Override
	public Counter getCounter() {
		return Counter.COMMITS_BY_DATE;
	}

	/**
	 * @param commit the commit to get the date from.
	 * @return the date (interval by day) of the commit.
	 */
	@Override
	protected Long getKey(final RevCommit commit) {
		return (commit.getCommitTime() / INTERVAL) * INTERVAL * 1000L;
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

		// Fill empty interval
		final List<Long> intervals = this.empty(commits.keySet().iterator().next());
		final Map<Long, Integer> statistics = new HashMap<>(intervals.size());
		for (final Long interval : intervals) {
			if (commits.containsKey(interval)) {
				statistics.put(interval, commits.get(interval));
			} else {
				statistics.put(interval, 0);
			}
		}

		return ImmutableSortedMap.copyOf(statistics);
	}

	/**
	 * Create a list with all the intervals between the initial and current time.
	 * @param initial the initial interval.
	 * @return a list with each interval between the initial and current time.
	 */
	private List<Long> empty(final long initial) {
		final long current = (System.currentTimeMillis() / INTERVAL) * INTERVAL;
		final List<Long> empty = new LinkedList<>();
		for (long interval = initial ; interval < current ; interval += INTERVAL * 1000) {
			empty.add(interval);
		}
		return empty;
	}
}