package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.controller.param.FileUploadParam;
import cn.codebro.fortresssystem.service.IFileService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-29 13:38:23
 */
@RestController
@RequestMapping("/doc")
public class FileController {

    private final IFileService documentService;

    public FileController(IFileService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public Result requireUploadId() {
        return Result.success(documentService.generateUploadId());
    }

    @PostMapping
    public Result upload(FileUploadParam param) {
        System.out.println(param);
        documentService.upload(param);
        return Result.success();
    }
}
