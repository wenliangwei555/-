package cn.baisee.oa.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp.LsEntry;

import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.dao.baisee.BaiseeDossierFileMapper;
import cn.baisee.oa.model.BaiseeDossierFile;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDossierFileService;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.StringUtil;

/**
 * 报修字典-地理位置 业务逻辑层实现
 *
 * @author liangfeng
 */
@Service
public class BaiseeDossierFileServiceImpl implements BaiseeDossierFileService {

    @Autowired
    private BaiseeDossierFileMapper dossierFileMapper;

    @Override
    public PageInfo<BaiseeDossierFile> getDossierFiles(int pageNum, int pageSize, Map<String, String> map) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<BaiseeDossierFile> list = dossierFileMapper.getDossierFiles(map);
        PageInfo<BaiseeDossierFile> page = new PageInfo<BaiseeDossierFile>(list);
        return page;
    }

    @Override
    public List<BaiseeDossierFile> getDossierFileAll() {
        // TODO Auto-generated method stub
        return dossierFileMapper.getDossierFileAll();
    }

    @Override
    public BaiseeDossierFile getById(String id) {
        // TODO Auto-generated method stub
        return dossierFileMapper.getById(id);
    }

    @Override
    public Integer saveOrUpdateTask(MultipartFile input_file, BaiseeDossierFile dossierFile) {
        String fileName = input_file.getOriginalFilename();
        //生成新的文件名：当前毫秒值+档案类型+所属班级
        fileName = System.currentTimeMillis() + "_" + dossierFile.getTypeId() + fileName.substring(fileName.lastIndexOf("."));
        dossierFile.setFileName(input_file.getOriginalFilename());
        //获取档案上传档案根目录
        PropertiesUtil p = new PropertiesUtil("ftp.properties");
        String dossierPath = p.get("file.server.catalog");
        dossierPath = dossierPath + p.get("dossier.manage.path");
        try {
            String path = dossierPath + dossierFile.getTypeId();
            Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
            SSHClientUtil.upload(path + "/" + fileName, input_file.getBytes(), SSHClientUtil.openChannel(session, UploadFileVideo.CHANNELTYPE));
            dossierFile.setFilePath(path + "/" + fileName);
            int count = countTaskNun();
            dossierFile.setTotalCount(count + 1);
            return dossierFileMapper.saveTask(dossierFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delDossierFiles(String[] ids) {
        List<String> list = new ArrayList<>();
        ChannelExec channelExec = null;
        String result = null;
        for (int i = 0;i<ids.length;i++){
            list.add(dossierFileMapper.selectId(ids[i]));
            String tagrtDir = list.get(i);// 转换后输出的路径
            try {
                Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
                channelExec = SSHClientUtil.openChannelExec(session, UploadFileVideo.CHANNELTYPEEXEC);
                InputStream in = channelExec.getInputStream();
                channelExec.setCommand(" rm -rf " + tagrtDir);
                Thread.sleep(100);
                channelExec.setErrStream(System.err);
                channelExec.connect();
                result = IOUtils.toString(in);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (channelExec != null) {
                    channelExec.disconnect();
                }
            }
        }

        return dossierFileMapper.delDossierFiles(ids);
    }

    @Override
    public List<BaiseeDossierFile> checkFileName(String fName) {
        // TODO Auto-generated method stub
        return dossierFileMapper.checkFileName(fName);
    }

    @Override
    public Vector<LsEntry> findClassAll(String itemlableSearch, String classInfo, String type) {
        // TODO Auto-generated method stub
        Session sshSession = FileConnParamLoadHelper.getSshSession("sftpConfig02");
        ChannelSftp sftp = SSHClientUtil.openChannel(sshSession, "sftp");
        PropertiesUtil ftpConf = new PropertiesUtil("ftp.properties");
        String prefix = ftpConf.get("dossier.manage.prefix");
        String defaultPath = ftpConf.get("dossier.manage.default");
        //如果参数都为空的话，查询默认结果
        if (StringUtil.isEmpty(itemlableSearch) && StringUtil.isEmpty(classInfo) && StringUtil.isEmpty(type)) {
            return SSHClientUtil.listFiles(defaultPath, sftp);
        }
        //如果classInfo不是空，说明查询的是学生档案
        if (StringUtil.isNotEmpty(classInfo)) {
            return SSHClientUtil.listFiles(prefix + type + "/" + classInfo, sftp);
        }
        return SSHClientUtil.listFiles(prefix + type, sftp);
    }

    @Override
    public List<BaiseeDossierFile> getDossierFileAll(Map<String, String> map) {
        // TODO Auto-generated method stub
        return dossierFileMapper.getDossierFileAll();
    }

    @Override
    public String convertFile(String id) {
        // TODO Auto-generated method stub
        BaiseeDossierFile dossierFile = getById(id);
        //获取档案上传档案根目录
        String sourceDir = dossierFile.getFilePath();//获取档案在文件服务器的路径
        PropertiesUtil p = new PropertiesUtil("ftp.properties");
        String tagrtDir = p.get("dossier.out.path");//转换后输出的路径
        String url = p.get("video.reader.url");//nginx的访问路径
        String pdf = p.get("dossier.pdf.path");//转换后保存pdf的存储路径
        Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
        String cmd = "soffice --headless --invisible --convert-to pdf " + sourceDir + " --outdir " + tagrtDir;
        boolean flag = SSHClientUtil.execCmd(cmd, session, true);
        //获取上传到文件服务器的文件名称
        String name = dossierFile.getFilePath().substring(dossierFile.getFilePath().lastIndexOf("/") + 1);
        //转换文件的后缀名
        name = name.substring(0, name.lastIndexOf(".")) + ".pdf";
        //拼接pdf路径
        return url + pdf + name;
    }

    @Override
    public int countTaskNun() {
        // TODO Auto-generated method stub
        return dossierFileMapper.countTaskNun();
    }

    @Override
    public int getName(String name) {
        return dossierFileMapper.getName(name);
    }


}
