package net.mj.camel.launcher;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryLoader {
	private static final Logger log = LoggerFactory.getLogger(LibraryLoader.class);
	/**
	 * 사용자 정의 library를 classloader에 추가한다.
	 * @throws Exception
	 */
	public static void load(String directory) throws Exception {
		
		final URLClassLoader loader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		final Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
		method.setAccessible(true);
		
		new File(directory).listFiles(new FileFilter() {
			public boolean accept(File jar) { 
				// jar 파일인 경우만 로딩
				if (jar.toString().toLowerCase().contains(".jar")) {
					try { 
						// URLClassLoader.addURL(URL url) 메소드 호출
						method.invoke(loader, new Object[] { jar.toURI().toURL() });
						log.info(jar.getAbsolutePath() + " is loaded.");
					} catch (Exception e) {
						log.error(jar.getAbsolutePath() + " can't load.", e);
					}
				}
				return false;
			}
		});
	}
}
