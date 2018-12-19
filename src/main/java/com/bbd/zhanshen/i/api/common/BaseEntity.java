package com.bbd.zhanshen.i.api.common;



import com.bbd.zhanshen.i.api.common.util.JacksonUtil;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8794620806353156262L;
//Excel导入错误,请检查
	public String toString() {
		return JacksonUtil.toJson(this);
	}
}
