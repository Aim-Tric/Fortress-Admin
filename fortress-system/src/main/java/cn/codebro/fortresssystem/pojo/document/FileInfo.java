package cn.codebro.fortresssystem.pojo.document;

import cn.codebro.fortresscore.model.Entity;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-28 22:01:10
 */
public class FileInfo extends Entity {
    private String id;
    private String uploadId;
    private String fileIdentity;
    private String fileName;
    private FileType fileType;
    private SaveType saveType;
    private Long fileSize;
    private Integer chunkCount;
    private Integer chunkSize;
    private Integer cloudPlatform;

    public String getFileSuffix() {
        return fileName.substring(fileName.charAt('.') + 1);
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public SaveType getSaveType() {
        return saveType;
    }

    public void setSaveType(SaveType saveType) {
        this.saveType = saveType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getCloudPlatform() {
        return cloudPlatform;
    }

    public void setCloudPlatform(Integer cloudPlatform) {
        this.cloudPlatform = cloudPlatform;
    }
}
