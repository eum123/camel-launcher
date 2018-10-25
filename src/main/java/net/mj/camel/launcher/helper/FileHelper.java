package net.mj.camel.launcher.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;

public class FileHelper {

	/**
	 * 파일 이름을 제외한 Path를 구한다
	 * @param path
	 * @return
	 */
	public static String getOriginPath(String path) {
		if(path != null && path.lastIndexOf("/") < path.length()) {
			return path.substring(0, path.lastIndexOf("/") + 1);
		} else {
			return path;
		}
	}

	/**
	 * 파일 이름을 구한다.
	 * @param path
	 * @return
	 */
	public static String getFilename(String path) {
		return path.substring(path.lastIndexOf("/") + 1);
	}

	/**
	 * 파일 내용 읽기
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static byte[] read(String path) throws Exception {
		File f = new File(path);
		byte[] contents = new byte[(int)f.length()];

		try (FileInputStream reader = new FileInputStream(f)) {
			reader.read(contents);
			return contents;
		}

	}
}
