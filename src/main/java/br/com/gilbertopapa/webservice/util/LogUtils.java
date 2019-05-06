package br.com.gilbertopapa.webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Request;

public class LogUtils {
    private static final Logger log = Logger.getLogger(LogUtils.class);
    private static final String UUID = "uuid";
    private static final String SERVICE_NAME = "service-name";

    public void object(Object o, Request r) {
        log.info(transformObject(o, r).toString().replace("\"", "\\\""));
    }

    public void message(String s, Request r) {
        log.info(transformMessage(s, r).toString().replace("\"", "\\\""));
    }

    public void messageError(String s) {
        log.error(transformMessage(s, null).toString().replace("\"", "\\\""));
    }

    private JsonObject transformMessage(String s, Request request) {

        JsonObject json = prepareLogMessage(s, request);
        json.addProperty("Message", s);
        return json;
    }

    private JsonObject transformObject(Object o, Request request) {

        ObjectWriter ow = new ObjectMapper().writer();
        String json;
        try {
            json = ow.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            json = "error while transforming object to json";
            e.printStackTrace();
        }

        JsonObject message = prepareLogMessage(o, request);
        message.addProperty("Body", json);

        return message;
    }

    private JsonObject prepareLogMessage(Object o, Request request) {
        JsonObject json = new JsonObject();

        if(request != null) {
            json.addProperty("Service", request.getProperty(SERVICE_NAME).toString());
            json.addProperty("UUID", request.getProperty(UUID).toString());
            json.addProperty("HTTPMethod", request.getHttpMethod().toString());
            json.addProperty("URI", request.getUri().toString());
        }

        return json;
    }
}
