package com.dao;

import java.util.ArrayList;

import com.beans.Lieu;

public interface LieuDao {

	Lieu getLieuById(String lieuID);
	public ArrayList<Lieu> getLieux();

}
