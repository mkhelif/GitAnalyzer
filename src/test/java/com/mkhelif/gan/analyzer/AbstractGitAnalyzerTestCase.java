package com.mkhelif.gan.analyzer;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkhelif.gan.wrapper.GitWrapperMock;

/**
 * Abstract class for Git analyzer test case.
 * This initialize a temporary Git repository and delete it on shutdown.
 *
 * @author mkhelif
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:WEB-INF/application.xml")
public class AbstractGitAnalyzerTestCase {

	/**
	 * {@link StatisticAnalyzer} initialized by Spring.
	 */
	@Autowired
	private GitAnalyzer analyzer;

	/**
	 * The temporary Git repository.
	 */
	private GitWrapperMock wrapper;

	/**
	 * Initialize the Git repository.
	 */
	@Before
	public void setUp() {
		wrapper = new GitWrapperMock();
	}

	/**
	 * Analyze the temporary Git repository to extract the desired statistic.
	 * @return the statistic of the Git repository.
	 */
	public Statistic analyze() {
		try {
			return analyzer.analyze(wrapper);
		} catch (final GitAPIException e) {
			Assert.fail("Failed to analyze Git repository: " + e.getMessage());
			return null;
		}
	}

	/**
	 * @return the temporary Git repository wrapper.
	 */
	public GitWrapperMock getWrapper() {
		return wrapper;
	}

	/**
	 * Delete the temporary Git repository.
	 */
	@After
	public void tearDown() {
		wrapper.close();
	}
}