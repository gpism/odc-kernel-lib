package org.opendatacore.kernel.metacon.data.util.app;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppData {
    private static final Logger _logger = Logger.getLogger(AppData.class);
    public String name, url , description, icon, version, type, subType, path, msg ;

    public AppData() {
    }

    //name
    public AppData name(String name) {
        this.name = name;
        return this;
    }

    //description
    public AppData description(String description) {
        this.description = description;
        return this;
    }

    //icon
    public AppData icon(String icon) {
        this.icon = icon;
        return this;
    }

    //version
    public AppData version(String version) {
        this.version = version;
        return this;
    }

    //type
    public AppData type(String type) {
        this.type = type;
        return this;
    }

    //subType
    public AppData subType(String subType) {
        this.subType = subType;
        return this;
    }

    //path
    public AppData path(String path) {
        this.path = path;
        return this;
    }

    //msg
    public AppData msg(String msg) {
        this.msg = msg;
        return this;
    }

    //url
    public AppData url(String url) {
        this.url = url;
        return this;
    }

    // toString() function using Jackson
    @Override
    public String toString() {
        try {
            return toJson();
        } catch (JsonProcessingException e) {
            _logger.error(e.getMessage());
            return "error converting to json" +e.getMessage() + "   ";
        }
    }

    //build
    public AppData build() {
        return this;
    }


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static AppData fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, AppData.class);
    }
}
