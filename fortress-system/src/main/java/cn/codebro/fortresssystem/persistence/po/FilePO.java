package cn.codebro.fortresssystem.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-01-03 13:15:57
 */
@TableName("f_file")
public class FilePO {
    @TableId
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
