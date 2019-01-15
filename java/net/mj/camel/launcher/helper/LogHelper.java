package net.mj.camel.launcher.helper;

public class LogHelper {
	
	private StringBuilder builder = new StringBuilder();
	
	/**
	 * StringBuilder에 입력한 모든 String을 append한다.	 
	 *  
	 * @param builder
	 * @param strings append할 String 리스트
	 */
	public void appendLog(String... strings) {
		for (String str : strings) {
			builder.append(str);
		}
	}
	
	public String toString() {
		String data = builder.toString();
		
		return data;
	}
}
