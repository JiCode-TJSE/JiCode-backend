package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

import java.util.ArrayList;
import java.util.List;

public class ScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourIsNull() {
            addCriterion("estimated_workhour is null");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourIsNotNull() {
            addCriterion("estimated_workhour is not null");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourEqualTo(Integer value) {
            addCriterion("estimated_workhour =", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourNotEqualTo(Integer value) {
            addCriterion("estimated_workhour <>", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourGreaterThan(Integer value) {
            addCriterion("estimated_workhour >", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourGreaterThanOrEqualTo(Integer value) {
            addCriterion("estimated_workhour >=", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourLessThan(Integer value) {
            addCriterion("estimated_workhour <", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourLessThanOrEqualTo(Integer value) {
            addCriterion("estimated_workhour <=", value, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourIn(List<Integer> values) {
            addCriterion("estimated_workhour in", values, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourNotIn(List<Integer> values) {
            addCriterion("estimated_workhour not in", values, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourBetween(Integer value1, Integer value2) {
            addCriterion("estimated_workhour between", value1, value2, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andEstimatedWorkhourNotBetween(Integer value1, Integer value2) {
            addCriterion("estimated_workhour not between", value1, value2, "estimatedWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourIsNull() {
            addCriterion("actual_workhour is null");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourIsNotNull() {
            addCriterion("actual_workhour is not null");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourEqualTo(Integer value) {
            addCriterion("actual_workhour =", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourNotEqualTo(Integer value) {
            addCriterion("actual_workhour <>", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourGreaterThan(Integer value) {
            addCriterion("actual_workhour >", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_workhour >=", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourLessThan(Integer value) {
            addCriterion("actual_workhour <", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourLessThanOrEqualTo(Integer value) {
            addCriterion("actual_workhour <=", value, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourIn(List<Integer> values) {
            addCriterion("actual_workhour in", values, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourNotIn(List<Integer> values) {
            addCriterion("actual_workhour not in", values, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourBetween(Integer value1, Integer value2) {
            addCriterion("actual_workhour between", value1, value2, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andActualWorkhourNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_workhour not between", value1, value2, "actualWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourIsNull() {
            addCriterion("remain_workhour is null");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourIsNotNull() {
            addCriterion("remain_workhour is not null");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourEqualTo(Integer value) {
            addCriterion("remain_workhour =", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourNotEqualTo(Integer value) {
            addCriterion("remain_workhour <>", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourGreaterThan(Integer value) {
            addCriterion("remain_workhour >", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain_workhour >=", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourLessThan(Integer value) {
            addCriterion("remain_workhour <", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourLessThanOrEqualTo(Integer value) {
            addCriterion("remain_workhour <=", value, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourIn(List<Integer> values) {
            addCriterion("remain_workhour in", values, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourNotIn(List<Integer> values) {
            addCriterion("remain_workhour not in", values, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourBetween(Integer value1, Integer value2) {
            addCriterion("remain_workhour between", value1, value2, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andRemainWorkhourNotBetween(Integer value1, Integer value2) {
            addCriterion("remain_workhour not between", value1, value2, "remainWorkhour");
            return (Criteria) this;
        }

        public Criteria andProgressIsNull() {
            addCriterion("progress is null");
            return (Criteria) this;
        }

        public Criteria andProgressIsNotNull() {
            addCriterion("progress is not null");
            return (Criteria) this;
        }

        public Criteria andProgressEqualTo(Float value) {
            addCriterion("progress =", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotEqualTo(Float value) {
            addCriterion("progress <>", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressGreaterThan(Float value) {
            addCriterion("progress >", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressGreaterThanOrEqualTo(Float value) {
            addCriterion("progress >=", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressLessThan(Float value) {
            addCriterion("progress <", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressLessThanOrEqualTo(Float value) {
            addCriterion("progress <=", value, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressIn(List<Float> values) {
            addCriterion("progress in", values, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotIn(List<Float> values) {
            addCriterion("progress not in", values, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressBetween(Float value1, Float value2) {
            addCriterion("progress between", value1, value2, "progress");
            return (Criteria) this;
        }

        public Criteria andProgressNotBetween(Float value1, Float value2) {
            addCriterion("progress not between", value1, value2, "progress");
            return (Criteria) this;
        }
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