package x1337x.matter123.quicktown.townmaker.exceptions;

import org.bukkit.entity.Player;

public class CommandException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6996209723992657495L;

	
	  private final Throwable cause;
	  private final String message;
	 

	  public CommandException(Throwable throwable, String message,String command,String pre,Player player)
	  {
	    this.cause = throwable;
	    this.message = pre + command + message;
	    player.sendMessage(message);
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
