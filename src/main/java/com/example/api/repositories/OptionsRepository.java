package com.example.api.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.api.models.OptionsModel;

@Repository
public interface OptionsRepository {
	List<String> showTables();

    List<OptionsModel> showColumns(String table);
}
