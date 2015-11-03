package Methods.POJO;

/**
 * Created by root on 27.10.15.
 */
public abstract class GeneralParams implements PojoObject {
    //sourceParams
    private Integer source_type_id;
    private String source_ip;
    private String source_mac;
    private Integer source_device_id;
    private Integer source_cl_acc_id;
    private String source_cl_acc_l;

    //generalParams
    private Integer general_event_type_id;
    private Long general_datetime;
    private Boolean general_res_type;
    private String general_description;

    public void setSourceParams(Integer type_id, String ip, String mac, Integer device_id, Integer cl_acc_id, String cl_acc_l){
        setSource_type_id(type_id);
        setSource_ip(ip);
        setSource_mac(mac);
        setSource_device_id(device_id);
        setSource_cl_acc_id(cl_acc_id);
        setSource_cl_acc_l(cl_acc_l);
    }

    public void setGeneralParams(Integer event_type_id, Long datetime, Boolean res_type, String description){
        setGeneral_event_type_id(event_type_id);
        setGeneral_datetime(datetime);
        setGeneral_res_type(res_type);
        setGeneral_description(description);
    }

    public Integer getSource_type_id() {
        return source_type_id;
    }

    public void setSource_type_id(Integer source_type_id) {
        this.source_type_id = source_type_id;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getSource_mac() {
        return source_mac;
    }

    public void setSource_mac(String source_mac) {
        this.source_mac = source_mac;
    }

    public Integer getSource_device_id() {
        return source_device_id;
    }

    public void setSource_device_id(Integer source_device_id) {
        this.source_device_id = source_device_id;
    }

    public Integer getSource_cl_acc_id() {
        return source_cl_acc_id;
    }

    public void setSource_cl_acc_id(Integer source_cl_acc_id) {
        this.source_cl_acc_id = source_cl_acc_id;
    }

    public String getSource_cl_acc_l() {
        return source_cl_acc_l;
    }

    public void setSource_cl_acc_l(String source_cl_acc_l) {
        this.source_cl_acc_l = source_cl_acc_l;
    }

    public Integer getGeneral_event_type_id() {
        return general_event_type_id;
    }

    public void setGeneral_event_type_id(Integer general_event_type_id) {
        this.general_event_type_id = general_event_type_id;
    }

    public Boolean getGeneral_res_type() {
        return general_res_type;
    }

    public void setGeneral_res_type(Boolean general_res_type) {
        this.general_res_type = general_res_type;
    }

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }

    public Long getGeneral_datetime() {
        return general_datetime;
    }

    public void setGeneral_datetime(Long general_datetime) {
        this.general_datetime = general_datetime;
    }
}
