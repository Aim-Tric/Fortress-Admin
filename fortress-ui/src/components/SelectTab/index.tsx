import { defineComponent, ref, PropType } from 'vue';

import styles from './index.module.scss'

export interface SelectTab {
    key: string,
    name: string
}

export default defineComponent({
    props: {
        model: {
            type: Array as PropType<SelectTab[]>,
            default: () => [],
            required: true
        },
        defaultActive: {
            type: String
        }
    },
    emits: ["itemChange"],
    setup(props, { emit }) {
        const active = ref(props.defaultActive || (props.model.length > 0 && props.model[0].key))

        const onItemClick = (item: SelectTab) => {
            active.value = item.key
            emit("itemChange", item)
        }

        return () => (
            <div class={styles.SelectTabWrapper}>
                {
                    props.model.map((item) => (
                        <div class={styles.SelectTabItemWrapper} onClick={() => onItemClick(item)}>
                            <span class={`${styles.SelectTabItem} ${active.value === item.key ? styles.SelectTabItemActive : ''}`}>{item.name}</span>
                        </div>
                    ))
                }
            </div>
        )
    },
})