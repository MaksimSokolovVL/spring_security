package com.dhabits.ss.demo.config.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public record RsaKeys(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
