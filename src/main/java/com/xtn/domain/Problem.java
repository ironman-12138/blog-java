package com.xtn.domain;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author xCoder
 * @email 1481806085@qq.com
 * @date 2022-01-27 10:32:29
 */
@Data
public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;
	/**
	 * 题目
	 */
	private String title;
	/**
	 * 答案
	 */
	private String answer;
	/**
	 * 题目类型(1：选择题，2：简答题)
	 */
	private Integer type;

	private Long userId;

}
