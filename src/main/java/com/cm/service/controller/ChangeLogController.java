package com.cm.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cm.service.models.hib.RecentItem;
import com.cm.service.service.ChangeLogService;

@RestController
public class ChangeLogController {
	@Autowired
	private ChangeLogService service;

	@GetMapping("/changelog")
	public ResponseEntity<List<RecentItem>> recentItems() {
		return new ResponseEntity<List<RecentItem>>(service.getRecentItems(), HttpStatus.OK);
	}
}
