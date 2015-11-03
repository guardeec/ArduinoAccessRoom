package accessToDataBase.JDBC_Impl.admin.client.reception;

import Methods.POJO.AdminClient.Security.CardDATA;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface ReceptionHelperImpl {
    /*
    чтение свободных карт
     */
    public List<CardDATA> readCards(CardDATA cardDATA);
}
