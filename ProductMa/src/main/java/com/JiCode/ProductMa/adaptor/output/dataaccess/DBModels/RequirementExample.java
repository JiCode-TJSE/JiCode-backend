package com.JiCode.ProductMa.adaptor.output.dataaccess.DBModels;

import java.util.ArrayList;
import java.util.List;

public class RequirementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RequirementExample() {
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

        public Criteria andRequirementIdIsNull() {
            addCriterion("requirement_id is null");
            return (Criteria) this;
        }

        public Criteria andRequirementIdIsNotNull() {
            addCriterion("requirement_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementIdEqualTo(String value) {
            addCriterion("requirement_id =", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdNotEqualTo(String value) {
            addCriterion("requirement_id <>", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdGreaterThan(String value) {
            addCriterion("requirement_id >", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdGreaterThanOrEqualTo(String value) {
            addCriterion("requirement_id >=", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdLessThan(String value) {
            addCriterion("requirement_id <", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdLessThanOrEqualTo(String value) {
            addCriterion("requirement_id <=", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdLike(String value) {
            addCriterion("requirement_id like", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdNotLike(String value) {
            addCriterion("requirement_id not like", value, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdIn(List<String> values) {
            addCriterion("requirement_id in", values, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdNotIn(List<String> values) {
            addCriterion("requirement_id not in", values, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdBetween(String value1, String value2) {
            addCriterion("requirement_id between", value1, value2, "requirementId");
            return (Criteria) this;
        }

        public Criteria andRequirementIdNotBetween(String value1, String value2) {
            addCriterion("requirement_id not between", value1, value2, "requirementId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdIsNull() {
            addCriterion("belong_product_id is null");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdIsNotNull() {
            addCriterion("belong_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdEqualTo(String value) {
            addCriterion("belong_product_id =", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdNotEqualTo(String value) {
            addCriterion("belong_product_id <>", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdGreaterThan(String value) {
            addCriterion("belong_product_id >", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("belong_product_id >=", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdLessThan(String value) {
            addCriterion("belong_product_id <", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdLessThanOrEqualTo(String value) {
            addCriterion("belong_product_id <=", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdLike(String value) {
            addCriterion("belong_product_id like", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdNotLike(String value) {
            addCriterion("belong_product_id not like", value, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdIn(List<String> values) {
            addCriterion("belong_product_id in", values, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdNotIn(List<String> values) {
            addCriterion("belong_product_id not in", values, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdBetween(String value1, String value2) {
            addCriterion("belong_product_id between", value1, value2, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andBelongProductIdNotBetween(String value1, String value2) {
            addCriterion("belong_product_id not between", value1, value2, "belongProductId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdIsNull() {
            addCriterion("requirement_content_id is null");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdIsNotNull() {
            addCriterion("requirement_content_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdEqualTo(String value) {
            addCriterion("requirement_content_id =", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdNotEqualTo(String value) {
            addCriterion("requirement_content_id <>", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdGreaterThan(String value) {
            addCriterion("requirement_content_id >", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdGreaterThanOrEqualTo(String value) {
            addCriterion("requirement_content_id >=", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdLessThan(String value) {
            addCriterion("requirement_content_id <", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdLessThanOrEqualTo(String value) {
            addCriterion("requirement_content_id <=", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdLike(String value) {
            addCriterion("requirement_content_id like", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdNotLike(String value) {
            addCriterion("requirement_content_id not like", value, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdIn(List<String> values) {
            addCriterion("requirement_content_id in", values, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdNotIn(List<String> values) {
            addCriterion("requirement_content_id not in", values, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdBetween(String value1, String value2) {
            addCriterion("requirement_content_id between", value1, value2, "requirementContentId");
            return (Criteria) this;
        }

        public Criteria andRequirementContentIdNotBetween(String value1, String value2) {
            addCriterion("requirement_content_id not between", value1, value2, "requirementContentId");
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