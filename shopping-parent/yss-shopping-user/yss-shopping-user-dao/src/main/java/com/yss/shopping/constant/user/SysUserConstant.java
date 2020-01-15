package com.yss.shopping.constant.user;

/**
 * 系统用户常量接口
 */
public interface SysUserConstant {

    /**
     * 用户状态：0-删除，1-启用，2-禁用
     */
    enum Status implements SysUserConstant {

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

        Status(Integer key, String value) {
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
            for (Status e : Status.values()) {
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
            for (Status e : Status.values()) {
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
