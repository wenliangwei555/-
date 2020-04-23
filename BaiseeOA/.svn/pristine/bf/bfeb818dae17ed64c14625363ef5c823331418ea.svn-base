package cn.baisee.oa.page.orderbyhelper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

/**
 * 处理 Order by
 *
 * @author liuzh
 * @since 2015-06-27
 */
public class OrderByParser {
	private static Logger logger = LoggerFactory.getLogger(OrderByParser.class);
	/**
     * convert to order by sql
     *
     * @param sql
     * @param orderBy
     * @return
     */
    public static String converToOrderBySql(String sql, String orderBy) {
        //解析SQL
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
            Select select = (Select) stmt;
            SelectBody selectBody = select.getSelectBody();
            //处理body-去最外层order by
            List<OrderByElement> orderByElements = extraOrderBy(selectBody);
            String defaultOrderBy = PlainSelect.orderByToString(orderByElements);
            if (defaultOrderBy.indexOf('?') != -1) {
                throw new RuntimeException("原SQL[" + sql + "]中的order by包含参数，因此不能使用OrderBy插件进行修改!");
            }
            //新的sql
            sql = select.toString();
        } catch (Throwable e) {
        	logger.error("转化orderbysql失败");
        }
        return sql + " order by " + orderBy;
    }

    /**
     * extra order by and set default orderby to null
     *
     * @param selectBody
     */
    public static List<OrderByElement> extraOrderBy(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            List<OrderByElement> orderByElements = ((PlainSelect) selectBody).getOrderByElements();
            ((PlainSelect) selectBody).setOrderByElements(null);
            return orderByElements;
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                return extraOrderBy(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getPlainSelects() != null && operationList.getPlainSelects().size() > 0) {
                List<PlainSelect> plainSelects = operationList.getPlainSelects();
                List<OrderByElement> orderByElements = plainSelects.get(plainSelects.size() - 1).getOrderByElements();
                plainSelects.get(plainSelects.size() - 1).setOrderByElements(null);
                return orderByElements;
            }
        }
        return null;
    }
}
