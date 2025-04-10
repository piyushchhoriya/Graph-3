Time Complexity : O(N*k) -> N = no of words and k is the average length
Space Complexity : O(N*k)
In this as we want to find a order and when we comapared 2 strings we found out a relationship that c1 comes before c2 and so on so we can say
that it is a graph problem. Now what we will do is first iterate through all the words and find unique characters and add them in a map
Then we will find out the relationship ones we get the mismatching character in both the strings then we will add them in a map and indegrees array and breaks.
then we will iterate and find out the character with 0 indegree and add it in a queue and carry out BFS. The popped character is stored in StringBuilder

import java.util.*;
class AlienDictionary{
    HashMap<Character,List<Character>> map;
    int[] indegree;
    public String getOrder(String[] str){
        if(str==null || str.length==0){
            return "";
        }
        map = new HashMap<>();
        indegree = new int[26];
        for(int j=0;j<str.length;j++){
            String s = str[j];
            for(int i=0;i<s.length();i++){
                char ch = s.charAt(i);
                map.put(ch,new ArrayList<>());
            }
        }

        for(int i=0;i<str.length-1;i++){
            String str1 = str[i]; 
            String str2 = str[i+1];
            for(int j=0;j<str1.length() && j<str2.length();j++){
                char ch1 = str1.charAt(j);
                char ch2 = str2.charAt(j);
                if(ch1 != ch2){
                    map.get(ch1).add(ch2);
                    indegree[ch2-'a']++;
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++){
            if(map.containsKey((char)('a'+i)) && indegree[i]==0){
                q.add((char)('a'+i));
            }
        }
        // System.out.println(q.peek());
        while(!q.isEmpty()){
            char ch = q.poll();
            sb.append(ch);
            List<Character> list = map.get(ch);
            for(int i=0;i<list.size();i++){
                char c = list.get(i);
                indegree[c-'a']--;
                if(indegree[c-'a']==0){
                    q.add(c);
                }
            }
        }
        if(sb.toString().length() < map.size()){
            return "";
        }
        return sb.toString();

        // for(Map.Entry<Character,List<Character>> entry : map.entrySet()){
        //     System.out.println(entry.getKey() +""+ entry.getValue());
        // }
        // for(int i=0;i<26;i++){
        //     if(indegree[i]!=0){
        //         System.out.println(i + " "+indegree[i]);
        //     }
        // }
        // return "";
        
    }

    public static void main(String[] args){
        String[] str = {"wrt","wrf","er","ett","rfttz"};
        // String[] str = {"z","a","z"};
        AlienDictionary ad = new AlienDictionary();
        System.out.println(ad.getOrder(str));
    }
}