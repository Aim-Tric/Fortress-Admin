package cn.codebro.fortress.system.service;

import cn.codebro.fortress.system.controller.param.FileUploadParam;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-30 14:09:31
 */
public interface IFileService {
    String generateUploadId();

    boolean upload(FileUploadParam param);
}
