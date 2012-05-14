import java.io.File;
import java.util.ArrayList;


public class PvpTogglePluginListener extends PluginListener{
	World norm = etc.getServer().getWorld(0);
	World ends = etc.getServer().getWorld(1);
	World nethers = etc.getServer().getWorld(-1);
	File f1 = new File("plugins/config");
	PropertiesFile f = new PropertiesFile("plugins/config/pvptoggle.properties");
	boolean usepvpinnether;
	boolean usepvpinend;
	boolean usepvpinworld;
	public void load(){
		usepvpinnether = f.getBoolean("Use-Pvplist-In-Nether", false);
		usepvpinend = f.getBoolean("Use-Pvplist-In-End", false);
		usepvpinworld = f.getBoolean("Use-Pvplist-In-World", true);
	}
	ArrayList<String> pvp = new ArrayList<String>();
	
	
	public boolean onCommand(Player player,java.lang.String[] split){
		if (split[0].equalsIgnoreCase("/pvp") && player.canUseCommand("/pvptoggle")){
			if (split.length <2 || split.length >2){
				player.notify("The correct usage is '/pvp <on|off>'");
				return true;
			}
			
			
			if (split[1].equalsIgnoreCase("off")){
			if (pvp.contains(player.getName())){
			pvp.remove(player.getName());
			player.sendMessage("§2Pvp Turned off.");
			return true;
				}else{
					player.notify("Your pvp is already turned off!");
					return true;
				}
			}
			
			
			if (split[1].equalsIgnoreCase("on")){
				if (!pvp.contains(player.getName())){
					pvp.add(player.getName());
					player.sendMessage("§2Pvp turned on.");
					return true;
					}else{
						player.notify("Your pvp is already turned on!");
						return true;
				}
			}
		}
		
		if (split[0].equalsIgnoreCase("/pvplist")&&player.canUseCommand("/pvptoggle")){
			if (split.length <1 || split.length >1){
				player.notify("The correct usage is '/pvplist'");
				return true;
			}else{
				player.sendMessage("§3"+pvp);
				return true;
			}
		}
		
		if (split[0].equalsIgnoreCase("/pvptoggle")&&player.canUseCommand("/pvpadmin")){
			if (split.length <3 || split.length >3){
				player.notify("The correct usage is '/pvptoggle <name|all> <on|off>'");
				return true;
			}
			if (split[1].equalsIgnoreCase("all")){
			if (split[2].equalsIgnoreCase("on")){
				for (Player p : etc.getServer().getPlayerList()) {
					if (p != player){
					if (!pvp.contains(p.getName())){
					pvp.add(p.getName());
					}
					}
				}
					if (!pvp.contains(player.getName())){
					pvp.add(player.getName());
					}
					player.sendMessage("§2Evryone is added to the pvp list!");
					etc.getServer().messageAll("§4"+player.getName()+"§2 Has added evryone to the pvp list!");
					return true;
			}
			if (split[2].equalsIgnoreCase("off")){
				etc.getServer().messageAll("§4"+player.getName()+"§2 Has cleared the pvp list!");
				pvp.clear();
				player.sendMessage("§2All pvp is toggeled off!");
				return true;
			}
			}else{
			Player p2 = etc.getServer().matchPlayer(split[1]);
			if (p2 == null){player.notify("Player Not Found!");return true;}
			if (split[2].equalsIgnoreCase("on")){
				if (pvp.contains(p2.getName())){
					player.notify("The pvp of this player is already on!");
					return true;
				}else{
					pvp.add(p2.getName());
					player.sendMessage("§2Player pvp turned on.");
					p2.sendMessage("§2Pvp turned on by §4"+player.getName());
					return true;
				}
			}
			if (split[2].equalsIgnoreCase("off")){
				if (!pvp.contains(p2.getName())){
				player.notify("The pvp of this player is already off!");
				return true;
				}else{
				pvp.remove(p2.getName());
				player.sendMessage("§2Player pvp turned off.");
				p2.sendMessage("§2Pvp turned off by §4"+player.getName());
				return true;
				}
				}
			}
		}
		return false;
	}
	
	public boolean onAttack(LivingEntity attacker,LivingEntity defender,Integer amount){
	      if ((defender.isPlayer()) && (attacker.isPlayer())) {
			Player d = defender.getPlayer();
			Player a = attacker.getPlayer();
			String defname = d.getName();
			String attname = a.getName();
			
			if (d.getWorld() == norm){
				if (usepvpinworld == true){
					if(!pvp.contains(defname)||(!pvp.contains(attname))){
				return true;
					}
					}
				return false;
		}else if (d.getWorld() == ends){
				if (usepvpinend == true){
					if(!pvp.contains(defname)||(!pvp.contains(attname))){
					return true;
					}
					}
				return false;
		}else if (d.getWorld() == nethers){
				if (usepvpinnether == true){
					if(!pvp.contains(defname)||(!pvp.contains(attname))){
					return true;
					}
				}
				return false;
		}
			}
		return false;
	}
	
	
}
