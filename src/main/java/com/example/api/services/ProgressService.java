package com.example.api.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.api.dto.ProgressDto;
import com.example.api.models.ProgressModel;
import com.example.api.repositories.ProgressRepository;

@Service
public class ProgressService implements ProgressRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

	@Override
	public List<ProgressModel> getAll() {
		return jdbcTemplate.query("SELECT BIN_TO_UUID(progress_id) as progress_id, nome, ano, mes, dia, hora, minuto, segundo FROM progress ORDER BY nome ASC;", new BeanPropertyRowMapper<ProgressModel>(ProgressModel.class));
	}

	@Override
	public ProgressModel getOne(String uuid) {
		return jdbcTemplate.queryForObject("SELECT BIN_TO_UUID(progress_id) as progress_id, nome, ano, mes, dia, hora, minuto, segundo FROM progress WHERE BIN_TO_UUID(progress_id) = ?;", new BeanPropertyRowMapper<ProgressModel>(ProgressModel.class), new Object[] { uuid });
	}

	@Override
	public void post(ProgressDto progressDto) {
		List<Object[]> params = new ArrayList<Object[]>();

		params.add(new Object[] {
			progressDto.getNome(),
			progressDto.getAno(),
			progressDto.getMes(),
			progressDto.getDia(),
			progressDto.getHora(),
			progressDto.getMinuto(),
			progressDto.getSegundo()
		});

		jdbcTemplate.batchUpdate("INSERT INTO progress (progress_id, nome, ano, mes, dia, hora, minuto, segundo) values (UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?, ?);", params);
	}

	@Override
	public void put(ProgressDto progressDto, String uuid) {

        String query = "UPDATE progress SET ";
        
        try {
            for (Field campo : progressDto.getClass().getDeclaredFields()) {
                campo.setAccessible(true);
                if (campo.get(progressDto) == null) continue;

                String[] typeofArr = campo.get(progressDto).getClass().toString().split("[.]");
                String typeof = typeofArr[typeofArr.length - 1];

                query += campo.getName() + " = " + ((typeof.equals("String")) ? "\"" + campo.get(progressDto) + "\"" : campo.get(progressDto)) + ", ";
            }
            query = query.substring(0, query.length() - 2) + " WHERE BIN_TO_UUID(progress_id) = ?";
            jdbcTemplate.update(query, new Object[] { uuid });
        } 
        catch (IllegalArgumentException e) {
                e.printStackTrace();
        } 
        catch (IllegalAccessException e) {
                e.printStackTrace();
        }
	}

	@Override
	public void delete(String uuid) {
		jdbcTemplate.update("DELETE FROM progress WHERE BIN_TO_UUID(progress_id) = ?", new Object[]{ uuid });
	}
}
