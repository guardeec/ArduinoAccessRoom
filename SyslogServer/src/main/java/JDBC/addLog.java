package JDBC;
import JDBC_Impl.addLogImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 22.10.15.
 */
public class addLog extends JdbcDaoSupport implements addLogImpl {
    private Map<String, String> answer = new HashMap<String, String>();

    public Map<String, String> log(Map<String, String> sourceParams, Map<String, String> eventGeneralParams, Map<String, String> eventSpecificParams) {

        /*
        Ищем id источника по sourceParams
        Если не находим - добавляем новый и берём id нового источника
         */
        try {
            Integer source_id = getJdbcTemplate().queryForInt(
                    "SELECT id AS source_id FROM event_sources WHERE type_id = coalesce(?, type_id) AND ip = coalesce(?, ip) AND mac = coalesce(?, mac) AND device_id = coalesce(?, device_id) AND cl_acc_id = coalesce(?, cl_acc_id) AND cl_acc_l = coalesce(?, cl_acc_l);",
                    new Object[]{
                            sourceParams.get("type_id")     == null ? null : Integer.parseInt(sourceParams.get("type_id")),
                            sourceParams.get("ip")          == null ? null : sourceParams.get("ip"),
                            sourceParams.get("mac")         == null ? null : sourceParams.get("mac"),
                            sourceParams.get("device_id")   == null ? null : Integer.parseInt(sourceParams.get("device_id")),
                            sourceParams.get("cl_acc_id")   == null ? null : Integer.parseInt(sourceParams.get("cl_acc_id")),
                            sourceParams.get("cl_acc_l")    == null ? null : sourceParams.get("cl_acc_l")
                    }
            );
            eventGeneralParams.put("source_id", source_id.toString());
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error");
            return answer;
        }catch (EmptyResultDataAccessException ex){
            try{
                Integer source_id = getJdbcTemplate().queryForInt(
                        "INSERT INTO event_sources (type_id, ip, mac, device_id, cl_acc_id, cl_acc_l) VALUES (?, ?, ?, ?, ?, ?) RETURNING id AS source_id;",
                        new Object[]{
                                sourceParams.get("type_id")     == null ? null : Integer.parseInt(sourceParams.get("type_id")),
                                sourceParams.get("ip")          == null ? null : sourceParams.get("ip"),
                                sourceParams.get("mac")         == null ? null : sourceParams.get("mac"),
                                sourceParams.get("device_id")   == null ? null : Integer.parseInt(sourceParams.get("device_id")),
                                sourceParams.get("cl_acc_id")   == null ? null : Integer.parseInt(sourceParams.get("cl_acc_id")),
                                sourceParams.get("cl_acc_l")    == null ? null : sourceParams.get("cl_acc_l")
                        }
                );
                eventGeneralParams.put("source_id", source_id.toString());
            }catch (CannotGetJdbcConnectionException ex2){
                answer.put("message", "Error when getting source id");
                return answer;
            }
        }

        /*
        Заносим общие параметры события
         */
        try{
            Integer event_id = getJdbcTemplate().queryForInt(
                    "INSERT INTO events (type_id, source_id, date_time, res_type, descr) VALUES (?, ?, ?, ?, ?) RETURNING id AS event_id;",
                    new Object[]{
                            eventGeneralParams.get("event_type_id") == null ? null : Integer.parseInt(eventGeneralParams.get("event_type_id")),
                            eventGeneralParams.get("source_id")     == null ? null : Integer.parseInt(eventGeneralParams.get("source_id")),
                            eventGeneralParams.get("date_time")     == null ? null : Date.valueOf(eventGeneralParams.get("date_time")),
                            eventGeneralParams.get("res_type")      == null ? null : Boolean.parseBoolean(eventGeneralParams.get("res_type")),
                            eventGeneralParams.get("descr")         == null ? null : eventGeneralParams.get("descr")
                    }
            );
            eventSpecificParams.put("event_id", event_id.toString());
        }catch (CannotGetJdbcConnectionException ex){
            answer.put("message", "Error when getting source id");
            return answer;
        }

        /*
        Заносим специфические параметры события
         */
        Integer event_type_id = Integer.parseInt(eventGeneralParams.get("event_type_id"));

        //HOST AGENT EVENTS
        if (event_type_id==1 || event_type_id==2 || event_type_id==3){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO host_ag_events (event_id, empl_id, empl_name, os_acc_id, os_acc_l) VALUES (?, ?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id") == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("empl_id")  == null ? null : Integer.parseInt(eventSpecificParams.get("empl_id")),
                                eventSpecificParams.get("empl_name")== null ? null : eventSpecificParams.get("empl_name"),
                                eventSpecificParams.get("os_acc_id")== null ? null : Integer.parseInt(eventSpecificParams.get("os_acc_id")),
                                eventSpecificParams.get("os_acc_l") == null ? null : eventSpecificParams.get("os_acc_l")
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error add HOST AGENT EVENTS");
                return answer;
            }
        }

        //ARDUINO RFID EVENTS
        if (event_type_id==4 || event_type_id==5){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO ard_rfid_events (event_id, user_id, user_name, sys_r_id, sys_r_title, card_id) VALUES (?, ?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("user_id")      == null ? null : Integer.parseInt(eventSpecificParams.get("user_id")),
                                eventSpecificParams.get("user_name")    == null ? null : eventSpecificParams.get("user_name"),
                                eventSpecificParams.get("sys_r_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("sys_r_id")),
                                eventSpecificParams.get("sys_r_title")  == null ? null : eventSpecificParams.get("sys_r_title"),
                                eventSpecificParams.get("card_id")      == null ? null : Integer.parseInt(eventSpecificParams.get("card_id"))
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add ARDUINO RFID EVENTS");
                return answer;
            }
        }

        //ARDUINO LOCAL ERROR EVENTS
        if (event_type_id==6 || event_type_id==7){
            answer.put("message", "Success");
            return answer;
            /*
            Nothing to Insert - Check Description
             */
        }

        //ARDUINO CONNECTION ERROR EVENTS
        if (event_type_id==8){
            answer.put("message", "Success");
            return answer;
            /*
            Nothing to Insert - Check Description
             */
        }

        //ARDUINO UNAUTHORIZED ACCESS EVENTS
        if (event_type_id==9){
            answer.put("message", "Success");
            return answer;
            /*
            Nothing to Insert - Check Description
             */
        }


        //ADMIN CLIENT AUTHORIZATION EVENTS
        if (event_type_id==10 || event_type_id==11 || event_type_id==12){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO adm_cl_auth_events (event_id, cl_acc_id, cl_acc_l, acc_r) VALUES (?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id") == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("cl_acc_id")== null ? null : Integer.parseInt(eventSpecificParams.get("cl_acc_id")),
                                eventSpecificParams.get("cl_acc_l") == null ? null : eventSpecificParams.get("cl_acc_l"),
                                eventSpecificParams.get("acc_r")    == null ? null : eventSpecificParams.get("acc_r")
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add ADMIN CLIENT AUTHORIZATION EVENTS");
                return answer;
            }
        }


        //TECHNICAL DEPARTMENT EVENTS
        if (event_type_id==13 || event_type_id==14 || event_type_id==15){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO tech_dep_events (event_id, device_id, device_ip, device_mac, device_spec) VALUES (?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("device_id")    == null ? null : Integer.parseInt(eventSpecificParams.get("device_id")),
                                eventSpecificParams.get("device_ip")    == null ? null : eventSpecificParams.get("device_ip"),
                                eventSpecificParams.get("device_mac")   == null ? null : eventSpecificParams.get("device_mac"),
                                eventSpecificParams.get("device_spec")  == null ? null : eventSpecificParams.get("device_spec")
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add TECHNICAL DEPARTMENT EVENTS");
                return answer;
            }
        }


        //RECEPTION DEPARTMENT EVENTS
        if (event_type_id==16 || event_type_id==17 || event_type_id==18){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO rec_dep_events (event_id, guest_id, guest_name, card_id, time_start, time_end) VALUES (?, ?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("guest_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("guest_id")),
                                eventSpecificParams.get("guest_name")   == null ? null : eventSpecificParams.get("guest_name"),
                                eventSpecificParams.get("card_id")      == null ? null : Integer.parseInt(eventSpecificParams.get("card_id")),
                                eventSpecificParams.get("time_start")   == null ? null : Date.valueOf(eventSpecificParams.get("time_start")),
                                eventSpecificParams.get("time_end")     == null ? null : Date.valueOf(eventSpecificParams.get("time_end"))
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add RECEPTION DEPARTMENT EVENTS");
                return answer;
            }
        }


        //HR DEPARTMENT EVENTS
        if (event_type_id==19 || event_type_id==20 || event_type_id==21){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO hr_dep_events (event_id, empl_id, empl_name, empl_st_id, empl_st_descr, sys_r_id, sys_r_title, card_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("empl_id")      == null ? null : Integer.parseInt(eventSpecificParams.get("empl_id")),
                                eventSpecificParams.get("empl_name")    == null ? null : eventSpecificParams.get("empl_name"),
                                eventSpecificParams.get("empl_st_id")   == null ? null : Integer.parseInt(eventSpecificParams.get("empl_st_id")),
                                eventSpecificParams.get("empl_st_descr")== null ? null : eventSpecificParams.get("empl_st_descr"),
                                eventSpecificParams.get("sys_r_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("sys_r_id")),
                                eventSpecificParams.get("sys_r_title")  == null ? null : eventSpecificParams.get("sys_r_title"),
                                eventSpecificParams.get("card_id")      == null ? null : Integer.parseInt(eventSpecificParams.get("card_id"))
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add HR DEPARTMENT EVENTS");
                return answer;
            }
        }


        //SECURITY DEPARTMENT SYSTEM ROLES EVENTS
        if (event_type_id==22 || event_type_id==23 || event_type_id==24){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO sec_dep_sys_r_events (event_id, sys_r_id, sys_r_title) VALUES (?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("sys_r_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("sys_r_id")),
                                eventSpecificParams.get("sys_r_title")  == null ? null : eventSpecificParams.get("sys_r_title")
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add SECURITY DEPARTMENT SYSTEM ROLES EVENTS");
                return answer;
            }
        }


        //SECURITY DEPARTMENT ACCESS RIGHTS EVENTS
        if (event_type_id==25){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO sec_dep_acs_r_events (event_id, device_id, device_spec, sys_r_id, sys_r_title, access) VALUES (?, ?, ?, ?, ?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("event_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("device_id")),
                                eventSpecificParams.get("device_spec")  == null ? null : eventSpecificParams.get("device_spec"),
                                eventSpecificParams.get("sys_r_id")     == null ? null : Integer.parseInt(eventSpecificParams.get("sys_r_id")),
                                eventSpecificParams.get("sys_r_title")  == null ? null : eventSpecificParams.get("sys_r_title"),
                                eventSpecificParams.get("access")       == null ? null : Boolean.parseBoolean(eventSpecificParams.get("access"))
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add SECURITY DEPARTMENT ACCESS RIGHTS EVENTS");
                return answer;
            }
        }


        //SECURITY DEPARTMENT CARDS EVENTS
        if (event_type_id==26 || event_type_id==27){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO sec_dep_cards_events (event_id, card_id) VALUES (?, ?);",
                        new Object[]{
                                eventSpecificParams.get("event_id") == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("card_id")  == null ? null : Integer.parseInt(eventSpecificParams.get("card_id"))
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add SECURITY DEPARTMENT CARDS EVENTS");
                return answer;
            }
        }


        //SECURITY DEPARTMENT ACCOUNT EVENTS
        if (event_type_id==28 || event_type_id==29 || event_type_id==30){
            try{
                getJdbcTemplate().update(
                        "INSERT INTO sec_dep_acc_events (event_id, empl_id, empl_name, cl_acc_id, cl_acc_l, acc_r) VALUES (?,?,?,?,?,?);",
                        new Object[]{
                                eventSpecificParams.get("card_id")  == null ? null : Integer.parseInt(eventSpecificParams.get("event_id")),
                                eventSpecificParams.get("empl_id")  == null ? null : Integer.parseInt(eventSpecificParams.get("empl_id")),
                                eventSpecificParams.get("empl_name")== null ? null : eventSpecificParams.get("empl_name"),
                                eventSpecificParams.get("cl_acc_id")== null ? null : Integer.parseInt(eventSpecificParams.get("cl_acc_id")),
                                eventSpecificParams.get("cl_acc_l") == null ? null : eventSpecificParams.get("cl_acc_l"),
                                eventSpecificParams.get("acc_r")    == null ? null : eventSpecificParams.get("acc_r")
                        }
                );
                answer.put("message", "Success");
                return answer;
            }catch (CannotGetJdbcConnectionException ex){
                answer.put("message", "Error when add SECURITY DEPARTMENT ACCOUNT EVENTS");
                return answer;
            }
        }

        answer.put("message", "Error - get the end of DAO, maybe wrong event_type");
        return answer;
    }
}
