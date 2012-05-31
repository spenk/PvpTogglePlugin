	import java.util.logging.Logger;
public class PvpTogglePlugin extends Plugin{
	  String name = "PvpTogglePlugin";
	  String version = "1.2";
	  String author = "spenk";
	  static Logger log = Logger.getLogger("Minecraft");

	  public void initialize()
	  {
		  PvpTogglePluginListener listener = new PvpTogglePluginListener();
	    log.info(this.name + " version " + this.version + " by " + this.author + " is initialized!");
	    etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
	    etc.getLoader().addListener(PluginLoader.Hook.DAMAGE, listener, this, PluginListener.Priority.MEDIUM);
	    etc.getLoader().addListener(PluginLoader.Hook.ATTACK, listener, this, PluginListener.Priority.MEDIUM);
	    listener.f1.mkdir();
	    listener.load();
	    
	  }
	  public void enable() {
	    log.info(this.name + " version " + this.version + " by " + this.author + " is enabled!");
	  }

	  public void disable() {
	    log.info(this.name + " version " + this.version + " is disabled!");
	  }
}