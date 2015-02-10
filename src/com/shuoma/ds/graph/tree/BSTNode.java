package com.shuoma.ds.graph.tree;

import com.shuoma.ds.graph.Node;

/**
 * Created by solma on 1/26/15.
 */
public class BSTNode {
    public String id;
    public double value;
    public BSTNode left;
    public BSTNode right;
    BSTNode next;

    public BSTNode(Node node) {
        id = node.id;
        value = node.value;
    }

    public BSTNode(BSTNode copy) {
        this.id = copy.id;
        this.value = copy.value;
        this.left = copy.left;
        this.right = copy.right;
    }

    /**
     * @param key
     * @param value
     */
    public BSTNode(String key, int value) {
        this.id = key;
        this.value = value;
        left = null;
        right = null;
        next = null;
    }

    public BSTNode(String key) {
        this(key, Integer.parseInt(key));
    }

    @Override
    public String toString() {
        return "(" + id + "," + value + ")";
    }
}
