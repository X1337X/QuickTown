package x1337x.matter123.quicktown.townmaker.exceptions;

import org.bukkit.entity.Player;

public class NoPermissionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -766224038540652000L;

	 private final Throwable cause;
	 private final String message;
	 private final String permission;
     private final Player player;
	  public NoPermissionException(Throwable throwable, String message,Player p,String permission)
	  {
	    this.cause = throwable;
	    this.message = message;
	    this.permission = permission;
	    this.player = p;
	  }

	

	  public Throwable getCause()
	  {
	    return this.cause;
	  }

	  public String getMessage()
	  {
	    return this.message;
	  }

	  public String getPermission() {
		return permission;
	  }
	  public Player getPlayer(){
		  return player;
	  }
}
