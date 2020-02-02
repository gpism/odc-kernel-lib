package org.opendatacore.kernel.metacon.data.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.manager.security.Key;

import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Resource {
    private static final Logger _logger = Logger.getLogger(Resource.class);
    public Access access;
   // public String accessId;
    public String type;
    public String subType;
    public String name;
    public String orgId;
    public String department;
    public String owner;

    public String supportEmail;

    public String id;

    public Key key;





    public Resource id(String id) {
        this.id = id;
        return this;
    }

    public Resource generateResourceId() {
        this.id= UUID.randomUUID().toString();

        return this;
    }




    public Resource access(Access access) {
        this.access = access;
        return this;
    }
    

    
    public Resource type(String type) {
        this.type = type;
        return this;
    }
    
    public Resource subType(String subType) {
        this.subType = subType;
        return this;
    }
    
    public Resource name(String name) {
        this.name = name;
        return this;
    }
    
    public Resource orgId(String orgId) {
        this.orgId = orgId;
        return this;
    }
    
    public Resource department(String department) {
        this.department = department;
        return this;
    }
    
    public Resource owner(String owner) {
        this.owner = owner;
        return this;
    }
    
    public Resource supportEmail(String supportEmail) {
        this.supportEmail = supportEmail;
        return this;
    }

    /**
     * Creates Json representation
     *
     * @return
     * @throws JsonProcessingException
     */
    public String toJson() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            _logger.error(e);
            return null;
        }
    }

    /**
     * Creates object from json
     *
     * @return
     * @throws JsonProcessingException
     */
    public Resource fromJson(String json) throws JsonProcessingException {

        return new ObjectMapper().readValue(json, Resource.class);

    }

}
