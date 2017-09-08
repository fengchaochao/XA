package com.prj.core.interceptor;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;


public class JsonMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public JsonMapper() {
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
			@Override
			public void serialize(Object paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) throws IOException {
				paramJsonGenerator.writeString("");
			}
		});
	}

}
