package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "searchmatchmanage")
public class Searchmatchmanage {
	
	@Column(name = "userid")
	private int userid;

	@Column(name = "pending_approval_new")
	private boolean pendingApprovalNew;

	@Column(name = "pending_approval_modification")
	private boolean pendingApprovalModification;

	@Column(name = "approved")
	private boolean approved;

	@Column(name = "denied")
	private boolean denied;

	@Column(name = "editing_permission_requested")
	private boolean editingPermissionRequested;

	@Column(name = "editing_pending")
	private boolean editingPending;

}

