package com.yp.tree;

/*
** n个节点的二叉树链表中含有n+1【公式 2n-(n-1)=n+1 】个空指针域 **
** 理由：n个节点有2n个指针域，n个节点有n-1条边，每个节点各占一个空指针域 **

前序遍历：
  1. 先输出当前节点
  2. 如果左子节点不为空，则递归继续前序遍历
  2. 如果右子节点不为空，则递归前序遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new HeroNode(1, "张三");
        tree.root.left = new HeroNode(2, "李四");
        tree.root.right = new HeroNode(3, "赵五");
        tree.root.left.right = new HeroNode(4, "林冲");

//        System.out.println("树的最大深度：" + tree.maxDepth());
//        tree.del(4);
//        System.out.println("删除节点4之后的最大深度：" + tree.maxDepth());

    }
}


// 二叉树
class BinaryTree {
    HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void del(int no) {
        if (this.root != null) {
            if (this.root.no == no) {
                root = null;
            } else {
                root.del(no);
            }
        }
    }

    /*
    查找树的最大深度
     */
    public int maxDepth() {
        return root.maxDepth();
    }

    /*
    前序查找
     */
    HeroNode preSearch(int no) {
        if (root != null) {
            return this.root.preSearch(no);
        }
        return null;
    }

    /*
    中序查找
     */
    HeroNode midSearch(int no) {
        if (root != null) {
            return root.midSearch(no);
        }
        return null;
    }

    /*
    后序查找
     */
    HeroNode postSearch(int no) {
        if (root != null) {
            return root.postSearch(no);
        }
        return null;
    }

    void pre() {
        if (root != null) {
            root.preOrder();
        }
    }

    void mid() {
        if (root != null) {
            root.infixOrder();
        }
    }

    void post() {
        if (root != null) {
            root.postOrder();
        }
    }
}

class HeroNode {
    int no;
    String name;
    HeroNode left;
    HeroNode right;


    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /*
    删除节点
    规定：如果删除的节点是叶子节点，则删除该节点
         如果是非叶子节点，则删除该子树
     */
    public void del(int no) {

        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return;
            }
        }
        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
                return;
            }
        }

        if (this.left != null) {
            this.left.del(no);
        }
        if (this.right != null) {
            this.right.del(no);
        }
    }


    /*
    前序查找
     */
    public HeroNode preSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode node = null;

        // 注意：当root左边有数据时，递归回溯到2，虽然right有值，但是return了，不会向下执行，所以不可以直接在 if (this.left != null){}中return
        if (this.left != null) {
            node = this.left.preSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            return this.right.preSearch(no);
        }
        return null;
    }

    /*
    中序查找
     */
    public HeroNode midSearch(int no) {
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.midSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            return this.right.midSearch(no);
        }
        return null;
    }


    /*
    后序查找
     */
    public HeroNode postSearch(int no) {
        HeroNode node = null;

        if (this.left != null) {
            node = this.left.postSearch(no);
        }
        if (node != null) {
            return node;
        }
        if (this.right != null) {
            return this.right.postSearch(no);
        }
        if (this.no == no) {
            return this;
        }
        return null;
    }


    /*
        前序遍历
         */
    public void preOrder() {
        System.out.print(this + "\t");
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /*
    中序遍历
     */
    public void infixOrder() {

        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this + "\t");

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /*
    后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this + "\t");
    }


    /*
   得到树的最大深度
    */
    int maxDepth() {
        return this.maxDepth(this);
    }

    int maxDepth(HeroNode node) {
        if (node == null) {
            return 0;
        }
        // 表示左子树的最大深度
        int maxL = 0;
        // 表示右子树的最大深度
        int maxR = 0;
        // 表示该节点的最大深度
        int max = 0;

        if (node.left != null) {
            maxL = node.left.maxDepth();
        }
        if (node.right != null) {
            maxR = node.right.maxDepth();
        }
        return max = maxL > maxR ? maxL + 1 : maxR + 1;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
