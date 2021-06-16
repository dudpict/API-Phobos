package com.dao;

import java.util.List;

import com.beans.Option;

public interface OptionDao {

	void updateOptionProfRef(String idOption, String idProf);

	Option getOptionById(String idOption);

	List<Option> getOptionByIdProfRef(int idProfRef);

	List<Option> getAllOption();

}
