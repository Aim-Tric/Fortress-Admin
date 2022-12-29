package cn.codebro.fortresssystem.controller.param;

import java.io.Serializable;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-29 14:31:06
 */
public class FileUploadParam implements Serializable {
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
     * 上传模式 0 simple 1 peace
     */
    private Integer mode;
    /**
     * 块数量
     */
    private Integer peaceCount;
    /**
     * 块大小 bytes
     */
    private Long peaceSize;
    /**
     * 块序号
     */
    private Integer peaceIndex;
    /**
     * 文件二进制
     */
    private byte[] data;

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

    public Integer getPeaceCount() {
        return peaceCount;
    }

    public void setPeaceCount(Integer peaceCount) {
        this.peaceCount = peaceCount;
    }

    public Long getPeaceSize() {
        return peaceSize;
    }

    public void setPeaceSize(Long peaceSize) {
        this.peaceSize = peaceSize;
    }

    public Integer getPeaceIndex() {
        return peaceIndex;
    }

    public void setPeaceIndex(Integer peaceIndex) {
        this.peaceIndex = peaceIndex;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
