package com.yss.shopping.user.constant;

/**
 * <p>
 * 系统用户常量接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public interface SysUserConstant {

    /**
     * 超级管理员UID-0
     */
    Long ADMIN_UID = 1L;

    /**
     * 用户默认的密码-123456
     */
    String DEFAULT_PASSWORD = "123456";


    /**
     * 系统用户表字段
     */
    enum Column implements SysUserConstant {

        /**
         * id-主键ID
         */
        ID("id", "主键ID"),

        /**
         * username-账号
         */
        USERNAME("username", "账号"),

        /**
         * nickname-昵称
         */
        NICKNAME("nickname", "昵称"),

        /**
         * email-邮箱
         */
        EMAIL("email", "邮箱"),

        /**
         * STATE-状态
         */
        STATE("state", "状态"),

        /**
         * 注册来源：1-后台注册，2-用户注册，3-QQ，4-WX
         */
        REGISTER_SOURCE("register_source", "注册来源"),

        /**
         * CREATE_TIME-创建时间
         */
        CREATE_TIME("create_time", "创建时间"),
        ;

        private String key;
        private String value;

        Column(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 根据整型索引得到对应的名称
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
     * 用户状态：0-删除，1-启用，2-禁用
     */
    enum State implements SysUserConstant {

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
     * 注册来源：1-系统注册，2-用户注册，3-QQ，4-WX
     */
    enum RegisterSource implements SysUserConstant {

        /**
         * 1-系统注册
         */
        SYSTEM(1, "系统注册"),

        /**
         * 2-用户注册
         */
        USER(2, "用户注册"),

        /**
         * 3-QQ
         */
        QQ(3, "QQ"),

        /**
         * 4-WX
         */
        WX(4, "WX"),
        ;

        private Integer key;
        private String value;

        RegisterSource(Integer key, String value) {
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
            for (RegisterSource e : RegisterSource.values()) {
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
            for (RegisterSource e : RegisterSource.values()) {
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
