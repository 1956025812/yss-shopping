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
     * 字符编码：GBK-GBK, UTF8-UTF-8
     */
    enum Encoding implements CommonConstant {

        /**
         * GBK-GBK
         */
        GBK("GBK", "GBK"),

        /**
         * UTF8-UTF-8
         */
        UTF8("UTF-8", "UTF-8"),
        ;

        private String key;
        private String value;

        Encoding(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


    /**
     * 请求数据类型：application/json - application/json
     */
    enum ContentType implements CommonConstant {

        /**
         * application/json - application/json
         */
        APPLICATION_JSON("application/json", "application/json"),
        ;

        private String key;
        private String value;

        ContentType(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }


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
