package com.graduation.blog.dao;


import com.graduation.blog.domain.FileInfo;
import java.util.List;

public interface FileInfoMapper {

  /**
   * 根据ID物理删除文件信息
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 插入文件表所有字段值,id字段由数据库自动插入
   */
  Integer insert(FileInfo record);

  /**
   * 插入FileInfo对象中不为null的属性的值
   */
  int insertSelective(FileInfo record);
  /**
   * 批量插入文件信息
   * @param list
   * @return
   */
  int insertList(List<FileInfo> list);

  FileInfo selectByPrimaryKey(String id);


  /**
   * 根据ID更新FileInfo对象中不为null的属性值
   */
  int updateByPrimaryKeySelective(FileInfo record);

  /**
   * 根据ID更新文件信息的所有内容
   */
  int updateByPrimaryKey(FileInfo record);
  /**
   * 根据文件ID(fileId)更新FileInfo对象中不为null的属性值
   * @param record
   * @return
   */
  int updateByIdSelective(FileInfo record);

}