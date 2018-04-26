package com.yunji.titan.test.utils.ftp;

import java.io.File;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;
import com.yunji.titan.utils.ftp.FtpConnManager;
import com.yunji.titan.utils.ftp.FtpUtils;

/**
 * FTP测试类
 * 
 * @author liuliang
 */
public class FtpTest {
	private static FtpConnManager ftpConnManager;

	public @BeforeClass static void testConnFTP() {
		ftpConnManager = new FtpConnManager();
		ftpConnManager.setHostname("172.16.16.105");
		ftpConnManager.setUserName("haoyx");
		ftpConnManager.setPassWord("123456");
		ftpConnManager.setEncoding("UTF-8");
		ftpConnManager.setBufferSize(1024);
		ftpConnManager.setConnectTimeout(2000);
		ftpConnManager.setSoTimeout(30000);
	}

	public @Test void testUpload() {
		FtpUtils ftpUtils = new FtpUtils(ftpConnManager);
		ftpUtils.uploadFile(new File("d:/titan-monitor.log"));
	}

	public @Test void testDelete() {
		FtpUtils ftpUtils = new FtpUtils(ftpConnManager);
		ftpUtils.deleteFile(new File("test.txt"));
	}

	public @Test void testDownLoad() {
		FtpUtils ftpUtils = new FtpUtils(ftpConnManager);
		int size = 10;
		for (int i = 0; i < size; i++) {
			ftpUtils.downloadFile(new File("test.txt"));
		}
	}
}
