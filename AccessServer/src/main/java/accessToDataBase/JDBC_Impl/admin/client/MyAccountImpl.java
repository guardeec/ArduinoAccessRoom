package accessToDataBase.JDBC_Impl.admin.client;

import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;

/**
 * Created by root on 02.11.15.
 */
public interface MyAccountImpl {
    /*
    личный кабинет пользователя
     */
    public AccountDATA_withPassword read(AccountDATA_withPassword accountDATA);

    public AccountDATA_withPassword update(AccountDATA_withPassword accountDATA);
}
