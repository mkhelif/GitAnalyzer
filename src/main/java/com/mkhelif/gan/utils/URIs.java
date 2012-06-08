package com.mkhelif.gan.utils;

import java.net.URI;

/**
 * Utility methods for {@link URI}.
 *
 * @author mkhelif
 */
public final class URIs {

	/**
	 * Protocol used by local file {@link URI}.
	 */
	private static final String PROTOCOL_FILE = "file";

	private URIs() { }

	/**
	 * Check if an URI is referencing a local file or not.
	 * @param uri the {@link URI} to check.
	 * @return TRUE if the {@link URI} reference a local file, FALSE if not a file (not necessarily remote).
	 */
	public static boolean isLocal(final URI uri) {
		if (uri == null) {
			return false;
		}
		return PROTOCOL_FILE.equals(uri.getScheme());
	}
}