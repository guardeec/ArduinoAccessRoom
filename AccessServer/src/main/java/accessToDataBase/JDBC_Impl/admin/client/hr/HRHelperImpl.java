package accessToDataBase.JDBC_Impl.admin.client.hr;

import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.RoleDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface HRHelperImpl {
    /*
    получит список доступных ролей
     */
    public List<RoleDATA> readRoles(RoleDATA roleDATA);

    /*
    получит список доступных карт
     */
    public List<CardDATA> readCards(CardDATA cardDATA);
}
