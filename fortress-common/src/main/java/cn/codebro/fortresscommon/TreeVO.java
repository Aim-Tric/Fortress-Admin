package cn.codebro.fortresscommon;

import java.util.*;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-26 20:28:43
 */
public class TreeVO<T extends Treetify<?>> {
    private final T record;
    private final List<TreeVO<T>> children;
    private boolean hasChildren;

    private TreeVO(T record) {
        this.record = record;
        this.children = new ArrayList<>();
        this.hasChildren = false;
    }

    /**
     * 将非树形的列表转换成树形结构
     *
     * @param nodeList 非树形列表
     * @param <T>      转换的类型，需要实现Treetify接口
     * @return 树形数据列表
     * @see cn.codebro.fortresscommon.Treetify
     */
    public static <T extends Treetify<?>> List<TreeVO<T>> listToTree(List<T> nodeList) {
        Map<Object, TreeVO<T>> map = new HashMap<>();
        nodeList.forEach(node -> {
            map.put(node.getId(), new TreeVO<>(node));
        });
        Set<Map.Entry<Object, TreeVO<T>>> entries = map.entrySet();
        for (Map.Entry<Object, TreeVO<T>> entry : entries) {
            TreeVO<T> value = entry.getValue();
            Object parentId = value.record.getParentId();
            if (parentId != null) {
                TreeVO<T> parent = map.get(parentId);
                parent.addChildren(value);
            }
        }
        List<TreeVO<T>> result = new ArrayList<>();
        entries.forEach(entry -> {
            if (!entry.getValue().hasChildren) {
                result.add(entry.getValue());
            }
        });
        return result;
    }

    public T getRecord() {
        return record;
    }

    public List<TreeVO<T>> getChildren() {
        return children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void addChildren(TreeVO<T> children) {
        this.children.add(children);
        hasChildren = true;
    }

}
