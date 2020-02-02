package org.opendatacore.kernel.metacon.plugin.manager.security;
//JSON 

//logging
import org.jboss.logging.Logger;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */

public class KeyBuilder {
    private static final Logger _logger = Logger.getLogger(KeyBuilder.class);

    public Key key;

    // init with key
    public KeyBuilder init(Key key) {
        this.key = key;
        return this;
    }

    //get Key by name
    public KeyBuilder name(String name) {
        this.key.name = name;
        return this;
    }

    //get Key by id
    public KeyBuilder id(String id) {
        this.key.id = id;
        return this;
    }

    //get Key by key
    public KeyBuilder key(String key) {
        this.key.key = key;
        return this;
    }

    //get Key by secret
    public KeyBuilder secret(String secret) {
        this.key.secret = secret;
        return this;
    }

    //get Key by role
    public KeyBuilder role(String role) {
        this.key.role = role;
        return this;
    }

    //get Key by type
    public KeyBuilder type(String type) {
        this.key.type = type;
        return this;
    }

    //get Key by created
    public KeyBuilder created(long created) {
        this.key.created = created;
        return this;
    }

    //get Key by validTill
    public KeyBuilder validTill(long validTill) {
        this.key.validTill = validTill;
        return this;
    }
    // build Key
    public Key build() {
        return this.key;
    }

}
