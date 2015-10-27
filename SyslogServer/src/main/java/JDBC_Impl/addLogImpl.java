package JDBC_Impl;

import java.util.Map;

/**
 * Created by root on 23.10.15.
 */
public interface addLogImpl {
    public Map<String, String> log(Map<String, String> sourceParams, Map<String, String> eventGeneralParams, Map<String, String> eventSpecificParams);
}
