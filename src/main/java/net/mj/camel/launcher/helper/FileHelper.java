package net.mj.camel.launcher.helper;

import java.io.File;
import java.io.FilenameFilter;

public class FileHelper {
	
	/**
	 * 특정 위치에 일치하는 확장자를 갖는 파일들을 찾는다.
	 * 
	 * @param path 파일 찾을 directory
	 * @param extension 찾을 확장자
	 * @return 파일 목록
	 */
	public static String[] findFileList(String path, String extension) {
		
		String[] files = new File(path).list(new FilenameFilter() {
			public boolean accept(File arg0, String arg1) {

				String extension = arg1.substring(arg1.lastIndexOf(".") + 1);
				if (extension.equalsIgnoreCase(extension)) {
					return true;
				}

				return false;
			}
		});

		if (files == null) {
			return null;
		}

		String[] list = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			list[i] = path + files[i];
		}

		return list;
	}

	public static String getOriginPath(String path) {
		if(path != null && path.lastIndexOf("/") < path.length()) {
			return path.substring(0, path.lastIndexOf("/") + 1);
		} else {
			return path;
		}
	}
}
