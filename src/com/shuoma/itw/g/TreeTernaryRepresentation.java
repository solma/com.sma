package com.shuoma.itw.g;

import com.shuoma.ds.tree.BST;
import com.shuoma.ds.tree.BSTNode;

/**
// Given a string of nested ternary operations, such as a?b?c:d:e, it denote the tree like following:
// root is a, a.left is the subtree of b?c:d, and a.right is e
//      a        
//    b   e
//  c   d
// Write code to build the tree based on given string.
 */
public class TreeTernaryRepresentation {
    public static void main(String[] args) {
        BST.BinarySearchTree tree = new BST.BinarySearchTree(fromTernaryString("a?b?:d:e"));
        tree.printPrettyTree();
        tree.printTreeInLevels();
    }
    
    static BSTNode fromTernaryString(String s) {
        return buildTreeRecursively(s, 0, s.length() - 1);
    }
    
    static BSTNode buildTreeRecursively(String str, int s, int e){
        if (s > e) return null;
        BSTNode root = new BSTNode(str.substring(s, s + 1), 1);
        String substring = str.substring(s, e + 1);
        if (substring.contains(":")) {
            int idxOfLastColon = s +substring.lastIndexOf(':');
            System.out.println("s=" + s + "  e=" + e + " idx=" + idxOfLastColon);
            root.left = buildTreeRecursively(str, s + 2, idxOfLastColon - 1);
            root.right = buildTreeRecursively(str, idxOfLastColon + 1, e);
        }
        return root;
    }
}
