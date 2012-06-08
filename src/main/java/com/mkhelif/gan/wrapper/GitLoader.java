package com.mkhelif.gan.wrapper;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;

/**
 * Utility class for loading a Git repository (remote or local).
 *
 * @author mkhelif
 */
public final class GitLoader {
	private GitLoader() { }

	/**
	 * Load a Git repository from the given URI.
	 * @param repositoryURI the URI of the repository to load.
	 * @return the loaded Git instance.
	 */
	public static GitWrapper load(final String repositoryURI) throws IOException {
		final File local = new File(repositoryURI);
		if (local.exists()) {
			// Local repository
			return new LocalGitWrapper(Git.open(local));
		} else {
			// Remote repository
			return new RemoteGitWrapper(Git.cloneRepository().setBare(true).setURI(repositoryURI));
		}
	}
}