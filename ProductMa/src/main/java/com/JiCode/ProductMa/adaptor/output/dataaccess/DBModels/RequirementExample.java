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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNull() {
            addCriterion("attachment is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIsNotNull() {
            addCriterion("attachment is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentEqualTo(String value) {
            addCriterion("attachment =", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotEqualTo(String value) {
            addCriterion("attachment <>", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThan(String value) {
            addCriterion("attachment >", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentGreaterThanOrEqualTo(String value) {
            addCriterion("attachment >=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThan(String value) {
            addCriterion("attachment <", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLessThanOrEqualTo(String value) {
            addCriterion("attachment <=", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentLike(String value) {
            addCriterion("attachment like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotLike(String value) {
            addCriterion("attachment not like", value, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentIn(List<String> values) {
            addCriterion("attachment in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotIn(List<String> values) {
            addCriterion("attachment not in", values, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentBetween(String value1, String value2) {
            addCriterion("attachment between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andAttachmentNotBetween(String value1, String value2) {
            addCriterion("attachment not between", value1, value2, "attachment");
            return (Criteria) this;
        }

        public Criteria andModuleEnumIsNull() {
            addCriterion("module_enum is null");
            return (Criteria) this;
        }

        public Criteria andModuleEnumIsNotNull() {
            addCriterion("module_enum is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEnumEqualTo(String value) {
            addCriterion("module_enum =", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumNotEqualTo(String value) {
            addCriterion("module_enum <>", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumGreaterThan(String value) {
            addCriterion("module_enum >", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumGreaterThanOrEqualTo(String value) {
            addCriterion("module_enum >=", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumLessThan(String value) {
            addCriterion("module_enum <", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumLessThanOrEqualTo(String value) {
            addCriterion("module_enum <=", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumLike(String value) {
            addCriterion("module_enum like", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumNotLike(String value) {
            addCriterion("module_enum not like", value, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumIn(List<String> values) {
            addCriterion("module_enum in", values, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumNotIn(List<String> values) {
            addCriterion("module_enum not in", values, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumBetween(String value1, String value2) {
            addCriterion("module_enum between", value1, value2, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andModuleEnumNotBetween(String value1, String value2) {
            addCriterion("module_enum not between", value1, value2, "moduleEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumIsNull() {
            addCriterion("source_enum is null");
            return (Criteria) this;
        }

        public Criteria andSourceEnumIsNotNull() {
            addCriterion("source_enum is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEnumEqualTo(String value) {
            addCriterion("source_enum =", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumNotEqualTo(String value) {
            addCriterion("source_enum <>", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumGreaterThan(String value) {
            addCriterion("source_enum >", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumGreaterThanOrEqualTo(String value) {
            addCriterion("source_enum >=", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumLessThan(String value) {
            addCriterion("source_enum <", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumLessThanOrEqualTo(String value) {
            addCriterion("source_enum <=", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumLike(String value) {
            addCriterion("source_enum like", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumNotLike(String value) {
            addCriterion("source_enum not like", value, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumIn(List<String> values) {
            addCriterion("source_enum in", values, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumNotIn(List<String> values) {
            addCriterion("source_enum not in", values, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumBetween(String value1, String value2) {
            addCriterion("source_enum between", value1, value2, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andSourceEnumNotBetween(String value1, String value2) {
            addCriterion("source_enum not between", value1, value2, "sourceEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumIsNull() {
            addCriterion("type_enum is null");
            return (Criteria) this;
        }

        public Criteria andTypeEnumIsNotNull() {
            addCriterion("type_enum is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEnumEqualTo(String value) {
            addCriterion("type_enum =", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumNotEqualTo(String value) {
            addCriterion("type_enum <>", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumGreaterThan(String value) {
            addCriterion("type_enum >", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumGreaterThanOrEqualTo(String value) {
            addCriterion("type_enum >=", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumLessThan(String value) {
            addCriterion("type_enum <", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumLessThanOrEqualTo(String value) {
            addCriterion("type_enum <=", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumLike(String value) {
            addCriterion("type_enum like", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumNotLike(String value) {
            addCriterion("type_enum not like", value, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumIn(List<String> values) {
            addCriterion("type_enum in", values, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumNotIn(List<String> values) {
            addCriterion("type_enum not in", values, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumBetween(String value1, String value2) {
            addCriterion("type_enum between", value1, value2, "typeEnum");
            return (Criteria) this;
        }

        public Criteria andTypeEnumNotBetween(String value1, String value2) {
            addCriterion("type_enum not between", value1, value2, "typeEnum");
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

        public Criteria andSupervisorIdIsNull() {
            addCriterion("supervisor_id is null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIsNotNull() {
            addCriterion("supervisor_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdEqualTo(String value) {
            addCriterion("supervisor_id =", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotEqualTo(String value) {
            addCriterion("supervisor_id <>", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThan(String value) {
            addCriterion("supervisor_id >", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdGreaterThanOrEqualTo(String value) {
            addCriterion("supervisor_id >=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThan(String value) {
            addCriterion("supervisor_id <", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLessThanOrEqualTo(String value) {
            addCriterion("supervisor_id <=", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdLike(String value) {
            addCriterion("supervisor_id like", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotLike(String value) {
            addCriterion("supervisor_id not like", value, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdIn(List<String> values) {
            addCriterion("supervisor_id in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotIn(List<String> values) {
            addCriterion("supervisor_id not in", values, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdBetween(String value1, String value2) {
            addCriterion("supervisor_id between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andSupervisorIdNotBetween(String value1, String value2) {
            addCriterion("supervisor_id not between", value1, value2, "supervisorId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdIsNull() {
            addCriterion("in_version_id is null");
            return (Criteria) this;
        }

        public Criteria andInVersionIdIsNotNull() {
            addCriterion("in_version_id is not null");
            return (Criteria) this;
        }

        public Criteria andInVersionIdEqualTo(String value) {
            addCriterion("in_version_id =", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdNotEqualTo(String value) {
            addCriterion("in_version_id <>", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdGreaterThan(String value) {
            addCriterion("in_version_id >", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdGreaterThanOrEqualTo(String value) {
            addCriterion("in_version_id >=", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdLessThan(String value) {
            addCriterion("in_version_id <", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdLessThanOrEqualTo(String value) {
            addCriterion("in_version_id <=", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdLike(String value) {
            addCriterion("in_version_id like", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdNotLike(String value) {
            addCriterion("in_version_id not like", value, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdIn(List<String> values) {
            addCriterion("in_version_id in", values, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdNotIn(List<String> values) {
            addCriterion("in_version_id not in", values, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdBetween(String value1, String value2) {
            addCriterion("in_version_id between", value1, value2, "inVersionId");
            return (Criteria) this;
        }

        public Criteria andInVersionIdNotBetween(String value1, String value2) {
            addCriterion("in_version_id not between", value1, value2, "inVersionId");
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