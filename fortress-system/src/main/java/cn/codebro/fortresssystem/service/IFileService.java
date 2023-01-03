package cn.codebro.fortresssystem.service;

import cn.codebro.fortresssystem.controller.param.FileUploadParam;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-30 14:09:31
 */
public interface IFileService {

    boolean upload(FileUploadParam param);
}
