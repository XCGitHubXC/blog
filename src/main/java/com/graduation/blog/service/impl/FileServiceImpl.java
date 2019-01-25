package com.graduation.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.dao.FileInfoMapper;
import com.graduation.blog.domain.FileInfo;
import com.graduation.blog.domain.dto.responsedto.FileUploadResponseDTO;
import com.graduation.blog.enums.PlatformEnum;
import com.graduation.blog.enums.StatusEnum;
import com.graduation.blog.service.FileService;
import com.graduation.blog.utils.AppException;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.CommonsUtils;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Java8DateUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class FileServiceImpl implements FileService {

  @Autowired
  private FileInfoMapper fileInfoMapper;

  @Value("${file.storage.root.dir}")
  private String storageRootDir;

  @Value("${file.storage.prefix}")
  private String prefix;

  @Value("${file.document.dir}")
  private String docDir;

  /**
   * 获取存放文件根目录
   */
  @Override
  public String getStorageRootDir() {
    return storageRootDir;
  }

  @Override
  public String getDocumentDir() {
    return docDir;
  }

  /**
   * <pre>
   * 获取当前上传文件需要存放的路径 不包含存放文件的根目录 eg:2018/04/27/
   *
   * @return
   */
  public String getCurrentPath() {
    StringBuffer sb = new StringBuffer();
    String year = Java8DateUtil.getCurrentYear();
    String month = Java8DateUtil.getCurrentMonth();
    String day = Java8DateUtil.getCurrentDayOfMonth();
    /* 获取存放文件的根目录 */
    // sb.append(storageRootDir).append(File.separatorChar);
    sb.append(year).append(File.separator);
    sb.append(month).append(File.separator);
    sb.append(day).append(File.separator);
    return sb.toString();
  }

  /**
   * <pre>
   * 传入文件名称, 响应文件类型后缀,包含后缀前面的点 如果文件没有后缀则响应空字符串""
   *
   * @param fileName
   * @return
   */
  private String getFileType(String fileName) {
    Assert.isNotNull(fileName, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.PARAMETER_IS_NULL);
    if (fileName.contains(".")) {
      int lastIndexOf = fileName.lastIndexOf(".");
      return fileName.substring(lastIndexOf);
    }
    return "";
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public FileInfo copyAndsaveFile(File srcFile, String remark, String platform) {
    Assert.isNotNull(srcFile, ErrorCode.EMPTY_FILE);
    Assert.isNotNull(platform, ErrorCode.ILLEGAL_PARAMETER);
    FileInfo bean = createNewFileInfo(srcFile.getName(), srcFile.length() + "", remark);
    try {
      File destFile = new File(storageRootDir + bean.getFilePath());
      CommonsUtils.createDirs(destFile.getParentFile().getPath());
      String newFilePath = getStorageRootDir() + bean.getFilePath();
      FileUtils.copyFile(srcFile, new File(newFilePath));
      bean.setPlatformSource(platform);
      fileInfoMapper.insertSelective(bean);
    } catch (Exception e) {
      bean = null;
      log.error("文件[{}]在复制时发生异常", srcFile.getName(), e);
      throw new AppException(ErrorCode.EMPTY_FILE);
    }
    return bean;
  }

  /**
   * <pre>
   * 根据[创建人],[文件描述],[文件名称]和[文件大小]创建一个完整的文件信息对象 平台来源(platformSource)请自行设置
   *
   * @param srcFileName
   *          原文件名称
   * @param fileSize
   *          文件大小
   * @param remark
   *          文件描述
   * @return FileInfo 完整的文件对象
   */
  @Override
  public FileInfo createNewFileInfo(String srcFileName, String fileSize, String remark) {
    Assert.isNotNull(srcFileName, ErrorCode.FILE_NAME_IS_EMPTY, ValidateMessage.FILE_NAME_IS_EMPTY);
    Assert.isNotNull(fileSize, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.FILE_SIZE_IS_EMPTY);
    FileInfo f = new FileInfo();
    f.setId(CommonsUtils.get32BitUUID());
    f.setOriginFileName(srcFileName);
    f.setFileSize(CommonsUtils.getPrintSize(fileSize));
    /* 文件存放在服务器上重命名后的名称remoteFileName */
    String remoteFileName = f.getId() + getFileType(srcFileName);
    f.setRemoteFileName(remoteFileName);
    /* 文件存放在服务器上的全路径destFilePath */
    String destFilePath = prefix + remoteFileName;
    f.setFilePath(remoteFileName);
    f.setRemark(remark);
    f.setFileUrl(destFilePath);
    return f;
  }

  /**
   * 将文件对象信息保存到数据库
   */
  @Override
  public void insertFileInfo(FileInfo bean) {
    Assert.isNotNull(bean, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.PARAMETER_IS_NULL);
    if (StringUtils.isEmpty(bean.getId())) {
      bean.setId(CommonsUtils.get32BitUUID());
    }
    fileInfoMapper.insertSelective(bean);
  }

  /**
   * 将文件对象信息保存到数据库
   */
  @Override
  public void insertFileInfo(List<FileInfo> list) {
    Assert.isNotNull(list, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.PARAMETER_IS_NULL);
    list.forEach(fileInfo -> {
      if (StringUtils.isEmpty(fileInfo.getId())) {
        fileInfo.setId(CommonsUtils.get32BitUUID());
      }
      fileInfo.setPlatformSource(PlatformEnum.WEB.getCode());
    });
    fileInfoMapper.insertList(list);
  }

  /**
   * 根据文件表的文件ID字段获取文件信息
   */
  @Override
  public FileInfo selectByFileId(String id) {
    Assert.isNotEmpty(id, ErrorCode.ILLEGAL_PARAMETER);
    return fileInfoMapper.selectByPrimaryKey(id);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean logicalDeleteFile(String fileId) {
    Assert.isNotEmpty(fileId, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.PARAMETER_IS_NULL);
    FileInfo fileInfo = this.fileInfoMapper.selectByPrimaryKey(fileId);
    this.deleteFile(fileInfo.getFilePath());
    fileInfo.setStatus(StatusEnum.DELETE.getCode());
    fileInfo.setId(fileId);
    log.info(JSON.toJSONString(fileInfo));
    // 删除文件
    fileInfoMapper.updateByIdSelective(fileInfo);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public FileUploadResponseDTO upload(MultipartFile file, String remark, HttpServletRequest request)
      throws Exception {
    /* 如果文件不为空，写入上传路径 */
    String filename = file.getOriginalFilename();
    String fileSize = String.valueOf(file.getSize());
    FileInfo bean = this.createNewFileInfo(filename, fileSize, remark);
    /* 上传文件名 */
    File filepath = new File(this.getStorageRootDir() + bean.getFilePath());
    /* 判断路径是否存在，如果不存在就创建一个 */
    if (!filepath.getParentFile().exists()) {
      filepath.getParentFile().mkdirs();
    }
    /* 将上传文件保存到一个目标文件当中,在本地测试中,必须之前配置一个文件临时目录和文件实际存放的目录, */
    file.transferTo(filepath);
    bean.setPlatformSource(PlatformEnum.WEB.getCode());
    bean.setFilePath(this.getStorageRootDir() + bean.getFilePath());
    FileUploadResponseDTO fdto = new FileUploadResponseDTO();
    fdto.setFileId(bean.getId());
    fdto.setFileUrl(bean.getFileUrl());
    fdto.setOriginFileName(bean.getOriginFileName());
    fdto.setRemoteFileName(bean.getRemoteFileName());
    this.insertFileInfo(bean);
    return fdto;
  }

  @Override
  public void download(String fileId, HttpServletResponse response, HttpServletRequest request)
      throws Exception {
    FileInfo bean = this.fileInfoMapper.selectByPrimaryKey(fileId);
    Assert.isNotNull(bean, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.FILE_NOT_FOUND);
    File file = new File(bean.getFilePath());
    String filename = bean.getOriginFileName();
    Assert.isTrue(file.exists(), ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.FILE_NOT_FOUND);
    /* 设置强制下载不打开 */
    response.setContentType("application/force-download");
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      String filenameIso = encodeName(request, filename);
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED, e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  @Override
  public void preview(String fileId, HttpServletResponse response, HttpServletRequest request)
      throws Exception {
    FileInfo bean = this.fileInfoMapper.selectByPrimaryKey(fileId);
    Assert.isNotNull(bean, ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.FILE_NOT_FOUND);
    File file = new File(bean.getFilePath());
    String filename = bean.getOriginFileName();
    Assert.isTrue(file.exists(), ErrorCode.ILLEGAL_PARAMETER, ValidateMessage.FILE_NOT_FOUND);
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      String filenameIso = encodeName(request, filename);
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "inline;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED, e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public List<FileUploadResponseDTO> multipleUpload(MultipartFile[] file, String remark,
      HttpServletRequest request)
      throws Exception {
    List<MultipartFile> files = Arrays.asList(file);
    Assert.isListNotEmpty(files, ErrorCode.EMPTY_FILE, ValidateMessage.NOT_FILE_UPLOAD);
    List<FileInfo> fileObjs = new ArrayList<FileInfo>();
    List<FileUploadResponseDTO> fileUploadResponseDTOList = new ArrayList<>();
    for (int i = 0; i < files.size(); ++i) {
      MultipartFile file1 = files.get(i);
      String filename = file1.getOriginalFilename();
      log.info("-----多文件上传,文件名:{}", filename);
      try {
        String fileSize = String.valueOf(file1.getSize());
        FileInfo bean = this.createNewFileInfo(filename, fileSize, remark);
        /* 上传文件名 */
        File filepath = new File(this.getStorageRootDir() + bean.getFilePath());
        /* 判断路径是否存在，如果不存在就创建一个 */
        CommonsUtils.createDirs(filepath.getParentFile().getPath());
        /* 将上传文件保存到一个目标文件当中 */
        file1.transferTo(filepath);
        bean.setPlatformSource(PlatformEnum.WEB.getCode());
        bean.setFilePath(this.getStorageRootDir() + bean.getFilePath());
        fileObjs.add(bean);
        FileUploadResponseDTO fileUploadResponseDTO = new FileUploadResponseDTO();
        fileUploadResponseDTO.setFileId(bean.getId());
        fileUploadResponseDTO.setFileUrl(bean.getFileUrl());
        fileUploadResponseDTO.setRemoteFileName(bean.getRemoteFileName());
        fileUploadResponseDTO.setOriginFileName(bean.getOriginFileName());
        fileUploadResponseDTOList.add(fileUploadResponseDTO);
      } catch (Exception e) {
        String message = "You failed to upload " + i + " => " + e.getMessage();
        throw new AppException(ErrorCode.UNEXCEPTED, message);
      } finally {
      }
    }
    if (fileObjs.size() > 0) {
      this.insertFileInfo(fileObjs);
    }
    return fileUploadResponseDTOList;
  }

  @Override
  public void downloadAuthTemplate(HttpServletResponse response, HttpServletRequest request) {
    /* 设置强制下载不打开 */
    response.setContentType("application/force-download");
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      File file = ResourceUtils.getFile("classpath:tpl_document/tpl_auth.docx");
      String filenameIso = encodeName(request, "授权委托书模板.doc");
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED, e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  @Override
  public void caseTemplate(HttpServletRequest request, HttpServletResponse response) {
    /* 设置强制下载不打开 */
    response.setContentType("application/force-download");
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      File file = ResourceUtils.getFile("classpath:static/multiTemplate.zip");
      String filenameIso = encodeName(request, "批量导入案件模板.zip");
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED, e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  @Override
  public void arbTemplate(HttpServletRequest request, HttpServletResponse response) {

    /* 设置强制下载不打开 */
    response.setContentType("application/force-download");
    byte[] buffer = new byte[1024];
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    try {
      /* 解决中文文件名称的问题 */
      File file = ResourceUtils.getFile("classpath:static/arbTemplate.xlsx");
      String filenameIso = encodeName(request, "仲裁员批量导入模板.xlsx");
      /* 设置文件名 */
      response.addHeader("Content-Disposition", "attachment;fileName=" + filenameIso);
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os = response.getOutputStream();
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
    } catch (Exception e) {
      throw new AppException(ErrorCode.UNEXCEPTED, e.getMessage());
    } finally {
      /* 关闭流 */
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(fis);
    }
  }

  /**
   * 删除本地文件
   */
  private void deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }

  /**
   * 解决各种浏览器下载文件乱码问题
   */
  public String encodeName(HttpServletRequest request, String fileName) {
    //IE9之前包括IE9都包含MSIE;IE10之后都包含Trident;edge浏览器包含Edge
    String filename = "";
    String userAgent = request.getHeader("User-Agent");
    try {
      if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent
          .contains("Edge")) {
        filename = URLEncoder.encode(fileName, "UTF-8");
      } else {
        filename = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
      }
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return filename;
  }

}
