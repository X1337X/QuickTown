package x1337x.matter123.quicktown.townmaker.exceptions;

public class PluginMissingException extends Exception {
	private static final long serialVersionUID = 5721389371901775894L;
	  private final Throwable cause;
	  private final String message;
      private final String pluginname;
	 

	  public PluginMissingException(Throwable throwable, String message,String pluginname)
	  {
	    this.cause = throwable;
	    this.message = pluginname + message;
	    this.pluginname = pluginname;
	  }

	  public String getPluginName(){
		  return this.pluginname;
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
