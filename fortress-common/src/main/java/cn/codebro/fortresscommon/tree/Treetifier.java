package cn.codebro.fortresscommon.tree;

import java.util.*;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-26 20:28:43
 */
public class Treetifier {

    /**
     * 将非树形的列表转换成树形结构
     *
     * @param nodeList 非树形列表
     * @param <T>      转换的类型，需要实现Treetify接口
     * @return 树形数据列表
     * @see Treetify
     */
    public static <T extends Treetify<K, T>, K> List<Treetify<K, T>> listToTree(List<T> nodeList) {
        Map<Object, Treetify<K, T>> map = new LinkedHashMap<>();
        nodeList.forEach(node -> {
            map.put(node.getId(), node);
        });
        Set<Map.Entry<Object, Treetify<K, T>>> entries = map.entrySet();
        for (Map.Entry<Object, Treetify<K, T>> entry : entries) {
            Treetify<K, T> value = entry.getValue();
            Object parentId = value.getParent();
            if (parentId != null) {
                Treetify<K, T> parent = map.get(parentId);
                if (parent != null) {
                    parent.addChildren(value);
                }
            }
        }
        List<Treetify<K, T>> result = new ArrayList<>();
        entries.forEach(entry -> {
            Object parent = entry.getValue().getParent();
            if (parent == null || ("".equals(parent))) {
                result.add(entry.getValue());
            }
        });
        return result;
    }

}
