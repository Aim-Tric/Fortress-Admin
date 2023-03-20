package cn.codebro.fortress.common.util.tree;

import java.util.Collection;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-27 00:15:09
 */
public interface Treeable<K, T> {

    K getId();

    K getParent();

    boolean hasChildren();

    Collection<Treeable<K, T>> getChildren();

    void addChildren(Treeable<K, T> obj);

}
