import { defineComponent, ref } from "vue"
import type { PropType } from "vue"

export abstract class Button {
    name: string
    type: string
    constructor(name: string, type: string) {
        this.name = name
        this.type = type
    }
    abstract onClick(): void;
}

const CrudToolBar = defineComponent({
    props: {
        searchable: {
            type: Boolean as PropType<boolean>,
            required: false,
            default: true,
        },
        buttons: {
            type: Array as PropType<Array<Button>>,
            required: false,
            default: () => []
        },
        searchPlaceholder: {
            type: String as PropType<string>,
            required: false,
            default: '请输入搜索条件'
        },
        searchIconName: {
            type: String as PropType<string>,
            required: false,
            default: 'Search'
        }
    },
    setup(props, { emit }) {
        const searchKey = ref<string>('')
        return () => (
            <el-row style={{ marginBottom: '10px' }} justify="space-between">
                {
                    props.buttons.length > 0 && (
                        <el-col span={4}>
                            <el-button-group>
                                {
                                    props.buttons.map((item: Button) => (
                                        <el-button type={item.type} onClick={item.onClick}>{item.name}</el-button>
                                    ))
                                }
                            </el-button-group>
                        </el-col>
                    )
                }
                {
                    props.searchable && (
                        <el-col span={4}>
                            <el-input v-model={searchKey.value} placeholder={props.searchPlaceholder} clearable v-slots={{
                                append: () => (
                                    <el-button icon={props.searchIconName} onClick={() => emit('search', searchKey.value)} />
                                )
                            }} />
                        </el-col>
                    )
                }

            </el-row>
        )
    }
})

export default CrudToolBar