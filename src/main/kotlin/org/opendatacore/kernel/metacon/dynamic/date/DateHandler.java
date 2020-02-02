package org.opendatacore.kernel.metacon.dynamic.date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.opendatacore.kernel.metacon.data.model.Data;
import org.opendatacore.kernel.metacon.data.model.Template;
import org.opendatacore.kernel.metacon.data.model.TemplateHandler;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DateHandler extends TemplateHandler {
    private static final Logger _logger = Logger.getLogger(DateHandler.class);

    // toJson() function using Jackson
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    // fromJson() function using Jackson
    public static DateHandler fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, DateHandler.class);
    }

    // get Formated Date
    public String getFormatedDate(DateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        String formattedDate = formatter.print(dateTime);
        return formattedDate;
    }

    @Override
    public Uni<String> getTemplateData(Template dynamicData) {
        Date date = new Date();
        // log DynamicData
        try {
            _logger.info(" DynamicData : " + dynamicData.toJson());

            Data data = dynamicData.data;
            String pattern = "yyyy-MM-dd HH:mm:ss";

            if (dynamicData.outputFormat != null) {
                pattern = dynamicData.outputFormat;
            }

            if (dynamicData.type.equals("date")) {
                _logger.info(" Type date : ");
                if (dynamicData.subType.equals("add")) {
                    int days = data.dValue.intValue();
                    // log operation type
                    _logger.info(" Type date : " + dynamicData.subType);

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusDays(days), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type date : " + data.subType);
                    int days = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusDays(days), pattern));
                }
            } else if (dynamicData.type.equals("month")) {
                _logger.info(" Type month : ");
                if (dynamicData.subType.equals("add")) {
                    _logger.info(" Type month : " + data.subType);
                    int months = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusMonths(months), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type month : " + data.subType);
                    int months = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusMonths(months), pattern));
                }
            } else if (dynamicData.type.equals("year")) {
                _logger.info(" Type year : ");
                if (dynamicData.subType.equals("add")) {
                    _logger.info(" Type year : " + data.subType);
                    int years = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusYears(years), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type year : " + data.subType);
                    int years = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusYears(years), pattern));
                }
            } else if (dynamicData.type.equals("week")) {
                _logger.info(" Type week : ");
                if (dynamicData.subType.equals("add")) {
                    _logger.info(" Type week : " + data.subType);
                    int weeks = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusWeeks(weeks), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type week : " + data.subType);
                    int weeks = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusWeeks(weeks), pattern));
                }
            } else if (dynamicData.type.equals("hour")) {
                _logger.info(" Type hour : ");
                if (dynamicData.subType.equals("add")) {
                    _logger.info(" Type hour : " + data.subType);
                    int hours = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusHours(hours), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type hour : " + data.subType);
                    int hours = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusHours(hours), pattern));
                }
            } else if (dynamicData.type.equals("minute")) {
                _logger.info(" Type minute : ");
                if (dynamicData.subType.equals("add")) {
                    _logger.info(" Type minute : " + data.subType);
                    int minutes = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.plusMinutes(minutes), pattern));
                }
                if (dynamicData.subType.equals("minus")) {
                    _logger.info(" Type minute : " + data.subType);
                    int minutes = data.dValue.intValue();

                    DateTime currentDate = new DateTime();
                    return Uni.createFrom().item(getFormatedDate(currentDate.minusMinutes(minutes), pattern));
                }
            }

            return Uni.createFrom().item(date.toString());
        } catch (Exception e) {
            _logger.error("Error in DynamicData : " + e.getMessage());
            return Uni.createFrom().item(date.toString());
        }
    }
}
