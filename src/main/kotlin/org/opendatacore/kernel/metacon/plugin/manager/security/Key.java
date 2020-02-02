package org.opendatacore.kernel.metacon.plugin.manager.security;
//JSON 

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//logging
import org.jboss.logging.Logger;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */

public class Key {
    private static final Logger _logger = Logger.getLogger(Key.class);


    public String name;
    public String id;
    public String key ,secret, role;
    public String type;
    public long created , validTill;



    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //add pretty print
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);

    }
    //get builder
    @JsonIgnore
    public KeyBuilder getBuilder() {
        return new KeyBuilder().init(this);
    }

    // get builder
    public static KeyBuilder builder() {
        return new KeyBuilder().init(new Key());
    }
    //get helper
  @JsonIgnore
    public  KeyHelper getHelper() {
        return new KeyHelper().init(this);
    }
    //get helper
    public static KeyHelper helper() {
        return new KeyHelper().init();
    }





    // fromJson() function using Jackson
    public static Key fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Key.class);
    }
}
