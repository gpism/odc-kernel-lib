package org.opendatacore.kernel.metacon.plugin.model.service;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.event.MDCRequest;
import org.opendatacore.kernel.metacon.data.model.event.MDCResponse;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Sample {
    private static final Logger _logger = Logger.getLogger(Sample.class);

    public String name,details;

    public MDCRequest request;
    public MDCResponse response;

    public Resource testResource;

    public Sample testResource(Resource resource){
        this.testResource = resource;
        return this;
    }

    public Sample testData(){

        this.name = "test";
        this.details = "Test Request Response  details";
        return this;
    }

    public Sample() {
    }

    public Sample(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Sample(String name, String details, MDCRequest request, MDCResponse response) {
        this.name = name;
        this.details = details;
        this.request = request;
        this.response = response;
    }

    // set name
    public Sample name(String name) {
        this.name = name;
        return this;
    }

    // set details
    public Sample details(String details) {
        this.details = details;
        return this;
    }

    // set request
    public Sample request(MDCRequest request) {
        this.request = request;
        return this;
    }

    // set response
    public Sample response(MDCResponse response) {
        this.response = response;
        return this;
    }




    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static Sample fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Sample.class);
    }
}
