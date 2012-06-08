package com.mkhelif.gan.wrapper;

import org.eclipse.jgit.api.Git;

/**
 * Wrapper of a local Git repository.
 *
 * @author mkhelif
 */
final class LocalGitWrapper extends AbstractGitWrapper {

	public LocalGitWrapper(final Git git) {
		super(git);
	}
}