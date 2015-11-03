package accessToDataBase.JDBC_Impl.admin.client.reception;

import Methods.POJO.AdminClient.Reception.GuestDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface GuestImpl {
    /*
    CRUD гостей
     */

    public GuestDATA create(GuestDATA guestDATA);

    public List<GuestDATA> read(GuestDATA guestDATA);

    public List<GuestDATA> readHistory(GuestDATA guestDATA);

    public GuestDATA update(GuestDATA guestDATA);

    public GuestDATA delete(GuestDATA guestDATA);
}
