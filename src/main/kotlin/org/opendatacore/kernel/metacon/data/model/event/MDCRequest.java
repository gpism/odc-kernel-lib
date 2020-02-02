package org.opendatacore.kernel.metacon.data.model.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.manager.registry.TemplateRegistry;
import org.opendatacore.kernel.metacon.data.model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MDCRequest {
    private static final Logger _logger = Logger.getLogger(MDCRequest.class);

    public String resourceId , type , subType  , sessionId ,template , agentId ,callBackUrl;

    public List<Data> dataList = new ArrayList<Data>();
    public String operation;
    public String key;
    public String body;

    public MDCRequest dataList(List<Data> dataList) {
        this.dataList = dataList;
        return this;
    }

    public MDCRequest addData(Data data) {
        this.dataList.add(data);
        return this;
    }

    //find data by name using stream
    public Data findData(String name) {
        return this.dataList.stream().filter(data -> data.name.equals(name)).findFirst().orElse(null);
    }
    public String find(String name) {
        return this.dataList.stream().filter(data -> data.name.equals(name)).findFirst().orElse(null).value;
    }

    //find all data by type using stream
    public List<Data> findDataByType(String type) {
        return this.dataList.stream().filter(data -> data.type.equals(type)).collect(Collectors.toList());
    }


    public MDCRequest operation(String operation) {
        this.operation = operation;
        return this;
    }

    public MDCRequest body(String body) {
        this.body = body;
        return this;
    }

    public MDCRequest key(String key) {
        this.key = key;
        return this;
    }

    public MDCRequest resourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public MDCRequest type(String type) {
        this.type = type;
        return this;
    }

    public MDCRequest subType(String subType) {
        this.subType = subType;
        return this;
    }

    public MDCRequest sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public MDCRequest template(String template) {
        this.template = template;
        return this;
    }
    //callBackUrl
    public MDCRequest callBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
        return this;
    }


    /**
     * Creates object from json
     *
     * @return
     * @throws JsonProcessingException
     */
    public MDCRequest fromJson(String json) throws JsonProcessingException {

        return new ObjectMapper().readValue(json, MDCRequest.class);

    }

    public MDCRequest parseTemplate(){
        _logger.info("Before template parsing : "+template);
        this.template= TemplateRegistry.getInstance().parseTemplate(template);
        _logger.info("After template parsing : "+template);
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
           _logger.error("Error converting MDCRequest to json", e);
            return "";
        }
    }

}
