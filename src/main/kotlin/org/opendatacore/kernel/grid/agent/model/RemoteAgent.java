package org.opendatacore.kernel.grid.agent.model;

import org.opendatacore.kernel.metacon.data.model.event.MDCRequest;
import org.opendatacore.kernel.metacon.data.model.event.MDCResponse;

public interface RemoteAgent {

    public Agent getAgent();
    public MDCResponse execute(MDCRequest request) ;

    public MDCResponse start() ;

    public MDCResponse status() ;
}
