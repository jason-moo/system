package me.gacl.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.context.Context;

/**
 * Created by jason_moo on 2017/11/26.
 */
public class VelocityHelper {

    private static final VelocityHelper instance = new VelocityHelper();

    /** 私有构造函数 */
    private VelocityHelper() {
        //初始化velocity的信息 主要设置一些Velocity的默认属性
        //初始化
        try {
            Velocity.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * 取得实例
     * </pre>
     */
    public static VelocityHelper getInstance() {
        return instance;
    }

    /**
     * <pre>
     * 渲染：从reader到writer
     * </pre>
     *
     * @param context
     * @param writer
     * @param reader
     * @return
     */
    public boolean evaluate(Context context, Writer writer, Reader reader) {
        try {
            return Velocity.evaluate(context, writer, "", reader);
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
        }
    }

    /**
     * <pre>
     * 通过Map过滤一个输入流
     * </pre>
     *
     * @param map
     * @param path
     * @return
     */
    @SuppressWarnings("unchecked")
    public InputStream evaluate(Map map, String path) {
        try {
            // 把产生的输出流(字符流)，转换成输入流(字节流)
            byte[] dataBytes = this.evaluateToWriter(map, path).toString().getBytes();
            return new ByteArrayInputStream(dataBytes);
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detial [" + e.getMessage() + "]");
        }
    }

    /**
     * <pre>
     * 通过Map过滤一个输入流
     * </pre>
     *
     * @param map
     * @param path
     * @return
     */
    @SuppressWarnings("unchecked")
    public String evaluateToWriter(Map map, String path) {
        try {
            VelocityContext context = convertVelocityContext(map);
            Reader reader = new FileReader(path);
            CharArrayWriter writer = new CharArrayWriter();
            //开始评估
            this.evaluate(context, writer, reader);

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("velocity evaluate error! detail [" + e.getMessage() + "]");
        }
    }

    /**
     * <pre>
     * 取得Velocity系统属性
     * </pre>
     *
     * @param key
     * @return
     */
    public Object getProperty(String key) {
        return Velocity.getProperty(key);
    }

    /**
     * <pre>
     * 把Map转换成Context
     * </pre>
     */
    private VelocityContext convertVelocityContext(Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        if (map == null) {
            return context;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
        return context;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();
        map.put("date", "2010-04-27");
        map.put("weather", "晴朗");
        // 今天是：2010-04-27，天气：晴朗！
        System.out.println(VelocityHelper.getInstance().evaluateToWriter(map, "D:/Tmp/Src.txt"));
    }
}
