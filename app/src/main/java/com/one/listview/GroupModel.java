package com.one.listview;

import java.util.List;

/**
 * 一级Item实体类
 * 
 * @author zihao
 * 
 */
public class GroupModel {
	private String groupName;
	/** 二级Item数据列表 **/
	private List<ChildModel> childList;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<ChildModel> getChildList() {
		return childList;
	}

	public void setChildList(List<ChildModel> childList) {
		this.childList = childList;
	}

}