package org.opendatacore.kernel.metacon.data.model.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.json.JSONArray;
import org.opendatacore.kernel.metacon.data.model.Data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MDCResponse {
    private static final Logger _logger = Logger.getLogger(MDCResponse.class);

    public MDCRequest request;
    public String status , message ,type, subType,orgId,data,format;
    @JsonRawValue
    public  String jsonData;

    public JSONObject json;

    public Integer code;



    public long startTime , endTime;

    public List<Data> dataList = new ArrayList<Data>();




    //get data in data list by name using stream api
    public Data getData(String name) {
        return this.dataList.stream().filter(data -> data.name.equals(name)).findFirst().orElse(null);
    }

    public boolean isJson(String response) {
        String jsonPattern = "^\\s*(\\{.*\\}|\\[.*\\])\\s*$";
        return response.matches(jsonPattern);
    }
    //type
    public MDCResponse type(String type) {
        this.type = type;
        return this;
    }

    // format


    //subtype

    public MDCResponse subType(String subType) {
        this.subType = subType;
        return this;
    }

    public MDCResponse addRawData(String data) {
        if (isJson(data)) {
            _logger.info("data is json");
            this.jsonData = data;
            this.json = new JSONObject(data);
            this.format = "json";
        } else {
            _logger.info("data is not json");
            this.data =data;
        }
        this.jsonData = jsonData;
        return this;
    }



    //get data in data list by type using stream api
    public List<Data> getDataByType(String type) {
        return this.dataList.stream().filter(data -> data.type.equals(type)).collect(Collectors.toList());
    }

    //get data in data list by name and type using stream api
    public Data getData(String name, String type) {
        return this.dataList.stream().filter(data -> data.name.equals(name) && data.type.equals(type)).findFirst().orElse(null);
    }

    public MDCResponse dataList(List<Data> dataList) {
        this.dataList = dataList;
        return this;
    }

    public MDCResponse setResourceIDJsonData(String id) {
        this.jsonData= "{\"resourceId\":\""+id+"\"}";
        return this;
    }


    public MDCResponse addData(Data data) {
        this.dataList.add(data);
        return this;
    }

    //find data by name using stream
    public Data findData(String name) {
        return this.dataList.stream().filter(data -> data.name.equals(name)).findFirst().orElse(null);
    }

    //find all data by type using stream
    public List<Data> findDataByType(String type) {
        return this.dataList.stream().filter(data -> data.type.equals(type)).collect(Collectors.toList());
    }

    public MDCResponse request(MDCRequest request) {
        this.request = request;
        return this;
    }

    public MDCResponse status(String status) {
        this.status = status;
        return this;
    }

    public MDCResponse message(String message) {
        this.message = message;
        return this;
    }
    public MDCResponse success(String message) {
        this.message = message;
        this.status = "success";
        this.code = 200;
        this.endTime=System.currentTimeMillis();
        return this;
    }
    public MDCResponse fail(String message) {
        this.message = message;
        this.status = "failed";
        this.code = 500;
        this.endTime=System.currentTimeMillis();
        return this;
    }

    public MDCResponse addDataAsJSON(Data data) {
        this.jsonData =data.toJson();
        return this;
    }
    //set jsonData

    public MDCResponse addRawJSON(String data) {
        this.jsonData =data;
        return this;
    }

    public MDCResponse code(int code) {
        this.code = code;
        return this;
    }

    public MDCResponse startTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public MDCResponse endTime(long endTime) {
        this.endTime = endTime;
        return this;
    }

    // Return time in seconds from start to end time
    public double getTimeInSec() {
        this.format = format;
        // check if end time is  greater than start time
        if (this.endTime < this.startTime) {
            return -100;
        }
        return (this.endTime - this.startTime) / 1000;
    }


    // function to convert JDBC resultset to JSON
    public MDCResponse resultSetToJson(ResultSet resultSet)  {
         JSONArray result = new JSONArray();
        try{ ResultSetMetaData md = resultSet.getMetaData();
            int numCols = md.getColumnCount();
            // Create a JSON object
            ObjectMapper mapper = new ObjectMapper();
            _logger.info("numCols: " + numCols);
            List<String> colNames = IntStream.range(0, numCols)
                    .mapToObj(i -> {
                        try {
                            return md.getColumnName(i + 1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return "?";
                        }
                    })
                    .collect(Collectors.toList());

            _logger.info("colNames: " + colNames);


            while (resultSet.next()) {
                JSONObject row = new JSONObject();

                colNames.forEach(cn -> {
                    try {

                        _logger.info("cn: " + cn);
                        row.put(cn, resultSet.getObject(cn));


                    } catch (Exception e) {
                        _logger.error("error converting result set to json" + e.getMessage());

                    }
                });
                _logger.info("ADding : " + result.length() +row.toString());
                try{
                    result.put(row);
                }catch (Exception e){
                    _logger.error("->>>>>>>>>>>>>>>>>>> error Add row result set to json Array" + e.getMessage());
                }

                _logger.info("result: " + result);



            }
            //  _logger.info("result: " + result);
            this.jsonData = result.toString();
            return this;

        }catch (Exception e){
            _logger.error("error converting result set to json" + e.getMessage());
            return this;
        }

    }

    /**
     * Creates Json representation
     *
     * @return
     * @throws JsonProcessingException
     */
    public String toJson()  {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
           return "error converting to json";
        }
    }

    /**
     * Creates object from json
     *
     * @return
     * @throws JsonProcessingException
     */
    public MDCResponse fromJson(String json)  {

        try {
            return new ObjectMapper().readValue(json, MDCResponse.class);
        } catch (JsonProcessingException e) {
            return null;
        }

    }


}
