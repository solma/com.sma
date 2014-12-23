
public class MatrixOrderOptimization {
    public static void main(String[] args){
        new MatrixOrderOptimization().main();
    }
    
    public void main(){
        int[] p={10, 30, 20, 10, 5, 25, 15};
        matrixChainOrder(p);
        
        StringBuilder expr=new StringBuilder();
        for(int i=0;i<p.length-1;i++){
        	if(i>0) expr.append("*");
        	expr.append(i+1);
        }
        printOptimalParenthesizations(partition, 0, partition.length - 1, expr.toString());
        System.out.println(expr);
    }
    
    protected int[][] cost;
    protected int[][] partition;
    public void matrixChainOrder(int[] p) {
        int n = p.length - 1; //number of matrices
        cost = new int[n][n];
        partition = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            cost[i] = new int[n];
            partition[i] = new int[n];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len+1; i++) {
                int j = i + len-1;
                cost[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j-1; k++) {
                    int q = cost[i][k] + cost[k+1][j] + p[i]*p[k+1]*p[j+1];
                    if (q < cost[i][j]) {
                        cost[i][j] = q;
                        partition[i][j] = k;
                    }
                }
            }
        }
    }
    
    
    void printOptimalParenthesizations(int[][] partition, int i, int j, String expr) {
        if (i != j) {
        	int split=partition[i][j];
        	
        	StringBuilder cpy=new StringBuilder();
        	for(int idx=0;idx<expr.length();idx++){
        		char c=expr.charAt(idx);        	
        		cpy.append(c);
        		if(c ==(split+1+'0')||c==(j+1+'0')) cpy.append(')');
        		if(c ==(split+1+'1')||c==(i+1+'0')) cpy.insert(cpy.length()-1, '(');
        	}
        	expr=cpy.toString();
        	System.out.println("split="+split+" expr="+expr);
        	
            printOptimalParenthesizations(partition, i, split, expr);
            printOptimalParenthesizations(partition, split + 1, j, expr);
        }
    }
}