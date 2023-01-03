package cn.codebro.fortresssystem.service.impl;

import cn.codebro.fortresscommon.util.SystemBusinessExceptionUtil;
import cn.codebro.fortresssystem.config.FileUploadProperties;
import cn.codebro.fortresssystem.controller.param.FileUploadParam;
import cn.codebro.fortresssystem.persistence.mapper.FileMapper;
import cn.codebro.fortresssystem.persistence.po.FilePO;
import cn.codebro.fortresssystem.pojo.document.FileInfo;
import cn.codebro.fortresssystem.service.IFileService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-30 14:10:38
 */
@Service
public class FileServiceImpl implements IFileService {

    private final FileUploadProperties fileUploadProperties;
    private final RedisTemplate<Object, Object> redisTemplate;
    private final FileMapper fileMapper;

    public FileServiceImpl(FileUploadProperties fileUploadProperties, RedisTemplate<Object, Object> redisTemplate, FileMapper mapper) {
        this.fileUploadProperties = fileUploadProperties;
        this.redisTemplate = redisTemplate;
        this.fileMapper = mapper;
    }

    @Override
    public boolean upload(FileUploadParam param) {
        FileInfo fileInfo = FileInfo.create(param.getUploadId(), param.getFileIdentity(), param.getFileName(), param.getSaveType(), param.getFileSize(), param.getChunkCount(), param.getChunkSize());
        Long localStoreMaxSize = fileUploadProperties.getLocalStoreMaxSize();
        if (fileInfo.getFileSize() > localStoreMaxSize) {
            throw SystemBusinessExceptionUtil.dump("文件超过大小限制");
        }
        if (fileInfo.isSingleFileMode()) {
            storeFile(fileInfo, param.getData());
            saveFile(fileInfo);
        } else {
            ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
            String cacheKey = fileUploadProperties.getCacheKey() + fileInfo.getUploadId();
            if (Boolean.FALSE.equals(redisTemplate.hasKey(cacheKey))) {
                valueOperations.set(cacheKey, 0);
            }
            Integer uploadCount = (Integer) valueOperations.get(cacheKey);
            if (uploadCount == null || uploadCount >= fileInfo.getChunkCount()) {
                throw SystemBusinessExceptionUtil.dump("文件上传失败");
            }
            storeFile(fileInfo, param.getData());
            valueOperations.increment(cacheKey);
            uploadCount = (Integer) valueOperations.get(cacheKey);
            if (fileInfo.getChunkCount().equals(uploadCount)) {
                saveFile(fileInfo);
            }
        }
        return true;
    }

    private void storeFile(FileInfo fileInfo, MultipartFile multipartFile) {
        String localPath = fileUploadProperties.getLocalPath();
        File dir = new File(localPath);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw SystemBusinessExceptionUtil.dump("文件上传异常");
            }
        }
        String fileName = fileInfo.getId() + "." + fileInfo.getFileSuffix();
        File dest = new File(dir, fileName);
        try {
            multipartFile.transferTo(dest);
        } catch (Exception e) {
            throw SystemBusinessExceptionUtil.dump("文件上传异常");
        }
    }

    private void saveFile(FileInfo fileInfo) {
        FilePO filePO = new FilePO();
        filePO.setId(fileInfo.getId());
        filePO.setFileIdentity(fileInfo.getFileIdentity());
        filePO.setFileName(fileInfo.getFileName());
        filePO.setFileSize(filePO.getFileSize());
        filePO.setUploadId(filePO.getUploadId());
        filePO.setChunkCount(fileInfo.getChunkCount());
        filePO.setChunkSize(fileInfo.getChunkSize());
        filePO.setSaveType(fileInfo.getSaveType().getValue());
        fileMapper.insert(filePO);
    }
}
