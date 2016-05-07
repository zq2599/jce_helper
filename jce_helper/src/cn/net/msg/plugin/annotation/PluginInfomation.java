package cn.net.msg.plugin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解用于提供插件基本信息
 * @author willzhao
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PluginInfomation {
	String creator();
	String desc();
	String version();
	PluginDataSource source();
	PluginDataType type();
	String jces();
	String path();
}
