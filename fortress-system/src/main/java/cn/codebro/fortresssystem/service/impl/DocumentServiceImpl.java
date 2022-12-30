package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.exception.IllegalBusinessOperationException;
import cn.codebro.fortresscommon.util.SystemBusinessExceptionUtil;
import cn.codebro.fortresssystem.controller.param.FileUploadParam;
import cn.codebro.fortresssystem.service.IDocumentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-30 14:10:38
 */
@Service
public class DocumentServiceImpl implements IDocumentService {

    @Override
    public boolean upload(FileUploadParam param) {
        // 检查是否为分片上传
        if (isPeaceMode(param)) {
            // 分片上传则按照分片上传的方法处理
            return peaceModeUpload(param);
        }
        // 不是分片上传则直接保存
        return localSave(param);
    }

    private boolean localSave(FileUploadParam param) {

        return false;
    }

    private boolean peaceModeUpload(FileUploadParam param) {
        String uploadId = param.getUploadId();
        if (!isExistUpload(uploadId)) {
            createUploadTask(param);
        }
        if (savePeace(param.getData())) {
            markPeaceSuccess(uploadId);
        }
        if (isUploadCompleted(uploadId)) {
            return mergePeace(param);
        }
        return false;
    }

    private boolean mergePeace(FileUploadParam param) {
        return false;
    }

    private boolean isUploadCompleted(String uploadId) {
        return false;
    }

    private void markPeaceSuccess(String uploadId) {

    }

    private boolean savePeace(MultipartFile data) {
        return false;
    }

    private void createUploadTask(FileUploadParam param) {

    }

    private boolean isExistUpload(String uploadId) {
        return false;
    }

    private boolean isPeaceMode(FileUploadParam param) {
        return param.getMode() == 1;
    }

}
