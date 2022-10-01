package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class publicKey {
    String Key;
    LocalDateTime time;
}
