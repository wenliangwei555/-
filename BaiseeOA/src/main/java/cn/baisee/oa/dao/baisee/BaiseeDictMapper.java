package cn.baisee.oa.dao.baisee;

import java.util.HashMap;
import java.util.List;
import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.Area;
import cn.baisee.oa.model.City;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.Province;




@Datasource(DatasourceTypes.OA)
public interface BaiseeDictMapper {
	List<IcipBusDict> getAllDict(HashMap<String, Object> map);
	
	Integer delDict(String[] dictIds);
	
	IcipBusDict getDictById(String dictId);
	
	Integer updateDict(IcipBusDict dict);
	
	IcipBusDict checkDict(IcipBusDict dict);
	/**
	 * 查询所有的省份信息
	 * @return
	 */
	List<Province> selectProvince();
	/**
	 * 查询市
	 * @param provinceCode
	 * @return
	 */
	List<City> selectCity(String provinceCode);
	/**
	 * 查询区县信息
	 * @param cityCode
	 * @return
	 */
	List<Area> selectArea(String cityCode);
	/**
	 * 根据provinceCode查看省份
	 * @param provinceCode
	 * @return
	 */
	String selectByProvinceCode(String provinceCode);
	/**
	 *根据cityCode查看城市
	 * @param cityCode
	 * @return
	 */
	String selectByCityCode(String cityCode);
	/**
	 * 根据areaCode查看区县
	 * @param areaCode
	 * @return
	 */
	String selectByAreaCode(String areaCode);
	/**从字典表中查询出所有保险公司的ID和姓名*/
	List<IcipBusDict>selectDictInsuranceCmpy();
	
}
