package com.jyeory.sso.oauth.encoder;

public abstract class BaseDigestPasswordEncoder extends BasePasswordEncoder {
	// ~ Instance fields
	// ================================================================================================

	private boolean encodeHashAsBase64 = false;

	// ~ Methods
	// ========================================================================================================

	public boolean getEncodeHashAsBase64() {
		return encodeHashAsBase64;
	}

	/**
	 * The encoded password is normally returned as Hex (32 char) version of the hash
	 * bytes. Setting this property to true will cause the encoded pass to be returned as
	 * Base64 text, which will consume 24 characters.
	 *
	 * @param encodeHashAsBase64 set to true for Base64 output
	 */
	public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
		this.encodeHashAsBase64 = encodeHashAsBase64;
	}
}

