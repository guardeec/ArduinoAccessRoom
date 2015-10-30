package JDBC;

import JDBC_Impl.GeneralImpl;
import Methods.POJO.PojoObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by root on 28.10.15.
 */
public class General extends JdbcDaoSupport implements GeneralImpl {
    @Override
    public String log(PojoObject object) {

        Integer source_id;

        /*
        Ищем id источника по sourceParams
        Если не находим - добавляем новый и берём id нового источника
         */
        try{
            source_id = getJdbcTemplate().queryForInt(
                   "SELECT id AS source_id FROM event_sources WHERE type_id = coalesce(?, type_id) AND ip = coalesce(?, ip) AND mac = coalesce(?, mac) AND device_id = coalesce(?, device_id) AND cl_acc_id = coalesce(?, cl_acc_id) AND cl_acc_l = coalesce(?, cl_acc_l);",
                   new Object[]{
                            object.getSource_type_id(),
                            object.getSource_ip(),
                            object.getSource_mac(),
                            object.getSource_device_id(),
                            object.getSource_cl_acc_id(),
                            object.getSource_cl_acc_l()
                   }
           );
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }catch (EmptyResultDataAccessException ex){
            try {
                source_id = getJdbcTemplate().queryForInt(
                        "INSERT INTO event_sources (type_id, ip, mac, device_id, cl_acc_id, cl_acc_l) VALUES (?, ?, ?, ?, ?, ?) RETURNING id AS source_id;",
                        new Object[]{
                                object.getSource_type_id(),
                                object.getSource_ip(),
                                object.getSource_mac(),
                                object.getSource_device_id(),
                                object.getSource_cl_acc_id(),
                                object.getSource_cl_acc_l()
                        }
                );
            }catch (CannotGetJdbcConnectionException ex1){
                return "Error";
            }

        }

        /*
        Заносим общие параметры события
         */
        Integer event_id;
        try{
            event_id = getJdbcTemplate().queryForInt(
                    "INSERT INTO events (type_id, source_id, date_time, res_type, descr) VALUES (?, ?, ?, ?, ?) RETURNING id AS event_id;",
                    new Object[]{
                            object.getGeneral_event_type_id(),
                            source_id,
                            new Timestamp(object.getGeneral_datetime()),
                            object.getGeneral_res_type(),
                            object.getGeneral_description()
                    }
            );
        }catch (CannotGetJdbcConnectionException ex){
            return "Error";
        }

        return event_id.toString();
    }
}
