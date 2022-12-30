package cn.codebro.fortresssystem.controller.param;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-29 14:31:06
 */
public class FileUploadParam implements Serializable {
    /**
     * 文件唯一性校验码
     */
    private String fileIdentity;
    /**
     * 上传唯一ID
     */
    private String uploadId;
    /**
     * 上传的文件名
     */
    private String fileName;
    /**
     * 文件大小 bytes
     */
    private Long fileSize;
    /**
     * 上传模式; 0 simple, 1 peace
     */
    private Integer mode;
    /**
     * 存储方式; 0 本地, 1 云存储
     */
    private Integer saveType;
    /**
     * 块数量
     */
    private Integer chunkCount;
    /**
     * 块大小 bytes
     */
    private Long chunkSize;
    /**
     * 块序号
     */
    private Integer chunkIndex;
    /**
     * 文件二进制
     */
    private MultipartFile data;

    public String getFileIdentity() {
        return fileIdentity;
    }

    public void setFileIdentity(String fileIdentity) {
        this.fileIdentity = fileIdentity;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getSaveType() {
        return saveType;
    }

    public void setSaveType(Integer saveType) {
        this.saveType = saveType;
    }

    public Integer getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(Integer chunkCount) {
        this.chunkCount = chunkCount;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public MultipartFile getData() {
        return data;
    }

    public void setData(MultipartFile data) {
        this.data = data;
    }
}
