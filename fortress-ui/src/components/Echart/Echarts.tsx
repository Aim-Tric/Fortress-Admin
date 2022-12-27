import { defineComponent, onMounted, ref, PropType, unref, onUnmounted, nextTick } from 'vue';
import type { EChartsOption } from 'echarts';
import * as echarts from 'echarts'

export enum RenderType {
    SVGRenderer = 'SVGRenderer',
    CanvasRenderer = 'SVGRenderer'
}

export enum ThemeType {
    Light = 'light',
    Dark = 'dark',
    Default = 'default'
}

export default defineComponent({
    props: {
        option: {
            type: Object as PropType<EChartsOption>,
            required: false
        },
        theme: {
            type: String,
            required: false,
            default: ThemeType.Default
        },
        autoChartSize: {
            type: Boolean,
            required: false,
            default: false
        },
        animation: {
            type: Boolean,
            required: false,
            default: false
        }
    },
    setup(props, context) {
        const elRef = ref<HTMLElement | null>()
        let chartInstance: echarts.ECharts | null = null;

        const initCharts = () => {
            const el = unref(elRef)
            if (!el || !unref(el))
                return
            chartInstance = echarts.init(el, props.theme);
        }

        const setOption = (option: EChartsOption) => {
            nextTick(() => {
                if (!chartInstance) {
                    initCharts()
                    if (!chartInstance)
                        return
                }

                chartInstance.setOption(option)
                hideLoading()
            })

        }

        function getInstance(): echarts.ECharts | null {
            if (!chartInstance) {
                initCharts()
            }
            return chartInstance
        }

        function resize() {
            chartInstance?.resize()
        }

        function watchEl() {
            if (!elRef.value)
                return
            if (props.animation)
                elRef.value.style.transition = 'width 1s, height 1s'
            const resizeObserver = new ResizeObserver(() => resize())
            resizeObserver.observe(elRef.value)
        }

        function showLoading() {
            if (!chartInstance)
                initCharts()
            chartInstance?.showLoading()
        }

        function hideLoading() {
            if (!chartInstance) {
                initCharts();
            }
            chartInstance?.hideLoading()
        }

        onMounted(() => {
            window.addEventListener('resize', resize)
            if (props.autoChartSize) {
                watchEl()
            }
        })

        onUnmounted(() => {
            window.removeEventListener('resize', resize)
        })

        context.expose({
            setOption,
            getInstance,
            showLoading,
            hideLoading
        })

        return () => (
            <div ref={elRef}></div>
        )
    }
})
