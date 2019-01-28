package com.graduation.blog.controller;

import com.graduation.blog.domain.Dict;
import com.graduation.blog.domain.dto.responsedto.DictResponseDTO;
import com.graduation.blog.service.DictService;
import com.graduation.blog.utils.BeanConvertUtils;
import com.graduation.blog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 成都 夏川
 * @Date: 2019/1/4
 * @Description:
 **/
@RestController
@RequestMapping("/common/dict")
@Api(value = "公用字典模块", tags = "公用字典模块")
public class DictController {

  @Autowired
  private DictService dictService;


  @GetMapping("/selectAll")
  @ApiOperation(value = "查询所有字典", notes = "查询所有字典")
  public Result<List<DictResponseDTO>> selectByPrimKey() {
    List<Dict> dicts = dictService.listDict();
    List<DictResponseDTO> dictResponseDTOS = BeanConvertUtils
        .copyList(dicts, DictResponseDTO.class);
    return Result.success(dictResponseDTOS);
  }



}
