
import java.util.Arrays;
import java.util.LinkedList;
public class Dfs_1 {
	public static void main(String[] args) {
	System.out.println(Solution.solution("2425"));
	}
}

class Solution {
    static boolean[] checked;
    static int [] primeset;
    static String[] numbers_arr;
    public static int solution(String numbers) {
        primeset=new int[10000000];
        checked=new boolean[10000000];

        for(int i=2;i*2<10000000;++i) {
        	// check 가 되어 있으면 넘어가기
        	// 작은 수의 배수이면 소수가 아니니까 true
            if(!checked[i])
            {
                for(int j=i*2;j<10000000;j+=i) {
                    checked[j]=true;
                }
            }
        }
        // 0과 1은 소수가 아니다
        checked[0]=true;
        checked[1]=true;
        
        numbers_arr=new String[numbers.length()+1];
        for(int i=1;i<=numbers.length();++i){
            numbers_arr[i]=numbers.substring(i-1,i);
        }

        for(int i=1;i<=numbers.length();++i){
            int[] check = new int[numbers.length()+1];
            LinkedList<String> rCom=new LinkedList<>();
            rePermutation(numbers.length(),i,rCom,check);
        }

        long result=Arrays.stream(primeset).filter(x -> x==1).count();
        int answer = (int)result;
        return answer;
    }
    
    private static void
    rePermutation(int n, int r, LinkedList<String> rCom, int[] check) {
        if(rCom.size() == r){
            String result ="";
            for(String i : rCom)
                result+=i;
            int comp=Integer.parseInt(result);
            if(!checked[comp]){
                primeset[comp]=1;
            }
            return;
        }

        for(int i=1; i<=n; i++){
            if(check[i] == 0){
                check[i] = 1;
                rCom.add(numbers_arr[i]);
                rePermutation(n, r, rCom, check);
                check[i] = 0;
                rCom.removeLast();
            }
        }

    }
    }



