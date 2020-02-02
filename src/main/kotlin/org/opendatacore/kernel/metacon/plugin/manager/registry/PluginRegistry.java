package org.opendatacore.kernel.metacon.plugin.manager.registry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.model.Plugin;
import org.opendatacore.kernel.metacon.plugin.model.service.PluginData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginRegistry {
    private static final Logger _logger = Logger.getLogger(PluginRegistry.class);
    List<Plugin> plugins = new ArrayList<Plugin>();

    //Singleton
    private static PluginRegistry instance = null;

    //PluginData
    public PluginData pluginData = new PluginData();

    private PluginRegistry() {

    }
    public static PluginRegistry getInstance() {
        if(instance == null) {
            _logger.info("Creating new PluginRegistry and calling registry ");
            instance = new PluginRegistry();
           // instance.startRegistry();
        }
        return instance;
    }

    // get plugin count
    public int getPluginCount(){
        return plugins.size();
    }
    //add plugin
    public Plugin registerPlugin(Plugin plugin){
        plugins.add(plugin);
        return plugin;
    }
    //get plugin by name
    public Plugin getPluginByName(String name){
        Plugin plugin = plugins.stream()
                .filter(p -> p.name.equals(name))
                .findAny()
                .orElse(null);
        return plugin;
    }

    //get all plugins
    public List<Plugin> getPlugins(){
        return plugins;
    }


    public PluginData getPluginData(){
        return pluginData.plugins(plugins);
    }

    //get all plugins by type
    public List<Plugin> getPluginsByType(String type){
        List<Plugin> plugins = this.plugins.stream()
                .filter(p -> p.resource.type.equals(type))
                .collect(Collectors.toList());
        return plugins;
    }

    //get all plugins by type and subtype
    public List<Plugin> getPluginsByTypeAndSubtype(String type, String subtype){
        List<Plugin> plugins = this.plugins.stream()
                .filter(p -> p.resource.type.equals(type) && p.resource.subType.equals(subtype))
                .collect(Collectors.toList());
        return plugins;
    }

    //get all plugins by resource id
    public List<Plugin> getPluginsByResourceId(String resourceId){
        List<Plugin> plugins = this.plugins.stream()
                .filter(p -> p.resource.id.equals(resourceId))
                .collect(Collectors.toList());
        return plugins;
    }

    //get all plugins by resource name
    public List<Plugin> getPluginsByResourceName(String resourceName){
        List<Plugin> plugins = this.plugins.stream()
                .filter(p -> p.resource.name.equals(resourceName))
                .collect(Collectors.toList());
        return plugins;
    }

    //update plugin
    public void updatePlugin(Plugin plugin){
        Plugin oldPlugin = getPluginByName(plugin.name);
        if(oldPlugin != null){
            plugins.remove(oldPlugin);
            plugins.add(plugin);
        }
    }

    //delete plugin
    public void deletePlugin(Plugin plugin){
        Plugin oldPlugin = getPluginByName(plugin.name);
        if(oldPlugin != null){
            plugins.remove(oldPlugin);
        }
    }

//    public void startRegistry(){
//        _logger.info("Starting registry");
//        try {
//            new SnowflakePlugin().register();
//            new OraclePlugin().register();
//            new MySqlPlugin().register();
//            new DyanamoDbPlugin().register();
//            new Db2Plugin().register();
//            new NetezzaPlugin().register();
//            new LambdaPlugin().register();
//            new AS400Plugin().register();
//            new SalesforcePlugin().register();
//            new RedshiftPlugin().register();
//        } catch (Exception e) {
//            _logger.error("Error starting registry", e);
//        }
//    }


    public List<Plugin> getAllPlugins() {

        return plugins;

    }

    public String getAllPluginsAsJson() throws JsonProcessingException {

        List<Plugin> pluginList = plugins;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(pluginList);

    }

    public Plugin getPluginById(String id) {
        return plugins.stream()
                .filter(p -> p.resource.id.equals(id))
                .findAny()
                .orElse(null);
    }

}
