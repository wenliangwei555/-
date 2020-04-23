package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeDossierFile;

/**
 * 档案管理表mapper接口
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeDossierFileMapper {

    /**
     * 查询档案文件管理表
     *
     * @param map 查询条件
     * @return
     */
    public List<BaiseeDossierFile> getDossierFiles(Map<String, String> map);

    /**
     * 查询档案管理表
     *
     * @return
     */
    public List<BaiseeDossierFile> getDossierFileAll();

    /**
     * 查询默认的档案管理表
     *
     * @return
     */
    public List<BaiseeDossierFile> findDefaultDossierFiles();

    /**
     * 查询档案管理表
     * map 查询条件
     *
     * @return
     */
    public List<BaiseeDossierFile> getDossierFileAll(Map<String, String> map);

    /**
     * 根据id查询档案管理表表详情
     *
     * @param id 任务id
     * @return
     */
    public BaiseeDossierFile getById(String id);

    /**
     * 新增任务
     *
     * @param dossierFile 任务对象
     * @return
     */
    public Integer saveTask(BaiseeDossierFile dossierFile);

    /**
     * 修改任务
     *
     * @param dossierFile 任务对象
     * @return
     */
    public Integer updateTask(BaiseeDossierFile dossierFile);


    /**
     * 批量删除任务
     *
     * @param ids 数组中装的是任务id
     * @return
     */
    public Integer delDossierFiles(String[] ids);

    /**
     * 查询该路径下是否已有此档案文件名称
     *
     * @param fName 校区名称
     * @return 档案文件管理集合
     */
    public List<BaiseeDossierFile> checkFileName(String fName);

    /**
     * 查询上传文档的数量
     *
     * @return
     */
    public int countTaskNun();

    public int getName(String name);


    public String selectId(String id);
}
