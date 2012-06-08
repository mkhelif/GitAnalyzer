package com.mkhelif.gan.wrapper;

import java.io.File;
import java.util.Random;

import org.eclipse.jgit.api.CloneCommand;
import org.springframework.util.FileSystemUtils;

/**
 * Wrapper of a remote Git repository.
 *
 * @author mkhelif
 */
final class RemoteGitWrapper extends AbstractGitWrapper {

	/**
	 * Base name of the temporary directory.
	 */
	private static final String TEMP_BASE = "gittemp-";

	/**
	 * Random used to generate an unique path for the remote repository.
	 */
	private static final Random RANDOM = new Random();

	public RemoteGitWrapper(final CloneCommand command) {
		super(command.setDirectory(getTemporaryFile()).call());
	}

	/**
	 * @return the temporary file where to store the remote Git repository.
	 */
	private static File getTemporaryFile() {
		return new File(TEMP_BASE + Long.toString(RANDOM.nextLong()));
	}

	/**
	 * @see com.mkhelif.gan.wrapper.GitWrapper#close()
	 */
	@Override
	public void close() {
		super.close();

		// Delete temporary repository
		FileSystemUtils.deleteRecursively(getRepository().getIndexFile());
	}
}