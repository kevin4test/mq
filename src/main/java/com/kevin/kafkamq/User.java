package com.kevin.kafkamq;

import java.io.Serializable;

public class User implements Serializable {

	/**  
	 * @Fields serialVersionUID : TODO(√Ë ˆ)
	 * @author LG
	 * @date 2020-03-18 11:38:08 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Integer sex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
