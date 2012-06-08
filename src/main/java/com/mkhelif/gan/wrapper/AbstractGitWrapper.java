package com.mkhelif.gan.wrapper;

import org.eclipse.jgit.api.Git;

/**
 * Abstract class for a Git repository wrapper.
 *
 * @author mkhelif
 */
abstract class AbstractGitWrapper extends Git implements GitWrapper {

	public AbstractGitWrapper(final Git git) {
		super(git.getRepository());
	}

	/**
	 * @see com.mkhelif.gan.wrapper.GitWrapper#close()
	 */
	@Override
	public void close() {
		this.getRepository().close();
	}
}