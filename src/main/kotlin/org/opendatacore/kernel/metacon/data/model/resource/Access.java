package org.opendatacore.kernel.metacon.data.model.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.util.security.SecurityUtils;
import org.opendatacore.kernel.metacon.plugin.util.security.conf.KEYS;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Access {
    private static final Logger _logger = Logger.getLogger(Access.class);
    public String userId;
    public String password;
    public String dc;
    public String schema;
    public String databaseName;

    public String tableName;
    public String account;
    public String region;
    public String role;

    public String url;
    public String serverName;
    public int portNumber;

    public String id;
    public String name;

    public String sessionToken;


    public Access() {
    }




    private String getEncryptedPassword(String password){
        _logger.info("Getting encrypted password");
        try {
            return   SecurityUtils.encrypt(password, KEYS.SIGN_KEY);
        } catch (Exception e) {
            _logger.error("Error encrypting password : " + e.getMessage());
            return "error : " + e.getMessage() ;

        }

    }

    //session token
    public Access sessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return this;
    }

    // hasToken return true if token is not null
    public boolean hasToken() {
        return this.sessionToken != null;
    }


    public Access encrypt() {
        this.password = getEncryptedPassword(this.password);
        return this;
    }

    public Access id(String id) {
        this.id = id;
        return this;
    }

    public Access name(String name) {
        this.name = name;
        return this;
    }



    public Access serverName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public Access portNumber(int portNumber) {
        this.portNumber = portNumber;
        return this;
    }



    public Access url(String url) {
        this.url = url;
        return this;
    }

    public Access userId(String userId) {
        this.userId = userId;
        return this;
    }

    public Access password(String password) {
        this.password = getEncryptedPassword(password);
        return this;
    }

    public Access dc(String dc) {
        this.dc = dc;
        return this;
    }

    public Access schema(String schema) {
        this.schema = schema;
        return this;
    }

    public Access databaseName(String db) {
        this.databaseName = db;
        return this;
    }

    public Access account(String token) {
        this.account = token;
        return this;
    }

    public Access region(String region) {
        this.region = region;
        return this;
    }

    public Access role(String role) {
        this.role = role;
        return this;
    }

    public Access tableName(String tableName) {
        this.tableName = tableName;
        return this;
    }



}
