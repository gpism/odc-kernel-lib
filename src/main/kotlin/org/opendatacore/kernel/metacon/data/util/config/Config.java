package org.opendatacore.kernel.metacon.data.util.config;

import org.jboss.logging.Logger;

public class Config {
    private static final Logger _logger = Logger.getLogger(Config.class);
    public String name , type , subType , value ;
    public boolean isRequired;
    public String defaultValue;
    public String description;
    public int iValue ,code;

    public double dValue;

}
