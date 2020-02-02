package org.opendatacore.kernel.metacon.data.model.types;
//JSON 

//logging


public interface OPERATION_TYPES {


        public static final String CHK_CONNECTION_STATUS = "chk_connection_status";

        public static final String SCHEDULED_MONITORING = "schedule_monitoring";
        public static final String SCHEDULED_MONITORING_START = "schedule_monitoring_start";
        public static final String SCHEDULED_MONITORING_STOP = "schedule_monitoring_stop";
        public static final String SCHEDULED_MONITORING_PAUSE = "schedule_monitoring_pause";
        public static final String SCHEDULED_MONITORING_RESUME = "schedule_monitoring_resume";
        public static final String SCHEDULED_MONITORING_STATUS = "schedule_monitoring_status";

    public static final String SCHEDULED_TESTING ="schedule_testing";
    public static final String SCHEDULED_TESTING_START ="schedule_testing_start";
    public static final String SCHEDULED_TESTING_STOP ="schedule_testing_stop";
    public static final String SCHEDULED_TESTING_PAUSE ="schedule_testing_pause";
    public static final String SCHEDULED_TESTING_RESUME ="schedule_testing_resume";
    public static final String SCHEDULED_TESTING_STATUS ="schedule_testing_status";
    public static final String SCHEDULED_TESTING_STATUS_ALL ="schedule_testing_status_all";


    public static final String CACHE_PUT  ="cache_put";
    public static final String CACHE_GET  ="cache_get";
    public static final String CACHE_DELETE  ="cache_delete";
    public static final String CACHE_CLEAR  ="cache_clear";
    public static final String CACHE_GET_ALL  ="cache_get_all";


}
