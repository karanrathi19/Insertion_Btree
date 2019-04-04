# B-Tree
B-tree is a self-balancing tree data structure that maintains sorted data and allows searches, sequential access, insertions, and deletions in logarithmic time. The B-tree is a generalization of a binary search tree in that a node can have more than two children.[1] Unlike self-balancing binary search trees, the B-tree is well suited for storage systems that read and write relatively large blocks of data, such as discs. It is commonly used in databases and file systems.


## Insertion in B-Tree

All insertions start at a leaf node. To insert a new element, search the tree to find the leaf node where the new element should be added. Insert the new element into that node with the following steps:

      1)If the node contains fewer than the maximum allowed number of elements, then there is room for the new element. Insert the new            element in the node, keeping the node's elements ordered.

      2)Otherwise the node is full, evenly split it into two nodes so:
            1)A single median is chosen from among the leaf's elements and the new element.
            2)Values less than the median are put in the new left node and values greater than the median are put in the new right node,                  with the median acting as a separation value.
            3)The separation value is inserted in the node's parent, which may cause it to be split, and so on. If the node has no parent             (i.e., the node was the root), create a new root above this node (increasing the height of the tree).
If the splitting goes all the way up to the root, it creates a new root with a single separator value and two children, which is why the lower bound on the size of internal nodes does not apply to the root. The maximum number of elements per node is U−1. When a node is split, one element moves to the parent, but one element is added. So, it must be possible to divide the maximum number U−1 of elements into two legal nodes. If this number is odd, then U=2L and one of the new nodes contains (U−2)/2 = L−1 elements, and hence is a legal node, and the other contains one more element, and hence it is legal too. If U−1 is even, then U=2L−1, so there are 2L−2 elements in the node. Half of this number is L−1, which is the minimum number of elements allowed per node.
