package kr.or.connect.todo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.connect.todo.dto.TodoDto;


/** 
 * 		 todo 데이터 처리를 제공하는 클래스
 */
public class TodoService {
	/** 
	 *		type 별로 분류하여 각각을 위한 리스트에 담아주는 메소드이다.
	 *
	 *		@param		totalList
	 *				분류 대상인 전체 todo 리스트
	 *
	 *		@return		각 타입과 대응되는 리스트가 담긴 해쉬 맵
	 */
	public static HashMap<String, List<TodoDto>> makeTypeList(List<TodoDto> totalList) {
		HashMap<String, List<TodoDto>> resultMap = new HashMap<String, List<TodoDto>>();
		List<TodoDto> typeList = new ArrayList<TodoDto>();
		
		int len = totalList.size();
		
		//	첫 번째 타입의 첫 원소에 대해 처리한다.
		String currentType = totalList.get(0).getType();
		typeList.add(totalList.get(0));
		
		//	첫 원소에 대해 처리했기 때문에, 인덱스는 1부터 시작한다.
		for(int idx = 1; idx < len; idx++) {
			//	이전 타입과 같다면
			if(totalList.get(idx).getType().equals(currentType)) {
				typeList.add(totalList.get(idx));
			} else {	// 이전 타입과 다르다면, 즉 새로운 타입이 시작된다면
				resultMap.put(currentType, typeList);
				
				//	새로운 타입을 담을 컨테이너 선언!
				typeList = new ArrayList<TodoDto>();
				
				typeList.add(totalList.get(idx));
				
				//	타입 바꿔주기
				currentType = totalList.get(idx).getType();
			}
		}
		
		//	마지막 타입에 대한 처리
		resultMap.put(currentType, typeList);
		
		return resultMap;
	}
	
	/** 
	 *		할일 제목이 최대 24자를 넘어가는지 서버에서 체크하기 위한 함수
	 *
	 *		@param		title
	 *				클라이언트 폼으로부터 전달 받은 title
	 *
	 *		@return		true : 24자 이하의 적절한 길이일 경우
	 */
	public static boolean isProperTitleLength(String title) {
		return title.length()<=24 ? true : false;
	}
}
