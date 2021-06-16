package com.dao;
import java.util.List;

import com.beans.UE;
public interface UEDao {
	public List<UE> getUES();

	void updateUeProfRef(String idUe, String idProf);

	UE getUeById(String idUe);

	 List<UE> getUeByIdProfRef(int idProfRef);

	void deleteUe(String idUe);

	void addUe(UE ue);

	
}
