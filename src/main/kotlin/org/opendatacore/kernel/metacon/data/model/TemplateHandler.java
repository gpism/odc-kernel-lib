package org.opendatacore.kernel.metacon.data.model;
//JSON 

import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.manager.registry.TemplateRegistry;


@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class TemplateHandler {
    private static final Logger _logger = Logger.getLogger(TemplateHandler.class);

    public String name, details , type , subType  , outputFormat;

    //populate from DynamicData
    public TemplateHandler populate(Template dynamicData) {
        this.name = dynamicData.name;
        this.details = dynamicData.details;
        this.type = dynamicData.type;
        this.subType = dynamicData.subType;
        this.outputFormat = dynamicData.outputFormat;
        return this;
    }

    public TemplateHandler() {
    }

    // name
    public TemplateHandler name(String name) {
        this.name = "@"+name;
        return this;
    }

    // details
    public TemplateHandler details(String details) {
        this.details = details;
        return this;
    }

    // type
    public TemplateHandler type(String type) {
        this.type = type;
        return this;
    }

    // subType
    public TemplateHandler subType(String subType) {
        this.subType = subType;
        return this;
    }

    // outputFormat
    public TemplateHandler outputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
        return this;
    }



    public abstract Uni<String> getTemplateData(Template dynamicData) ;


    public TemplateHandler register(){
        TemplateRegistry.getInstance().registerHandler(this  );
        return this;
    }


    //toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    //fromJson() function using Jackson
    public static TemplateHandler fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TemplateHandler.class);
    }





}
