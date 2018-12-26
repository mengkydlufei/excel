package com.example.demo.entity;

import com.example.demo.utils.ExcelDesc;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	/**
	 * 创建用户id
	 */
	private Long createBy;
	/**
	 * 创建时间
	 */
	@ExcelDesc(value = "createTime" , orderBy = "7")
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date lastUpdateTime;

	/**
	 * 最近更新用户id
	 */
	private Long lastUpdateBy;

	/**
	 * 有效状态
	 */
	@ExcelDesc(value = "enabled" , orderBy = "5")
	private Boolean enabled;

}
