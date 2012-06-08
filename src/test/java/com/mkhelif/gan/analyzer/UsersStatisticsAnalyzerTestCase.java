package com.mkhelif.gan.analyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.PersonIdent;
import org.junit.Test;

import com.mkhelif.gan.analyzer.model.Person;
import com.mkhelif.gan.wrapper.GitWrapperMock;

/**
 * Test case for the {@link UsersStatistics} analyzer.
 *
 * @author mkhelif
 */
public final class UsersStatisticsAnalyzerTestCase extends AbstractGitAnalyzerTestCase {

	/**
	 * Test method for {@link com.mkhelif.gan.analyzer.user.UsersStatisticsAnalyzer#analyze(com.mkhelif.gan.wrapper.GitWrapper)}.
	 */
	@Test
	public void testAnalyze() throws GitAPIException, IOException {
		final GitWrapperMock git = this.getWrapper();
		final PersonIdent identA = new PersonIdent("UserA", "user.a@mail.com");
		final PersonIdent identB = new PersonIdent("UserB", "user.b@mail.com");
		final PersonIdent identC = new PersonIdent("UserC", "user.c@mail.com");
		final Person userA = new Person(identA);
		final Person userB = new Person(identB);
		final Person userC = new Person(identC);

		// Create some activity on repository
		git.add().addFilepattern("file1").addFilepattern("file2").call();
		git.commit().setCommitter(identA).setAuthor(identB).setAll(true).setMessage("Commit #1").call();
		git.add().addFilepattern("file1").addFilepattern("file2").call();
		git.commit().setCommitter(identA).setAuthor(identA).setAll(true).setMessage("Commit #2").call();
		git.add().addFilepattern("file1").addFilepattern("file2").call();
		git.commit().setCommitter(identB).setAuthor(identC).setAll(true).setMessage("Commit #3").call();

		// Analyze the Git repository
		final Statistic statistics = analyze();
		assertNotNull("Git repository statistic is null", statistics);

		final Map<Person, Integer> authored = (Map<Person, Integer>) statistics.getCounter("commits_by_author");
		assertNotNull("Commits by author counter is null", authored);
		assertEquals("Three authors in Repository", 3, authored.size());
		assertTrue("UserA is an author", authored.containsKey(userA));
		assertTrue("UserB is an author", authored.containsKey(userB));
		assertTrue("UserC is an author", authored.containsKey(userC));

		final Map<Person, Integer> committed = (Map<Person, Integer>) statistics.getCounter("commits_by_committer");
		assertNotNull("Commits by committer counter is null", committed);
		assertEquals("Three committers in Repository", 3, committed.size());
		assertTrue("UserA is a committer", committed.containsKey(userA));
		assertTrue("UserB is a committer", committed.containsKey(userB));
		assertTrue("UserC is a committer", committed.containsKey(userC));

		assertEquals("UserA authored", Integer.valueOf(2), authored.get(userA));
		assertEquals("UserA commited", Integer.valueOf(1), committed.get(userA));

		assertEquals("UserB authored", Integer.valueOf(1), authored.get(userB));
		assertEquals("UserB authored", Integer.valueOf(1), committed.get(userB));

		assertEquals("UserC authored", Integer.valueOf(0), authored.get(userC));
		assertEquals("UserC authored", Integer.valueOf(1), committed.get(userC));
	}
}