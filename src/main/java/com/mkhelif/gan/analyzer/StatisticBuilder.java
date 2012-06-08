package com.mkhelif.gan.analyzer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder of Git repository statistics.
 *
 * @author mkhelif
 */
final class StatisticBuilder {

	/**
	 * Create a new instance of statistics from the executed analyzers.
	 * @param analyzers list of analyzers executed.
	 * @return the created instance of statistics.
	 */
	public Statistic build(final List<StatisticAnalyzer> analyzers) {
		final Map<String, Object> counters = new HashMap<>(analyzers.size());
		for (final StatisticAnalyzer analyzer : analyzers) {
			counters.put(analyzer.getCounter().name().toLowerCase(), analyzer.build());
		}
		return new Statistic(counters);
	}
}