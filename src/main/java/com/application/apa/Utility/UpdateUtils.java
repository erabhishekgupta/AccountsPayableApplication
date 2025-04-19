package com.application.apa.Utility;

import java.util.Objects;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

@Component
public class UpdateUtils {
	
	public <T> void compareAndUpdate(
	T oldVal, T newVal, String fieldName, 
	Consumer<T> setter, StringBuilder oldValue, StringBuilder newValue) {
		if(newValue!=null && !Objects.equals(oldValue, newValue)) {
			oldValue.append(fieldName).append(": ").append(oldVal).append("; ");
			newValue.append(fieldName).append(": ").append(newVal).append("; ");	
			setter.accept(newVal);
		}
	}
}
