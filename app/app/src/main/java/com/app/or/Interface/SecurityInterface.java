package com.app.or.Interface;

import java.math.BigInteger;

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
     * 비밀키 변수에 저장
     */
    void MakePrivateKey();

    /**
     * 비대칭 키를 이용해서 비밀키 전송
     */
    void SendPrivateKey();

    /**
     * 현재 사용하고 있는 public 키가 유효 한지 확인
     * @return
     */
    boolean TouchPublicKey();

    /**
     * 서버에서 public 키들 받아오기
     * @return
     */
    boolean GetPublicKey();

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
}
