package com.msghub.msghub.mapper;

import com.msghub.msghub.model.Bill;
import com.msghub.msghub.model.Business;
import com.msghub.msghub.model.Msgconfig;
import com.msghub.msghub.model.Template;
import org.apache.ibatis.annotations.Mapper;
import com.msghub.msghub.model.user;

/**
 * 短信 dao接口
 *
 *   MyBatis 自定义插件针对
 MyBatis 四大对象（
 Executor、StatementHandler、ParameterHandler、ResultSetHandler
 ）进行拦截：
 */

@Mapper
public interface CellphoneMsgMapper {


    user getuser();

    Msgconfig queryCompany(Msgconfig msgconfigs);

    Template queryTemplate(String templateId);

    Business queryBusiness(String client_id);

    Bill queryisMoney(String client_id);

    void charge(Bill bill);
}
