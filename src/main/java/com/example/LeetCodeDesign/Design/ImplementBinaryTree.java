package com.example.LeetCodeDesign.Design;

public class ImplementBinaryTree {

    BTNode root;

    //create binary tree
    public void createTree(int data) {
        BTNode nodeToAdd = new BTNode(data);
        if(root == null) {
            root = nodeToAdd;
        } else {
            BTNode traversingNode = root;
            traverseAndAddNode(traversingNode, nodeToAdd);
        }
    }

    private void traverseAndAddNode(BTNode traversingNode, BTNode nodeToAdd) {
        if (nodeToAdd.data < traversingNode.data) {
            if (traversingNode.left == null) {
                traversingNode.left = nodeToAdd;
                nodeToAdd.parent = traversingNode;
            } else {
                traverseAndAddNode(traversingNode.left, nodeToAdd);
            }
        } else {
            if (traversingNode.right == null) {
                traversingNode.right = nodeToAdd;
                nodeToAdd.parent = traversingNode;
            } else {
                traverseAndAddNode(traversingNode.right, nodeToAdd);
            }
        }
    }



    //traversal - preOrder, inOrder, postOrder
    //preOrder = root, left, right
    //inOrder = left, root, right
    //postOrder = left, right, root

    public void preOrderTraversal(BTNode node) {

        System.out.println(node.data);
        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        if (node.right != null) {
            preOrderTraversal(node.right);
        }

    }

    public void InOrderTraversal(BTNode node) {

        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        System.out.println(node.data);
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(BTNode node) {

        if (node.left != null) {
            preOrderTraversal(node.left);
        }
        if (node.right != null) {
            preOrderTraversal(node.right);
        }
        System.out.println(node.data);

    }

    //delete node from tree
    //Case 1: delete leaf node
    //Case 2: delete node with one child node
    //Case 3: delete node with both child node

    public void delete(int data) {
        BTNode nodeToDelete = findNodeToBeDeleted(data);
        if (nodeToDelete!=null) {
            //case1
            if (nodeToDelete.left == null && nodeToDelete.right == null) {
                deleteCase1(nodeToDelete);
            }
            //case 2
            else if (nodeToDelete.left == null) {
                deleteCase2(nodeToDelete);
            }
            else if (nodeToDelete.right == null) {
                deleteCase2(nodeToDelete);
            }
            //case 3
            if(nodeToDelete.left != null && nodeToDelete.right != null) {
                if (nodeToDelete.right != null) {
                    BTNode minNode = minLeftTraversal(nodeToDelete.right);
                    deleteCase2(minNode);
                    minNode.parent = nodeToDelete.parent;
                    minNode.left = nodeToDelete.left;
                    minNode.right = nodeToDelete.right;

                    if (nodeToDelete.parent.left == nodeToDelete) {
                        nodeToDelete.parent.left = minNode;
                    } else if (nodeToDelete.parent.right == nodeToDelete) {
                        nodeToDelete.parent.right = minNode;
                    }
                }
            }
        }

    }

    private static void deleteCase2(BTNode nodeToDelete) {
        if (nodeToDelete.parent.left != nodeToDelete) {
            if (nodeToDelete.left!=null) {
                nodeToDelete.parent.left = nodeToDelete.left;
            }
            if (nodeToDelete.right!=null) {
                nodeToDelete.parent.left = nodeToDelete.right;
            }
        }
        if (nodeToDelete.parent.right != nodeToDelete) {
            if (nodeToDelete.right!=null) {
                nodeToDelete.parent.right = nodeToDelete.right;
            }
            if (nodeToDelete.left!=null) {
                nodeToDelete.parent.right = nodeToDelete.left;
            }
        }
    }

    private static void deleteCase1(BTNode nodeToDelete) {
        if (nodeToDelete.parent.left== nodeToDelete) {
            nodeToDelete.parent.left = null;
        } else {
            nodeToDelete.parent.right = null;
        }
    }

    private BTNode minLeftTraversal(BTNode nodeToDelete) {
        if(nodeToDelete.left == null) {
            return nodeToDelete;
        }
        return minLeftTraversal(nodeToDelete.left);
    }

    private BTNode findNodeToBeDeleted(int data) {
        if (root != null) {
            return find(root, new BTNode(data));
        }
        return null;
    }

    private BTNode find(BTNode searchNode, BTNode nodeToDelete) {
        if (searchNode == null) {
            return null;
        }
        if (searchNode.data == nodeToDelete.data) {
            return nodeToDelete;
        }
        else {
            BTNode returnNode = find(searchNode.left, nodeToDelete);
            if (returnNode == null) {
                returnNode = find(searchNode.right, nodeToDelete);
            }
            return returnNode;
        }
    }

}

class BTNode {
    int data;
    BTNode left;
    BTNode right;

    BTNode parent;

    BTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

