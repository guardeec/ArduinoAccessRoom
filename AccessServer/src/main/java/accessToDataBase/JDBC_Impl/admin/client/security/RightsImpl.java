package accessToDataBase.JDBC_Impl.admin.client.security;

import Methods.POJO.AdminClient.Security.RightDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface RightsImpl {
    public List<RightDATA> readAllRights(RightDATA rightDATA);

    public RightDATA read(RightDATA rightDATA);

    public RightDATA update(RightDATA rightDATA);
}
