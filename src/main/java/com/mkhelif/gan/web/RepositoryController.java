package com.mkhelif.gan.web;

import java.io.IOException;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkhelif.gan.analyzer.GitAnalyzer;
import com.mkhelif.gan.analyzer.Statistic;
import com.mkhelif.gan.wrapper.GitLoader;
import com.mkhelif.gan.wrapper.GitWrapper;

/**
 * Main {@link Controller} of the Web application.
 *
 * @author mkhelif
 */
@Controller
public final class RepositoryController {

	/**
	 * Analyzer of Git repository.
	 */
	private final GitAnalyzer analyzer;

	@Autowired
	public RepositoryController(final GitAnalyzer analyzer) {
		this.analyzer = analyzer;
	}

	/**
	 * Display Git repository main statistics.
	 */
	@RequestMapping(value = "/")
	public String displayIndex() {
		return "index";
	}

	/**
	 * Load the statistics on commits.
	 */
	@ModelAttribute("statistics")
	public Statistic loadStatistics() throws GitAPIException, IOException {
		final GitWrapper git = GitLoader.load("gittemp--2887883691560304074");
		return analyzer.analyze(git);
	}
}