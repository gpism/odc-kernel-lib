package org.opendatacore.kernel.metacon.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.types.DATA_SUB_TYPE;
import org.opendatacore.kernel.metacon.data.model.types.DATA_TYPES;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    private static final Logger _logger = Logger.getLogger(Data.class);
    public String name , type , value , subType;
    public Double dValue;

    public Data name(String name) {
        this.name = name;
        return this;
    }

    public Data type(String type) {
        this.type = type;
        return this;
    }

    public Data value(String value) {
        this.value = value;
        return this;
    }



    public Data dValue(double dValue) {
        this.dValue = dValue;
        this.type= DATA_TYPES.DOUBLE;
        return this;
    }

    //subType
    public Data subType(String subtype) {
        this.subType = subtype;
        return this;
    }

    //add string value
    public Data addValue(String value) {
        this.value = value;
        this.type= DATA_TYPES.STRING;
        return this;
    }

    public Data addFilter(String name, String value) {
        this.name = name;
        this.value = value;
        this.type= DATA_TYPES.STRING;
        this.subType = DATA_SUB_TYPE.FIlTER;
        return this;
    }



    public String toJson()  {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "error converting to json";
        }
    }


}
