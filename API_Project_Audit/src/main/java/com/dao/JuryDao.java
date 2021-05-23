package com.dao;

import java.util.ArrayList;

import com.beans.Jury;

public interface JuryDao {

    ArrayList<Jury> getJurys();

    public Jury getJuryById(String juryID);

	Jury getJurybyidAudit(String idAudit);

	void addJury(String designation);

	Jury getJuryByString(String designation);


}
