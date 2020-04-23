package cn.baisee.oa.service.impl;

import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.dao.baisee.BaiseeDossierCategoryMapper;
import cn.baisee.oa.model.BaiseeDossierCategory;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDossierCategoryService;
import cn.baisee.oa.utils.FileUtil;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BaiseeDossierCategoryImpl implements BaiseeDossierCategoryService {
    @Autowired
    private BaiseeDossierCategoryMapper dossierCategoryMapper;

    @Override
    public PageInfo<BaiseeDossierCategory> getDossierCategory(Integer pageNum, Integer pageSize, Map<String, String> map) {
        PageHelper.startPage(pageNum, pageSize);
        List<BaiseeDossierCategory> list = dossierCategoryMapper.getDossierCategor(map);
        PageInfo<BaiseeDossierCategory> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Integer delDossierCategory(String[] ids) {
        // TODO Auto-generated method stub
        return dossierCategoryMapper.delDossierCategory(ids);
    }

    @Override
    public BaiseeDossierCategory getById(String id) {
        return dossierCategoryMapper.getById(id);
    }

    @Override
    public Integer saveOrUpdateAssigmentManage(BaiseeDossierCategory baiseeDossierCategory) {
        //判断如果id为空就是新增操作，反之则是更新操作
        if (StringUtil.isNotEmpty(baiseeDossierCategory.getId())) {
            return dossierCategoryMapper.updateBaiseeCategory(baiseeDossierCategory);
        }
        //获取档案管理绝对路径
        PropertiesUtil ftpConf = new PropertiesUtil("ftp.properties");
		String dossierPath = ftpConf.get("dossier.manage.path");
        Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
        ChannelSftp sftp = SSHClientUtil.openChannel(session, UploadFileVideo.CHANNELTYPE);
        try {
    	   SSHClientUtil.makeDirs(dossierPath+baiseeDossierCategory.getcName(), sftp);
        } catch (Exception e) {
			e.printStackTrace();
		}
        return dossierCategoryMapper.saveOrUpdateAssigmentManage(baiseeDossierCategory);
    }


    @Override
    public Map<String, String> checkAssignmentPerson(String cName) {
        Map<String, String> map = new HashMap<>();
        List<BaiseeDossierCategory> repairPlaces = dossierCategoryMapper.checkPname(cName);
        //        判读是否有数据
        if (repairPlaces != null && repairPlaces.size() > 0) {
            map.put("codes", "111");
        } else {
            map.put("codes", "000");
        }
        return map;
    }

	@Override
	public List<BaiseeDossierCategory> getCategoryAll() {
		// TODO Auto-generated method stub
		return dossierCategoryMapper.getCategoryAll();
	}
}
