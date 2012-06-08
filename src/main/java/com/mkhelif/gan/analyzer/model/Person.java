package com.mkhelif.gan.analyzer.model;

import java.util.Objects;

import org.eclipse.jgit.lib.PersonIdent;

/**
 * Represents a person using a Git repository (author or committer).
 *
 * @author mkhelif
 */
public final class Person implements Comparable<Person> {

	/**
	 * The name of the person.
	 */
	private final String name;

	/**
	 * The email address of the person.
	 */
	private final String email;

	public Person(final String name, final String email) {
		this.name = name;
		this.email = email;
	}

	public Person(final PersonIdent identity) {
		this(identity.getName(), identity.getEmailAddress());
	}

	public String getName() { return name; }
	public String getEmail() { return email; }

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Person that) {
		if (name.equals(that.name)) {
			return email.compareTo(that.email);
		}
		return name.compareTo(that.name);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, email);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (object instanceof Person) {
			return this.hashCode() == object.hashCode();
		}
		return false;
	}
}