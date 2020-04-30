package com.yss.shopping.user.constant;

/**
 * <p>
 * 系统角色常量接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public interface SysRoleConstant {

    /**
     * 默认的父级角色顶级ID-1L
     */
    Long PARENT_ID_DEFAULT_TOP = 1L;


    /**
     * 默认的系统超级管理员角色-天眼ID-1L
     */
    Long PARENT_ID_SUPER_MANAGER_TIYAN = 1L;


    /**
     * 默认的顶级角色的级别-1
     */
    Integer LEVEL_DEFAULT_TOP = 1;


    /**
     * 系统角色表字段
     */
    enum Column implements SysRoleConstant {

        /**
         * id-主键ID
         */
        ID("id", "主键ID"),

        /**
         * role_name-角色名称
         */
        ROLE_NAME("role_name", "角色名称"),

        /**
         * state-状态：0-删除，1-启用，2-禁用
         */
        STATE("state", "状态：0-删除，1-启用，2-禁用"),

        /**
         * parent_id-父角色ID
         */
        PARENT_ID("parent_id", "父角色ID"),
        ;

        private String key;
        private String value;

        Column(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 根据整型索引得到对应的名称
         *
         * @param key
         * @return value
         */
        public static String getValue(String key) {
            for (Column e : Column.values()) {
                if (e.key.equals(key)) {
                    return e.value;
                }
            }
            return null;
        }

        /**
         * 判断是否包含value
         *
         * @param value
         * @return 如果包含value, 则返回对应的key, 否则返回null
         */
        public static String containValue(String value) {
            for (Column e : Column.values()) {
                if (e.value.equals(value)) {
                    return e.key;
                }
            }
            return null;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }


    /**
     * 角色状态：0-删除，1-启用，2-禁用
     */
    enum State implements SysRoleConstant {

        /**
         * 0-刪除
         */
        DEL(0, "删除"),

        /**
         * 1-启用
         */
        OPEN(1, "启用"),

        /**
         * 2-禁用
         */
        CLOSE(2, "禁用");

        private Integer key;
        private String value;

        State(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 根据整型索引得到对应的名称
         *
         * @param key
         * @return value
         */
        public static String getValue(Integer key) {
            for (State e : State.values()) {
                if (e.key.equals(key)) {
                    return e.value;
                }
            }
            return null;
        }

        /**
         * 判断是否包含value
         *
         * @param value
         * @return 如果包含value, 则返回对应的key, 否则返回null
         */
        public static Integer containValue(String value) {
            for (State e : State.values()) {
                if (e.value.equals(value)) {
                    return e.key;
                }
            }
            return null;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
