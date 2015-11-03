package Methods.POJO;

/**
 * Created by root on 28.10.15.
 */
public interface PojoObject {

    //это маркер для сериализации в json в методе класса PostRequest
    public void setSourceParams(Integer type_id, String ip, String mac, Integer device_id, Integer cl_acc_id, String cl_acc_l);

    public void setGeneralParams(Integer event_type_id, Long datetime, Boolean res_type, String description);

    public Integer getSource_type_id();

    public void setSource_type_id(Integer source_type_id);

    public String getSource_ip();

    public void setSource_ip(String source_ip);

    public String getSource_mac();

    public void setSource_mac(String source_mac);

    public Integer getSource_device_id();

    public void setSource_device_id(Integer source_device_id);

    public Integer getSource_cl_acc_id();

    public void setSource_cl_acc_id(Integer source_cl_acc_id);

    public String getSource_cl_acc_l();

    public void setSource_cl_acc_l(String source_cl_acc_l);

    public Integer getGeneral_event_type_id();

    public void setGeneral_event_type_id(Integer general_event_type_id);

    public Long getGeneral_datetime();

    public void setGeneral_datetime(Long general_datetime);

    public Boolean getGeneral_res_type();

    public void setGeneral_res_type(Boolean general_res_type);

    public String getGeneral_description();

    public void setGeneral_description(String general_description);
}
