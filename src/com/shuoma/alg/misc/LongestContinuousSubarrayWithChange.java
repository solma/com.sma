import java.util.Arrays;

/*
http://www.mitbbs.com/article_t/JobHunting/32719993.html
	
	一个0和1组成的数组，改变其中一个数（0变1或者1变0），使得改变后数组里连续的0
	或者1的长度最大，返回这个最大长度。要求O(N)。
	比如[1 0 1]，改变0，返回3
	[1 1 0 1 0 0]，改变中间的0或者1，返回4
*/

public class LongestContinuousSubarrayWithChange {

	public static void main(String[] args) {
		System.out.println(maxConsecutive(new int[]{1, 1, 0, 1, 0, 0}));
	}
	
	static int maxConsecutive(int[] ary) {
		   int[] changed   = {0, 0}; 
		   int[] noChange = {0, 0};
		   int maxL = 0;

		   for (int x : ary)  {
		        maxL = Math.max(maxL, Math.max(++changed[x], ++noChange[x]) );
		        changed[1-x] = 1+ noChange[1-x];
		        noChange[1-x] = 0;
		        System.out.println(maxL+" "+Arrays.toString(changed)+" "+Arrays.toString(noChange));
		   }

		   return maxL;
		}
	
	

}
