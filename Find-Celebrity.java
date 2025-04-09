## Problem 2:Find Celebrity(https://leetcode.com/problems/find-the-celebrity/)

In this question we have to find out the celebrity from n people and we are given a condition that 
- all people except celebrity knows the celebrity 
- celebrity does not know the people 
and to find out who knows whom we are given a function knows(a,b) which returns a true is a knows b and flase id a does not know b

so this question can be solved in 2 ways 
1. bruteforce
in this we will maintain and indegrees array so a celebrity will have n-1 indegrees so if knows(a,b) = true so we will do 
indegre[a]-- and indegree[b]++ and at the end if indegree[i]==n-1 then it is the celebrity
Time Complexity : O(n2)
Space Complexity : O(n)
public class Solution extends Relation {

     public int findCelebrity(int n) {

        if(n==0){
            return -1;
        }
        int[] indegree = new int[n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i!=j){
                    if(knows(i,j)){
                        indegree[i]--;
                        indegree[j]++;
                    }
                }
            }
            
        }
        for(int i=0;i<n;i++){
            if(indegree[i]==n-1){
                return i;
            }
        }
        return -1;

     }


}

//Optimal Solution
In this what we will do is we will try to eliminate the people who are not the celebrities and find the potential celebrity and then we will check if he is the only celeberity
We will do this using 2 loops
1. will eliminate all other and find a potential celebrity 
2. We will make sure he is the celebrity

Time Complexity : O(n)
Space Complexity : O(1)

public class Solution extends Relation {

     public int findCelebrity(int n) {

        if(n==0){
            return -1;
        }
        int potential = 0;
        //this will help us to find the potential celebrity
        for(int i=1;i<n;i++){
            if(knows(potential,i)){
                potential = i;
            }
        }

        //we will confirm if he is the celebrity
        for(int i=0;i<n;i++){
            if(i!=potential){
                if(knows(potential,i) || !knows(i,potential)){
                    return -1;
                }
            }
        }

        return potential;

     }
}