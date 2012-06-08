package com.mkhelif.gan.analyzer;

import org.eclipse.jgit.revwalk.RevCommit;

/**
 * An analyzer is used to extract a statistic from the Git repository.
 *
 * @author mkhelif
 */
public interface StatisticAnalyzer {

	/**
	 * The interval used to analyze commits.
	 */
	long INTERVAL = 60 * 60 * 24;

	/**
	 * Analyze the Git repository.
	 * @param commit information about the commit to analyze.
	 */
	void analyze(final RevCommit commit);

	/**
	 * Build the statistic computed by this analyzer.
	 * @return the computed statistic.
	 */
	Object build();

	/**
	 * @return the computed {@link Counter} by this analyzer.
	 */
	Counter getCounter();
}