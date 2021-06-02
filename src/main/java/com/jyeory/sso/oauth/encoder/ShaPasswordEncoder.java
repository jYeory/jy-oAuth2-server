package com.jyeory.sso.oauth.encoder;

import org.springframework.stereotype.Component;

@Component(value="shaPasswordEncoder")
public class ShaPasswordEncoder extends MessageDigestPasswordEncoder {

	/**
	 * Initializes the ShaPasswordEncoder for SHA-512 strength
	 */
	public ShaPasswordEncoder() {
		this(512);
	}

	/**
	 * Initialize the ShaPasswordEncoder with a given SHA stength as supported by the JVM
	 * EX: <code>ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);</code>
	 * initializes with SHA-256
	 *
	 * @param strength EX: 1, 256, 384, 512
	 */
	public ShaPasswordEncoder(int strength) {
		super("SHA-" + strength);
	}
}