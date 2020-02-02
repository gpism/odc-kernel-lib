package org.opendatacore.kernel.grid.agent.model;
//JSON 

import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentBuilder {
    private static final Logger _logger = Logger.getLogger(AgentBuilder.class);


   public Agent agent;

   //init
    public AgentBuilder init(){
         agent = new Agent();
         return this;
    }

    //init
    public AgentBuilder init(Agent agent){
         this.agent = agent;
         return this;
    }

    //name
    public AgentBuilder name(String name){
         agent.name = name;
         return this;
    }

    //id
    public AgentBuilder id(String id){
         agent.id = id;
         return this;
    }

    //orgId
    public AgentBuilder orgId(String orgId){
         agent.orgId = orgId;
         return this;
    }

    //type
    public AgentBuilder type(String type){
         agent.type = type;
         return this;
    }

    //subType
    public AgentBuilder subType(String subType){
         agent.subType = subType;
         return this;
    }

    //parent
    public AgentBuilder parent(String parent){
         agent.parent = parent;
         return this;
    }

    //priority
    public AgentBuilder priority(String priority){
         agent.priority = priority;
         return this;
    }

    //status
    public AgentBuilder status(String status){
         agent.status = status;
         return this;
    }

    //description
    public AgentBuilder description(String description){
         agent.description = description;
         return this;
    }

    //key
    public AgentBuilder key(String key){
         agent.key = key;
         return this;
    }

    //password
    public AgentBuilder password(String password){
         agent.password = password;
         return this;
    }

    //host
    public AgentBuilder host(String host){
         agent.host = host;
         return this;
    }

    //port
    public AgentBuilder port(String port){
         agent.port = port;
         return this;
    }

    //createdOn
    public AgentBuilder createdOn(long createdOn){
         agent.createdOn = createdOn;
         return this;
    }

    //updatedOn
    public AgentBuilder updatedOn(long updatedOn){
         agent.updatedOn = updatedOn;
         return this;
    }

    //supportEmail
    public AgentBuilder supportEmail(String supportEmail){
         agent.supportEmail = supportEmail;
         return this;
    }

    //supportPerson
    public AgentBuilder supportPerson(String supportPerson){
         agent.supportPerson = supportPerson;
         return this;
    }


    //build
    public Agent build(){
         return agent;
    }


}
