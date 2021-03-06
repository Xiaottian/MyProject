/**
 * ProjectName:    MyProject
 * PackageName:    tk.mybatis.generator
 * FileName：      MyCommentGenerator.java
 * Copyright:      Copyright(C) 2018
 * Company:        北京神州泰岳软件股份有限公司
 * Author:         JIT
 * CreateDate:     2018/11/15 11:29
 */

package tk.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.api.dom.java.Field;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

public class MyCommentGenerator extends DefaultCommentGenerator {

	/**
	 * 由于默认实现类中的可配参数都没有提供给子类可以访问的方法，这里要定义一遍
	 */
	private boolean suppressAllComments;

	/**
	 * 同上
	 */
	private boolean addRemarkComment;

	/**
	 * 设置用户配置的参数
	 *
	 * @param properties
	 */
	public void addConfigurationProperties(Properties properties) {
		//先调用父类方法保证父类方法可以正常使用
		super.addConfigurationProperties(properties);
		//获取suppressAllComments参数
		suppressAllComments = isTrue(properties.getProperty(
				PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
		//获取addRemarkComment
		addRemarkComment = isTrue(properties.getProperty(
				PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

	}

	/**
	 * 给字段添加注释信息
	 *
	 * @param field
	 * @param introspectedTable
	 * @param introspectedColumn
	 */
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
								IntrospectedColumn introspectedColumn){
		//如果阻止生成所有注释，直接返回
		if (suppressAllComments){
			return;
		}
		//文档注释开始
		field.addJavaDocLine("/**");

		//获取数据库字段的备注信息
		String remarks = introspectedColumn.getRemarks();

		//根据参数和备注信息判断是否添加备注信息
		if (addRemarkComment && stringHasValue(remarks)){
			String[]  remarkLines  = remarks.split(
					System.getProperty("line.separator"));
			for (String remarkLine : remarkLines) {
				field.addJavaDocLine(" * " + remarkLine);
			}
		}
		//由于Java对象名和数据库字段名可能不一样，注释中保留数据库字段名
		field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
		field.addJavaDocLine(" */");
	}
}
