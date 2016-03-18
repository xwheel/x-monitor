package com.xwheel.xmonitor.portal.entity;

import java.io.Serializable;

/**
* @author: leihang@live.cn
* @date:   2015-11-23 12:37:22
* @description: 数据字典明细
*/
public class SysDictItem implements Serializable {

	//唯一标识
	private Integer recordId;

	//所属字典组
	private String dictGroupCode;

	//字典序号
	private Integer dictItemSeq;

	//字典编码
	private String dictItemCode;

	//字典名称
	private String dictItemName;

	//描述
	private String dictItemNote;

	//语言(zh:中文;en:英文)
	private String language;

    public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

    public String getDictGroupCode() {
		return dictGroupCode;
	}

	public void setDictGroupCode(String dictGroupCode) {
		this.dictGroupCode = dictGroupCode;
	}

    public Integer getDictItemSeq() {
		return dictItemSeq;
	}

	public void setDictItemSeq(Integer dictItemSeq) {
		this.dictItemSeq = dictItemSeq;
	}

    public String getDictItemCode() {
		return dictItemCode;
	}

	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}

    public String getDictItemName() {
		return dictItemName;
	}

	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}

    public String getDictItemNote() {
		return dictItemNote;
	}

	public void setDictItemNote(String dictItemNote) {
		this.dictItemNote = dictItemNote;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}