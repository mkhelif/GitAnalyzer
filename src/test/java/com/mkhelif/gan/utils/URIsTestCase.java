package com.mkhelif.gan.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

/**
 * Test case of the URIs utility class.
 *
 * @author mkhelif
 */
public class URIsTestCase {

	/**
	 * Test method for {@link com.mkhelif.gan.utils.URIs#isLocal(java.net.URI)}.
	 */
	@Test
	public void testIsLocal() throws URISyntaxException {
		assertTrue("Standard file case", URIs.isLocal(new URI("file://file.txt")));
		assertTrue("Relative path file case", URIs.isLocal(new URI("file://directory/../file.txt")));
		assertFalse("HTTP protocol case", URIs.isLocal(new URI("http://www.github.com/")));
		assertFalse("HTTPS protocol case", URIs.isLocal(new URI("https://www.github.com/")));
		assertFalse("Git protocol case", URIs.isLocal(new URI("git://github.com/mkhelif/")));
		assertFalse("FTP protocol case", URIs.isLocal(new URI("ftp://server/")));
		assertFalse("null case", URIs.isLocal(null));
	}
}