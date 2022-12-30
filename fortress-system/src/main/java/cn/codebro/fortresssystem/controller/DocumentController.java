package cn.codebro.fortresssystem.controller;

import cn.codebro.fortresscommon.Result;
import cn.codebro.fortresssystem.controller.param.FileUploadParam;
import cn.codebro.fortresssystem.service.IDocumentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-12-29 13:38:23
 */
@RestController
@RequestMapping("/doc")
public class DocumentController {

    private final IDocumentService documentService;

    public DocumentController(IDocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public Result upload(@RequestBody FileUploadParam param) {
        documentService.upload(param);
        return Result.success();
    }
}
