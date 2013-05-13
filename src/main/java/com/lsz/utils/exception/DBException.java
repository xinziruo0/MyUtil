package com.lsz.utils.exception;


public class DBException extends RuntimeException {
	/**
	 * Logger for this class
	 */
	
	public DBException(Throwable throwable) {
		    super(throwable);
		  }

		  public DBException(String msg, Throwable root) {
		    super(msg, root);
		  }

		  public DBException(String msg) {
		    super(msg);
		  }

}
