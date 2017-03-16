package com.smd.demo.mapper;

import java.util.List;

/**
 * 系统中所有其他数据库映射类的父接口，定义一些常用的、公有的操作
 * 
 * @param <T>
 *            要操作的实体对象类型
 */
public interface BaseMapper<T> {

	/**
	 * 插入一个对象
	 * 
	 * @param t
	 *            带有查询条件的对象
	 */
	public void save(T t);

	/**
	 * 更新一个对象
	 * 
	 * @param t
	 *            带有查询条件的对象
	 */
	public void update(T t);

	/**
	 * 根据id删除一个对象
	 * 
	 * @param id
	 *            待删除对象的id
	 */
	public void deleteById(Object id);

	/**
	 * 根据id拼成的串删除多个对象
	 * 
	 * @param idArr
	 *            待删除对象的id拼成的串
	 */
	public void deleteList(String[] idArr);

	/**
	 * 根据条件查询总数
	 * 
	 * @param t
	 *            带有查询条件的对象
	 * @return 符合条件的记录数量
	 */
	public int selectCount(T t);

	/**
	 * 根据id查询单个对象
	 * 
	 * @param id
	 *            需要查询的对象的id
	 * @return 符合条件的查询结果
	 */
	public T selectById(Object id);

	/**
	 * 根据条件查询所有对象
	 * 
	 * @param t
	 *            带有查询条件的对象
	 * @return 符合条件的查询结果
	 */
	public List<T> selectList(T t);
}
