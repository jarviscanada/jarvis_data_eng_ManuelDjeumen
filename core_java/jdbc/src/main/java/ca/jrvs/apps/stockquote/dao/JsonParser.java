package ca.jrvs.apps.stockquote.dao;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonParser {

    public JsonParser(){
    }

    /**
     * Convert a java object to JSON String
     * @param object input object
     * @return JSON String
     * @throws JsonProcessingException
     *
     *
     */

    public String toJson(Object object, boolean prettyJson, boolean includeNullValues) throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        if (!includeNullValues){
            m.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if(prettyJson){
            m.enable(SerializationFeature.INDENT_OUTPUT);
        }
        return m.writeValueAsString(object);
    }


    /**
     * Parse JSON string to an object
     * @param json JSON String
     * @param clazz object classs
     * @param  <T> Type
     * @return Object
     * @throws java.io.IOException
     */

    public <T> T toObjectFromJson(String json, Class clazz) throws IOException{
        ObjectMapper m = new ObjectMapper();
        return (T)m.readValue(json, clazz);
    }

}
