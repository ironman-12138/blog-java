package com.xtn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author xCoder
 * @email 1481806085@qq.com
 * @date 2022-01-27 10:32:29
 */
@Data
public class AnswerRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 总答题数
     */
    private Integer totalNum;

    /**
     * 回答正确题数
     */
    private Integer successNum;

    /**
     * 回答错误题数
     */
    private Integer failNum;

    /**
     * 答题用户id
     */
    private Long userId;

    /**
     * 答题用户名称
     */
    private String username;

    /**
     * 答题时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}