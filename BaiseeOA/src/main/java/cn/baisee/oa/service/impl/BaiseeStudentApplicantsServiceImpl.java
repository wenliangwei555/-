package cn.baisee.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeDictMapper;
import cn.baisee.oa.dao.baisee.BaiseeStudentApplicantsMapper;
import cn.baisee.oa.model.student.BaiseeStudentApplicants;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeStudentApplicantsService;
import cn.baisee.oa.utils.StringUtil;

@Service
public class BaiseeStudentApplicantsServiceImpl implements BaiseeStudentApplicantsService {
    @Autowired
    private BaiseeStudentApplicantsMapper baiseeStudentApplicantsMapper;
    @Autowired
    private BaiseeDictMapper baiseeDictMapper;

    @Override
    public int addStudentApplicants(BaiseeStudentApplicants studentApplicants) {
        return baiseeStudentApplicantsMapper.insertStudentApplicants(studentApplicants);

    }

    @Override
    public BaiseeStudentApplicants getStudentApplicantsByid(String id) {
        return baiseeStudentApplicantsMapper.selectStudentApplicantsByid(id);
    }

    @Override
    public String getIDNumberVerification(String IdNumber) {
        return baiseeStudentApplicantsMapper.selectIDNumberVerification(IdNumber);
    }

    @Override
    public PageInfo<BaiseeStudentApplicants> getAllStudentApplicants(
            int pageNum, int pageSize, String startTime, String endTime,
            String stuName, String areas, String status) {
        if (startTime != null) {
            startTime = startTime.replace("-", "");
        }
        if (endTime != null) {
            endTime = endTime.replace("-", "");
        }

        //开始分页
        PageHelper.startPage(pageNum, pageSize);
        List<BaiseeStudentApplicants> list = baiseeStudentApplicantsMapper.selectAllStudentApplicants(startTime, endTime, stuName, areas, status);
        for (BaiseeStudentApplicants baiseeStudentApplicants : list) {
            if (baiseeStudentApplicants.getArea() != null) {
                baiseeStudentApplicants.setProvince(
                        StringUtil.ifNullToBlank(
                                baiseeDictMapper.selectByProvinceCode(
                                        baiseeStudentApplicants.getProvince())) +
                                StringUtil.ifNullToBlank(
                                        baiseeDictMapper.selectByCityCode(
                                                baiseeStudentApplicants.getCity())) +
                                StringUtil.ifNullToBlank(
                                        baiseeDictMapper.selectByAreaCode(
                                                baiseeStudentApplicants.getArea())));
            }

        }
        PageInfo<BaiseeStudentApplicants> page = new PageInfo<BaiseeStudentApplicants>(list);
        return page;
    }

    @Override
    public int alterApplicants(BaiseeStudentApplicants studentApplicants) {
        return baiseeStudentApplicantsMapper.updateStudentApplicantsByid(studentApplicants);
    }

    @Override
    public int updateApplicantsStuStatus(String id, String status) {
        return baiseeStudentApplicantsMapper.updateStudentApplicantsStatus(id, status);

    }

}
