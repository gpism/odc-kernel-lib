package org.opendatacore.kernel.metacon.data.manager.registry;
//JSON 

import com.fasterxml.jackson.annotation.JsonInclude;
//logging
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jboss.logging.Logger;
import org.opendatacore.kernel.metacon.data.model.Template;
import org.opendatacore.kernel.metacon.data.model.TemplateHandler;
import org.opendatacore.kernel.metacon.dynamic.date.DateHandler;
import org.opendatacore.kernel.metacon.plugin.util.io.FileHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateRegistry {
    private static final Logger _logger = Logger.getLogger(TemplateRegistry.class);

    // Store templates
    private TemplateStorage templateStorage = new TemplateStorage();

    private void loadTemplatesFromFile() {
        templateStorage = FileHelper.loadTemplates();

    }

    public void saveTemplatesToFile() {
        templateStorage.templates = templates;
        FileHelper.saveTemplates(templateStorage);
    }

    List<Template> templates = new ArrayList<>();

    List<TemplateHandler> handlers= new ArrayList<>();

    //singleton
    private static TemplateRegistry instance;

    public static TemplateRegistry getInstance(){
        if(instance == null){
            instance = new TemplateRegistry();
            instance.loadTemplatesFromFile();
            instance.registerHandlers();
        }
        return instance;
    }

    //init
    public TemplateRegistry init(){
        return this;
    }

    // add DynamicData

    public TemplateRegistry register(Template dynamicData){
        _logger.info("registerTemplate : " + dynamicData.name);
        templates.add(dynamicData);
        saveTemplatesToFile();

        return this;
    }

    // get all Dynamic Handlers
    public List<TemplateHandler> getHandlers(){
        return handlers;
    }

    //get all DynamicData
    public List<Template> getTemplates(){
        return templates;
    }
    //


    public TemplateRegistry registerHandler(TemplateHandler handler){
        _logger.info("addHandler : " + handler.name);
        handlers.add(handler);
        return this;
    }



    public TemplateRegistry add(Template dynamicData){
        templates.add(dynamicData);
        return this;
    }
    // get DynamicData via stream api
    public Template getTemplateByName(String name){
        return templates.stream().filter(dynamicData -> dynamicData.name.equals(name)).findFirst().get();
    }

    // Remove DynamicData
    public TemplateRegistry remove(String name){
        templates.removeIf(dynamicData -> dynamicData.name.equals(name));
        saveTemplatesToFile();
        return this;

    }

    public void registerHandlers(){
        new DateHandler().details("Tomorrow Date ").outputFormat("yyyy-MM-dd").type("date").register();
    }

    public String getAllTemplatesAsJson() throws JsonProcessingException {



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(templates);

    }

    public String getAllHandlersAsJson() throws JsonProcessingException {



        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(handlers);

    }

    //Update DynamicData
    public TemplateRegistry update(Template dynamicData){
        remove(dynamicData.name);
        add(dynamicData);
        saveTemplatesToFile();
        return this;
    }

    //get DynamicDataHandler by type check for null
     public TemplateHandler getHandler(String type){
          return handlers.stream().filter(handler -> handler.type.equals(type)).findFirst().orElse(null);
     }


    public String replaceTokenWithDynamicData(String tokenName) {
        try {
            // Log the token tokenName being searched for
            _logger.info("Finding a token with tokenName: " + tokenName);

            // Find the DynamicData object with the given tokenName in the dynamicDataList
            Template dd = templates.stream()
                    .filter(dynamicData -> dynamicData.name.equals(tokenName))
                    .findFirst()
                    .orElse(null);

            // Check if DynamicData was found
            if (dd != null) {
                // Get the template data from the DynamicData handler
                String dynamicData = dd.findHandler(dd.type).getTemplateData(dd).await().indefinitely();

                // Log the successful retrieval of the template data
                _logger.info("Successfully retrieved template data for token: " + tokenName + " with value: " + dynamicData);


                return dynamicData;
            } else {
                // Log that the DynamicData was not found for the given tokenName
                _logger.warn("DynamicData not found for token: " + tokenName);

                // Return a meaningful message indicating that the data was not found
                return  tokenName;
            }
        } catch (Exception e) {
            // Log any errors that occur during the retrieval process
            _logger.error("Error while getting template data for token: " + tokenName, e);

            // Return a default message in case of an error
            return tokenName;
        }
    }


    public static List<String> getTokens(String text) {
        List<String> templateVariables = new ArrayList<>();

        // Define the regex pattern to match template variables starting with "@"
        // and may include additional characters like "+1", "1", etc.
        String regex = "@[\\w+-]*\\d*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // Find all occurrences of the pattern in the text
        while (matcher.find()) {
            String variable = matcher.group(); // Get the matched template variable
            _logger.info("variable : " + variable);
            templateVariables.add(variable);
        }

        return templateVariables;
    }


    /**
     * Parse Template
     * @param template
     * @return parsed template
     *
     */
    public String parseTemplate(String template){
      // Get Tokens from template passed
        List<String> tokens = getTokens(template);
        _logger.info("tokens : " + tokens);

        // Loop through tokens and replace with dynamic data
        for (String token : tokens) {
            String dynamicDataOutput = replaceTokenWithDynamicData(token);
            _logger.info("dynamicDataOutput : " + dynamicDataOutput);
            template = template.replace( token, dynamicDataOutput);
        }

        return template;
    }



}
