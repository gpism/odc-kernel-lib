package org.opendatacore.kernel.metacon.data.model.event;

import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;

import java.util.UUID;

public class MDCSession {
    private static final Logger _logger = Logger.getLogger(MDCSession.class);
    public MDCRequest request;
    public MDCResponse response;
    public Resource resource;

    public String sessionId;

    public MDCSession sessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    //Generate session id as GUID
    public MDCSession generateSessionId() {
        sessionId= UUID.randomUUID().toString();
        return this;
    }



    public MDCSession request(MDCRequest request) {
        this.request = request;
        return this;
    }

    public MDCSession response(MDCResponse response) {
        this.response = response;
        return this;
    }

    public MDCSession resource(Resource resource) {
        this.resource = resource;
        return this;
    }


}
