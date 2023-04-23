package com.example.api.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.api.dto.ComidaDto;
import com.example.api.models.ComidaModel;
import com.example.api.repositories.ComidaRepository;

@Service
public class ComidaService implements ComidaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ComidaModel> getAll() {
        return jdbcTemplate.query("SELECT BIN_TO_UUID(comida_id) as comida_id, nome, carb, protl, proth, fat, img FROM comida", new BeanPropertyRowMapper<ComidaModel>(ComidaModel.class));
    }

    @Override
    public ComidaModel getOne(String uuid) {
        return jdbcTemplate.queryForObject("SELECT BIN_TO_UUID(comida_id) as comida_id, nome, carb, protl, proth, fat, img FROM comida WHERE BIN_TO_UUID(comida_id) = ?", new BeanPropertyRowMapper<ComidaModel>(ComidaModel.class), new Object[] { uuid });
    }

    @Override
    public void post(ComidaDto comidaDto) {
        List<Object[]> params = new ArrayList<Object[]>();

        params.add(new Object[] {
            comidaDto.getNome(),
            comidaDto.getCarb(),
            comidaDto.getProtl(),
            comidaDto.getProth(),
            comidaDto.getFat(),
            comidaDto.getImg()
        });
        
        jdbcTemplate.batchUpdate("INSERT INTO comida (comida_id, nome, carb, protl, proth, fat, img) values (UUID_TO_BIN(UUID()), ?, ?, ?, ?, ?, ?)", params);
    }

    @Override
    public void put(ComidaDto comidaDto, String uuid) {
        String query = "UPDATE comida SET ";
        
        try {
            for (Field campo : comidaDto.getClass().getDeclaredFields()) {
                if (campo.getName() == "quantidade") continue;
                campo.setAccessible(true);
                if (campo.get(comidaDto) == null) continue;

                String[] typeofArr = campo.get(comidaDto).getClass().toString().split("[.]");
                String typeof = typeofArr[typeofArr.length - 1];

                query += campo.getName() + " = " + ((typeof.equals("String")) ? "\"" + campo.get(comidaDto) + "\"" : campo.get(comidaDto)) + ", ";
            }
            query = query.substring(0, query.length() - 2) + " WHERE BIN_TO_UUID(comida_id) = ?";
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
        jdbcTemplate.update("DELETE FROM comida WHERE BIN_TO_UUID(comida_id) = ?;", new Object[] { uuid });
    }
}
