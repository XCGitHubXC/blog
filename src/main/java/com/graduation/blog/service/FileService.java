package com.graduation.blog.service;

import com.graduation.blog.domain.dto.FileUploadResponseDTO;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.graduation.blog.domain.FileInfo;

public interface FileService {

  /**
   * 将文件对象信息保存到数据库 对象中除了ID都必需赋值
   */
  void insertFileInfo(FileInfo bean);

  /**
   * 将文件对象列表信息保存到数据库 对象中除了ID都必需赋值
   */
  void insertFileInfo(List<FileInfo> list);

  /**
   * 根据文件表的文件ID字段获取文件信息
   */
  FileInfo selectByFileId(String fileId);

  /**
   * 获取存放文件根目录
   */
  String getStorageRootDir();

  /**
   * 文书根路径
   */
  String getDocumentDir();

  /**
   * <pre>
   * 根据[创建人],[文件描述],[文件名称]和[文件大小]创建一个完整的文件信息对象
   * 平台来源(platformSource)请自行设置
   * platform 文件来源: 0-WEB,1-小程序,2-后台生成,9- 其他
   * @param srcFileName 原文件名称
   * @param fileSize 文件大小
   * @param remark 文件描述
   * @return FileInfo 完整的文件对象
   */
  FileInfo createNewFileInfo(String srcFileName, String fileSize, String remark);

  /**
   * <pre>
   * 复制和保存文件
   * 参数:文件对象,描述,创建者
   * 响应文件在本系统中保存的对象信息(不包含ID字段)
   * 并将文件对象数据插入数据库
   * @param srcFile 需要copy的文件对象
   * @param remark  文件注释
   * @param platform 文件来源: 0-WEB,1-小程序,2-后台生成,9- 其他,默认2
   * @return
   */
  FileInfo copyAndsaveFile(File srcFile, String remark, String platform);

  /**
   * 根据文件ID逻辑删除文件
   *
   * @param fileId 待删除的文件ID
   */
  boolean logicalDeleteFile(String fileId);

  /**
   * 上传文件service接口
   *
   * @param file 文件
   * @param remark 备注
   */
  FileUploadResponseDTO upload(MultipartFile file, String remark, HttpServletRequest request)
      throws Exception;

  /**
   * 下载统一service接口 * @param fileId
   */
  void download(String fileId, HttpServletResponse response, HttpServletRequest request)
      throws Exception;

  /**
   * 批量上传多个文件
   *
   * @param file 文件
   * @param remark 备注
   */
  List<FileUploadResponseDTO> multipleUpload(MultipartFile[] file, String remark,
      HttpServletRequest request) throws Exception;

  /**
   * 下载授权模板
   */
  void downloadAuthTemplate(HttpServletResponse response, HttpServletRequest request);

  /**
   * 下载批量导入案件模板
   */
  void caseTemplate(HttpServletRequest request, HttpServletResponse response);

  /**
   * 下载仲裁员批量导入模板
   */
  void arbTemplate(HttpServletRequest request, HttpServletResponse response);
}
