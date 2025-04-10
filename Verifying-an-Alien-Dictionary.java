class Solution {
    HashMap<Character, Integer> map;
    public boolean isAlienSorted(String[] words, String order) {
      if(words==null || words.length==0){
        return true;
      }  
      map = new HashMap<>();
      for(int i=0;i<order.length();i++){
        char c = order.charAt(i);
        map.put(c,i);
      }
      for(int i=0;i<words.length-1;i++){
        boolean answer = checkLexicographical(words[i],words[i+1]);
        if(answer == false){
            return false;
        }
      }
      return true;
    }
    private boolean checkLexicographical(String one, String two){
        int m = one.length();
        int n = two.length();
        for(int i=0;i<m && i< n;i++){
            char firstChar = one.charAt(i);
            char secondChar = two.charAt(i);
            if(map.get(firstChar) != map.get(secondChar)){
                return map.get(firstChar) < map.get(secondChar);
            }
        }
        return m<=n;
    }
}