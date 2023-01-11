import { UploadRequestOptions } from "element-plus";
import { Awaitable } from "element-plus/es/utils";
import { defineComponent, ref } from "vue";
import { request } from "@/utils/request";
import CryptoJS from 'crypto-js'

export interface FileUploadParam {
    uploadId: '',

}

enum UploadMode {
    SIMPLE = '0',
    PEACE = '1'
}
enum SaveType {
    LOCAL = '0',
    OSS = '1'
}

const getUploadId = (): Promise<string> => {
    return request.get('/doc')
}

const FortressUploader = defineComponent({
    setup(props, { slots }) {
        const uploadIdRef = ref<string>('')
        const doUpload = (options: UploadRequestOptions): Awaitable<boolean> => {
            const file = options.file
            const uploadId = uploadIdRef.value
            return new Promise<boolean>((resolve) => {
                const formData = new FormData()
                const fileSize = file.size ? file.size : 0
                const chunkCount = Math.floor(file.size / (1024 * 1024 * 512)) + 1
                const mode = fileSize > (1024 * 1024 * 512) ? UploadMode.SIMPLE : UploadMode.PEACE
                file.text().then((text) => {
                    formData.set('fileIdentity', CryptoJS.MD5(text).toString())
                    formData.set('uploadId', uploadId)
                    formData.set('fileName', file.name)
                    formData.set('fileSize', fileSize.toString())
                    formData.set('mode', mode)
                    formData.set('saveType', SaveType.LOCAL)
                    formData.set('chunkCount', chunkCount.toString())
                    formData.set('data', file)
                    request.post('/doc', formData, {
                        headers: {
                            'content-type': 'multipart/form-data; charset=utf-8'
                        }
                    }).then(() => {
                        resolve(true)
                    }).catch((error) => {
                        console.log("error", error)
                        resolve(false)
                    })
                })
            })
        }

        getUploadId().then((data) => {
            uploadIdRef.value = data
        })

        return () => (
            <el-upload
                class="upload-demo"
                http-request={doUpload}
                withCredentials={true}
                v-slots={{
                    tip: () => (slots.tip && slots.tip()),
                    trigger: () => (slots.trigger && slots.trigger()),
                    file: () => (slots.file && slots.file())
                }}
            >
                {slots.default && slots.default()}
            </el-upload>
        )
    }
})

export default defineComponent({
    components: {
        FortressUploader
    },
    setup() {
        return () => (
            <fortress-uploader v-slots={{
                tip: () => (
                    <div class="el-upload__tip">
                        jpg/png files with a size less than 500KB.
                    </div>
                )
            }}>
                <el-button type="primary">上传文件</el-button>
            </fortress-uploader>
        )
    }
})