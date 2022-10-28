package com.app.or.Security;

public interface SecurityInterface {
    /**
     * 세션에서 사용하기로한 비밀키로 암호화
     * @param Plain
     * @return
     */
    String encryptionBySessionKey(String Plain);

    /**
     * 세션에서 사용하기로한 비밀키로 복호화
     * @param cipher
     * @return
     */
    String decryptionBySessionKey(String cipher);

    /**
     * 초기화 기능 수행
     */
    void init();


    /**
     * 각종 정보 저장하기 위한 키 생성
     */
    void SetKeyStore();

    /**
     * 키스토어 암호로 암호화 실행
     * @param plain
     * @return
     */
    String encryption(String plain);

    /**
     * 키스토어 암호로 복호화
     * @param cipherTXT
     * @return
     */
    String decryption(String cipherTXT);

    boolean isSetKeyStore();

    String Signature(String txt);

    String RSA(String txt);

    String GetSessionKeyByRSA();

    String temp();
}
