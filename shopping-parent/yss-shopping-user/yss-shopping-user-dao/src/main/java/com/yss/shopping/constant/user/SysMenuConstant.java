package com.yss.shopping.constant.user;

/**
 * 系统菜单常量接口
 */
public interface SysMenuConstant {

    /**
     * 默认的父级顶级ID-0L
     */
    Long PARENT_ID_DEFAULT_TOP = 0L;

    /**
     * 默认的顶级菜单的级别-1
     */
    Integer LEVEL_DEFAULT_TOP = 1;


    /**
     * 系统菜单表字段
     */
    enum Column implements SysMenuConstant {

        /**
         * id-主键ID
         */
        ID("id", "主键ID"),

        /**
         * menu_type-菜单类型：1-页面，2-按钮
         */
        MENU_TYPE("menu_type", "菜单类型：1-页面，2-按钮"),

        /**
         * state-状态：0-删除，1-启用，2-禁用
         */
        STATE("state", "状态：0-删除，1-启用，2-禁用"),

        /**
         * parent_id-父菜单ID
         */
        PARENT_ID("parent_id", "父菜单ID"),
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
     * 菜单状态：0-删除，1-启用，2-禁用
     */
    enum State implements SysMenuConstant {

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


    /**
     * 菜单类型：1-页面，2-按钮
     */
    enum MenuType implements SysMenuConstant {

        /**
         * 1-页面
         */
        PAGE(1, "页面"),

        /**
         * 2-按钮
         */
        BUTTON(2, "按钮"),
        ;

        private Integer key;
        private String value;

        MenuType(Integer key, String value) {
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
            for (MenuType e : MenuType.values()) {
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
            for (MenuType e : MenuType.values()) {
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
