package cn.baisee.oa.core.datasource;

/**
 * 数据源切换类型
 */
public enum DatasourceTypes {
    /**
     * oa
     */
    OA("baiseeDatasource"),
    PAS("pasDatasource"),
    BAISEEW("baiseewDatasource"),
    STU("stuDatasource"),
    CARD("baiseeCardDatasource");

    private String sourceName;

    DatasourceTypes(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return this.sourceName;
    }

}
