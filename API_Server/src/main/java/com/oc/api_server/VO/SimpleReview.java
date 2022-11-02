package com.oc.api_server.VO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class SimpleReview {

    Long id; //자동생성 ID (PK)
    String title;
    String preview;
    Integer good; //좋아요 수
    Float owner_rating; //사용자 총점
    Integer address;
    String create_day;
}
