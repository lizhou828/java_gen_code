<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ${className}Example{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    private Boolean forUpdate;

    public SysUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    public void setForUpdate(Boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Boolean getForUpdate() {
        return forUpdate;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }
<#list table.columns as c>
        public Criteria and${c.columnNameUpper}IsNull() {
            addCriterion("${c.sqlName} is null");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}IsNotNull() {
            addCriterion("${c.sqlName} is not null");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}EqualTo(${c.javaType} value) {
            addCriterion("${c.sqlName} =", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}NotEqualTo(${c.javaType} value) {
            addCriterion("${c.sqlName} <>", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}GreaterThan(${c.javaType} value) {
            addCriterion("${c.sqlName} >", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}GreaterThanOrEqualTo(${c.javaType} value) {
            addCriterion("${c.sqlName} >=", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}LessThan(${c.javaType} value) {
            addCriterion("${c.sqlName} <", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}LessThanOrEqualTo(${c.javaType} value) {
            addCriterion("${c.sqlName} <=", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

    <#if c.javaType == "java.lang.String">
        public Criteria and${c.columnNameUpper}Like(String value) {
            addCriterion("${c.sqlName} like", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}NotLike(String value) {
            addCriterion("${c.sqlName} not like", value, "${c.columnNameUpper}");
            return (Criteria) this;
        }
    </#if>

        public Criteria and${c.columnNameUpper}In(List<${c.javaType}> values) {
            addCriterion("${c.sqlName} in", values, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}NotIn(List<${c.javaType}> values) {
            addCriterion("${c.sqlName} not in", values, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}Between(${c.javaType} value1, ${c.javaType} value2) {
            addCriterion("${c.sqlName} between", value1, value2, "${c.columnNameUpper}");
            return (Criteria) this;
        }

        public Criteria and${c.columnNameUpper}NotBetween(${c.javaType} value1, ${c.javaType} value2) {
            addCriterion("${c.sqlName} not between", value1, value2, "${c.columnNameUpper}");
            return (Criteria) this;
        }
</#list>
    }


    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}