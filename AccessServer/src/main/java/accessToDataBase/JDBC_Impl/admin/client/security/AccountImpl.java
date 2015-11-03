package accessToDataBase.JDBC_Impl.admin.client.security;

import Methods.POJO.AdminClient.Security.AccountDATA;
import Methods.POJO.AdminClient.Security.AccountDATA_withPassword;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface AccountImpl {
    public AccountDATA_withPassword create (AccountDATA_withPassword accountDATA);

    public List<AccountDATA> read (AccountDATA accountDATA);

    public AccountDATA update (AccountDATA accountDATA);

    public AccountDATA delete (AccountDATA accountDATA);
}
