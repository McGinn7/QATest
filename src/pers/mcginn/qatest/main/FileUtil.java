package pers.mcginn.qatest.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readFileByLine(String filename) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		String line = null;
		try {
			List<String> result = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				result.add(line);
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readFileByCharacter(String filename) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fileReader);
		try {
			StringBuffer sb = new StringBuffer();
			int read;
			while ((read = br.read()) != -1) {
				char ch = (char) read;
				sb.append(ch);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
