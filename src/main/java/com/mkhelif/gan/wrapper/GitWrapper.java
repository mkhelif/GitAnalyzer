package com.mkhelif.gan.wrapper;

import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ListTagCommand;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.Repository;

/**
 * Wrapper of a Git {@link Repository}.
 *
 * @author mkhelif
 */
public interface GitWrapper extends AutoCloseable {

	/**
	 * @see {org.eclipse.jgit.api.Git#getRepository()}
	 */
	Repository getRepository();

	/**
	 * @see {org.eclipse.jgit.api.Git#branchList()}
	 */
	ListBranchCommand branchList();

	/**
	 * @see {org.eclipse.jgit.api.Git#tagList()}
	 */
	ListTagCommand tagList();

	/**
	 * @see {org.eclipse.jgit.api.Git#log()}
	 */
	LogCommand log();

	/**
	 * Close the {@link Repository} and all related resources.
	 */
	@Override
	void close();
}