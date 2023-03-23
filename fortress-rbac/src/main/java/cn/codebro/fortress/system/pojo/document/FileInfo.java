package cn.codebro.fortress.system.pojo.document;

import cn.codebro.fortress.common.model.BaseEntity;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-28 22:01:10
 */
public class FileInfo extends BaseEntity {
    private final String id;
    private final String uploadId;
    private final String fileIdentity;
    private final String fileName;
    private final FileType fileType;
    private final SaveType saveType;
    private final Long fileSize;
    private final Integer chunkCount;
    private final Long chunkSize;

    public FileInfo(String id, String uploadId, String fileIdentity, String fileName, Integer saveType, Long fileSize, Integer chunkCount, Long chunkSize) {
        this.id = id;
        this.fileIdentity = fileIdentity;
        this.fileName = fileName;
        this.saveType = SaveType.valueOf(saveType);
        this.fileSize = fileSize;
        this.chunkCount = chunkCount;
        this.chunkSize = chunkSize;
        this.uploadId = uploadId;
        this.fileType = judgeFileType();
    }

    public static FileInfo create(String uploadId, String fileIdentity, String fileName, Integer saveType, Long fileSize, Integer chunkCount, Long chunkSize) {
        return new FileInfo(IdUtil.fastSimpleUUID(), uploadId, fileIdentity, fileName, saveType, fileSize, chunkCount, chunkSize);
    }

    private static final String[] IMAGE_SUFFIX = {"bmp", "jpg", "png", "tif", "gif", "pcx", "tga", "exif", "fpx",
            "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp", "avif", "apng"};
    private static final String[] DOC_SUFFIX = {"doc", "docx", "xls", "xlsx", "ppt", "pptx"};
    private static final String[] CODE_SUFFIX = {"js", "jsx", "ts", "tsx", "css", "sass", "scss", "html", "htm",
            "md", "java", "kt", "class", "c", "o", "h", "cs", "cpp", "py", "go", "php"};

    private FileType judgeFileType() {
        String fileSuffix = getFileSuffix();
        if (ArrayUtil.contains(IMAGE_SUFFIX, fileSuffix)) {
            return FileType.IMAGE;
        }
        if (ArrayUtil.contains(DOC_SUFFIX, fileSuffix)) {
            return FileType.OFFICE_DOCUMENT;
        }
        if (ArrayUtil.contains(CODE_SUFFIX, fileSuffix)) {
            return FileType.CODE;
        }
        return FileType.TEXT;
    }

    public String getFileSuffix() {
        return fileName.substring(fileName.indexOf('.') + 1);
    }

    public boolean isSingleFileMode() {
        return chunkCount == 1;
    }

    public boolean isLocalStore() {
        return saveType.equals(SaveType.LOCAL);
    }

    public String getId() {
        return id;
    }

    public String getUploadId() {
        return uploadId;
    }

    public String getFileIdentity() {
        return fileIdentity;
    }

    public String getFileName() {
        return fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public SaveType getSaveType() {
        return saveType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public Integer getChunkCount() {
        return chunkCount;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

}
