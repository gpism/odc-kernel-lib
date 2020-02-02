package org.opendatacore.kernel.metacon.plugin.util;

import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.model.Plugin;
import org.opendatacore.kernel.metacon.plugin.manager.storage.ResourceRegistry;
import org.opendatacore.kernel.metacon.data.model.types.OPERATION_TYPES;
import org.opendatacore.kernel.metacon.plugin.manager.registry.PluginRegistry;
import org.opendatacore.kernel.metacon.data.model.event.MDCRequest;
import org.opendatacore.kernel.metacon.data.model.event.MDCResponse;
import org.opendatacore.kernel.metacon.data.model.resource.Resource;

public class PluginUtil {
    private static final Logger _logger = Logger.getLogger(PluginUtil.class);


    public MDCResponse processRequest(MDCRequest mdcRequest) {
        MDCResponse mdcResponse = new MDCResponse().request(mdcRequest);

        // get from registry
        try {

            _logger.info("1. Get Resource from id   : " + mdcRequest.resourceId);
            Resource resource1 = ResourceRegistry.getInstance().getResourceById(mdcRequest.resourceId);
            if(resource1 == null){
                _logger.error("1. Error in running tests   : " + "Resource not found");
                return mdcResponse.message("Resource not found").status("FAILED");
            }else{
                _logger.info("1. Resource found   : " + resource1.name);
                // Get plugin from registry from request type
                _logger.info("2. Get Plugin from name   : " + mdcRequest.type);
                Plugin plugin = PluginRegistry.getInstance().getPluginByName(mdcRequest.type);
                if(plugin == null){
                    _logger.error("2. Error in running tests   : " + "Plugin not found");
                    return mdcResponse.message("Plugin not found").status("FAILED");
                }else{
                    _logger.info("3. Plugin found   : " + plugin.name);
                    plugin.connect(resource1);
                    if(mdcRequest.operation!=null){
                        if(mdcRequest.operation.equals(OPERATION_TYPES.CHK_CONNECTION_STATUS)){
                            _logger.info("4. Operation found   : " + mdcRequest.operation);
                           return plugin.connect(resource1);
                        }
                    }

                    mdcResponse = plugin.execute(mdcRequest.parseTemplate());
                    _logger.info("mdcResponse ->>>>> " + mdcResponse.toJson());
                    return  mdcResponse;
                }

            }


        } catch (Exception e) {
            _logger.error("Error in running tests   : " + e.getMessage());
            return mdcResponse.message(e.getMessage()).status("FAILED");


        }
    }

}
