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

        public Criteria andRequirementidIsNull() {
            addCriterion("requirementId is null");
            return (Criteria) this;
        }

        public Criteria andRequirementidIsNotNull() {
            addCriterion("requirementId is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementidEqualTo(String value) {
            addCriterion("requirementId =", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidNotEqualTo(String value) {
            addCriterion("requirementId <>", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidGreaterThan(String value) {
            addCriterion("requirementId >", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidGreaterThanOrEqualTo(String value) {
            addCriterion("requirementId >=", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidLessThan(String value) {
            addCriterion("requirementId <", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidLessThanOrEqualTo(String value) {
            addCriterion("requirementId <=", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidLike(String value) {
            addCriterion("requirementId like", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidNotLike(String value) {
            addCriterion("requirementId not like", value, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidIn(List<String> values) {
            addCriterion("requirementId in", values, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidNotIn(List<String> values) {
            addCriterion("requirementId not in", values, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidBetween(String value1, String value2) {
            addCriterion("requirementId between", value1, value2, "requirementid");
            return (Criteria) this;
        }

        public Criteria andRequirementidNotBetween(String value1, String value2) {
            addCriterion("requirementId not between", value1, value2, "requirementid");
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

        public Criteria andRequirementcontentidIsNull() {
            addCriterion("requirementContentId is null");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidIsNotNull() {
            addCriterion("requirementContentId is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidEqualTo(String value) {
            addCriterion("requirementContentId =", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidNotEqualTo(String value) {
            addCriterion("requirementContentId <>", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidGreaterThan(String value) {
            addCriterion("requirementContentId >", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidGreaterThanOrEqualTo(String value) {
            addCriterion("requirementContentId >=", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidLessThan(String value) {
            addCriterion("requirementContentId <", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidLessThanOrEqualTo(String value) {
            addCriterion("requirementContentId <=", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidLike(String value) {
            addCriterion("requirementContentId like", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidNotLike(String value) {
            addCriterion("requirementContentId not like", value, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidIn(List<String> values) {
            addCriterion("requirementContentId in", values, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidNotIn(List<String> values) {
            addCriterion("requirementContentId not in", values, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidBetween(String value1, String value2) {
            addCriterion("requirementContentId between", value1, value2, "requirementcontentid");
            return (Criteria) this;
        }

        public Criteria andRequirementcontentidNotBetween(String value1, String value2) {
            addCriterion("requirementContentId not between", value1, value2, "requirementcontentid");
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