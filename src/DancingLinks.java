import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DancingLinks {
	
	class DancingNode {
		private DancingNode left, right, up, down;
		protected ColumnNode col; 
		
		public DancingNode() {
			left = right = up = down = this; //just initialize all references as itself
		}
		
		public DancingNode(ColumnNode c) {
			this(); //neat trick I discovered B-)
			col = c;			
		}
		
		public void addDown(DancingNode n) {
			n.setDown(this.down); //n's down is this down
			n.setUp(this); //n's up is this
			this.down.setUp(n); //this down's up is n
			this.setDown(n); //this down is n
		}
		
		public void addRight(DancingNode n) {
			n.setRight(this.right); //n's right is this right
			n.setLeft(this); //n's left is this
			this.right.setLeft(n); //this right's left is n
			this.setRight(n); //this right is n
		}
		
		public void removeUD() {
			this.up.setDown(this.down);
			this.down.setUp(this.up);
		}
		
		public void addUD() {
			this.up.setDown(this);
			this.down.setUp(this);
		}
		
		public void removeLR() {
			this.left.setRight(this.right);
			this.right.setLeft(this.left);
		}
		
		public void addLR() {
			this.left.setRight(this);
			this.right.setLeft(this);
		}

		public ColumnNode getCol() {
			return col;
		}

		public void setCol(ColumnNode col) {
			this.col = col;
		}

		public DancingNode getLeft() {
			return left;
		}

		public void setLeft(DancingNode left) {
			this.left = left;
		}

		public DancingNode getRight() {
			return right;
		}

		public void setRight(DancingNode right) {
			this.right = right;
		}

		public DancingNode getUp() {
			return up;
		}

		public void setUp(DancingNode up) {
			this.up = up;
		}

		public DancingNode getDown() {
			return down;
		}

		public void setDown(DancingNode down) {
			this.down = down;
		}
	}
	
	class ColumnNode extends DancingNode {
		int size;
		
		public ColumnNode() {
			super();
			size = 0;
			this.col = this;
		}
		
		public void coverCol() {
			removeLR();
			for(DancingNode i = this.getDown(); i != this; i = i.getDown()){
                for(DancingNode j = i.getRight(); j != i; j = j.getRight()){
                    j.removeUD();
                    j.col.size--;
                }
            }
		}
		
		public void uncoverCol() {
			for(DancingNode i = this.getUp(); i != this; i = i.getUp()){
                for(DancingNode j = i.getLeft(); j != i; j = j.getLeft()){
                    j.col.size++;
                    j.addUD();
                }
            }
            addLR();
		}
	}
	
	private ColumnNode header;
    private int solutions = 0;
    private SolutionHandler handler;
    private List<DancingNode> answer;
    
    private void search(int k) {
    	if(header.getRight() == header) { //if solution found
    		solutions++; //add one to possible solutions
    		handler.handleSolution(answer); //the handler handles the solutions
    	}
    	else { //if no solution found
    		ColumnNode cur = selectColumnNode();
    		cur.coverCol();
    		
    		for(DancingNode row = cur.getDown(); row != cur; row = row.getDown() ) {
    			answer.add(row);
    			
    			for(DancingNode index = row.getRight(); index != row; index = index.getRight() ) {
    				index.col.coverCol();
    			}
    			
    			search(k+1);
    			
    			row = answer.remove(answer.size()-1);
    			cur = row.getCol();
    			
    			for(DancingNode index = row.getLeft(); index != row; index = index.getLeft() ) {
    				index.col.uncoverCol();
    			}
    		}
    	}
    }
    
    private ColumnNode selectColumnNode() {
    	int min = Integer.MAX_VALUE;
    	ColumnNode selected = null;
    	
    	for(ColumnNode c = (ColumnNode) header.getRight(); c != header; c = (ColumnNode) c.getRight()){
    		if (c.size < min){
    			min = c.size;
    			selected = c;
    		}
    	}
    }

    private ColumnNode makeBoard( int[][] grid ) {
    	int cols = grid[0].length;
    	int rows = grid.length;
    	
    	ColumnNode headerNode = new ColumnNode();
    	ArrayList<ColumnNode> columnNodes = new ArrayList<ColumnNode>();
    	
    	for(int i = 0; i < cols; i++) {
    		ColumnNode n = new ColumnNode();
    		columnNodes.add(n);
    		headerNode.addRight(n);
    		headerNode = (ColumnNode) headerNode.getRight();
    	}
    	headerNode = headerNode.getRight().getCol();
    	
    	for(int i = 0; i < rows; i++) {
    		DancingNode prev = null;
    		for(int j = 0; j < cols; j++) {
    			if(grid[i][j] == 1) {
    				ColumnNode column = columnNodes.get(j);
    				DancingNode newNode = new DancingNode(column);
    				if(prev == null) {
    					prev = newNode;
    				}
    				column.getUp().addDown(newNode);
    				prev.addRight(newNode);
    				prev = prev.getRight();
    				column.size++;
    			}
    		}
    	}
    	
    	headerNode.size = cols;
    	
    	return headerNode;
    }

    public DancingLinks(int[][] grid, SolutionHandler h){
    	header = makeBoard(grid);
    	handler = h;
    }
    
    public void solver() {
    	solutions = 0;
    	answer = new LinkedList<DancingNode>();
    	search(0);
    }
}
