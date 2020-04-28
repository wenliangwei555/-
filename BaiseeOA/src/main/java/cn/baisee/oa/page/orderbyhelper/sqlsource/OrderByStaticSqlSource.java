package cn.baisee.oa.page.orderbyhelper.sqlsource;

import java.util.List;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import cn.baisee.oa.page.orderbyhelper.OrderByHelper;
import cn.baisee.oa.page.orderbyhelper.OrderByParser;

/**
 * 描述信息
 *
 * @author liuzh
 * @since 2015-06-26
 */
public class OrderByStaticSqlSource implements SqlSource, OrderBySqlSource {
    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;
    private SqlSource original;

    @SuppressWarnings("unchecked")
		public OrderByStaticSqlSource(StaticSqlSource sqlSource) {
        MetaObject metaObject = SystemMetaObject.forObject(sqlSource);
        this.sql = (String) metaObject.getValue("sql");
        this.parameterMappings = (List<ParameterMapping>) metaObject.getValue("parameterMappings");
        this.configuration = (Configuration) metaObject.getValue("configuration");
        this.original = sqlSource;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        String orderBy = OrderByHelper.getOrderBy();
        String tempSql = sql;
        if (orderBy != null) {
            tempSql = OrderByParser.converToOrderBySql(sql, orderBy);
        }
        return new BoundSql(configuration, tempSql, parameterMappings, parameterObject);
    }

    public SqlSource getOriginal() {
        return original;
    }

}
