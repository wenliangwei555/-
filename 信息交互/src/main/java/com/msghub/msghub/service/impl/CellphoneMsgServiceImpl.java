package com.msghub.msghub.service.impl;

import com.msghub.msghub.mapper.CellphoneMsgMapper;
import com.msghub.msghub.model.*;
import com.msghub.msghub.service.CellphoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CellphoneMsgServiceImpl implements CellphoneMsgService {
    static final int a = 2;

    @Override
    public void aaaa() {

    }

    public static void main(String[] args) {
        System.out.println(a);
    }
    @Override
    public user get1() {
        System.out.println("111111");
        return cellphoneMsgMapper.getuser();
    }

    @Override
    public Template queryTemplate(String templateId) {
        return cellphoneMsgMapper.queryTemplate(templateId);
    }

    @Override
    public Business queryBusiness(String client_id) {
        return cellphoneMsgMapper.queryBusiness(client_id);
    }

    @Override
    public Bill queryisMoney(String client_id) {
        return cellphoneMsgMapper.queryisMoney(client_id);
    }

    @Override
    public void charge(Bill bill) {
        cellphoneMsgMapper.charge(bill);
    }

    // @Autowired
    private CellphoneMsgMapper cellphoneMsgMapper=null;

    public CellphoneMsgServiceImpl() {
    }

    @Autowired
    public CellphoneMsgServiceImpl(CellphoneMsgMapper cellphoneMsgMapper) {
        this.cellphoneMsgMapper = cellphoneMsgMapper;
    }


    @Override
    public Msgconfig queryCompany(Msgconfig msgconfigs) {
        return cellphoneMsgMapper.queryCompany(msgconfigs);
    }
}
