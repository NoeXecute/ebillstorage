package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "searchmanage")
public class Searchmanage {

	@Column(name = "searchno")
	private int searchno;

	@Column(name = "condition")
	private String condition;

	@Column(name = "condition_japanese")
	private String conditionJapanese;

	@Column(name = "review_status")
	private int reviewStatus;

	@Column(name = "search_status")
	private boolean searchStatus;

}
