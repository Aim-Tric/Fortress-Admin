package cn.codebro.fortresssystem.controller.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-01-03 03:23:51
 */
public interface FileUploadStrategy<T> {
    T doUpload(MultipartFile file);
}
