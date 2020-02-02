package org.opendatacore.kernel.metacon.plugin.model.service;
//JSON 

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.plugin.model.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceInfo {
    private static final Logger _logger = Logger.getLogger(ServiceInfo.class);

    public String name,description,version="1.0" ,url,icon    ;
    public List<Feature> features = new ArrayList<>();
    public Sample sample = new Sample();


    public ServiceInfo() {
    }


    public ServiceInfo populate(Plugin plugin){
        this.name = plugin.getName();
        this.description = plugin.getDescription();

        return this;
    }

    public ServiceInfo populateForDb(String dbName, int defaultPort) {
        features.add(new Feature().name("userId").uiTypeString().description("Your " + dbName + " User ID").example("my_user_id").required());
        features.add(new Feature().name("password").uiTypeString().description("Your " + dbName + " Password").example("my_password").required());
        features.add(new Feature().name("serverName").uiTypeString().description("The Server Name of your " + dbName + " database").example("my_server_name").required());
        features.add(new Feature().name("port").uiTypeInt().description("The Port number for connecting to your " + dbName + " database").example("usually " + defaultPort).required());
        features.add(new Feature().name("databaseName").uiTypeString().description("The Name of your " + dbName + " Database").example("my_database_name").required());

        return this;
    }

    //sample
    public ServiceInfo sample(Sample sample) {
        this.sample = sample;
        return this;
    }






    public ServiceInfo(String name, String description, String version, String url, String icon) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.url = url;
        this.icon = icon;
    }

    public ServiceInfo name(String name) {
        this.name = name;
        return this;
    }

    public ServiceInfo description(String description) {
        this.description = description;
        return this;
    }

    public ServiceInfo version(String version) {
        this.version = version;
        return this;
    }

    public ServiceInfo url(String url) {
        this.url = url;
        return this;
    }

    public ServiceInfo icon(String icon) {
        this.icon = icon;
        return this;
    }

    public ServiceInfo features(List<Feature> features) {
        this.features = features;
        return this;
    }

    public ServiceInfo addFeature(Feature feature) {
        this.features.add(feature);
        return this;
    }

    public ServiceInfo addFeatures(List<Feature> features) {
        this.features.addAll(features);
        return this;
    }

    public ServiceInfo clearFeatures() {
        this.features.clear();
        return this;
    }

    public ServiceInfo removeFeature(Feature feature) {
        this.features.remove(feature);
        return this;
    }

    public ServiceInfo removeFeatures(List<Feature> features) {
        this.features.removeAll(features);
        return this;
    }

    public ServiceInfo removeFeature(int index) {
        this.features.remove(index);
        return this;
    }

    public ServiceInfo removeFeatures(int fromIndex, int toIndex) {
        this.features.subList(fromIndex, toIndex).clear();
        return this;
    }

    // get all features


    //Get all features of group ui type from using stream
    public List<Feature> getFeaturesByUiType() {
        return features.stream().filter(feature -> feature.group.equals(FEATURE_TYPES.UI_GROUP)).collect(Collectors.toList());
    }



    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static ServiceInfo fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, ServiceInfo.class);
    }
}
