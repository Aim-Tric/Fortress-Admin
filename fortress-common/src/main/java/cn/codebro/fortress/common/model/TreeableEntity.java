package cn.codebro.fortress.common.model;

import cn.codebro.fortress.common.util.tree.Treeable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2023-03-21 15:48:36
 */
public abstract class TreeableEntity<K, V> extends BaseEntity implements Treeable<K, V> {
    private Collection<Treeable<K, V>> children = new ArrayList<>();

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public Collection<Treeable<K, V>> getChildren() {
        return children;
    }

    @Override
    public void addChildren(Treeable<K, V> obj) {
        children.add(obj);
    }
}
