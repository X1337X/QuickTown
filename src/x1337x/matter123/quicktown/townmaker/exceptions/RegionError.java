package x1337x.matter123.quicktown.townmaker.exceptions;

public class RegionError extends Exception {
	private static final long serialVersionUID = 5721389371901775894L;
	  private final Throwable cause;
	  private final String message;
	 

	  public RegionError(Throwable throwable, String message)
	  {
	    this.cause = throwable;
	    this.message = message;
	  }

	

	  public Throwable getCause()
	  {
	    return this.cause;
	  }

	  public String getMessage()
	  {
	    return this.message;
	  }
}
