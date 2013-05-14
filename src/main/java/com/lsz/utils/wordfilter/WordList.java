package com.lsz.utils.wordfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WordList {
	public static List<String> getWords() throws IOException{
		List<String> list = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(WordList.class.getResourceAsStream("mgc.dic")));
		String tmp = "";
		while(( tmp = br.readLine())  != null)
		{
				if(tmp.trim().equals(""))continue;
				list.add(tmp);
		}
		
		return list;
	}
	
}
