package sets;
import java.util.Scanner;
import java.util.Arrays;

public class intSet {

	private int[] set;
	private int size;
	private int count;
	Scanner scan = new Scanner (System.in);
	private intSet(){
		
		System.out.print("Enter number of values in set"+ count +":");
		int n = scan.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter values in set"+ count +":");
		for(int i=0; i<n; i++){
			arr[i] = scan.nextInt();
		}
		
		arrToSet(arr, n);
	}
	
	private intSet(int[] arr){
		
		int n = arr.length;
		arrToSet(arr, n);
		
	}
	
	public void arrToSet(int[] arr, int n){
		Arrays.sort(arr);
		int j=0;
		set=new int[n];
		for (int i=0; i<n-1; i++){
			if(arr[i]!= arr[i+1]){
				set[j++] = arr[i];
			}
		}
		set[j++]=arr[n-1];
		size=j;
	}
	
	public void displaySet(){
		
		System.out.print("{ ");
		for(int i=0; i<size; i++){
			System.out.print(set[i]+ " ");
		}
		System.out.println("}");
		count++;
	}
	
	public boolean isMember (int x){
		boolean isMem = false;
		for(int i=0; i<size; i++){
			if(x == set[i]){
				isMem= true; break;
			}
		}
		return isMem;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isSubSet(int[] s){		
		if(s.length > set.length){
			return false;
		}
		
		if(s[s.length-1] > set[set.length-1]){
			return false;
		}
		
		int subsetIndex=0;
		for(int i=0; i<set.length; i++){
			if(subsetIndex <= set.length){
				if(set[i] == s[subsetIndex]){
					subsetIndex++;
				}
				else if(s[subsetIndex] > set[i]){}
				else{
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public intSet getComplement(){
		final int upperLimit = 1000;
		int[] compSet= new int[upperLimit-set.length+1];
		
		int compSetIndex=0, setIndexCount=0;
		for(int i=1; i<=upperLimit; i++){
			if(setIndexCount < set.length){
				if(isMember(i)){
					setIndexCount++;
				}
				else{
					compSet[compSetIndex]=i;
					compSetIndex++;
				}
			}
			else{
				compSet[compSetIndex]=i;
				compSetIndex++;
			}
		}
		
		intSet complSet = new intSet(compSet);
		return complSet;
	}
	
	
	public intSet union(int[] set1 ,int[] set2){
		int[] set3 = new int[set1.length + set2.length];
		int i=0;
		for(i=0; i<set1.length;i++){
			set3[i] = set1[i];
		}
		for(int j=0; j<set2.length; j++)
			set3[j+i] = set2[j];
		intSet setUn = new intSet(set3);
		return setUn;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		intSet s1 = new intSet();
		s1.displaySet();
		
		System.out.print("Enter number to check if it's member of Set :");
		int x = in.nextInt();
		boolean checkMem = s1.isMember(x);
		System.out.println("Number " +x +" is member of Set or not :"+checkMem);
		
		
		System.out.println("\nSize of Set :"+s1.size()+"\n");
		
		
		System.out.print("Complement of ");
		s1.displaySet();
		System.out.print("is :");
		intSet s4 = s1.getComplement();
		s4.displaySet();
		System.out.println();
		
		
		System.out.println("Enter set to check if is Subset of Main set or not ->");
		intSet s2 = new intSet();
		System.out.println("Is subset : " + s1.isSubSet(s2.set)+"\n");
		
		
		System.out.println("Enter a set for union ->");
		intSet s3 = new intSet();
		System.out.print("Set1 is :");
		s1.displaySet();
		System.out.print("Set2 is :");
		s3.displaySet();
		intSet unionSet = s1.union(s1.set, s3.set);
		System.out.print("Union Set is :");
		unionSet.displaySet();
		
		in.close();
	}

}
