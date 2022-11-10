package cn.codebro.fortresscommon.tree;

import java.util.Collection;

/**
 * @author Guo wentao
 * @project fortress
 * @date 2022-10-27 00:15:09
 */
public interface Treetify<K, T> {

    K getId();

    K getParent();

    boolean hasChildren();

    Collection<Treetify<K, T>> getChildren();

    void addChildren(Treetify<K, T> obj);

}
