package com.dao;
import java.util.List;

import com.beans.UE;
public interface UEDao {
	public List<UE> getUES();

	void updateUeProfRef(String idUe, String idProf);

	
}
