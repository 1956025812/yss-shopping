package com.yss.shopping.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanshushu
 * @date 2020/02/03
 * @description REDIS工具类
 */
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 删除keys数组对应的缓存
     *
     * @param keys key数组
     */
    public void delObject(String... keys) {
        log.info("删除keys数组对应的缓存，keys数组为： {}", keys);
        try {
            if (keys != null && keys.length > 0) {
                if (keys.length == 1) {
                    redisTemplate.delete(keys[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(keys));
                }
            }
        } catch (Exception e) {
            log.error("删除keys数组对应的缓存失败，keys数组为： {}， 失败原因为：{}", keys, e);
        }
    }


    /**
     * 获取key对应的整型value
     *
     * @param key
     * @return Integer
     */
    public Integer getIntOfStr(String key) {
        log.info("获取key对应的整型value, key为:{}", key);
        try {
            String value = (String) redisTemplate.boundValueOps(key).get();
            if (StringUtils.isNotBlank(value)) {
                return Integer.valueOf(value);
            }
        } catch (Exception e) {
            log.error("获取key对应的整型value失败, key为:{}, 失败原因为：{}", key, e);
        }
        return null;
    }


    /**
     * 获取key对应的字符串类型value
     *
     * @param key
     * @return
     */
    public String getStrOfStr(String key) {
        log.info("获取key对应的字符串类型value, key为:{}", key);
        try {
            return (String) redisTemplate.boundValueOps(key).get();
        } catch (Exception e) {
            log.error("获取key对应的字符串类型value失败，key为： {}， 失败原因为：{}", key, e);
        }
        return null;
    }


    /**
     * 获取缓存<br>
     * 注：基本数据类型(Character除外)，请直接使用get(String key, Class<T> clazz)取值
     *
     * @param key
     * @return
     */
    public Object getObj(String key) {
        try {
            return redisTemplate.boundValueOps(key).get();
        } catch (Exception e) {
            log.error("getObj error key:{}", key, e);
        }
        return null;
    }


    /**
     * 获取缓存<br>
     * 注：该方法暂不支持Character数据类型
     *
     * @param key   key
     * @param clazz 类型
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        try {
            return (T) redisTemplate.boundValueOps(key).get();
        } catch (Exception e) {
            log.error("get error key:{}", key, e);
        }
        return null;
    }


    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public void setObject(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("setObject error key:{}", key, e);
        }
    }


    /**
     * 将value以String类型写入redis
     *
     * @param key
     * @param value
     * @param time
     */
    public void setString(String key, String value, long time) {
        try {
            redisTemplate.opsForValue().set(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("setString error key:{}", key, e);
        }
    }


    /**
     * 递减操作
     *
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by) {
        try {
            if (!redisTemplate.hasKey(key)) {
                setObject(key, by, -1);
                return by;
            } else {
                double old = evalDouble(getObj(key));
                setObject(key, old + by, -1);
                return old + by;
            }
        } catch (Exception e) {
            log.error("incr error key:{} by:{}", key, by, e);
        }
        return 0.0;
    }


    /**
     * 加1
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        try {
            return redisTemplate.opsForValue().increment(key, 1);
        } catch (Exception e) {
            log.error("incr error key:{}", key, e);
        }
        return 0;
    }


    /**
     * 减1
     *
     * @param key
     * @return
     */
    public long decr(String key) {
        try {
            return redisTemplate.opsForValue().increment(key, -1);
        } catch (Exception e) {
            log.error("decr error key:{}", key, e);
        }
        return 0;
    }


    /**
     * 获取double类型值
     *
     * @param key
     * @return
     */
    public double getDoubleOfStr(String key) {
        try {
            String value = (String) redisTemplate.boundValueOps(key).get();
            if (StringUtils.isNotBlank(value)) {
                return Double.valueOf(value);
            }
            return 0d;
        } catch (Exception e) {
            log.error("getDoubleOfStr error key:{}", key, e);
        }
        return 0d;
    }


    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public void setDoubleOfStr(String key, double value, long time) {
        try {
            redisTemplate.opsForValue().set(key, String.valueOf(value));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("setDoubleOfStr error key:{}", key, e);
        }
    }


    /**
     * 设置double类型值
     *
     * @param key
     * @param value
     * @param time  失效时间(秒)
     */
    public void setIntOfStr(String key, int value, long time) {
        try {
            redisTemplate.opsForValue().set(key, String.valueOf(value));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("setIntOfStr error key:{}", key, e);
        }
    }


    /**
     * key对应的hash中是否包含某个字段
     *
     * @param key
     * @param item
     * @return
     */
    public boolean hHashKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * 将map写入缓存
     *
     * @param key
     * @param map
     * @param time 失效时间(秒)
     */
    public <T> void setMap(String key, Map<String, T> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("setMap error key:{}", key, e);
        }
    }


    /**
     * 向key对应的map中添加缓存对象
     *
     * @param key   cache对象key
     * @param field map对应的key
     * @param value 值
     */
    public void addField2Map(String key, String field, Object value) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
        } catch (Exception e) {
            log.error("addField2Map error key:{} field:{}", key, field, e);
        }
    }


    /**
     * 获取map缓存 TODO
     *
     * @param key
     * @param clazz
     * @return
     */
 /*   public <T> Map<String, T> mget(String key, Class<T> clazz) {
        try {
            BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
            return boundHashOperations.entries();
        } catch (Exception e) {
            log.error("mget error key:{}", key, e);
        }
        return null;
    }*/


    /**
     * 获取map缓存中的某个对象
     *
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapField(String key, String field, Class<T> clazz) {
        try {
            return (T) redisTemplate.boundHashOps(key).get(field);
        } catch (Exception e) {
            log.error("getMapField error key:{} field:{}", key, field, e);
        }
        return null;
    }


    /**
     * 获取key对应的hash对象转换为map对象
     *
     * @param key
     * @return
     */
    public Map<String, Object> getMap(String key) {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
            if (map != null) {
                for (Object o : map.keySet()) {
                    result.put(evalString(o), map.get(o));
                }
            }
            return result;
        } catch (Exception e) {
            log.error("getMap error key:{}", key, e);
        }
        return null;
    }


    /**
     * 删除map中的某个对象 TODO
     *
     * @param key   map对应的key
     * @param field map中该对象的key
     * @author lh
     * @date 2016年8月10日
     */
/*    public void delMapField(String key, String... field) {
        try {
            BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
            boundHashOperations.delete(field);
        } catch (Exception e) {
            log.error("delMapField error key:{} field:{}", key, field, e);
        }
    }*/


    /**
     * hashKey 加减（只能是Number类型）
     *
     * @param key
     * @param hashKey
     */
    public void increaseHash(String key, String hashKey, long count) {
        try {
            if (!hasKey(key)) {
                redisTemplate.opsForHash().put(key, hashKey, count);
            } else {
                long oldValue = evalLong(redisTemplate.opsForHash().get(key, hashKey), 0);
                redisTemplate.opsForHash().put(key, hashKey, count + oldValue);
            }
        } catch (Exception e) {
            log.error("increaseHash error key:{}", key, e);
        }
    }
    //---------------------------------------------------------hash 接口 end----------------------------------------


    /**
     * 指定缓存的失效时间
     *
     * @param key  缓存KEY
     * @param time 失效时间(秒)
     * @author FangJun
     * @date 2016年8月14日
     */
    public void expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }


    /**
     * 获取剩余过期时间
     *
     * @param key 缓存key
     * @return 剩余过期时间(秒)
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    //-----------------------------------------------------set 操作==============================================

    /**
     * 添加set
     *
     * @param key
     * @param value
     */
    public Long sadd(String key, String... value) {
        try {
            return redisTemplate.boundSetOps(key).add(value);
        } catch (Exception e) {
            log.error("sadd error key:{}", key, e);
        }
        return null;
    }


    /**
     * 删除set集合中的对象
     *
     * @param key
     * @param value
     */
    public Long srem(String key, String... value) {
        try {
            return redisTemplate.boundSetOps(key).remove(value);
        } catch (Exception e) {
            log.error("srem error key:{}", key, e);
        }
        return null;
    }


    /**
     * set重命名
     *
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey) {
        try {
            redisTemplate.boundSetOps(oldkey).rename(newkey);
        } catch (Exception e) {
            log.error("srename error key:{} newkey:{}", oldkey, newkey, e);
        }
    }


    /**
     * 获取集合中数量
     *
     * @param key
     * @return
     */
    public long scard(String key) {
        try {
            return redisTemplate.boundSetOps(key).size();
        } catch (Exception e) {
            log.error("scard error key:{}", key, e);
        }
        return 0;
    }


    /**
     * 获取set中所有成员
     *
     * @param key
     * @return
     */
    public Set<Object> smembers(String key) {
        try {
            Set<Object> members = redisTemplate.boundSetOps(key).members();
        } catch (Exception e) {
            log.error("getMembers error key:{}", key, e);
        }
        return new HashSet<>();
    }


    /**
     * 获取set中所有成员
     *
     * @param key
     * @return
     */
    public Set<String> members(String key) {
        HashSet<String> resp = new HashSet<>();
        try {
            Set<Object> members = redisTemplate.boundSetOps(key).members();
            for (Object member : members) {
                resp.add((String) member);
            }
        } catch (Exception e) {
            log.error("members error key:{}", key, e);
        }
        return resp;
    }


    /**
     * 移除并返回集合中的一个随机元素。
     *
     * @param key
     * @return
     */
    public Object spop(String key) {
        try {
            return redisTemplate.boundSetOps(key).pop();
        } catch (Exception e) {
            log.error("spop error key:{}", key, e);
        }
        return null;
    }


    public boolean sIsMember(String key, String value) {
        try {
            return redisTemplate.boundSetOps(key).isMember(value);
        } catch (Exception e) {
            log.error("sismember error key:{}", key, e);
            return false;
        }
    }

    //-----------------------------------------------------set 操作 end==============================================


    //=========================================zset 开始=====================================

    /**
     * 查询成员数
     *
     * @param key
     */
    public long zcard(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            log.error("zcard error key:{}", key, e);
        }
        return 0L;
    }


    /**
     * 往zset中添加元素
     *
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, Object value, Double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            log.error("zadd error key:{}", key, e);
        }
    }


    /**
     * 添加所有set元素
     *
     * @param key
     * @param values
     */
    public void zaddAll(String key, Set<ZSetOperations.TypedTuple<Object>> values) {
        try {
            redisTemplate.opsForZSet().add(key, values);
        } catch (Exception e) {
            log.error("zaddAll error key:{}", key, e);
        }
    }


    /**
     * 返回有序集 key 中，指定区间内的成员。按照score值从小到大排序
     *
     * @param key
     * @param clazz
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public <T> Set<T> zrange(String key, Class<T> clazz, int start, int end) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            log.error("zrange error key:{}", key, e);
        }
        return null;
    }


    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递减(从大到小)来排列。
     *
     * @param key
     * @param clazz
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public <T> Set<T> zRevRange(String key, Class<T> clazz, int start, int end) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            log.error("zRevRange error key:{}", key, e);
        }
        return null;
    }


    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。
     *
     * @param key
     * @param clazz
     * @param min
     * @param max
     * @param <T>
     * @return
     */
    public <T> Set<T> zrangeByScore(String key, Class<T> clazz, double min, double max) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            log.error("zrangeByScore error key:{}", key, e);
        }
        return null;
    }


    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递减(从大到小)次序排列。
     *
     * @param key
     * @param clazz
     * @param min
     * @param max
     * @param <T>
     * @return
     */
    public <T> Set<T> zRevRangeByScore(String key, Class<T> clazz, double min, double max) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
        } catch (Exception e) {
            log.error("zRevRangeByScore error key:{}", key, e);
        }
        return null;
    }


    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param key
     * @param value
     */
    public void zrem(String key, Object... value) {
        try {
            redisTemplate.opsForZSet().remove(key, value);
        } catch (Exception e) {
            log.error("zrem error key:{}", key, e);
        }
    }


    /**
     * 通过score来删除元素（包括等于 min 或 max ）
     *
     * @param key
     * @param minScore
     * @param maxScore
     */
    public void zremByScore(String key, double minScore, double maxScore) {
        try {
            redisTemplate.opsForZSet().removeRangeByScore(key, minScore, maxScore);
        } catch (Exception e) {
            log.error("zremByScore error key:{}", key, e);
        }
    }


    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max )的成员。有序集成员按 score 值递增(从小到大)次序排列。
     *
     * @param key
     * @param clazz
     * @param min
     * @param max
     * @param offset
     * @param count
     * @param <T>
     * @return
     */
    public <T> Set<T> zrangeByScore(String key, Class<T> clazz, double min, double max, int offset, int count) {
        return (Set<T>) redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }


    /**
     * 通过下标来删除元素
     *
     * @param key
     * @param start
     * @param end
     */
    public void zremRange(String key, long start, long end) {
        try {
            redisTemplate.opsForZSet().removeRange(key, start, end);
        } catch (Exception e) {
            log.error("zremRange error key:{}", key, e);
        }
    }
    //-----------------------------------------------zset结束----------------------------------------


    /**
     * 模糊查询keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return null;
    }


    /**
     * 删除key
     *
     * @param key
     */
    public void delKey(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("delKey error key:{}", key, e);
        }
    }


    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("hasKey error key:{}", key, e);
        }
        return false;
    }


    //***************************************************list**********************************************

    public Long llen(String key) {
        return redisTemplate.opsForList().size(key);
    }


    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error("left push  error key:{}", key, e);
        }
        return false;
    }


    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("lGet error key:{}", key, e);
        }
        return null;
    }


    public Long leftPush(String key, Object value) {
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error("leftPush key:{}", key, e);
        }
        return null;
    }


    public Long leftPushAll(String key, Collection value) {
        try {
            return redisTemplate.opsForList().leftPushAll(key, value);
        } catch (Exception e) {
            log.error("leftPushAll key:{}", key, e);
        }
        return null;
    }


    public Long rightPush(String key, Object value) {
        try {
            return redisTemplate.opsForList().rightPush(key, value);
        } catch (Exception e) {
            log.error("rightPush key:{}", key, e);
        }
        return null;
    }


    public Object leftPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("leftPop key:{}", key, e);
        }

        return null;
    }


    public Object rightPop(String key) {
        try {
            return redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            log.error("rightPop key:{}", key, e);
        }
        return null;
    }


    public Object rightPopLeftPush(String source, String destination) {
        try {
            return redisTemplate.opsForList().rightPopAndLeftPush(source, destination);
        } catch (Exception e) {
            log.error("rightPopLeftPush source:{}, destination={}", source, destination, e);
        }
        return null;
    }


    public Object leftBlockPop(String key, long timeout) {
        try {
            return redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("leftBlockPop key:{}", key, e);
        }
        return null;
    }


    /**
     * 阻塞的方式取队列数据
     *
     * @param key
     * @param timeout
     * @return
     */
    public Object rightBrPop(String key, long timeout) {
        try {
            return redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("rightBrPop key:{}", key, e);
        }
        return null;
    }


    /**
     * 获取redis锁，并设置自动过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean setNXWithExpireTime(String key, Object value, long timeout, TimeUnit unit) {
        try {
            if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
                if (redisTemplate.expire(key, timeout, unit)) {
                    return true;
                } else {
                    redisTemplate.delete(key);
                }
            }
        } catch (Exception e) {
            redisTemplate.delete(key);
            log.error("setNXWithExpireTime key:{}", key, e);
        }
        return false;
    }

    // TODO
/*    public void deleteByPrex(String prex) {
        try {
            Set<String> keys = redisTemplate.keys(prex);
            if (!CollectionUtils.isEmpty(keys)) {
                redisTemplate.delete(keys);
            }
        } catch (Exception e) {
            log.error("deleteByPrex key:{}", prex, e);
        }
    }*/


    /**
     * 通过是时间类型设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        boolean expire = false;
        expire = redisTemplate.expire(key, timeout, unit);
        if (!expire) {
            redisTemplate.delete(key);
        }
        return expire;
    }


    /**
     * list裁剪
     *
     * @param key
     * @param start
     * @param end
     */
    public void ltrim(String key, long start, long end) {
        try {
            redisTemplate.opsForList().trim(key, start, end);
        } catch (Exception e) {
            log.error("ltrim key:{} start:{} end:{}", key, start, end, e);
        }
    }


    public double evalDouble(Object obj) {
        if (obj == null) {
            return 0d;
        } else if (StringUtils.isNotBlank(obj.toString())) {
            return Double.parseDouble(obj.toString().trim());
        }
        return 0d;
    }


    public String evalString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }


    public long evalLong(Object obj) {
        if (obj == null) {
            return -1000;
        }
        return NumberUtils.toLong(obj.toString().replaceAll(" ", ""), -1000);
    }


    public long evalLong(Object obj, long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        return NumberUtils.toLong(obj.toString().replaceAll(" ", ""), defaultValue);
    }


}