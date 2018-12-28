/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.simple.model
 * FileName：      Country.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/8 10:29
 */

package tk.mybatis.simple.model;

import com.sun.deploy.util.StringUtils;

import java.util.*;

public class Country {
	private Long id;
	private String countryname;
	private String countrycode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("1",1);
		map.put("2",3);
		if (map.get("1") != null | map.get("2") != null){
			System.out.println(2);
		}
	}
}
