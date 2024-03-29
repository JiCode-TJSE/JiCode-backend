package com.JiCode.ProductDev.adaptor.output.dataaccess.DBModels;

import java.util.ArrayList;
import java.util.List;

public class BacklogitemBacklogitemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BacklogitemBacklogitemExample() {
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

        public Criteria andBacklogitemid1IsNull() {
            addCriterion("backlogitemId1 is null");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1IsNotNull() {
            addCriterion("backlogitemId1 is not null");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1EqualTo(String value) {
            addCriterion("backlogitemId1 =", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1NotEqualTo(String value) {
            addCriterion("backlogitemId1 <>", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1GreaterThan(String value) {
            addCriterion("backlogitemId1 >", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1GreaterThanOrEqualTo(String value) {
            addCriterion("backlogitemId1 >=", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1LessThan(String value) {
            addCriterion("backlogitemId1 <", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1LessThanOrEqualTo(String value) {
            addCriterion("backlogitemId1 <=", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1Like(String value) {
            addCriterion("backlogitemId1 like", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1NotLike(String value) {
            addCriterion("backlogitemId1 not like", value, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1In(List<String> values) {
            addCriterion("backlogitemId1 in", values, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1NotIn(List<String> values) {
            addCriterion("backlogitemId1 not in", values, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1Between(String value1, String value2) {
            addCriterion("backlogitemId1 between", value1, value2, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid1NotBetween(String value1, String value2) {
            addCriterion("backlogitemId1 not between", value1, value2, "backlogitemid1");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2IsNull() {
            addCriterion("backlogitemId2 is null");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2IsNotNull() {
            addCriterion("backlogitemId2 is not null");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2EqualTo(String value) {
            addCriterion("backlogitemId2 =", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2NotEqualTo(String value) {
            addCriterion("backlogitemId2 <>", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2GreaterThan(String value) {
            addCriterion("backlogitemId2 >", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2GreaterThanOrEqualTo(String value) {
            addCriterion("backlogitemId2 >=", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2LessThan(String value) {
            addCriterion("backlogitemId2 <", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2LessThanOrEqualTo(String value) {
            addCriterion("backlogitemId2 <=", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2Like(String value) {
            addCriterion("backlogitemId2 like", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2NotLike(String value) {
            addCriterion("backlogitemId2 not like", value, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2In(List<String> values) {
            addCriterion("backlogitemId2 in", values, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2NotIn(List<String> values) {
            addCriterion("backlogitemId2 not in", values, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2Between(String value1, String value2) {
            addCriterion("backlogitemId2 between", value1, value2, "backlogitemid2");
            return (Criteria) this;
        }

        public Criteria andBacklogitemid2NotBetween(String value1, String value2) {
            addCriterion("backlogitemId2 not between", value1, value2, "backlogitemid2");
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