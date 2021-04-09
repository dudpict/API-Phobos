package com.dao;

import java.util.List;

import com.beans.Audit;

public interface AuditDao {
	List<Audit> getAudits();
	Audit getPAuditById(int id);
}
