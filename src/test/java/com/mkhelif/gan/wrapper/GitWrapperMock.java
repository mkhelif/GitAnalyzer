package com.mkhelif.gan.wrapper;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.springframework.util.FileSystemUtils;

/**
 * Implementation of a remote Git wrapper
 *
 * @author mkhelif
 */
public final class GitWrapperMock extends AbstractGitWrapper {

	/**
	 * The path to the temporary Git repository.
	 */
	private static final File TEMPORARY = new File("gittemp");

	public GitWrapperMock() {
		super(Git.init().setDirectory(TEMPORARY).call());
	}

	/**
	 * @see com.mkhelif.gan.wrapper.GitWrapper#close()
	 */
	@Override
	public void close() {
		super.close();

		// Delete temporary repository
		FileSystemUtils.deleteRecursively(TEMPORARY);
	}
}