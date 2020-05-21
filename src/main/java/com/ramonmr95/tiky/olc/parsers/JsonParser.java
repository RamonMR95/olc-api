package com.ramonmr95.tiky.olc.parsers;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Class used to parse between types. (String, Map, UUID).
 * 
 */
public class JsonParser {

	/**
	 * 
	 * Parses a map to json string.
	 * 
	 * @param map to be parsed.
	 * @return string in json format.
	 */
	public String getMapAsJsonFormat(Map<String, Set<String>> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
//		return new Gson().toJson(map);
	}

	/**
	 * 
	 * Parses an array of errors as json string.
	 * 
	 * @param strings Array of errors
	 * @return string in json format that contains the errors.
	 */
	public String getErrorsAsJSONFormatString(String... strings) {
		return this.getStringAsJSONFormat("errors", strings);
	}

	/**
	 * 
	 * Creates a string in json format given its key and values.
	 * 
	 * @param key     key of json attribute.
	 * @param strings values of the json key.
	 * @return a string in json format with the given key and values.
	 */
	public String getStringAsJSONFormat(String key, String... values) {
		HashMap<String, Set<String>> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		Collections.addAll(set, values);
		map.put(key, set);
		return this.getMapAsJsonFormat(map);
	}

	public Map<String, Set<String>> parseToMap(String key, String... values) {
		HashMap<String, Set<String>> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		Collections.addAll(set, values);
		map.put(key, set);
		return map;
	}

	public Map<String, Set<String>> parseJsonToMap(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Set<String>> map = null;
		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String, Set<String>>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return map;
//		return new Gson().fromJson(json, new TypeToken<Map<String, Set<String>>>() {
//		}.getType());
	}

}