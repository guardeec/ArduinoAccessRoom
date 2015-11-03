package accessToDataBase.JDBC_Impl.admin.client.security;

import Methods.POJO.AdminClient.Security.CardDATA;
import Methods.POJO.AdminClient.Security.CardDATA_withCardNumber;

import java.util.List;

/**
 * Created by root on 02.11.15.
 */
public interface CardsImpl {
    /*
    CRD карт
     */

    public CardDATA_withCardNumber create(CardDATA_withCardNumber cardDATA);

    public List<CardDATA> read(CardDATA cardDATA);

    public CardDATA delete(CardDATA cardDATA);
}
