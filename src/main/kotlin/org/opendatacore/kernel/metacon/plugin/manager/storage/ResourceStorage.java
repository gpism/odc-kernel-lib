package org.opendatacore.kernel.metacon.plugin.manager.storage;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceStorage {


    private static final Logger _logger = Logger.getLogger(ResourceStorage.class);

    public List<Resource> resources= new ArrayList<Resource>();

    //save to file


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static ResourceStorage fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ResourceStorage.class);
    }
}
