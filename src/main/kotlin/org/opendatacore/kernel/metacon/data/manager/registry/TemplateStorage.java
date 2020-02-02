package org.opendatacore.kernel.metacon.data.manager.registry;
//JSON 

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.Template;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateStorage {


    private static final Logger _logger = Logger.getLogger(TemplateStorage.class);

    public List<Template> templates= new ArrayList<Template>();

    //save to file


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static TemplateStorage fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TemplateStorage.class);
    }
}
