
import java.util.Scanner;

 class BTrees {
    static int order; 
    BNode root;  
    public BTrees(int order)
    {
        BTrees.order = order;
    root = new BNode(order, null);
    }
    public void split(BNode x, int i, BNode y){
        BNode z = new BNode(order,null);                          
        z.leaf = y.leaf;
        z.count = order - 1;
        for(int j = 0; j < order - 1; j++)
        {   z.key[j] = y.key[j+order];   }
        
                if(!y.leaf)
        {
        for(int k = 0; k < order; k++)
        {   z.child[k] = y.child[k+order];   }
        } 

        y.count = order - 1; 
        for(int j = x.count ; j> i ; j--)
        {    x.child[j+1] = x.child[j];   }
                
        x.child[i+1] = z; 
        for(int j = x.count-1; j>= i; j--)
        {     x.key[j + 1] = x.key[j];       }
                
        x.key[i] = y.key[order-1];
        y.key[order-1 ] = 0; 
        for(int j = 0; j < order - 1; j++)
        {    y.key[j + order] = 0;           }
                
        x.count ++;  
    }
     public void nonfullInsert(BNode x, char key){
        int i = x.count; 
        if(x.leaf){
            while(i >= 1 && key < x.key[i-1])
            {
                x.key[i] = x.key[i-1];
                i--;
            }
            x.key[i] = key;
            x.count ++;
        }
                else {
            int j = 0;
            while(j < x.count  && key > x.key[j])
            {    j++;    }
            if(x.child[j].count == order*2 - 1) {
                split(x,j,x.child[j]);
                if(key > x.key[j])
                {    j++;     }
            }
            nonfullInsert(x.child[j],key);
        }
    }

    public void insert(BTrees t, char key)
    {
        BNode r = t.root;         
        if(r.count == 2*order - 1)
        {
            BNode s = new BNode(order,null);
            t.root = s;       
            s.leaf = false;
            s.count = 0;      
            s.child[0] = r;
            split(s,0,r);
            nonfullInsert(s, key); 
        }
        else
            nonfullInsert(r,key);
    }
    
    public int height(BNode n){
        BNode temp=root;
        int sum1=0,sum=0;
        while(temp.child[0]!=null){   //calculating from left child
            sum++;
            temp=temp.child[0];  
        }
        temp=root;
        while(temp.child[temp.count]!=null){   //calculating from right child
            sum1++;
            temp=temp.child[temp.count];  
        }
        System.out.println("rh:"+(sum1+1));
        System.out.println("lh:"+(sum+1));
        return Math.max(sum,sum1)+1;
    }

    public void print(BNode n){
        for(int i = 0; i < n.count; i++)
        {   
            if(n.child[0]==null)
               System.out.print(" *"+n.getValue(i));  
            else
               System.out.print(n.getValue(i)+" " );    
        
        } 
  
        if(!n.leaf){
            for(int j = 0; j <= n.count  ; j++) {                 
                if(n.getChild(j) != null)
                {            
                System.out.println();     
                print(n.getChild(j));     
                }
            }
        }
        System.out.println("");
    }
}

public class Btree {
    public static void main(String[] args){
        Scanner sc = new Scanner( System.in );
        int o; String temp;
        System.out.print("Enter the min degree of the Tree:  ");
        o=sc.nextInt();
        while (o<2)
        {    
            System.out.print("Order can not be less than 2!");
            o=sc.nextInt();
        }
        BTrees tree = new BTrees(o);
        String arr[]=new String[]{"F","S","Q","K","C","L","H","T","V","W","M","R","N","P","A","B","X","Y","D","Z","E"};
        for (String arr1 : arr) {
            tree.insert(tree, arr1.charAt(0));
        }
            tree.print(tree.root);
         System.out.println("Now Inserting G ");
         tree.insert(tree,'G');
         tree.print(tree.root);
         System.out.println("Now Inserting J ");
         tree.insert(tree,'J');
         tree.print(tree.root);
         System.out.println("Height="+tree.height(tree.root));
    }
}
 class BNode
{
    static int t;  
    int count;   //keys in a node
    char key[];  
    BNode child[]; 
    boolean leaf; 
    BNode parent;  
    public BNode()
    {}
    public BNode(int t, BNode parent){
        BNode.t = t;  
        this.parent = parent; 
        key = new char[2*t - 1];  
        child = new BNode[2*t]; 
        leaf = true; 
        count = 0; 
    }
    public char getValue(int index)
    {   return key[index];   }
    public BNode getChild(int index)
    {   return child[index];  }
}
