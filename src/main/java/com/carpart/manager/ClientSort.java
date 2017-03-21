package com.carpart.manager;

import com.carpart.model.Client;

import java.util.Comparator;

public class ClientSort implements Comparator<Client> {

	public int compare(Client prev, Client now) {
		return (int) (now.getLogindatetime().getTime()-prev.getLogindatetime().getTime());
	}

}
