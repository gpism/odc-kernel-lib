package org.opendatacore.kernel.metacon.data.model;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.manager.registry.TemplateRegistry;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Template {
    private static final Logger _logger = Logger.getLogger(Template.class);

    public String name, details , type , subType  , outputFormat;


//    @JsonIgnore
//    public DynamicDataHandler handler;

    public Data data = new Data();

    public Template() {
    }

    //dynamicDataHandler
    public Template addHandler(TemplateHandler handler) {
        handler.populate(this);
        TemplateRegistry.getInstance().registerHandler(handler.populate(this));
        return this;
    }
    //get handler by type
    public TemplateHandler findHandler(String type) {
        return TemplateRegistry.getInstance().getHandler(type);
    }

    // Data
    public Template data(Data data) {
        this.data = data;
        return this;
    }

    // name
    public Template name(String name) {
        this.name = "@"+name;
        this.data.name = name;

        return this;
    }

    // details
    public Template details(String details) {
        this.details = details;
        return this;
    }

    // type
    public Template type(String type) {
        this.type = type;
        this.data.type = type;
        return this;
    }

    // subType
    public Template subType(String subType) {
        this.subType = subType;
        this.data.subType = subType;
        return this;
    }



    // outputFormat
    public Template outputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
        return this; 
    }

    // register
    public Template register() {
        TemplateRegistry.getInstance().register(this);
        return this;
    }


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static Template fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Template.class);
    }
}
