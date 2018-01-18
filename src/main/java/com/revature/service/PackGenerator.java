package com.revature.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.revature.model.bean.Card;

public class PackGenerator {
	
	private List<Card> cardPool;

	public PackGenerator(String cardsFilePath, DBService dbService) throws IOException {
		super();
		List<Card> cardPool = new ArrayList<Card>();
		String line = null;
		Card card;
		ClassPathResource classPathResource = new ClassPathResource(cardsFilePath);
		InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		while((line = bufferedReader.readLine()) != null) {
			//Regular expression that breaks up line of text between name and its value
			String[] draftPair = line.split(" \\WDraft Number\\W");
			card = dbService.cardFindByName(draftPair[0]);
			cardPool.add(card);
		}
		bufferedReader.close();
		inputStreamReader.close();
		this.cardPool = cardPool;
	}
	
	public List<List<Card>> generatePacks(int players){
		List<List<Card>> packs = new ArrayList<List<Card>>();
		List<Card> copyOfCardPool = new ArrayList<Card>(cardPool);
		int numOfPacks = players * 3;
		for(int i = 0; i < numOfPacks; i++) {
			packs.add(generatePack(15));
		}
		cardPool = copyOfCardPool;
		return packs;
	}

	private List<Card> generatePack(int numOfCards) {
		List<Card> pack = new ArrayList<Card>(15);
		Random random = new Random();
		int max = cardPool.size();
		int min = 0;
		int index = 0;
		for(int i = 0; i < numOfCards; i++) {
			index = random.nextInt(max - min) + min;
			pack.add(cardPool.get(index));
			cardPool.remove(index);
			max = cardPool.size();
		}
		return pack;
	}
	
	

}
