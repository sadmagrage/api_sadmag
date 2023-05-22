package com.example.api.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.ProgressDto;
import com.example.api.models.ProgressModel;
import com.example.api.repositories.ProgressRepository;

@Service
public class ProgressService {

    @Autowired
    ProgressRepository progressRepository;

	public ResponseEntity<Object> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(progressRepository.findAllOrderBy());
	}

	public ResponseEntity<Object> findOne(UUID uuid) {
		var progressModelOptional = progressRepository.findById(uuid);

		if (!progressModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found.");

		return ResponseEntity.status(HttpStatus.OK).body(progressModelOptional.get());
	}

	@Transactional
	public ResponseEntity<Object> saveProgress(ProgressDto progressDto) {
		var progressModel = new ProgressModel();
		try {
			BeanUtils.copyProperties(progressDto, progressModel);

			progressRepository.save(progressModel);
			return ResponseEntity.status(HttpStatus.CREATED).body("Progress created.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Error creating progress.");
		}
	}
	
	@Transactional
	public ResponseEntity<Object> updateProgress(ProgressDto progressDto, UUID uuid) {
		var progressModel = new ProgressModel();

		if (!progressRepository.findById(uuid).isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found.");

		try {
			BeanUtils.copyProperties(progressDto, progressModel);

			progressModel.setProgress_id(uuid);
			progressRepository.save(progressModel);

			return ResponseEntity.status(HttpStatus.OK).body("Progress updated.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error updating progress.");
		}
	}

	@Transactional
	public ResponseEntity<Object> delete(UUID uuid) {
		var progressModelOptional = progressRepository.findById(uuid);

		if (!progressModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Progress not found.");

		progressRepository.deleteById(uuid);
		return ResponseEntity.status(HttpStatus.OK).body("Progress deleted.");
	}
}