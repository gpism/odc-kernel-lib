package org.opendatacore.kernel.metacon.plugin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.util.security.SecurityUtils;
import org.opendatacore.kernel.metacon.plugin.util.security.conf.KEYS;
import org.opendatacore.kernel.metacon.data.model.event.MDCRequest;
import org.opendatacore.kernel.metacon.data.model.event.MDCResponse;
import org.opendatacore.kernel.metacon.data.model.event.MDCSession;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;
import org.opendatacore.kernel.metacon.plugin.model.service.ServiceInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class Plugin {
    private static final Logger _logger = Logger.getLogger(Plugin.class);

    public String name,description;


    @JsonIgnore
    List<MDCSession> sessions = new ArrayList<MDCSession>();

    @JsonIgnore
    public Resource resource;

    public Plugin init(Resource resource) {
        this.resource = resource;
        return this;
    }

    protected String decryptPassword(String password){
        _logger.info("Getting decrypted password");
        try {
            String password1 = SecurityUtils.decrypt(password, KEYS.SIGN_KEY);
            _logger.info("Password decrypted →  " + password1);
            return password1;
        } catch (Exception e) {
            _logger.error("Error decrypting password : " + e.getMessage());
            return "error : " + e.getMessage() ;

        }


    }


    public abstract MDCResponse execute(MDCRequest mdcRequest) throws Exception;

    public abstract MDCResponse connect(Resource resource) throws Exception;


    public abstract ServiceInfo getServiceInfo() ;



    public abstract Plugin register() throws Exception;

    public abstract String getName() ;

    public abstract String getDescription() ;







    //Get session
}
