package com.mkhelif.gan.analyzer.commit;

import java.util.Map;

import org.eclipse.jgit.revwalk.RevCommit;

import com.mkhelif.gan.analyzer.StatisticAnalyzer;
import com.mkhelif.gan.utils.ValuesCounter;

/**
 * Abstract class for all analyzers that count commit by specific key.
 *
 * @author mkhelif
 * @param <K> the key of the counted values.
 */
abstract class AbstractCountingAnalyzer<K> implements StatisticAnalyzer {

	/**
	 * List of commits already analyzed.
	 */
	private final ValuesCounter<K> values;

	public AbstractCountingAnalyzer() {
		values = new ValuesCounter<>();
	}

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#analyze(org.eclipse.jgit.revwalk.RevCommit)
	 */
	@Override
	public final void analyze(final RevCommit commit) {
		values.increment(this.getKey(commit));
	}

	/**
	 * Retrieve the key to use for statistic from the commit.
	 * @param commit the commit to get a key from.
	 * @return the key of the commit.
	 */
	protected abstract K getKey(final RevCommit commit);

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#build()
	 */
	@Override
	public Map<K, Integer> build() {
		return values.build();
	}
}