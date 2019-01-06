package com.graduation.blog.controller;

import com.graduation.blog.constants.ValidateMessage;
import com.graduation.blog.domain.dto.FileUploadResponseDTO;
import com.graduation.blog.service.FileService;
import com.graduation.blog.utils.Assert;
import com.graduation.blog.utils.ErrorCode;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @date
 */
@RestController
@Validated
@Slf4j
@Api(value = "公用的文件上传接口", tags = "公用的文件上传接口")
@RequestMapping(value = "/common/fileInfo")
public class FileController {

  @Resource
  private FileService fileService;

  /**
   * 文件上传接口
   *
   * @return Result
   */
  @PostMapping(value = "upload")
  @ApiOperation(value = "单文件上传接口", notes = "单文件上传接口")
  public Result<FileUploadResponseDTO> upload(@ApiParam(value = "文件") MultipartFile file,
      @ApiParam(value = "备注") String remark, HttpServletRequest request)
      throws Exception {
    Assert.isNotNull(file, ErrorCode.EMPTY_FILE, ValidateMessage.NOT_FILE_UPLOAD);
    return Result.success(this.fileService.upload(file, remark, request));
  }

  /**
   * 文件下载
   */
  @GetMapping("download/{fileId}")
  @ApiOperation(value = "文件下载接口", notes = "文件下载接口")
  public void downloadFile(
      @ApiParam(value = "附件id") @Valid @NotEmpty(
          message = ValidateMessage.FILE_NOT_FOUND) @PathVariable("fileId") String fileId,
      HttpServletResponse response, HttpServletRequest request) throws Exception {
    this.fileService.download(fileId, response, request);
  }

  /**
   * 上传文件接口
   *
   * @param file 文件
   * @param remark 备注
   */
  @PostMapping(value = "multipleUpload")
  @ApiOperation(value = "多文件上传", notes = "多文件上传")
  public Result<List<FileUploadResponseDTO>> multipleUpload(
      @ApiParam(value = "文件数组") MultipartFile[] file, @ApiParam(value = "备注") String remark,
      HttpServletRequest request)
      throws Exception {
    return Result.success(this.fileService.multipleUpload(file, remark, request));
  }

  /**
   * 删除附件
   *
   * @param fileId 文件id
   */
  @DeleteMapping("{fileId}")
  @ApiOperation(value = "根据fileId删除附件", notes = "根据fileId删除附件")
  public Result deleteFile(@ApiParam(value = "附件id") @PathVariable("fileId") String fileId) {
    Assert.isNotEmpty(fileId, ErrorCode.ILLEGAL_PARAMETER);
    return Result
        .success(this.fileService.logicalDeleteFile(fileId));
  }





}
