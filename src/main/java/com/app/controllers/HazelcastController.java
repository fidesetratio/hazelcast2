package com.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {
	
	private final HazelcastInstance hazelcastInstance;
	@Autowired
	HazelcastController(HazelcastInstance hazelcastInstance){
		this.hazelcastInstance = hazelcastInstance;
	}
	
	
	@GetMapping(value="/write-data")
	public String writeToDataHazelcast(@RequestParam String key, @RequestParam String value) {
		Map<String,String> hazelCastMap = hazelcastInstance.getMap("my-map");
		hazelCastMap.put(key, value);
		return "data is stored";
	}
	
	@GetMapping(value="/read-data")
	public String readData(@RequestParam String key) {
		Map<String,String> hazelCastMap = hazelcastInstance.getMap("my-map");

		return hazelCastMap.get(key);
	}
	
	
	@GetMapping(value="/read-all-data")
	public Map<String,String> readAllData() {
		Map<String,String> hazelCastMap = hazelcastInstance.getMap("my-map");

		return hazelCastMap;
	}
}
