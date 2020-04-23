package cn.baisee.oa.model.tuition;
/**
 * 学费分期规则表（分期中每期缴纳学费详细）
 * @author LiFQ
 *
 */
public class TuitionStagesRule {
	/*
	 * 学费分期规则表
	 */
	private String tuStRuleId;//TU_ST_RULE_ID
	/*
	 * 学费分期主键
	 */
	private String tuStId;//TU_ST_ID
	/*
	 * 学费每期首次最低缴纳费用
	 */
	private String tuStRuleMinimumTuition;//TU_ST_RULE_MINIMUM_TUITION
	/*
	 * 每期费用
	 */
	private String tuStRuleHighestTuition;//TU_ST_RULE_HIGHEST_TUITION
	/*
	 * 时间限制（例如：第一期8个月）
	 */
	private String tuStRuleTimeLimit;//TU_ST_RULE_TIME_LIMIT
	/*
	 * 学费分期周期
	 */
	private String tuStCycle;//TU_ST_CYCLE
	/*
	 *学费分期中，第一期、第二期。。。 
	 */
	private String tuStRulePeriod;//TU_ST_RULE_PERIOD
	/*
	 * 学费主键id
	 */
	private String tuId;
	
	public String getTuId() {
		return tuId;
	}
	public void setTuId(String tuId) {
		this.tuId = tuId;
	}
	public String getTuStCycle() {
		return tuStCycle;
	}
	public void setTuStCycle(String tuStCycle) {
		this.tuStCycle = tuStCycle == null ? null: tuStCycle.trim();
	}
	/*
	 * 最后更新时间
	 */
	private String tuStRuleUpdateTime;//UPDATE_TIME
	public String getTuStRuleId() {
		return tuStRuleId;
	}
	public void setTuStRuleId(String tuStRuleId) {
		this.tuStRuleId = tuStRuleId == null ? null: tuStRuleId.trim();
	}
	public String getTuStId() {
		return tuStId;
	}
	public void setTuStId(String tuStId) {
		this.tuStId = tuStId == null ? null: tuStId.trim();
	}
	public String getTuStRuleMinimumTuition() {
		return tuStRuleMinimumTuition;
	}
	public void setTuStRuleMinimumTuition(String tuStRuleMinimumTuition) {
		this.tuStRuleMinimumTuition = tuStRuleMinimumTuition == null ? null: tuStRuleMinimumTuition.trim();
	}
	public String getTuStRuleHighestTuition() {
		return tuStRuleHighestTuition;
	}
	public void setTuStRuleHighestTuition(String tuStRuleHighestTuition) {
		this.tuStRuleHighestTuition = tuStRuleHighestTuition == null ? null: tuStRuleHighestTuition.trim();
	}
	public String getTuStRuleTimeLimit() {
		return tuStRuleTimeLimit;
	}
	public void setTuStRuleTimeLimit(String tuStRuleTimeLimit) {
		this.tuStRuleTimeLimit = tuStRuleTimeLimit == null ? null: tuStRuleTimeLimit.trim();
	}
	public String getTuStRuleUpdateTime() {
		return tuStRuleUpdateTime;
	}
	public void setTuStRuleUpdateTime(String tuStRuleUpdateTime) {
		this.tuStRuleUpdateTime = tuStRuleUpdateTime == null ? null: tuStRuleUpdateTime.trim();
	}
	public String getTuStRulePeriod() {
		return tuStRulePeriod;
	}
	public void setTuStRulePeriod(String tuStRulePeriod) {
		this.tuStRulePeriod = tuStRulePeriod == null ? null: tuStRulePeriod.trim();
	}
	@Override
	public String toString() {
		return "TuitionStagesRule [tuStRuleId=" + tuStRuleId + ", tuStId=" + tuStId + ", tuStRuleMinimumTuition="
				+ tuStRuleMinimumTuition + ", tuStRuleHighestTuition=" + tuStRuleHighestTuition + ", tuStRuleTimeLimit="
				+ tuStRuleTimeLimit + ", tuStCycle=" + tuStCycle + ", tuId=" + tuId + ", tuStRuleUpdateTime="
				+ tuStRuleUpdateTime + "]";
	}

	

}
