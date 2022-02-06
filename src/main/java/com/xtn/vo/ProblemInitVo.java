package com.xtn.vo;

import lombok.Data;

@Data
public class ProblemInitVo {
    private Long userId;
    private Integer type;
    private Integer num = 10;
}
