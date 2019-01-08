package net.mj.camel.launcher.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.springframework.util.Assert;

public class FileHelperTest {
	@Test
	public void windowPathTest() throws URISyntaxException, IOException {
		String routesPath = "file://D:\\900.private\\10.project\\camel-launcher/src/main/resources/conf/route/*.xml";
	System.out.println(System.getProperties());	
		routesPath = "file:/D:/900.private/10.project/camel-launcher/src/test/resources/conf/route/*.xml";
		
		System.out.println(FileHelper.getOriginPath(routesPath));
		
		Path path = Paths.get(new URI(FileHelper.getOriginPath(routesPath)));
		String filename = FileHelper.getFilename(routesPath);
		
		Files.newDirectoryStream(path, filename).forEach(x->{
			System.out.println(x.toString());
		});
	}
	
	@Test
	public void fileSeperatorTest() throws URISyntaxException {
		String path = "D:\\aaa\\aaa.txt";
		
		Assert.isTrue("D:/aaa/aaa.txt".contentEquals(FileHelper.convertSeparator(path)));
		
		Paths.get(new File(FileHelper.getOriginPath(path)).toURI());
	}
	
	@Test
	public void getOriginPath() {
		
		String path = "D:/900.private/10.project/camel-launcher/src/main/resources/conf/route/*.xml";
		String originPath = FileHelper.getOriginPath(path);
		
		Assert.isTrue("D:/900.private/10.project/camel-launcher/src/main/resources/conf/route/".contentEquals(originPath));
		
		
		Path p = new File(originPath).toPath();
		Assert.isTrue("D:\\900.private\\10.project\\camel-launcher\\src\\main\\resources\\conf\\route".contentEquals(p.toString()));
		
		
	}
}
