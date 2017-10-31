package ecrans;

    import java.awt.GridLayout;
    import java.awt.Toolkit;
    import java.io.File;
import java.util.Vector;

import javax.swing.JOptionPane;
    import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTree;
	import javax.swing.tree.DefaultMutableTreeNode;
	import javax.swing.tree.DefaultTreeModel;
	import javax.swing.tree.MutableTreeNode;
	import javax.swing.tree.TreePath;
	import javax.swing.tree.TreeSelectionModel;
	import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

  public class ArbreDynamique extends JPanel {
	    public static final long serialVersionUID = 1L;
		
	    public static DefaultMutableTreeNode racinedepot;
	    public static DefaultTreeModel treeModel;
	    public static JTree tree;
	    public static String laracine =(new File(System.getenv("WScesscrea"))).getName();
	    public static Toolkit toolkit = Toolkit.getDefaultToolkit();

	     public ArbreDynamique() {
			
	        super(new GridLayout(1,0));
	        
	       racinedepot = new DefaultMutableTreeNode(laracine);
	        treeModel = new DefaultTreeModel(racinedepot);
		treeModel.addTreeModelListener(new MyTreeModelListener());
	        tree = new JTree(treeModel);
	        tree.setEditable(true);
	        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	        tree.setShowsRootHandles(true);

	        JScrollPane scrollPane = new JScrollPane(tree);
	        add(scrollPane);
	    }

	    /** Remove all nodes except the root node. */
	    public void clear() {
	    	racinedepot.removeAllChildren();
	        treeModel.reload();
	    }
public static Vector<String> getselectionevcc(){
	Vector<String> resevcc= new Vector<String>();
	String resdate="";
	String rescode="";
	String resnomdepot="";
 TreePath selectioncourante =	tree.getSelectionPath();
 if (selectioncourante != null) {
     DefaultMutableTreeNode   noeudcourant =  (DefaultMutableTreeNode) (selectioncourante.getLastPathComponent());
                        resdate =  noeudcourant.toString();
                        resnomdepot = noeudcourant.getRoot().toString();
          MutableTreeNode parent = (MutableTreeNode)(noeudcourant.getParent());
        	            if (parent != null) {  rescode =  parent.toString();    }                   
    resevcc.add(resnomdepot); 
    resevcc.add(rescode);
    resevcc.add(resdate);
 } 
 else{JOptionPane.showMessageDialog(FenetreTraitement.fentemp,"evcc introuvable");}
	return resevcc;
}
	    /** Remove the currently selected node. */
	    public void removeCurrentNode() {
	        TreePath currentSelection = tree.getSelectionPath();
	        if (currentSelection != null) {
	            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
	                         (currentSelection.getLastPathComponent());
	            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
	            if (parent != null) {
	                treeModel.removeNodeFromParent(currentNode);
	                return;
	            }
	        } 

	        // Either there was no selection, or the root was selected.
	        toolkit.beep();
	    }

	    /** Add child to the currently selected node. */
	    public DefaultMutableTreeNode addObject(Object child) {
	        DefaultMutableTreeNode parentNode = null;
	        TreePath parentPath = tree.getSelectionPath();

	        if (parentPath == null) {
	            parentNode = racinedepot;
	        } else {
	            parentNode = (DefaultMutableTreeNode)
	                         (parentPath.getLastPathComponent());
	        }

	        return addObject(parentNode, child, true);
	    }

	    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
	                                            Object child) {
	        return addObject(parent, child, false);
	    }

	    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
	                                            Object child, 
	                                            boolean shouldBeVisible) {
	        DefaultMutableTreeNode childNode = 
	                new DefaultMutableTreeNode(child);

	        if (parent == null) {
	            parent = racinedepot;
	        }
		
		//It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
	        treeModel.insertNodeInto(childNode, parent, 
	                                 parent.getChildCount());

	        //Make sure the user can see the lovely new node.
	        if (shouldBeVisible) {
	            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
	        }
	        return childNode;
	    }

	    class MyTreeModelListener implements TreeModelListener {
	        public void treeNodesChanged(TreeModelEvent e) {
	            DefaultMutableTreeNode node;
	            node = (DefaultMutableTreeNode)(e.getTreePath().getLastPathComponent());

	            /*
	             * If the event lists children, then the changed
	             * node is the child of the node we've already
	             * gotten.  Otherwise, the changed node and the
	             * specified node are the same.
	             */

	                int index = e.getChildIndices()[0];
	                node = (DefaultMutableTreeNode)(node.getChildAt(index));

	            System.out.println("The user has finished editing the node.");
	            System.out.println("New value: " + node.getUserObject());
	        }
	        public void treeNodesInserted(TreeModelEvent e) {
	        }
	        public void treeNodesRemoved(TreeModelEvent e) {
	        }
	        public void treeStructureChanged(TreeModelEvent e) {
	        }
	    }
	}


