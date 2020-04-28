package com.msghub.msghub.service;

import com.msghub.msghub.model.*;

public interface CellphoneMsgService  extends  aaaa{

    user get1();

    Msgconfig queryCompany(Msgconfig msgconfigs);

    Template queryTemplate(String templateId);

    Business queryBusiness(String client_id);

    Bill queryisMoney(String client_id);

    void charge(Bill bill);

}
