package org.opendatacore.kernel.grid.agent.model;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agent {

    public String name ;
    public String id ;
    public String orgId ;
    public String type ;
    public String subType ;
    public String parent ;
    public String priority ;
    public String status ;
    public String description ;

    public String supportEmail;
    public String supportPerson;

    public String key,password;
    public String host,port;

    public  long createdOn ,updatedOn;
    private static final Logger _logger = Logger.getLogger(Agent.class);


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static Agent fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Agent.class);
    }
}
