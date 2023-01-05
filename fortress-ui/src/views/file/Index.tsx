import { UploadFile, UploadFiles } from "element-plus";
import { Awaitable } from "element-plus/es/utils";
import { defineComponent } from "vue";
import { request } from "@/utils/request";

export interface FileUploadParam {
    uploadId: '',

}

enum UploadMode {
    SIMPLE = '0',
    PEACE = '1'
}

export default defineComponent({
    setup() {
        const doUpload = (uploadFile: UploadFile, uploadFiles: UploadFiles): Awaitable<boolean> => {
            console.log("doUpload", uploadFile, uploadFiles)
            const formData = new FormData()
            if (uploadFile) {
                const fileSize = uploadFile.size ? uploadFile.size : 0
                const mode = fileSize > 1024 * 1024 * 512 ? UploadMode.SIMPLE : UploadMode.PEACE
                formData.set('data', '')
                formData.set('fileName', uploadFile.name)
                formData.set('fileSize', fileSize.toString())
                formData.set('mode', mode)
            }


            return request.post('', formData, {
                withCredentials: true,
                headers: {

                }
            });
        }
        return () => (
            <el-upload
                class="upload-demo"
                action="/api/doc"
                http-request={doUpload}
                withCredentials={true}
                v-slots={{
                    tip: () => (
                        <div class="el-upload__tip">
                            jpg/png files with a size less than 500KB.
                        </div>
                    )
                }}
            >
                <el-button type="primary">上传文件</el-button>
            </el-upload>
        )
    }
})