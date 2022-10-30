package com.oc.api_server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignUpData {
    String Email;
    String NickName;
    String Code;
    boolean Code_Certification;
    boolean NickName_Certification;
    int code_wrong=0;


}
