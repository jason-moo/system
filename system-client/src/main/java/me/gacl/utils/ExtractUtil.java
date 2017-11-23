package me.gacl.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springside.modules.utils.reflect.ClassUtil;
import org.springside.modules.utils.reflect.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 结构抽取工具类
 * Created by caosk on 2017/5/16.
 */
public class ExtractUtil {

    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> result = new HashMap<>();
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                try {
                    result.put(field.getName(), ReflectionUtil.invokeGetter(obj, field.getName()));
                } catch (Exception e) {// NOSONAR
                    // Field不在当前类定义,继续向上转型
                    ClassUtil.makeAccessible(field);
                    try {
                        result.put(field.getName(), field.get(obj));
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return result;
    }

    public static Map<String, String> object2StrMap(Object obj) {
        Map<String, String> result = new HashMap<>();
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                try {
                    result.put(field.getName(), (String) ReflectionUtil.invokeGetter(obj, field.getName()));
                } catch (Exception e) {// NOSONAR
                    // Field不在当前类定义,继续向上转型
                    ClassUtil.makeAccessible(field);
                    try {
                        result.put(field.getName(), (String)field.get(obj));
                    } catch (IllegalAccessException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        return result;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List extractToMap(final Collection collection) {
        List list = new ArrayList(collection.size());

        try {
            for (Object obj : collection) {
                list.add(object2Map(obj));
            }
        } catch (Exception e) {
            throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
        }
        return list;
    }

    /**
     * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
     *
     * @param collection
     *            来源集合.
     * @param keyPropertyName
     *            要提取为Map中的Key值的属性名.
     *
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map extractToMap(final Collection collection, final String keyPropertyName) {
        Map map = new HashMap(collection.size());

        try {
            for (Object obj : collection) {
                map.put(PropertyUtils.getProperty(obj, keyPropertyName), obj);
            }
        } catch (Exception e) {
            throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
        }

        return map;
    }

    /**
     * 提取集合中的对象的两个属性(通过Getter函数), 组合成Map.
     *
     * @param collection
     *            来源集合.
     * @param keyPropertyName
     *            要提取为Map中的Key值的属性名.
     * @param valuePropertyName
     *            要提取为Map中的Value值的属性名.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map extractToMap(final Collection collection, final String keyPropertyName,
                                   final String valuePropertyName) {
        Map map = new HashMap(collection.size());

        try {
            for (Object obj : collection) {
                map.put(PropertyUtils.getProperty(obj, keyPropertyName),
                        PropertyUtils.getProperty(obj, valuePropertyName));
            }
        } catch (Exception e) {
            throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
        }

        return map;
    }

    /**
     * 提取集合中的对象的一个属性(通过Getter函数), 组合成List.
     *
     * @param collection
     *            来源集合.
     * @param propertyName
     *            要提取的属性名.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List extractToList(final Collection collection, final String propertyName) {
        List list = new ArrayList(collection.size());

        try {
            for (Object obj : collection) {
                Object value = PropertyUtils.getProperty(obj, propertyName);
                if (value != null && !list.contains(value)) {
                    list.add(value);
                }
            }
        } catch (Exception e) {
            throw ReflectionUtil.convertReflectionExceptionToUnchecked(e);
        }

        return list;
    }

    /**
     * 提取集合中的对象的一个属性(通过Getter函数), 组合成由分割符分隔的字符串.
     *
     * @param collection
     *            来源集合.
     * @param propertyName
     *            要提取的属性名.
     * @param separator
     *            分隔符.
     */
    @SuppressWarnings("rawtypes")
    public static String extractToString(final Collection collection, final String propertyName, final String separator) {
        List list = extractToList(collection, propertyName);
        return StringUtils.join(list, separator);
    }

    /**
     * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
     */
    @SuppressWarnings("rawtypes")
    public static String convertToString(final Collection collection, final String separator) {
        return StringUtils.join(collection, separator);
    }

    /**
     * 转换Collection所有元素(通过toString())为String,
     * 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
     */
    @SuppressWarnings("rawtypes")
    public static String convertToString(final Collection collection, final String prefix, final String postfix) {
        StringBuilder builder = new StringBuilder();
        for (Object o : collection) {
            builder.append(prefix).append(o).append(postfix);
        }
        return builder.toString();
    }

}
