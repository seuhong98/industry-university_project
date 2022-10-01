package com.app.or.ETC;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

public class SimpleKey implements RSAPublicKey {

    BigInteger Modulus;

    @Override
    public BigInteger getPublicExponent() {
        return new BigInteger("65537");
    }

    @Override
    public String getAlgorithm() {
        return null;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public byte[] getEncoded() {
        return new byte[0];
    }

    @Override
    public BigInteger getModulus() {
        return Modulus;
    }

    public void setModulus(BigInteger modulus) {
        Modulus = modulus;
    }
}
