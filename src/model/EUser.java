package model;

public enum EUser {
    ADMIN,USER;
   public static EUser findEUserbyStr(String str){
        for (EUser e: values()){
            if(e.toString().equals(str)){
                return e;
            }
        }
        return null;
   }


}
