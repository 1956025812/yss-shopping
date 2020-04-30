package com.yss.shopping.common.constant;


/**
 * <p>
 * 通用常量接口
 * </p>
 *
 * @author yss
 * @since 2020-04-30 10:24
 */
public interface CommonConstant {

    String DEFAULT_SYSTEM_USER = "system";

    /**
     * 数字：0-3
     */
    enum Num implements CommonConstant {

        /**
         * 0-零
         */
        ZERO(0, "零"),

        /**
         * 1-一
         */
        ONE(1, "一"),

        /**
         * 2-二
         */
        TOW(2, "二"),

        /**
         * 3-三
         */
        THREE(3, "三");

        private Integer key;
        private String value;

        Num(Integer key, String value) {
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
            for (Num e : Num.values()) {
                if (e.key.equals(key)) {
                    return e.value;
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
