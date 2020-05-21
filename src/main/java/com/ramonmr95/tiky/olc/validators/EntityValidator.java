package com.ramonmr95.tiky.olc.validators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.ramonmr95.tiky.olc.parsers.JsonParser;

/**
 * 
 * Class used to validate if an entity fulfill all of its validation beans.
 * 
 * @param <T> Class of the entity.
 */
public class EntityValidator<T> {

	private JsonParser jsonParser = new JsonParser();

	/**
	 * 
	 * Gets a map with all of the validation errors.
	 * 
	 * @param entity Entity to be validated.
	 * @return map with validation errors.
	 */
	public Map<String, Set<String>> getEntityValidationErrors(T entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> validationErrors = validator.validate(entity);
		Set<String> errorsSet = new HashSet<>();

		for (ConstraintViolation<T> constraintViolation : validationErrors) {
			errorsSet.add(constraintViolation.getMessage());
		}

		Map<String, Set<String>> errors = new HashMap<>();
		errors.put("errors", errorsSet);
		return errors;
	}

	/**
	 * 
	 * Gets all of the validation errors as string.
	 * 
	 * @param entity Entity to be validated.
	 * @return json format string with the errors.
	 */
	public String getEntityValidationErrorsString(T entity) {
		return jsonParser.getMapAsJsonFormat(getEntityValidationErrors(entity));
	}

	/**
	 * 
	 * Checks if an entity contains any validation error.
	 * 
	 * @param entity Entity to be validated.
	 * @return true if contains any error, false if not.
	 */
	public boolean isEntityValid(T entity) {
		return this.getEntityValidationErrors(entity).get("errors").isEmpty();
	}

}