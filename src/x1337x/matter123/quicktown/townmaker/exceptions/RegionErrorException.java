package x1337x.matter123.quicktown.townmaker.exceptions;

import org.bukkit.entity.Player;

public class RegionErrorException extends Exception {
	private static final long serialVersionUID = 5721389371901775894L;
	  private final Throwable cause;
	  private final String message;
	 

	  public RegionErrorException(Throwable throwable, String message,Player p)
	  {
	    this.cause = throwable;
	    this.message = message;
	    p.sendMessage(message);
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
