package com.mkhelif.gan.analyzer;

import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mkhelif.gan.wrapper.GitWrapper;

/**
 * Analyzer of Git repository. Build statistics on commits, users and branches.
 * It delegates specific statistic computation to {@link GitAnalyzer}.
 *
 * @author mkhelif
 */
@Component
public final class GitAnalyzer {

	/**
	 * List of available {@link StatisticAnalyzer}.
	 */
	private final List<StatisticAnalyzer> analyzers;

	@Autowired
	public GitAnalyzer(final List<StatisticAnalyzer> analyzers) {
		this.analyzers = analyzers;
	}

	/**
	 * Analyze the Git repository and extract the desired statistic.
	 * @param git the Git repository wrapper.
	 * @param builder the builder of statistics to update.
	 * @return the updated statistics builder.
	 * @throws GitAPIException an exception occured while accessing Git repository.
	 */
	public Statistic analyze(final GitWrapper git) throws GitAPIException {
		final StatisticBuilder builder = new StatisticBuilder();
		for (final RevCommit commit : git.log().call()) {
			for (final StatisticAnalyzer analyzer : analyzers) {
				analyzer.analyze(commit);
			}
		}
		return builder.build(analyzers);
	}
}