package org.opendatacore.kernel.metacon.plugin.manager.security;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//logging
import org.jboss.logging.Logger;

import java.util.List;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */

public class KeyStore {

    public String name;
    public List<Key> keys;

    //init with keys
    public KeyStore init(List<Key> keys) {
        this.keys = keys;
        return this;
    }
    private static final Logger _logger = Logger.getLogger(KeyStore.class);


    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static KeyStore fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, KeyStore.class);
    }
}
