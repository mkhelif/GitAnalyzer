package com.mkhelif.gan.analyzer.user;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Maps;
import com.mkhelif.gan.analyzer.Counter;
import com.mkhelif.gan.analyzer.StatisticAnalyzer;
import com.mkhelif.gan.analyzer.model.Person;

/**
 * Analyzer of users statistics.
 *
 * @author mkhelif
 */
@Component
public final class UsersStatisticsAnalyzer implements StatisticAnalyzer {

	/**
	 * Number of commits per author.
	 */
	private final Map<Person, UserStatisticBuilder> builders;

	public UsersStatisticsAnalyzer() {
		builders = new HashMap<>();
	}

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#analyze(org.eclipse.jgit.revwalk.RevCommit)
	 */
	@Override
	public void analyze(final RevCommit commit) {
		getBuilder(commit.getAuthorIdent()).authoredCommit();
		getBuilder(commit.getCommitterIdent()).committedCommit();
	}

	/**
	 * Retrieve or instantiate the {@link UserStatisticBuilder} for the user.
	 * @param person the person to retrieve statistic for.
	 * @return the builder of statistics of the user.
	 */
	private UserStatisticBuilder getBuilder(final PersonIdent ident) {
		final Person person = new Person(ident);
		if (!builders.containsKey(person)) {
			builders.put(person, new UserStatisticBuilder());
		}
		return builders.get(person);
	}

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#build()
	 */
	@Override
	public Object build() {
		final Map<Person, UserStatistic> statistics = Maps.transformValues(builders, new Function<UserStatisticBuilder, UserStatistic>() {
			@Override
			public UserStatistic apply(final UserStatisticBuilder builder) {
				return builder.build();
			}
		});
		return ImmutableSortedMap.copyOf(statistics);
	}

	/**
	 * @see com.mkhelif.gan.analyzer.StatisticAnalyzer#getCounter()
	 */
	@Override
	public Counter getCounter() {
		return Counter.COMMITS_BY_USER;
	}
}