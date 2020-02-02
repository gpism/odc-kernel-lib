package org.opendatacore.kernel.grid.agent.model;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentHelper {
    private static final Logger _logger = Logger.getLogger(AgentHelper.class);


    public Agent agent;

    //init
    public AgentHelper init(){
         agent = new Agent();
         return this;
    }

    //init
    public AgentHelper init(Agent agent){
         this.agent = agent;
         return this;
    }

    //read write it to file
    public AgentHelper read(String path) throws JsonProcessingException{
         ObjectMapper mapper = new ObjectMapper();
        // agent = mapper.readValue(new File(path), Agent.class);
         return this;
    }
    //Write using file helper class
    public AgentHelper write() throws JsonProcessingException{
      //  FileHelper.writeToFile(agent.toJson(),"agent.json");
         ObjectMapper mapper = new ObjectMapper();
         //mapper.writeValue(new File(path), agent);
         return this;
    }
}
