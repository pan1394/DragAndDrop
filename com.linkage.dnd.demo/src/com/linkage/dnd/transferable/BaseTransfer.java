package com.linkage.dnd.transferable;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * ��ק�������transfer������
 * ˵����ByteArrayTransfer����Ϊ��Դorg.eclipse.swt.dnd.ByteArrayTransfer
 */
public abstract class BaseTransfer extends ByteArrayTransfer{
 
	@Override
	public TransferData[] getSupportedTypes() {
		// TODO Auto-generated method stub
		return super.getSupportedTypes();
	}
 
	@Override
	public boolean isSupportedType(TransferData transferData) {
		// TODO Auto-generated method stub
		return super.isSupportedType(transferData);
	}
 
	@Override
	protected int[] getTypeIds() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	protected String[] getTypeNames() {
		// TODO Auto-generated method stub
		return null;
	}
 
	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		// TODO Auto-generated method stub
		super.javaToNative(object, transferData);
	}
 
	@Override
	protected Object nativeToJava(TransferData transferData) {
		// TODO Auto-generated method stub
		return super.nativeToJava(transferData);
	}	
}