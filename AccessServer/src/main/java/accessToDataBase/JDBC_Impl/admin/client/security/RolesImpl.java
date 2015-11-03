package accessToDataBase.JDBC_Impl.admin.client.security;

import Methods.POJO.AdminClient.Security.RoleDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface RolesImpl {
    public RoleDATA create(RoleDATA roleDATA);

    public List<RoleDATA> read(RoleDATA roleDATA);

    public RoleDATA update (RoleDATA roleDATA);

    public RoleDATA delete(RoleDATA roleDATA);
}
