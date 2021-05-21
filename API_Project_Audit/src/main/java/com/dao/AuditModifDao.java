package com.dao;

import java.util.ArrayList;

import com.beans.AuditModif;

public interface AuditModifDao {

	ArrayList<AuditModif> getModifiAudit(String id);
	
}
