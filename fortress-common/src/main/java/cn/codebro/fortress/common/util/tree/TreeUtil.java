package cn.codebro.fortress.common.util.tree;

import java.util.*;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-26 20:28:43
 */
public class TreeUtil {

    /**
     * 将非树形的列表转换成树形结构
     *
     * @param nodeList 非树形列表
     * @param <T>      转换的类型，需要实现Treetify接口
     * @return 树形数据列表
     * @see Treeable
     */
    public static <T extends Treeable<K, T>, K> List<Treeable<K, T>> listToTree(List<T> nodeList) {
        Map<Object, Treeable<K, T>> map = new LinkedHashMap<>();
        nodeList.forEach(node -> {
            map.put(node.getId(), node);
        });
        Set<Map.Entry<Object, Treeable<K, T>>> entries = map.entrySet();
        for (Map.Entry<Object, Treeable<K, T>> entry : entries) {
            Treeable<K, T> value = entry.getValue();
            Object parentId = value.getParent();
            if (parentId != null) {
                Treeable<K, T> parent = map.get(parentId);
                if (parent != null) {
                    parent.addChildren(value);
                }
            }
        }
        List<Treeable<K, T>> result = new ArrayList<>();
        entries.forEach(entry -> {
            Object parent = entry.getValue().getParent();
            if (parent == null || ("".equals(parent))) {
                result.add(entry.getValue());
            }
        });
        return result;
    }

}
