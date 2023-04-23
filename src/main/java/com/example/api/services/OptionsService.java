package com.example.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.api.models.OptionsModel;
import com.example.api.repositories.OptionsRepository;

@Service
public class OptionsService implements OptionsRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<String> showTables() {
		return jdbcTemplate.queryForList("SHOW TABLES;", String.class);
	}

	@Override
	public List<OptionsModel> showColumns(String table) {
		return jdbcTemplate.query(String.format("SHOW COLUMNS FROM %s;", table), new BeanPropertyRowMapper<OptionsModel>(OptionsModel.class));
	}
    
}
