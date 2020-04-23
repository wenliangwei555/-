package cn.baisee.oa.controller;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.baisee.oa.model.BaiseeDossierCategory;
import cn.baisee.oa.model.BaiseeDossierFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.sun.org.apache.xpath.internal.operations.Mod;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.thoughtworks.xstream.core.util.Base64Encoder;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.FileUploadUtil;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeDossierCategoryService;
import cn.baisee.oa.service.BaiseeDossierFileService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.PropertiesUtil;
import cn.baisee.oa.utils.poi.WordUtils;

/**
 * 档案文件管理Controller
 *
 * @author liangfeng
 */
@Controller
@RequestMapping("/dossierFile")
public class BaiseeDossierFileController {

    @Autowired
    private BaiseeDossierFileService dossierFileService;
    @Autowired
    private BaiseeDossierCategoryService dossierCategoryService;

    /**
     * 查询档案文件列表
     *
     * @param request
     * @param itemlableSearch 名称搜索条件
     * @param classInfo       班级名称搜索条件
     * @param type            档案类型搜索条件
     * @return
     */
    @RequestMapping("/dossierList")
    @Role(value = "DAWJ_LIST")
    public ModelAndView toDossierFileList(HttpServletRequest request, String itemlableSearch, String type) {
        //查询所有档案类型
        List<BaiseeDossierCategory> categorys = dossierCategoryService.getCategoryAll();
        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);
        Map<String, String> map = new HashMap<>();
        map.put("itemlableSearch", itemlableSearch);
        map.put("type", type);
        PageInfo<BaiseeDossierFile> dossierFiles = dossierFileService.getDossierFiles(pageNum, pageSize, map);
        ModelAndView mv = new ModelAndView("dossier/dossierFile_list");
        mv.addObject("pageResult", dossierFiles);
        mv.addObject("categorys", categorys);
        mv.addObject("itemlableSearch", itemlableSearch);
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 去档案文件管理添加页面
     *
     * @param request
     * @param name    例如：学生档案/班级信息....
     * @return
     */
    @RequestMapping("/toAddDossierFile")
    @Role(value = "DAWJ_XZWJ")
    public ModelAndView toAddPlace(HttpServletRequest request, String name) {
        ModelAndView mav = new ModelAndView("dossier/dossierFile_add");
        //查询所有档案类型
        List<BaiseeDossierCategory> categorys = dossierCategoryService.getCategoryAll();
        mav.addObject("categorys", categorys);
        return mav;
    }

    /**
     * 执行报修字典-校区表添加
     *
     * @param request
     * @param dossierFile 对象
     * @return
     * @throws Exception
     */
    @RequestMapping("/addDossierFile")
    @Role(value = "DAWJ_XZWJ")
    public ModelAndView addPlace(HttpServletRequest request, MultipartFile input_file, BaiseeDossierFile dossierFile) throws Exception {
        //获取当前用户名称
        dossierFile.setcTime(new Date());
        //执行添加
        dossierFileService.saveOrUpdateTask(input_file, dossierFile);
        return toDossierFileList(request, null, null);
    }

    /**
     * 根据id查询档案文件信息
     *
     * @param id 任务id
     * @return
     */
    @RequestMapping("/selectDossierFileId")
    @Role(value = "DAWJ_CXWJ")
    public ModelAndView selectByPlaceId(String id) {
        BaiseeDossierFile dossierFile = dossierFileService.getById(id);
        ModelAndView mav = new ModelAndView("dossier/dossierFile_update");
        mav.addObject("dossierFile", dossierFile);
        return mav;
    }

    /**
     * 执行任务修改
     *
     * @param request
     * @param dossierFile 对象
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateFile")
    @Role(value = "DAWJ_XGWJ")
    public ModelAndView updatePlace(HttpServletRequest request, BaiseeDossierFile dossierFile) throws Exception {
//		dossierFileService.saveOrUpdateTask(dossierFile);
        return toDossierFileList(request, null, null);
    }


    /**
     * 根据任务id，批量删除任务
     *
     * @param request
     * @param ids     数组中装的是页面列表选中的任务id
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteFiles")
    @Role(value = "DAWJ_SCWJ")
    public ModelAndView delPlace(HttpServletRequest request, String[] ids) throws Exception {
        dossierFileService.delDossierFiles(ids);
        return toDossierFileList(request, null, null);
    }

    /**
     * 检查档案文件管理表中是否已存在此文件名称
     *
     * @param fName 文件名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkFileName")
    @Role(value = "DAWJ_CXWJ")
    public Object checkPname(String fName) {
        //查看分配表中是否有此分配人
        List<BaiseeDossierFile> dossierFiles = dossierFileService.checkFileName(fName);
        Map<String, String> map = new HashMap<>();
        //判断repairPlaces中是否有数据,如果为000说明没有此地理位置名称，反之111说明有此地理位置名称
        if (dossierFiles != null && dossierFiles.size() > 0) {
            map.put("codes", "111");
        } else {
            map.put("codes", "000");
        }
        return map;
    }


    /**
     * 预览档案文件
     *
     * @param request
     * @param id      任务id
     * @return
     */
    @RequestMapping("/view")
    @Role(value = "DAWJ_LIST")
    public ModelAndView view(HttpServletRequest request, String id) {
        String pdfPath = dossierFileService.convertFile(id);
        ModelAndView mv = new ModelAndView("dossier/preview_word");
        mv.addObject("pdfPath", pdfPath);
        return mv;
    }


    @ResponseBody
    @RequestMapping("/download/dossier")
    @Role(value = "DAWJ_LIST")
    public void downloadFile(HttpServletRequest request, String id, HttpServletResponse response) {
    	BaiseeDossierFile dossierFile = dossierFileService.getById(id);
        ChannelSftp sftp = null;
        Session session = null;
        try {
        	session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
        	sftp = SSHClientUtil.openChannel(session, UploadFileVideo.CHANNELTYPE);
             InputStream inputStream = sftp.get(dossierFile.getFilePath());
             byte[] buffer = IOUtils.toByteArray(inputStream);
             FileUploadUtil.writeFile(buffer, response, dossierFile.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	sftp.quit();
        	session.disconnect();
		}
    }

    /*
     * 查询档案名字是否重复
     *
     * */
    @RequestMapping(value = "fName")
    @ResponseBody
    @Role(value = "DAWJ_XZWJ")
    public Map<String,String> fName(String name) {
        int flag = dossierFileService.getName(name);
        Map<String,String>map=new HashMap<>();
        if (flag>0){
            map.put("name",name);
            return map;
        }else {
            return null;
        }
    }

    public static String base64EncodeFileName(String fileName) {
        Base64Encoder base64Encoder = new Base64Encoder();
        try {
            return "=?UTF-8?B?"
                    + new String(base64Encoder.encode(fileName
                    .getBytes("UTF-8"))) + "?=";
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
