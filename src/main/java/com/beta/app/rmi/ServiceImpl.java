package com.beta.app.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl  extends UnicastRemoteObject    implements IService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7186363500840749751L;
	private String name;
	protected ServiceImpl(String name) throws RemoteException {
		super();
		this.name  = name;
	}

	@Override
	public String service(String content) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
