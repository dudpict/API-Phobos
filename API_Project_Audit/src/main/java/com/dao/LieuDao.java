package com.dao;

import java.util.List;

import com.beans.Lieu;

public interface LieuDao {

	Lieu getLieuById(String lieuID);
	public List<Lieu> getLieux();

}
