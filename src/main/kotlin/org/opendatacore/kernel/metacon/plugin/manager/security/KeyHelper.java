package org.opendatacore.kernel.metacon.plugin.manager.security;
//JSON 

import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.util.io.FileHelper;

import java.util.UUID;

/**
 * Copyright (c) 2023 Dnky Labs.
 * All rights reserved. This software is the confidential and proprietary
 * information of Dnky Labs ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Dnky Labs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyHelper {
    private static final Logger _logger = Logger.getLogger(KeyHelper.class);
    Key key;

    public KeyHelper init(Key key) {
        this.key = key;
        return this;
    }
    public KeyHelper init() {
        this.key = new Key();
        return this;
    }

    public Key getKey() {
        return this.key;
    }

    //Generate guid
    public static String generateGuid() {
        return UUID.randomUUID().toString();
    }

    // Add more helper methods as needed
    public static Key  autoGenerate() {
        //ge

       return Key.builder().name("master").created(System.currentTimeMillis()).validTill(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 365 * 10).
                role("admin").type("master").key(generateGuid()).secret(generateGuid()).
                build();

    }

    public  Key  autoGenIfNotExists() throws Exception {

        //ge
        if(!FileHelper.checkIfMasterKeyExists()){
            //log
            _logger.info("Master key not found, generating new master key");
            Key key = autoGenerate();
            FileHelper.writeMasterKeyFromFile(key);
            return key;

        }else{
            //log
            _logger.info("Master key found");
            String keyJson = FileHelper.readMasterKeyFromFile();
            return new Key().fromJson(keyJson);
        }

    }


}
