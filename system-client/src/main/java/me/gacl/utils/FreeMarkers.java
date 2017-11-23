package me.gacl.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springside.modules.utils.base.ExceptionUtil;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * FreeMarkers渲染引擎
 * Created by caosk on 2017/11/22.
 */
public class FreeMarkers {

	/**
	 * 渲染模板字符串。
	 */
	public static String renderString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("default", new StringReader(templateString), new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

	/**
	 * 渲染Template文件.
	 */
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw ExceptionUtil.unchecked(e);
		}
	}

	/**
	 * 创建默认配置，设定模板目录.
	 */
	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
}
