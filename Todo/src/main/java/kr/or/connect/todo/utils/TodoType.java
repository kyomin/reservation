package kr.or.connect.todo.utils;

/** 
 * 		 todo 타입을 위한 열거형
 */
public enum TodoType {
	//상수("연관시킬 문자")
    TODO("TODO"), DOING("DOING"), DONE("DONE");
     
    final private String type;
     
    public String getType() {
        return type;
    }
 
    private TodoType(String type){
        this.type = type;
    }
}
